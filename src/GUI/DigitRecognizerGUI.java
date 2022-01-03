package GUI;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;

import TrainSet.TrainSet;
import fullyConnectedNetwork.Network;
import fullyConnectedNetwork.NetworkTools;
import Mnist.*;

/**
 * This class allows user to draw a digit which the program identifies.
 * @author Bryan Wang
 * Reference:https://math.hws.edu/eck/cs124/s14/lab7/lab7-files/Grid.java
 *
 */
public class DigitRecognizerGUI extends JPanel implements ActionListener, MouseMotionListener {
	private static JFrame window; 
	
	private int numRows; 
	private int numCols;
	private Color[][] gridColour; //the colour of grid[row][column]; if null, it it is transparent
	private Color lineColour; // Colour of grid lines; if null, no lines are drawn.
	
	//menubar
	private static JMenuBar mb = new JMenuBar();
	private static JMenu menu = new JMenu();
	private static JMenuItem reset;
	private static JMenuItem recognize;
	private static JMenuItem exit;
	
	private final int brushRadius = 20; //in pixels
	
	public static Network network;

	
	/**
	 * Creates a panel with a specified number of rows and columns of squares of a certain size.
	 * @param rows  The number of rows of squares.
	 * @param columns  The number of columns of squares.
	 * @param preferredSquareSize  The desired size, in pixels, for the squares. This will
	 *     be used to compute the preferred size of the panel. 
	 */
	public DigitRecognizerGUI(int rows, int columns, int preferredSquareSize) {
		menuBar();
		gridColour = new Color[rows][columns]; // Create the array that stores square colors.
		numRows = rows;
		numCols = columns;
		lineColour = Color.BLACK;
		setPreferredSize( new Dimension(preferredSquareSize*columns, 
				preferredSquareSize*rows) );
		setBackground(Color.WHITE); // Set the background color for this panel.
		addMouseMotionListener(this);     // Mouse actions will call methods in this object.
	}
	
	/**
	 * This creates a window and sets its content to be a panel of type Grid.
	 * @param args
	 */
	public static void main(String[] args) {
		//Network
		//input layer is 28x28 (size of an image) and output is 10 (10 possible digits)
		network = new Network(28*28, 70, 35, 10); 
		TrainSet set = Mnist.createTrainSet(0, 59999);
		Mnist.trainData(network, set, 10, 50, 100);
		
		
		
		
		//GUI
		window = new JFrame("Digit Recongnizer");  // Create a window and names it.
		DigitRecognizerGUI content = new DigitRecognizerGUI(28,28,20);  // 28 by 28 grid of 20px x 20px squares
		window.setContentPane(content);  // Add the Grid panel to the window.
		window.pack(); // Set the size of the window based on the panel's preferred size.
		Dimension screenSize; // A simple object containing the screen's width and height.
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		// position for top left corner of the window
		int left = (screenSize.width - window.getWidth()) / 2;
		int top = (screenSize.height - window.getHeight()) / 2;
		window.setLocation(left,top);
		window.setResizable(false);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
		
	}
	
	/**
	 * Sets up the menubar
	 */
	private void menuBar() {
		mb = new JMenuBar();
		menu = new JMenu("Menu");

		// menu items
		reset = new JMenuItem("Reset Canvas");
		recognize = new JMenuItem("Recognize Digit");
		exit = new JMenuItem("Exit");

		// add to action listener for the menu items
		reset.addActionListener(this);
		recognize.addActionListener(this);
		exit.addActionListener(this);

		window.setJMenuBar(mb); // add menu bar
		mb.add(menu); // add menu to menubar
		menu.add(reset); // add items
		menu.add(recognize);
		menu.add(exit);

	}
	
	/**
	 * Finds the row numbers for grid squares within brushRadius many pixels from a y-coordinate
	 * @param pixelY a pixel y-coordinate. 
	 * @return The row numbers brushRadius away from pixelY. 
	 */
	private ArrayList<Integer> findRows(int pixelY) {
		ArrayList<Integer> rows = new ArrayList<Integer>();
		for(int i = (int) Math.round(((double)pixelY-brushRadius)/getHeight()*numRows); 
				i<= Math.round(((double)pixelY+brushRadius)/getHeight()*numRows);i++) {
			rows.add(i);
		}
		return rows;
	}
	
	/**
	 * Finds the column numbers for grid squares within brushRadius many pixels from a x-coordinate
	 * @param pixelX a pixel x-coordinate. 
	 * @return The column numbers corresponding to pixelY. 
	 */
	private ArrayList<Integer> findColumns(int pixelX) {
		ArrayList<Integer> cols = new ArrayList<Integer>();
		for(int i = (int) Math.round(((double)pixelX-brushRadius)/getHeight()*numCols); 
				i<= Math.round(((double)pixelX+brushRadius)/getHeight()*numCols);i++) {
			cols.add(i);
		}
		return cols;
	}
 
	/**
	 * Converts the grid drawn by the user to an input to the neural network.
	 * @return
	 */
	private double[] gridToNNInput() {
		double input[] = new double[numRows*numCols];
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				if (gridColour[row][col] != null) {
					input[row*numCols+col]=1;
				}
			}
		}
//		int index=0;
//		for(int r = 0; r < numRows; r++) {
//			for(int c = 0; c < numCols; c++) {
//				System.out.printf(input[index]<0.10?"   ":  "XX "); 
//				index++; 
//			}
//			System.out.println();
//		}
//		System.out.println("======================");
		return input;
	}

	/**
	 * Draws the grid of squares and grid lines (if the colour isn't null).
	 */
	protected void paintComponent(Graphics g) {
		g.setColor(getBackground());
		g.fillRect(0,0,getWidth(),getHeight());
		double cellWidth = (double)getWidth() / numCols;
		double cellHeight = (double)getHeight() / numRows;
		for (int row = 0; row < numRows; row++) {
			for (int col = 0; col < numCols; col++) {
				if (gridColour[row][col] != null) {
					int x1 = (int)(col*cellWidth);
					int y1 = (int)(row*cellHeight);
					int x2 = (int)((col+1)*cellWidth);
					int y2 = (int)((row+1)*cellHeight);
					g.setColor(gridColour[row][col]);
					g.fillRect( x1, y1, (x2-x1), (y2-y1) );
				}
			}
		}
		if (lineColour != null) {
			g.setColor(lineColour);
			for (int row = 1; row < numRows; row++) {
				int y = (int)(row*cellHeight);
				g.drawLine(0,y,getWidth(),y);
			}
			for (int col = 1; col < numRows; col++) {
				int x = (int)(col*cellWidth);
				g.drawLine(x,0,x,getHeight());
			}
		}
	}
	
	/**
	 * Turns the grid squares where the user clicks (and holds) black.
	 */
	@Override
	public void mouseDragged(MouseEvent evt) {
		// the rows and columns in the grid of squares where the user clicked.
		ArrayList<Integer> rows = findRows(evt.getY() );
		ArrayList<Integer> cols = findColumns(evt.getX());
		for(int row = 0; row <rows.size();row++) {
			for(int col = 0; col < cols.size();col++) {
				gridColour[rows.get(row)][cols.get(col)] = Color.BLACK;
			}
		}
		
		repaint(); // Causes the panel to be redrawn, by calling the paintComponent method.
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// resets the canvas
		if (event.getSource() == reset) {
			for(int row = 0; row < numRows; row++) {
				for(int col = 0; col < numCols; col++) {
					gridColour[row][col] = null;
				}
			}
			repaint();
		}
		// recognizes the digit
		else if (event.getSource() == recognize) {
			double output[] = network.feedForward(gridToNNInput());
//			int output = NetworkTools.indexOfMax(network.feedForward(gridToNNInput()));
			int result = NetworkTools.indexOfMax(output);
//			System.out.println(Arrays.toString(output));
			System.out.println(result);
		}
		// exits 
		else if (event.getSource() == exit) {
			System.exit(0);
		}
		
	}

	
} 