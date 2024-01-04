import tkinter as tk
from tkinter import ttk

from ui.tabs.gesture_recorder import GestureRecorder
from ui.tabs.nn_settings_tab import NNSettingsTab
from ui.tabs.prediction_tab import PredictionTab


class MainUI:
    def __init__(self, root, categories):
        self.root = root
        self.root.title("Gesture Recognition Neural Network")

        self.notebook = ttk.Notebook(root)
        self.notebook.pack(fill=tk.BOTH, expand=True)

        self.categories = categories
        self.recorders = {}

        tab_network_settings = ttk.Frame(self.notebook)
        self.notebook.add(tab_network_settings, text="Network Settings")

        self.neural_network_settings = NNSettingsTab(tab_network_settings, self)

        tab_prediction = ttk.Frame(self.notebook)
        self.notebook.add(tab_prediction, text="Prediction")
        self.prediction_tab = PredictionTab(tab_prediction, self)

        tab_categories = ttk.Frame(self.notebook)
        self.notebook.add(tab_categories, text="Prepare Training Data")

        categories_notebook = ttk.Notebook(tab_categories)
        categories_notebook.pack(fill=tk.BOTH, expand=True)

        for category in categories:
            tab = ttk.Frame(categories_notebook)
            categories_notebook.add(tab, text=category)

            counter_label = tk.Label(tab, text="Gestures: 0")
            counter_label.pack()

            save_button = tk.Button(tab, text="Save Gestures", command=lambda c=category: self.save_gestures(c))
            save_button.pack()

            save_all_button = tk.Button(tab, text="Save All Gestures", command=self.save_all_gestures)
            save_all_button.pack()

            recorder = GestureRecorder(tab, category, counter_label, save_button, self)
            self.recorders[category] = recorder

    def save_gestures(self, category):
        self.recorders[category].save_gestures_to_file()

    def save_all_gestures(self):
        for category in self.categories:
            self.save_gestures(category)


if __name__ == "__main__":
    categories = ["alpha", "beta", "gamma", "delta", "epsilon"]

    root = tk.Tk()
    app = MainUI(root, categories)
    root.mainloop()
