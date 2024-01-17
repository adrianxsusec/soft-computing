import numpy as np
import ast

labels = {
    "alpha": [1, 0, 0, 0, 0],
    "beta": [0, 1, 0, 0, 0],
    "gamma": [0, 0, 1, 0, 0],
    "delta": [0, 0, 0, 1, 0],
    "epsilon": [0, 0, 0, 0, 1]
}


def get_dataset(total_points=10):
    categories = ["alpha", "beta", "gamma", "delta", "epsilon"]
    dataset = [gesture for category in categories for gesture in
               extract_features_and_labels(category, total_points)]

    return dataset


def prediction_to_label(prediction):
    max_index = np.argmax(prediction)
    return list(labels.keys())[max_index]


def prepare_for_prediction(gesture, total_points):
    processed_gesture = preprocess_gesture(gesture, total_points)
    return processed_gesture.flatten().reshape(-1, 1)


def extract_features_and_labels(gesture_name, total_points):
    gestures = import_gestures(gesture_name)
    processed_gestures = [preprocess_gesture(gesture, total_points) for gesture in gestures]

    labeled_gestures = [(gesture.flatten().reshape(-1, 1), np.array(labels[gesture_name]).reshape(-1, 1)) for gesture in
                        processed_gestures]
    return labeled_gestures


def import_gestures(gesture_name):
    gestures = []
    filename = f"raw/{gesture_name}_gestures.txt"
    with open(filename, "r") as file:
        for line in file:
            gesture = ast.literal_eval(line)
            gestures.append(gesture)

    return gestures


def preprocess_gesture(gesture, M) -> np.ndarray:
    gesture = np.array(gesture)

    Tc = np.mean(gesture, axis=0)

    centered_gesture = gesture - Tc

    mx = np.max(np.abs(centered_gesture[:, 0]))
    my = np.max(np.abs(centered_gesture[:, 1]))
    m = max(mx, my)

    scaled_gesture = centered_gesture / m

    D = np.sum(np.linalg.norm(np.diff(scaled_gesture, axis=0), axis=1))

    intervals = np.linspace(0, D, M + 1)

    representative_points = []
    for interval in intervals:
        if len(representative_points) == M:
            break
        cumulative_distance = 0.0
        for i in range(len(scaled_gesture) - 1):
            distance = np.linalg.norm(scaled_gesture[i] - scaled_gesture[i + 1])
            cumulative_distance += distance

            if cumulative_distance >= interval:
                if abs(cumulative_distance - interval) < abs((cumulative_distance - distance) - interval):
                    point = scaled_gesture[i + 1]
                else:
                    point = scaled_gesture[i]

                representative_points.append(point)
                break

    representative_points = np.array(representative_points)
    return representative_points
