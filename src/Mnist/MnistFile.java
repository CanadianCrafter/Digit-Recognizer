package Mnist;

import java.io.*;

public abstract class MnistFile extends RandomAccessFile {
	private int numItems;
	
	public MnistFile(String name, String mode) throws IOException {
		super(name, mode);
		if(getMagicNumber() != readInt()) {
			System.out.println("Error: This MNIST file should start with " + getMagicNumber());
		}
		numItems = readInt();
	}
	
	/**
	 * Returns the unique integer number that should be found at the beginning of the MNIST file.
	 * @return the magic number of the MNIST file
	 */
	protected abstract int getMagicNumber();
	
	
	/**
	 * Gets the current entry index
	 * @return the current index
	 * @throws IOException
	 */
	public long getCurrentIndex() throws IOException{
		return (getFilePointer() - getHeaderSize()) / getItemSize() + 1;
	}
	
	/**
	 * Set the current entry index
	 * @param index is the entry index
	 * @throws IOException
	 */
	public void setCurrentIndex(long index) throws IOException {
		if(index<0 || index > numItems) return;
		seek(getHeaderSize() + (index-1)*getItemSize());
	}
	
	/**
	 * Gets the size of each item in the MNIST database in bytes
	 * @return the number of bytes for each item 
	 */
	public int getItemSize() {
		return 1; //default size for an item is 1 byte
	}
	
	/**
	 * Gets the size of the header of the MNIST database in bytes
	 * @return the number of bytes of the "header"
	 */
	public int getHeaderSize() {
		return 4+4; //the mnist file begins with two integers (4 bytes each).
	}
	
	/**
	 * Moves to the next item
	 * @throws IOException 
	 */
	public void next() throws IOException {
		if(getCurrentIndex() < numItems) {
			seek(getFilePointer()+getItemSize());
		}
	}
	
	
	/**
	 * Moves to the previous item
	 * @throws IOException 
	 */
	public void prev() throws IOException {
		if(getCurrentIndex() > 0) {
			seek(getFilePointer()-getItemSize());
		}
	}
	
	
	public int getNumItems() {
		return numItems;
	}
	
}