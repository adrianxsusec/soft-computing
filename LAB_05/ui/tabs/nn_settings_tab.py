import tkinter as tk
import tkinter.messagebox as messagebox
import threading

from neural_network.neural_net import NeuralNetwork, BackpropVariant
from utils.utils import get_dataset


class NNSettingsTab:
    def __init__(self, parent, main_app):
        self.parent = parent
        self.frame = tk.Frame(parent)
        self.frame.pack(fill=tk.BOTH, expand=True)

        self.main_app = main_app

        inner_frame = tk.Frame(self.frame)
        inner_frame.place(relx=0.5, rely=0.5, anchor=tk.CENTER)

        label_variant = tk.Label(inner_frame, text="Training Variant:")
        label_variant.pack()

        self.var_variant = tk.StringVar()
        self.var_variant.set(BackpropVariant.STOCH.name)

        variant_options = [variant.name for variant in BackpropVariant]
        dropdown_variant = tk.OptionMenu(inner_frame, self.var_variant, *variant_options)
        dropdown_variant.pack()

        label_learning_rate = tk.Label(inner_frame, text="Learning Rate:")
        label_learning_rate.pack()

        self.entry_learning_rate = tk.Entry(inner_frame)
        self.entry_learning_rate.insert(0, "0.01")
        self.entry_learning_rate.pack()

        label_architecture = tk.Label(inner_frame, text="Architecture: \n (output layer must be 5)")
        label_architecture.pack()

        self.entry_architecture = tk.Entry(inner_frame)
        self.entry_architecture.insert(0, "20,10,5")
        self.entry_architecture.pack()

        label_epochs = tk.Label(inner_frame, text="Epochs:")
        label_epochs.pack()

        self.entry_epochs = tk.Entry(inner_frame)
        self.entry_epochs.insert(0, "1000")
        self.entry_epochs.pack()

        button_train = tk.Button(inner_frame, text="Start Training", command=self.start_training)
        button_train.pack()

        self.neural_network = None

    def start_training(self):
        variant = BackpropVariant[self.var_variant.get()]
        learning_rate = float(self.entry_learning_rate.get())
        architecture = [int(x) for x in self.entry_architecture.get().split(",")]
        epochs = int(self.entry_epochs.get())

        if architecture[-1] != 5:
            messagebox.showerror("Error", "Output layer must be 5!")
            return

        self.neural_network = NeuralNetwork(dimensions=architecture)

        self.main_app.neural_network = self.neural_network

        self.neural_network.fit(variant=variant, dataset=get_dataset(int(architecture[0] / 2)), epochs=epochs,
                                learning_rate=learning_rate)

        messagebox.showinfo("Info", f"Training completed! Final MSE: {self.neural_network.mse}")

        print("Training completed!")

    def get_neural_network(self):
        return self.neural_network
