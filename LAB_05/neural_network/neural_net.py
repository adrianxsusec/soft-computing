from enum import Enum
from typing import List
from scipy.special import expit
import numpy as np

from utils import utils


def sigmoid(x):
    return expit(x)


def sigmoid_derivative(x):
    return x * (1 - x)


class BackpropVariant(Enum):
    STOCH = 1
    BATCH = 2
    FULL = 3


def create_mini_batches(dataset):
    if len(dataset) < 10:
        return [dataset]

    groups = {category: [] for category in set([utils.prediction_to_label(instance[1]) for instance in dataset])}

    for instance in dataset:
        groups[utils.prediction_to_label(instance[1])].append(instance)

    mini_batches = [[] for _ in range(10)]
    for label, instances in groups.items():
        for i in range(0, len(instances), 2):
            mini_batches[int(i / 2)].extend(instances[i:i + 2])

    return mini_batches


class NeuralNetwork:
    def __init__(self, dimensions: List[int]):
        self.dimensions = dimensions
        self.layers = self.set_weight_layers()
        self.biases = self.set_bias_layers()
        self.mse = None

    def set_weight_layers(self):
        layers = []

        for i in range(len(self.dimensions) - 1):
            layers.append(np.random.rand(self.dimensions[i + 1], self.dimensions[i]))

        return layers

    def set_bias_layers(self):
        layers = []

        for i in range(len(self.dimensions) - 1):
            layers.append(np.random.rand(self.dimensions[i + 1], 1))

        return layers

    def feed_forward(self, input_instance: np.ndarray, training=False):
        outputs = [input_instance.reshape((len(input_instance), 1))]

        for i in range(len(self.layers) - 1):
            output = np.dot(self.layers[i], outputs[i])
            output += self.biases[i]
            output = sigmoid(output)
            outputs.append(output)

        output = np.dot(self.layers[-1], outputs[-1]) + self.biases[-1]
        outputs.append(output)

        if training:
            return outputs
        else:
            return outputs[-1]

    def fit(self, variant, dataset, epochs=1000, learning_rate=0.01):
        for i in range(epochs):
            if i % (epochs / 10) == 0:
                print(f"Epoch {i}")
                print(f"Error: {self.calculate_mse(dataset)}")
            self.backprop(dataset, learning_rate, variant)

    def backprop(self, dataset, learning_rate, variant):
        if variant == BackpropVariant.STOCH:
            self.stochastic_backprop(dataset, learning_rate)
        elif variant == BackpropVariant.BATCH:
            self.mini_batch_backprop(dataset, learning_rate)
        elif variant == BackpropVariant.FULL:
            self.full_batch_backprop(dataset, learning_rate)

    def stochastic_backprop(self, dataset, learning_rate):
        for instance in dataset:
            self.full_batch_backprop([instance], learning_rate)

        # for instance in dataset:
        #     outputs = self.feed_forward(instance[0], training=True)
        #
        #     error = instance[1] - outputs[-1]
        #     delta = error
        #
        #     self.adjust_weights(delta, learning_rate, outputs)

    def mini_batch_backprop(self, dataset, learning_rate):
        mini_batches = create_mini_batches(dataset)

        for mini_batch in mini_batches:
            self.batch_backprop(mini_batch, learning_rate)

    def full_batch_backprop(self, dataset, learning_rate):
        self.batch_backprop(dataset, learning_rate)

    def batch_backprop(self, dataset, learning_rate):
        weight_adjustments = [np.zeros(layer.shape) for layer in self.layers]
        bias_adjustments = [np.zeros(layer.shape) for layer in self.biases]

        for instance in dataset:
            outputs = self.feed_forward(instance[0], training=True)

            error = instance[1] - outputs[-1]
            delta = error

            weight_gradient, bias_gradient = self.calculate_gradients(delta, outputs)

            weight_adjustments = [weight_adjustment + learning_rate * weight_gradient for
                                  weight_adjustment, weight_gradient in
                                  zip(weight_adjustments, weight_gradient)]

            bias_adjustments = [bias_adjustment + learning_rate * bias_gradient for bias_adjustment, bias_gradient in
                                zip(bias_adjustments, bias_gradient)]

        self.layers = [layer + weight_adjustment for layer, weight_adjustment in
                       zip(self.layers, weight_adjustments)]
        self.biases = [layer + bias_adjustment for layer, bias_adjustment in
                       zip(self.biases, bias_adjustments)]

    def calculate_gradients(self, delta, outputs):
        weight_gradient = [np.zeros(layer.shape) for layer in self.layers]
        bias_gradient = [np.zeros(layer.shape) for layer in self.biases]

        weight_gradient[-1] = np.dot(delta, outputs[-2].transpose())
        bias_gradient[-1] = delta

        for i in range(len(self.layers) - 2, -1, -1):
            delta = sigmoid_derivative(outputs[i + 1]) * np.dot(self.layers[i + 1].T, delta)
            weight_gradient[i] = np.dot(delta, outputs[i].transpose())
            bias_gradient[i] = delta

        return weight_gradient, bias_gradient

    def adjust_weights(self, delta, learning_rate, outputs):
        self.layers[-1] += learning_rate * np.dot(delta, outputs[-2].transpose())
        self.biases[-1] += learning_rate * delta

        for i in range(len(self.layers) - 2, -1, -1):
            delta = np.dot(self.layers[i + 1].T, delta) * sigmoid_derivative(outputs[i + 1])
            self.layers[i] += learning_rate * np.dot(delta, outputs[i].T)
            self.biases[i] += learning_rate * delta

    def calculate_mse(self, dataset):
        error = 0
        for instance in dataset:
            prediction = self.feed_forward(instance[0])
            error += np.sum((instance[1] - prediction) ** 2)

        self.mse = error / len(dataset)
        return self.mse
