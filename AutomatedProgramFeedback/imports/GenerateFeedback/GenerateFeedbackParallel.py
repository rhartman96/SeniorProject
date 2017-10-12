import os
import sys
sys.path.insert(0, '/root/SeniorProject/Automated Program Feedback/imports')
import Cmd
import multiprocessing as mp

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

def processInput(input):
	submittedFullPath = input[0]
	referenceFullPath = input[1]
	submittedPath = input[2]
	referencePath = input[3]
	workingDir = input[4]
	verbose = input[5]
	command = "java -cp .. analyze.Difference "+submittedFullPath +" " + referenceFullPath
	output, error, status = Cmd.runCmd(command, workingDir=workingDir, verbose=verbose)
	return (submittedPath, referencePath, output)

def generateFeedback(traceFiles, pathToProjectFolder, traceDir, verbose=False):
	#generate the feedback
	#INPUT
	#traceFiles - Tuple of submitted and reference trace files
		#submittedTraceFiles - Dict mapping test case # to username to absolute trace file location on disk
		#referenceTraceFiles - Dict mapping test case # to username to absolute trace file location on disk

	#OUTPUT
	#feedback - Dict mapping username to feedback
	print("Generating Feedback", flush=True)
	submittedTraceFiles, referenceTraceFiles = traceFiles

	editDistanceMapping = {}
	feedback = {}
	submittedPathDict = dict()
	referencePathDict = dict()

	inputs = list()
	print("getting inputs...", flush=True)
	for testcase in submittedTraceFiles:
		for submittedPath in submittedTraceFiles[testcase]:
			submittedPathDict[submittedPath] = True
			if not submittedPath in editDistanceMapping:
				editDistanceMapping[submittedPath] = {}
			submittedFileName = submittedTraceFiles[testcase][submittedPath]
			submittedFullPath = os.path.join(submittedPath,submittedFileName)
			for referencePath in referenceTraceFiles[testcase]:
				referencePathDict[referencePath] = True
				referenceFileName = referenceTraceFiles[testcase][referencePath]
				referenceFullPath = os.path.join(referencePath,referenceFileName)
				#output, error, status = Cmd.runCmd("java -cp .. analyze.Difference "+submittedFullPath +" " + referenceFullPath, workingDir=traceDir + "/analyze", verbose=verbose)
				inputs.append((submittedFullPath, referenceFullPath, submittedPath, referencePath, traceDir + "/analyze", verbose))

	print("Finished getting inputs", flush=True)
	print("Calculating edit distances in parallel...", flush=True)
	pool = mp.Pool(processes=10)
	results = pool.imap(processInput, inputs)

	#print the progress
	for i in range(len(inputs)):
		result = results.next()

		submittedPath = result[0]
		referencePath = result[1]
		output = result[2]
		distanceAway = 0
		try:
			distanceAway = int(output)
		except ValueError as verr:
			#error, getting edit distance, skip
			continue

		if not referencePath in editDistanceMapping[submittedPath]:
			distList = list()
			distList.append(distanceAway)
			editDistanceMapping[submittedPath][referencePath] = (distanceAway, distList)
		else:
			oldDist, distList = editDistanceMapping[submittedPath][referencePath]
			distList.append(distanceAway)
			editDistanceMapping[submittedPath][referencePath] = (oldDist+distanceAway, distList)

		if i%10 is 0:
			print(str(i) + " of " + str(len(inputs)) + " distances calculated", flush=True)

	print("Finished Reading results", flush=True)
	
	#output the editDistanceMapping csv file
	#submittedPaths = list(submittedPathDict.keys())
	#referencePaths = list(referencePathDict.keys())
	#csvStr = " "
	#add the reference program labels accross the top
	#for rPath in referencePaths:
		#csvStr += "," + rPath
	
	#csvStr += "\n"

	#loop through the rows, printing the labels on the left side first
	#for sPath in submittedPaths:
		#csvStr += sPath
		#for rPath in referencePaths:
			#csvStr += "," + str(editDistanceMapping[sPath][rPath][0])
		#csvStr += "\n"

	#mappingCsvFile = open('editDistanceMappting.csv', 'wt', encoding='utf-8')
	#mappingCsvFile.write(csvStr)

	for submittedPath in editDistanceMapping:
		minPath = ""
		minDistance = 1000000000
		for referencePath in editDistanceMapping[submittedPath]:
			if editDistanceMapping[submittedPath][referencePath][0] < minDistance:
				if(verbose):
					print("Edit Distance: " + str(submittedPath) + " => " + str(referencePath) + ": " + str(editDistanceMapping[submittedPath][referencePath]))
				minDistance, distList = editDistanceMapping[submittedPath][referencePath]
				minPath = referencePath
		feedback[submittedPath] = (minPath, minDistance, distList)
	for user in feedback:
		print(getName(user) + " " + readResults(feedback[user][0]))
	print("Finished Generating Feedback")
	return feedback


def main():
	#used for testing the generate feedback
	traceFiles = (1,2)
	pathToProjectFolder = "/root/SeniorProject/"
	traceDir = "Trace"
	feedback = generateFeedback(traceFiles,pathToProjectFolder, traceDir)

	print(str(feedback))

if __name__ == "__main__":
    main()
