from utils import utils
from neural_network.neural_net import NeuralNetwork, BackpropVariant

if __name__ == "__main__":
    categories = ["alpha", "beta", "gamma", "delta", "epsilon"]
    total_points = 10

    dataset = utils.get_dataset(total_points)

    neural_net = NeuralNetwork([total_points * 2, 10, len(categories)])
    neural_net.fit(BackpropVariant.STOCH, dataset)
