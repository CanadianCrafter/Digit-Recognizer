package TrainSet;

import java.util.ArrayList;

public class TrainSet {
	public final int INPUT_SIZE;
	public final int OUTPUT_SIZE;
	
	// double[][] index 1: 0 = input; 1 = output.
	private ArrayList<double[][]> data = new ArrayList<double[][]>();

	public TrainSet(int INPUT_SIZE, int OUTPUT_SIZE) {
		this.INPUT_SIZE = INPUT_SIZE;
		this.OUTPUT_SIZE = OUTPUT_SIZE;
	}
	
	public void addData(double[] input, double[] target) {
		if(input.length!=INPUT_SIZE||target.length!=OUTPUT_SIZE) return;
		data.add(new double[][] {input, target});
	}
	
	public int size() {
		return data.size();
	}
	
	public double[] getInput(int index) {
		if(index<0||index>=size()) return null;
		return data.get(index)[0];
	}

	public double[] getOutput(int index) {
		if(index<0||index>=size()) return null;
		return data.get(index)[1];
	}

	public int getINPUT_SIZE() {
		return INPUT_SIZE;
	}

	public int getOUTPUT_SIZE() {
		return OUTPUT_SIZE;
	}
	
	
}
