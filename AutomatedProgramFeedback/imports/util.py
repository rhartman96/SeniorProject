import numpy as np
import math

def cosin_sim(v, w):
    zero_vector = np.zeros(len(v))
    if np.array_equal(v, zero_vector) and np.array_equal(w, zero_vector):
        return 1.0  # for distance 1 - 1 = 0
    elif np.array_equal(v, zero_vector) or np.array_equal(w, zero_vector):
        return -1.0  # 1 - -1 = 2
    else:
    	value = np.dot(v, w) / np.math.sqrt(np.dot(v, v) * np.dot(w, w))
        #return np.dot(v, w) / np.math.sqrt(np.dot(v, v) * np.dot(w, w))
    	return value

def cosin_dist(v, w):
    return 1.0 - cosin_sim(v, w)

def getMagnitude(v):
    return np.linalg.norm(v)

def str2bool(v):
    return v.lower() in ("yes", "true", "1", "t")