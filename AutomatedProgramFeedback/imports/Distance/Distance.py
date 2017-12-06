import os
import sys
sys.path.insert(0, "../")
import util
import Histogram
import LineDistance
import ExitStatusDistance
import ExceptionTypeDistance
import TimeoutDistance
import ProgramOutputDistance
import Cmd
import numpy as np

def getWeights():
    lineDistWeight = 0.0 # 2/28, 2/17 on selection sort when run by itself (0.071) (THIS SEEMS TO HURT THE RESULTS)
    methodDistWeight = 0.0 # 19/28, 6/17 on selection sort when run by itself (0.679)
    timeoutDistWeight = 0.0# 6/28, 5/17 on selection sort when run by itself (0.214) ** Not useful, reduntent with exit status
    exitStatusDistWeight = 0.0 # 8/28, 5/17 on selection sort when run by itself (0.286)
    exceptionTypeDistWeight = 0.0 # 4/28, 0/17 on selection sort when run by itself (0.143)
    programOutputDistWeight = 0.0 # 18/28, 8/17 on selection sort when run by itself (0.643)
    editDistWeight = 1.0 # 20/28, 9/17 on selection sort when run by itself (0.714)

    #USING SELECTION SORT OPTIMAL WEIGHTS
    # 15/28, 7/17 with all of these metrics weighted accordingly (except program Output distance)
    # 24/28, 10/17 with all of these metrics weighted accordingly
    # 23/28, 10/17 with program output dist and edit dist weighted accordingly
    # 24/28, 10/17 with everything except edit distance!!! Much much faster!!
    # 25/28, 10/17 with everything except line Distance and edit distance !!!
    # 21/28, 8/17 with everything except line distance and program output distance
    # 26/28, 10/17 with everything except line distance, timeout dist, and edit dist

    #USING BINARY SEARCH OPTIMAL WEIGHTS
    # 15/28, 5/17 with all of these metrics weighted accordingly (except program Output distance)
    # 23/28, 10/17 with all of these metrics weighted accordingly
    # 23/28, 10/17 with program output dist and edit dist weighted accordingly
    # 21/28, 11/17 with everything except edit distance!!! Much much faster!!
    # 24/28, 11/17 with everything except line Distance and edit distance !!!
    # 20/28, 6/17 with everything except line distance and program output distance
    # 25/28, 10/17 with everything except line distance, timeout dist, and edit dist

    return np.array([ lineDistWeight, methodDistWeight, timeoutDistWeight, exitStatusDistWeight, exceptionTypeDistWeight, programOutputDistWeight, editDistWeight ])

#calculate all the distance metrics, put them in a vector and compute cosine similarity
def getDistance(traceDir, traceFileA, traceFileB, javaFileA, javaFileB, mainConfig):
    verbose = False
    if(util.str2bool(mainConfig['verbose'])):
        verbose = True

    if verbose:
        print("Trace file A: " + str(traceFileA))
        print("Trace file B: " + str(traceFileB))

    vector = list()
    lineDistance = 0.0
    if(getWeights()[0] > 0.0):
        lineDistance = LineDistance.getDistance(javaFileA, javaFileB, mainConfig)
        if(lineDistance < 0 and verbose):
            print("There was a problem calculating line distance between " + str(javaFileA) + " and " + str(javaFileB) + ". Negative one was outputted as a distance.")
        if(verbose):
            print("Line Distance: " + str(lineDistance))
    vector.append(lineDistance) # Line Distance metric
    
    methodDistance = 0.0
    if(getWeights()[1] > 0.0):
        methodDistance = Histogram.get_distance(traceFileA, traceFileB, mainConfig)
        if(verbose):
            print("Method Distance: " + str(methodDistance))
    vector.append(methodDistance) # Method Distance metric
    
    timeoutDistance = 0.0
    if(getWeights()[2] > 0.0):
        timeoutDistance = TimeoutDistance.get_distance(traceFileA, traceFileB, mainConfig)
        if(verbose):
            print("Timeout Distance: " + str(timeoutDistance))
    vector.append(timeoutDistance) # Timeout Distance metric

    exitDistance = 0.0
    if(getWeights()[3] > 0.0):
        exitDistance = ExitStatusDistance.get_distance(traceFileA, traceFileB, mainConfig)
        if(verbose):
            print("Exit Status Distance: " + str(exitDistance))
    vector.append(exitDistance) # Exit Status Distance metric

    exceptionDistance = 0.0
    if(getWeights()[4] > 0.0):
        exceptionDistance = ExceptionTypeDistance.get_distance(traceFileA, traceFileB, mainConfig)
        if(verbose):
            print("Exception Type Distance: " + str(exceptionDistance))
    vector.append(exceptionDistance) # Exception Distance metric

    programOutputDistance = 0.0
    if(getWeights()[5] > 0.0):
        programOutputDistance = ProgramOutputDistance.get_distance(traceFileA, traceFileB, mainConfig)
        if(verbose):
            print("Program Output Distance: " + str(programOutputDistance))
    vector.append(programOutputDistance) # Program Output Distance metric
    
    editDistance = 0.0
    if(getWeights()[6] > 0.0):
        output, error, status = Cmd.runCmd("java -cp .. analyze.Difference "+ traceFileA + " " + traceFileB, workingDir= traceDir + "/analyze", verbose=verbose)
        editDistance = int(output)
        if(verbose):
            print("Edit Distance: " + str(editDistance))
    vector.append(editDistance)

    return vector

def main():
	#TODO: Implement test for this vecotor distance output
    print("TODO: Fill this method in.")

if __name__ == "__main__":
	main()
