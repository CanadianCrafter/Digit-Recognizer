package Mnist;

import java.io.IOException;

/**
 * Processing MNIST files containing labels for the image samples.
 * @author Bryan Wang
 *
 */
public class MnistLabelFile extends MnistFile {
	
	public MnistLabelFile(String name, String mode) throws IOException {
		super(name, mode);
	}
	
	/**
	 * Reads the integer at the current position
	 * @return
	 * @throws IOException
	 */
	public int readLabel() throws IOException {
		return readUnsignedByte();
	}
	
	/**
	 * Reads 'num' labels from the current position
	 * @return
	 * @throws IOException
	 */
	public int[] readLabels(int num) throws IOException{
		int[] labels = new int [num];
		for(int i = 0; i< num; i++) {
			labels[i] = readLabel();
		}
		return labels;
	}


	@Override
	protected int getMagicNumber() {
		return 2049;
	}


}
