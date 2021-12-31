package Mnist;

import fullyConnectedNetwork.*;

import java.io.File;
import java.io.IOException;
import TrainSet.*;

public class Mnist {
	
	public static void main(String[] args) {
		//input layer is 28x28 (size of an image) and output is 10 (10 possible digits)
		Network network = new Network(28*28, 70, 35, 10); 
		TrainSet set = createTrainSet(0, 49999);
		trainData(network, set, 1000, 50, 100);
		
		TrainSet testSet = createTrainSet(50000,59999);
		testTrainSet(network, testSet);
	}
	
	
	
	public static TrainSet createTrainSet(int start, int end) {
		TrainSet set = new TrainSet(28*28, 10);
		
		try {
			
			MnistImageFile imageFile = new MnistImageFile("res/train-images.idx3-ubyte", "rw");
			MnistLabelFile labelFile = new MnistLabelFile("res/train-labels.idx1-ubyte", "rw");

//			imageFile.setCurrentIndex(start);
//			labelFile.setCurrentIndex(start);
			for(int i = start; i<= end; i++) {
				
				if(i % 100 == 0) {
					System.out.println("Prepared: " + i);
				}
				 
				double input[] = imageFile.readInput();
				double output[] = new double [10];
				output[labelFile.readLabel()] = 1d; //the correct digit has value 1. Others have 0
				set.addData(input, output);
				imageFile.next();
				labelFile.next();
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return set;
	}
	
	public static void trainData(Network net, TrainSet set, int epochs, int loops, int batchSize) {
		for(int i = 0; i < epochs; i++) {
			System.out.println("=================== Epoch " + i + " ===================");
			net.train(set, loops, batchSize);
		}
	}
	
	public static void testTrainSet(Network net, TrainSet set) {
		int numCorrect = 0;
		
		for(int i = 0; i<set.size();i++) {
			double output = NetworkTools.indexOfMax(net.feedForward(set.getInput(i)));
			double target = NetworkTools.indexOfMax(set.getOutput(i));
//			System.out.println(output + " | " + target);

			if(target == output) {
				numCorrect ++;
			}
			
			if(i%10 == 0) {
				System.out.println(i + ": " + (double)numCorrect / (double) (i+1));
			}
		}
		
		System.out.println("Testing Completed:" + numCorrect + " / " + set.size() + " -> " + (double) (100* numCorrect) / (double) set.size() + " %");	
		
	}
	
	
	
}