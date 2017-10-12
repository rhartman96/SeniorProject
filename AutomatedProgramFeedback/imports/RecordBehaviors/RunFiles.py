import os.path
import Cmd

def javaClassFiles(classFiles, testCases, timeout, verbose, mainConfig=dict()):
	outputTraceFileMap = dict()
	for classFile in classFiles:
		
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

			if("retrace" in mainConfig and not str2bool(mainConfig["retrace"])):
				#don't recompile, just use the class file if it's there
				if(os.path.isfile(workingDir + "/" + outputFileName)):
					if(verbose):
						print("'retrace' set to false in the main.config file. Skipping generation of new trace file since it already exists for this test case.")
					#the trace file already exists
					outputTraceFileMap[testCaseNum][classFileDir] = outputFileName
					testCaseNum += 1
					continue

			compileCmd = "cat " + testCaseFile + " | java " + className + "> " + outputFileName
			cmdOutput, cmdErr, cmdExitStatus = Cmd.runCmd(compileCmd, verbose, workingDir, timeout)
			
			if(verbose and bool(cmdOutput)):
				print("Cmd Output: " + str(cmdOutput))

			if(verbose and bool(cmdErr)):
				print("Cmd Error: " + str(cmdErr))

			outputTraceFileMap[testCaseNum][classFileDir] = outputFileName
			testCaseNum += 1

	return outputTraceFileMap

def str2bool(v):
    return v.lower() in ("yes", "true", "1", "t")

