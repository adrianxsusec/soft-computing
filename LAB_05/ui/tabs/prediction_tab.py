import tkinter as tk
import tkinter.messagebox as messagebox

from utils import utils


class PredictionTab:
    def __init__(self, parent, main_app, total_points=10):
        self.parent = parent
        self.frame = tk.Frame(parent)
        self.frame.pack(fill=tk.BOTH, expand=True)

        self.main_app = main_app
        self.neural_network_settings = self.main_app.neural_network_settings

        self.points = []

        self.canvas = tk.Canvas(self.frame, bg="white", width=700, height=500)
        self.canvas.pack(expand=tk.YES, fill=tk.BOTH)

        self.canvas.bind("<ButtonPress-1>", self.on_mouse_press)
        self.canvas.bind("<B1-Motion>", self.on_mouse_drag)
        self.canvas.bind("<ButtonRelease-1>", self.on_mouse_release)

        self.label_prediction = tk.Label(self.frame, text="Start drawing to predict!")
        self.label_prediction.pack(pady=10)

        self.total_points = total_points

    def on_mouse_press(self, event):
        self.points = [(event.x, event.y)]
        self.canvas.delete("all")

    def on_mouse_drag(self, event):
        self.points.append((event.x, event.y))

        if len(self.points) > 1:
            x1, y1 = self.points[-2]
            x2, y2 = self.points[-1]
            self.canvas.create_line(x1, y1, x2, y2, width=2, fill="black")

    def on_mouse_release(self, event):
        if self.neural_network_settings.get_neural_network() is None:
            messagebox.showerror("Error", "Please train the neural network first!")
            return

        gesture = list(self.points)
        processed_gesture = utils.prepare_for_prediction(gesture, self.total_points)
        prediction = self.neural_network_settings.get_neural_network().feed_forward(processed_gesture)
        self.label_prediction.config(text=f"Prediction: {utils.prediction_to_label(prediction)}")
