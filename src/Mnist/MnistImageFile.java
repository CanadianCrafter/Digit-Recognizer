package Mnist;

import java.io.IOException;

/**
 * Processing MNIST files containing image samples.
 * @author Bryan Wang
 *
 */
public class MnistImageFile extends MnistFile {
	private int numRows;
	private int numCols;

	public MnistImageFile(String name, String mode) throws IOException {
		super(name, mode);
		numRows = readInt();
		numCols = readInt();
	}
	
	/**
	 * Reads the "image at the current position
	 * @return
	 * @throws IOException
	 */
	public int[][] readImage() throws IOException{
		int[][] image = new int [numRows][numCols];
		for(int r = 0; r < numRows; r++) {
			for(int c = 0; c < numCols; c++) {
				image[r][c] = readUnsignedByte(); 
			}
		}
		return image;
	}
	
	/**
	 * Reads in the "image" as an input array
	 * @return A double array with each value from 0 to 1.
	 * @throws IOException
	 */
	public double[] readInput() throws IOException {
		double[] input = new double [getItemSize()];
		for(int i = 0; i < getItemSize(); i++) {
			//Each pixels range from 0 to 255, so we divide by 255 so the data is normalized to fit in [0,1].
			//This makes things a lot easier to work with.
			input[i] = (double) readUnsignedByte() / 255d; 
		}
		return input;
	}

	public void nextImage() throws IOException {
		super.next();
	}

	public void prevImage() throws IOException {
		super.prev();
	}

	@Override
	protected int getMagicNumber() {
		return 2051;
	}

	public int getnumRows() {
		return numRows;
	}

	public void setnumRows(int numRows) {
		this.numRows = numRows;
	}

	public int getnumCols() {
		return numCols;
	}

	public void setnumCols(int numCols) {
		this.numCols = numCols;
	}
	
	@Override
	public int getItemSize() {
		return numRows * numCols;
	}

	public int getHeaderSize() {
		return super.getHeaderSize() + 4 + 4; // two more integers for numRows and numCols
	}

}