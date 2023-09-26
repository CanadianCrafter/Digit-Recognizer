# Digit-Recognizer
Digit Recognizer Using Machine Learning Built From Scratch. Here I will attempt to explain how it works.

## Neural Network
* Imagine a neural network a box that takes in an input and returns an output. 
* Inside this box is a series of layers, the first being the input layer, and the last being the output layer; the ones in-between are hidden layers. 
* Each layer also has a size which is the amount of neurons it holds (think of each neuron as a node in the network). 
* Each node is connected to every node in the layer preceding it and following it.
* Each connection is weighted and also given a bias (which in a way, can be thought of as an additional weight). This determines how data is processed.

* To code this, we need:
  * An array containing the sizes of each layer.
  * Constants for the size of the input and output of the network, as well as the size of the network.
  * A 2D array (layer, neuron) for the outputs of each node.
  * A 3D array (layer, neuron, previous neuron) for the weights (since each neuron-to-neuron link has a weight).
  * A 2D array (layer, neuron) for the bias of a specific node.
  * There is other stuff, but we'll get there eventually.

## Feed Forward Process
* We generate an array of randomized values as input and pass it to the feed-forward method. 
* We set the first row of our outputs to the input,
* Now we loop through each layer of the network (skipping the input layer), and then loop through each neuron in the layer.
* For each neuron, we calculate its output.
  * First add the neuron's bias.
  * Then sum up the products of the neuron's weight times the outputs for each neuron in the previous layer.
  * Finally, put the total sum through a sigmoid function, which transforms any number into a value between 0 and 1.

(Work in Progress)
