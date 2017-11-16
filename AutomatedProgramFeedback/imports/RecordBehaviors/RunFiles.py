import os.path
import Cmd

def javaClassFiles(classFilesTup, testCases, timeout, verbose, mainConfig=dict()):
	outputTraceFileMap = dict()
	outputJavaFileMap = dict()
	for f in range(len(classFilesTup[1])):
		classFile = classFilesTup[1][f]
		javaFile = classFilesTup[0][f]
		
		if(not classFile.endswith(".class")):
			#this can't be a class file so skip it
			continue

		#strip the extension off the end
		classFileArr = classFile.split("/")
		classFileDir = ""
		for i in range(0, len(classFileArr)-1):
			if(i == len(classFileArr)-2):
				#last iteration
				classFileDir += classFileArr[i]
			else:
				classFileDir += classFileArr[i] + "/"

		workingDir = classFileDir
		className = classFileArr[len(classFileArr)-1].replace(".class", "")
		testCaseNum = 1
		for testCaseFile in testCases:
			if(not (testCaseNum in outputTraceFileMap)):
				outputTraceFileMap[testCaseNum] = dict()

			outputFileName = "run" + str(testCaseNum) + ".trace"
			stdOutFileName = outputFileName + "_stdOut"
			outputTimeoutStatusFileName = outputFileName + "_timeout_status"
			outputStatusFileName = outputFileName + "_status"
			outputExceptionFileName = outputFileName + "_exception"

			if("retrace" in mainConfig and not str2bool(mainConfig["retrace"])):
				#don't retrace, just use the class file if it's there
				if(os.path.isfile(workingDir + "/" + outputFileName)):
					if(verbose):
						print("'retrace' set to false in the main.config file. Skipping generation of new trace file since it already exists for this test case.")
					#the trace file already exists
					outputTraceFileMap[testCaseNum][classFileDir] = outputFileName
					outputJavaFileMap[classFileDir] = javaFile
					testCaseNum += 1
					continue

			compileCmd = "cat " + testCaseFile + " | java " + className + " > " + outputFileName
			timeoutStatusDict = dict()
			cmdOutput, cmdErr, cmdExitStatus = Cmd.runCmd(compileCmd, verbose, workingDir, timeout, timeoutStatusDict)

			#take the program output and send it to a different file
			Cmd.runCmd("tail -n 1 " + outputFileName + " > " + stdOutFileName, verbose, workingDir)

			#output the command status to a file for later use
			if("timeout" in timeoutStatusDict.keys()):
				with open(workingDir + "/" + outputTimeoutStatusFileName, "w") as text_file:
					text_file.write(str(timeoutStatusDict["timeout"]))

			with open(workingDir + "/" + outputStatusFileName, "w") as text_file:
				text_file.write(str(cmdExitStatus))
			
			if(verbose and "timeout" in timeoutStatusDict.keys()):
				print("Cmd Timeout Status: " + str(timeoutStatusDict["timeout"]))

			if(verbose and bool(cmdOutput)):
				print("Cmd Output: " + str(cmdOutput))

			javaException = ""
			if(verbose and bool(cmdErr)):
				javaException = extractExceptionType(cmdErr)
				print("Cmd Error: " + str(cmdErr))
				print("Exception Type: " + str(javaException))

			with open(workingDir + "/" + outputExceptionFileName, "w") as text_file:
				text_file.write(str(javaException))

			outputTraceFileMap[testCaseNum][classFileDir] = outputFileName
			outputJavaFileMap[classFileDir] = javaFile
			testCaseNum += 1

	return outputTraceFileMap, outputJavaFileMap

def str2bool(v):
    return v.lower() in ("yes", "true", "1", "t")

def extractExceptionType(cmdErrStr):
	if(not("Exception in thread" in cmdErrStr)):
		#not an exception
		return ""

	#extract the actual exception type
	errWords = cmdErrStr.split()
	exceptionType = ""
	for wordI in range(4, len(errWords)):
		if(errWords[wordI-4] == "Exception"):
			if(errWords[wordI-3] == "in"):
				if(errWords[wordI-2] == "thread"):
					#this word is the exception type
					exceptionType = errWords[wordI].rstrip(':')
					break

	return exceptionType
