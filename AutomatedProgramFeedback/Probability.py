import sys
import os.path
sys.path.insert(0, 'imports')

mainConfig = dict()
with open("main.config") as fileObject:
    for line in fileObject:
        lineSplit = line.split("=")
        if(len(lineSplit) > 1):
            mainConfig[lineSplit[0]] = lineSplit[1].rstrip()

with open("prob.config") as fileObject:
    for line in fileObject:
        lineSplit = line.split("=")
        if(len(lineSplit) > 1):
            mainConfig[lineSplit[0]] = lineSplit[1].rstrip()

import FileFinder
import math
#import decimal
#from decimal import Decimal

facts = {}

def get_facts():
    facts[0] = 1
    for i in range(500):
        facts[i + 1] = (i + 1) * facts[i]

get_facts()

def factorial(k):
    return facts[k]

def nCk(n,k):
    f = factorial
    return f(n) / f(k) / f(n-k)

def main():
	#run the entire program
	problemDir = mainConfig["problem_dir"]
	pathToProjectFolder = mainConfig["project_dir"]

	#grab all the files
	pathToSubmissions = pathToProjectFolder + problemDir + "Submissions"
	pathToReferences = pathToProjectFolder + problemDir + "References"
	pathToTestCases = pathToProjectFolder + problemDir + "TestCases"

	#trace dir
	traceDir = pathToProjectFolder + "Trace"
	timeout = 5 #5 second timeout for each test case run
	comparePoints = False
	if("compare_points" in mainConfig):
		comparePoints = str2bool(mainConfig["compare_points"])

	verbose = False
	if("verbose" in mainConfig):
		verbose = str2bool(mainConfig["verbose"])

	submittedFeedbackFiles = FileFinder.getAll(pathToSubmissions, ".txt")
	referenceFeedbackFiles = FileFinder.getAll(pathToReferences, ".txt")

	computeProb(mainConfig, submittedFeedbackFiles, referenceFeedbackFiles, verbose)


def computeProb(mainConfig, submittedFeedbackFiles, referenceFeedbackFiles, verbose=False):
	#sort the feedback and tally up the number of different kinds of feedback
	referenceFeedback = dict()
	submittedFeedback = dict()
	totalSubmitted = 0
	totalReference = 0
	for submittedFeedbackFP in submittedFeedbackFiles:
		fileString = getFileString(submittedFeedbackFP)
		oldAmt = 0
		if(fileString in submittedFeedback):
			oldAmt = submittedFeedback[fileString]
		submittedFeedback[fileString] = oldAmt + 1
		totalSubmitted += 1

	for referenceFeedbackFP in referenceFeedbackFiles:
		fileString = getFileString(referenceFeedbackFP)
		oldAmt = 0
		if(fileString in referenceFeedback):
			oldAmt = referenceFeedback[fileString]
		referenceFeedback[fileString] = oldAmt + 1
		totalReference += 1

	#if(verbose):
	print("The feedback distribution for reference programs is as follows:", flush=True)
	for feedback in referenceFeedback.keys():
		print("    " + str(feedback) + " => " + str(referenceFeedback[feedback]), flush=True)
	print("    Total: " + str(totalReference), flush=True)
	print("", flush=True)
	print("The feedback distribution for submitted programs is as follows:", flush=True)
	for feedback in submittedFeedback.keys():
		print("    " + str(feedback) + " => " + str(submittedFeedback[feedback]), flush=True)
	print("    Total: " + str(totalSubmitted), flush=True)
	print("", flush=True)

	#temporary, for debugging
	totalFailure = 1.0
	expectedCorrect = 0.0
	for feedback in submittedFeedback:
		probSuccess = getProbSuccess(feedback, referenceFeedback, totalReference)
		probFailure = 1.0 - probSuccess
		expectedCorrect += probSuccess * float(submittedFeedback[feedback])
		#print("Feedback '" + str(feedback) + "'")
		#print("    Prob Success: " + str(probSuccess))
		#print("    Prob Failure: " + str(probFailure))
		totalFailure *= math.pow(probFailure, submittedFeedback[feedback])
	print("Amount Expected Correct: " + str(expectedCorrect))

	#now get the cumulative distribution function
	cdf = getCDF(referenceFeedback, submittedFeedback, totalReference, totalSubmitted, verbose)
	print("CDF: ", flush=True)
	for amtCorrect in range(len(cdf)):
		print("    " + str(amtCorrect) + " Correct => " + str((cdf[amtCorrect]*100)) + "%")
	print("", flush=True)

def getCDF(referenceFeedback, submittedFeedback, totalReference, totalSubmitted, verbose=False):
	#for every x (# correct), return the probability of >= x correct
	#the prob(y >= x) is equal to the sum of the probabilities from x to xMax (100% correct)
	pdf = getPDF(referenceFeedback, submittedFeedback, totalReference, totalSubmitted, verbose)
	cdf = list()
	for thing in pdf:
		cdf.append(0.0)
	cumulativeProb = 0.0
	for i in range(len(pdf)):
		index = len(pdf) - (i+1)
		cumulativeProb += pdf[index]
		cdf[index] = cumulativeProb
	return cdf

def getPDF(referenceFeedback, submittedFeedback, totalReference, totalSubmitted, verbose=False):
	pdf = list()
	for i in range(totalSubmitted+1):
		pdf.append(0)
	getProbs(referenceFeedback, submittedFeedback, totalReference, totalSubmitted, pdf, 0, 1.0)

	if(verbose):
		print("PDF: ", flush=True)
		for amtCorrect in range(len(pdf)):
			print("    " + str(amtCorrect) + " Correct => " + str((pdf[amtCorrect]*100.0)) + "%")
		print("", flush=True)

	return pdf

def getProbs(referenceFeedback, submittedFeedback, totalReference, totalSubmitted, pdf, numberCorrectSoFar, probSoFar, case=""):
	#print("Number Correct So Far: " + str(numberCorrectSoFar), flush=True)
	foundOne = False
	for feedback in submittedFeedback:
		numberTotal = submittedFeedback[feedback]
		if(numberTotal > 0):
			foundOne = True
		else:
			continue
		probSuccess = getProbSuccess(feedback, referenceFeedback, totalReference)
		probFailure = 1.0 - probSuccess
		submittedFeedback[feedback] = 0
		for x in range(numberTotal+1):
			#simulate x successes and y failures, such that (x+y) = submittedFeedback[feedback]
			y = numberTotal - x
			pxy = math.pow(probSuccess, x) * math.pow(probFailure, y) * nCk(numberTotal, x) #probabiliy of x successes and y failures happening
			caseStr = case+", "+str(x)+"-"+str(feedback)+" and "+str(y)+"-NOT "+str(feedback)
			getProbs(referenceFeedback, submittedFeedback, totalReference, totalSubmitted, pdf, numberCorrectSoFar+x, probSoFar*pxy, caseStr)
		submittedFeedback[feedback] = numberTotal
		break

	if(not foundOne):
		#base case, we must be done, insert everything into the pdf
		#print("Case: " + str(case), flush=True)
		#if(numberCorrectSoFar == 80):
			#print("80 correct: " + str((probSoFar*10000000.0)), flush=True)
		pdf[numberCorrectSoFar] = pdf[numberCorrectSoFar] + probSoFar

def getProbSuccess(feedback, referenceFeedback, totalReference):
	if(not (feedback in referenceFeedback)):
		#this is impossible to have success
		return 0.0
	returnProb = (float(referenceFeedback[feedback]) / float(totalReference))
	return returnProb

def getFileString(filePath):
	fileString = ""
	with open(filePath) as fileObject:
		fileString = fileObject.read()

	return fileString

def str2bool(v):
    return v.lower() in ("yes", "true", "1", "t")

if __name__ == "__main__":
    main()