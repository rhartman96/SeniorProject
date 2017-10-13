import sys
import os.path

#below line only needed when running this file directly from the main below
#sys.path.insert(0, '../')

import Cmd

def javaFiles(filePaths, verbose=False, mainConfig=dict()):
	successfullyCompiled = list()
	successJavaFiles = list()
	failed = list()

	filePathsRefined = list()
	if("feature_check" in mainConfig and str2bool(mainConfig["feature_check"])):
		for filePath in filePaths:
			if(not featureCheck(filePath)):
				#this .java file does not contain the correct features
				if(verbose):
					print("File: '" + str(filePath) + "' does not contain any of the required features.")
				continue
			filePathsRefined.append(filePath)
		print("      " + str(len(filePaths) - len(filePathsRefined)) + " .java files eleminated after feature_check. " + str(len(filePathsRefined)) + " .java files still remain.")
	else:
		filePathsRefined = filePaths

	for filePath in filePathsRefined:
		#compile the file given by the file path
		if(not filePath.endswith(".java")):
			#this isn't a java file, skip it
			if(verbose):
				print("File: '" + str(filePath) + "' isn't a .java file, skipping it.")
			continue

		#this must be a java file
		#".java" is 5 characters long, remove it from the end and replace it with ".class"
		classFilePath = filePath[:-5] + str(".class")

		if("recompile" in mainConfig and not str2bool(mainConfig["recompile"])):
			#don't recompile, just use the class file if it's there
			#if(os.path.isfile(classFilePath)):
			if(verbose):
				print("Recompile set to false in the main.config file, not recompiling since found preexisting class file.")
			#the class file already exists
			successfullyCompiled.append(classFilePath)
			successJavaFiles.append(filePath)
			continue

		compileCmd = "javac " + str(filePath)
		cmdOutput, cmdErr, cmdExitStatus = Cmd.runCmd(compileCmd, verbose)
		if(cmdExitStatus == 0):
			successfullyCompiled.append(classFilePath)
			successJavaFiles.append(filePath)
		else:
			failed.append(filePath)

	return ((successJavaFiles, successfullyCompiled), failed)

def str2bool(v):
    return v.lower() in ("yes", "true", "1", "t")

def featureCheck(filePath):
	foundFeatures = False
	with open(filePath) as fileObject:
		for line in fileObject:
			if("List" in line):
				foundFeatures = True

	return foundFeatures

def main():

	#test compile class
	filePaths = list()
	filePaths.append("/Users/andreleone/SeniorProject/Trace/fall2016_selection_sort/Submissions/autry_john/submission/Selection.java")

	verbose = True
	successfullyCompiled, failed = javaFiles(filePaths, verbose)

	print("Successfully Compiled: ")
	for successFilePath in successfullyCompiled[0]:
		print(str(successFilePath))

	if(len(failed) > 0):
		print("Failed: ")
		for filePath in failed:
			print(str(filePath))

#main()
