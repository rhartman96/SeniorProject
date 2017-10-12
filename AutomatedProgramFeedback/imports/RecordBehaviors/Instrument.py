import os.path

import Cmd


def javaClassFiles(classFiles, traceDir, verbose, mainConfig=dict()):

	if("instrument" in mainConfig and not str2bool(mainConfig["instrument"])):
		#instrument is set to false, skip it
		if(verbose):
			print(str("Instrument is set to false in the config file, skipping instrumentation."))
		return

	for classFile in classFiles:
		#Works but puts the new class file in the current working directory (/Automated Feedback...)
		classFileArr = classFile.split("/")
		classFileDir = ""
		for i in range(0, len(classFileArr)-1):
			if(i == len(classFileArr)-2):
				#last iteration
				classFileDir += classFileArr[i]
			else:
				classFileDir += classFileArr[i] + "/"

		workingDir = classFileDir
		classFileName = classFileArr[len(classFileArr) - 1].replace(".class", "")

		instrumentDir = traceDir + "/instrument"
		javassistJarPath = traceDir + "/javassist.jar"
		classTransformationClass = "instrument.ClassTransformation"
		#java -cp ../Trace/instrument:../Trace/javassist.jar:../Trace:/Users/andreleone/SeniorProject/Trace/fall2016_selection_sort/Submissions/autry_john/submission instrument.ClassTransformation Selection
		compileCmd = "java -cp " + instrumentDir + ":" + javassistJarPath + ":" + traceDir + ": " + classTransformationClass + " " + classFileName
		cmdOutput, cmdErr, cmdExitStatus = Cmd.runCmd(compileCmd, verbose, workingDir)

	return

def str2bool(v):
    return v.lower() in ("yes", "true", "1", "t")
