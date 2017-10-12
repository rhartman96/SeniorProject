import numpy as np

def cosin_sim(v, w):
    zero_vector = np.zeros(len(v))
    if np.array_equal(v, zero_vector) and np.array_equal(v, zero_vector):
        return 1.0  # for distance 1 - 1 = 0
    elif np.array_equal(v, zero_vector) or np.array_equal(v, zero_vector):
        return -1.0  # 1 - -1 = 2
    else:
        return np.dot(v, w) / np.math.sqrt(np.dot(v, v) * np.dot(w, w))

def cosin_dist(v, w):
    return 1.0 - cosin_sim(v, w)

def str2bool(v):
    return v.lower() in ("yes", "true", "1", "t")