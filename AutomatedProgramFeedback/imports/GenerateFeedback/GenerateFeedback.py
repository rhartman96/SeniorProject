import os
import sys
sys.path.insert(0, '/root/SeniorProject/Automated Program Feedback/imports')
import Cmd
import util
import Distance
import numpy as np

def getName(filePath):
	pathSplit = filePath.split("/")
	return pathSplit[len(pathSplit) - 2]

def readResults(filePath):
	results = ""
	fullPath = filePath+ "/summary.txt"
	if(os.path.isfile(fullPath)):
		with open(fullPath) as fileObject:
			results = fileObject.read().replace('\n', '')
		return results
	return ""

def generateFeedback(javaFiles, traceFiles, pathToProjectFolder, traceDir, verbose=False, main_config = dict()):
	#generate the feedback
	#INPUT
	#javaFiles - Tuple of submitted and reference dicts
		#Each map maps the class file dir to the java file
	#traceFiles - Tuple of submitted and reference trace files
		#submittedTraceFiles - Dict mapping test case # to username to absolute trace file location on disk
		#referenceTraceFiles - Dict mapping test case # to username to absolute trace file location on disk

	#OUTPUT
	#feedback - Dict mapping username to feedback
	print("Generating Feedback", flush=True)
	submittedTraceFiles, referenceTraceFiles = traceFiles
	submittedJavaFiles, referenceJavaFiles = javaFiles

	editDistanceMapping = {}
	feedback = {}
	submittedPathDict = dict()
	referencePathDict = dict()
	for testcase in submittedTraceFiles:
		print("  Evaluating on Test Case %d"%(testcase), flush=True)
		for submittedPath in submittedTraceFiles[testcase]:
			submittedPathDict[submittedPath] = True
			if not submittedPath in editDistanceMapping:
				editDistanceMapping[submittedPath] = {}
			submittedFileName = submittedTraceFiles[testcase][submittedPath]
			submittedFullPath = os.path.join(submittedPath,submittedFileName)
			print("    Evaluating: %s"%(getName(submittedPath)), flush=True)
			for referencePath in referenceTraceFiles[testcase]:
				referencePathDict[referencePath] = True
				referenceFileName = referenceTraceFiles[testcase][referencePath]
				referenceFullPath = os.path.join(referencePath,referenceFileName)
				distanceVector = Distance.getDistance(traceDir, submittedFullPath, referenceFullPath, submittedJavaFiles[submittedPath], referenceJavaFiles[referencePath], main_config)
				if not referencePath in editDistanceMapping[submittedPath]:
					distList = list()
					distList.append(distanceVector)
					editDistanceMapping[submittedPath][referencePath] = (np.array(distanceVector), distList)
				else:
					oldDist, distList = editDistanceMapping[submittedPath][referencePath]
					distList.append(distanceVector)
					editDistanceMapping[submittedPath][referencePath] = (oldDist+np.array(distanceVector), distList)
	
	#normalize the vectors and convert to a magnitude distance
	for submittedPath in editDistanceMapping.keys():
		editDistanceMapping[submittedPath] = normalize(editDistanceMapping[submittedPath], verbose)

	#output the editDistanceMapping csv file
	submittedPaths = list(submittedPathDict.keys())
	referencePaths = list(referencePathDict.keys())
	csvStr = " "
	#add the reference program labels accross the top
	for rPath in referencePaths:
		csvStr += "," + rPath
	
	csvStr += "\n"

	#loop through the rows, printing the labels on the left side first
	for sPath in submittedPaths:
		csvStr += sPath
		for rPath in referencePaths:
			csvStr += "," + str(editDistanceMapping[sPath][rPath][0])
		csvStr += "\n"

	mappingCsvFile = open('editDistanceMappting.csv', 'wt', encoding='utf-8')
	mappingCsvFile.write(csvStr)

	for submittedPath in editDistanceMapping:
		minPath = ""
		minDistance = 1000000000
		for referencePath in editDistanceMapping[submittedPath]:
			if editDistanceMapping[submittedPath][referencePath][0] < minDistance:
				if(verbose):
					print("Distance: " + str(submittedPath) + " => " + str(referencePath) + ": " + str(editDistanceMapping[submittedPath][referencePath]))
				minDistance, distList = editDistanceMapping[submittedPath][referencePath]
				minPath = referencePath

		feedback[submittedPath] = (minPath, minDistance, distList)
	for user in feedback:
		print(getName(user) + " " + readResults(feedback[user][0]))
	print("Finished Generating Feedback")
	return feedback

def normalize(referenceMapToDistVectors, verbose):
	#find the min and max value of each component
	print()
	print()
	maxVector = None
	minVector = None
	for referencePath in referenceMapToDistVectors.keys():
		vector = referenceMapToDistVectors[referencePath][0]
		if(maxVector is None):
			maxVector = np.copy(vector)
		if(minVector is None):
			minVector = np.copy(vector)
		for i in range(len(vector)):
			maxVector[i] = max(vector[i], maxVector[i])
			minVector[i] = min(vector[i], minVector[i])
	
	if(verbose):
		print("Min Vector: " + str(minVector))
		print("Max Vector: " + str(maxVector))

	for referencePath in referenceMapToDistVectors.keys():
		#actually do the normalizing
		vector, spot2 = referenceMapToDistVectors[referencePath]
		for i in range(len(vector)):
			top = (float(vector[i]) - float(minVector[i]))
			bottom = (float(maxVector[i]) - float(minVector[i]))
			if(bottom == 0):
				vector[i] = 0
				if(verbose):
					print("Bottom of fraction was zero while normalizing! Top was: " + str(top))
				continue
			vector[i] = (top / bottom) * 100 #normalized between 0 and 1
		
		referenceMapToDistVectors[referencePath] = (vector, spot2)

	#now convert the distance vectors to a single number (magnitude), also insert the weights
	for referencePath in referenceMapToDistVectors.keys():
		vector, spot2 = referenceMapToDistVectors[referencePath]
		weights = Distance.getWeights()
		print("Vector: " + str(vector))
		print("Vector Weighted: " + str(vector*weights))
		referenceMapToDistVectors[referencePath] = (util.getMagnitude(vector*weights), spot2)

	return referenceMapToDistVectors

def main():
	#used for testing the generate feedback
	traceFiles = (1,2)
	pathToProjectFolder = "/root/SeniorProject/"
	traceDir = "Trace"
	feedback = generateFeedback(traceFiles,pathToProjectFolder, traceDir)

	print(str(feedback))

if __name__ == "__main__":
    main()