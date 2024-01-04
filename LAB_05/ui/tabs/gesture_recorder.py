import tkinter as tk
import os


class GestureRecorder:
    def __init__(self, parent, category, counter_label, save_button, main_app):
        self.parent = parent
        self.frame = tk.Frame(parent)
        self.frame.pack(fill=tk.BOTH, expand=True)

        self.category = category
        self.counter_label = counter_label
        self.save_button = save_button
        self.main_app = main_app

        self.gestures = []
        self.points = []

        self.canvas = tk.Canvas(self.frame, bg="white", width=700, height=500)
        self.canvas.pack(expand=tk.YES, fill=tk.BOTH)

        self.canvas.bind("<ButtonPress-1>", self.on_mouse_press)
        self.canvas.bind("<B1-Motion>", self.on_mouse_drag)
        self.canvas.bind("<ButtonRelease-1>", self.on_mouse_release)

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
        gesture = list(self.points)
        print(f"Recorded Gesture for {self.category}:", gesture)

        self.gestures.append(gesture)

        self.update_counter_label()

        self.points = []

    def update_counter_label(self):
        count = len(self.gestures)
        self.counter_label.config(text=f"Gestures: {count}")

    def save_gestures_to_file(self):
        directory = "raw"
        if not os.path.exists(directory):
            os.makedirs(directory)

        filename = f"{directory}/{self.category}_gestures.txt"
        with open(filename, "w") as file:
            for gesture in self.gestures:
                file.write(", ".join(map(str, gesture)) + "\n")
        print(f"Gestures for {self.category} saved to {filename}")
