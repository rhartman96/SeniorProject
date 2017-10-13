import os
import sys
sys.path.insert(0, "../")
import util
import Histogram
import LineDistance
import Cmd
import numpy as np

def getWeights():
    lineDistWeight = 0.10
    methodDistWeight = 0.20
    editDistWeight = 1.0
    return np.array([ lineDistWeight, methodDistWeight, editDistWeight ])

#calculate all the distance metrics, put them in a vector and compute cosine similarity
def getDistance(traceDir, traceFileA, traceFileB, javaFileA, javaFileB, mainConfig):
    verbose = False
    if(util.str2bool(mainConfig['verbose'])):
        verbose = True

    if verbose:
        print("Trace file A: " + str(traceFileA))
        print("Trace file B: " + str(traceFileB))

    vector = list()
    lineDistance = LineDistance.getDistance(javaFileA, javaFileB, mainConfig)
    if(lineDistance < 0 and verbose):
        print("There was a problem calculating line distance between " + str(javaFileA) + " and " + str(javaFileB) + ". Negative one was outputted as a distance.")
    vector.append(lineDistance) # Line Distance metric
    if(verbose):
        print("Line Distance: " + str(lineDistance))
    
    methodDistance = Histogram.get_distance(traceFileA, traceFileB, mainConfig)
    vector.append(methodDistance) # Method Distance metric
    if(verbose):
        print("Method Distance: " + str(methodDistance))

    output, error, status = Cmd.runCmd("java -cp .. analyze.Difference "+ traceFileA + " " + traceFileB, workingDir= traceDir + "/analyze", verbose=verbose)
    editDistance = int(output)
    vector.append(editDistance)
    if(verbose):
        print("Edit Distance: " + str(editDistance))

    return vector

def main():
	#TODO: Implement test for this vecotor distance output
    print("TODO: Fill this method in.")

if __name__ == "__main__":
	main()
