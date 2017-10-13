import os.path

import Compile
import Instrument
import RunFiles


def recordBehaviors(submitted, reference, testCases, traceDir, timeout, verbose=False, mainConfig=dict()):
	#record behaviors
	print("Recording Behaviors", flush=True)
	#INPUT
	#submitted - list of .java absolute file paths
	#reference - list of .java absolute file paths
	#testCases - list of absolute file paths

	#OUTPUT
	#traceFiles - Tuple of submitted and reference trace files
	#submittedTraceFiles - Dict mapping test case # to username path to absolute trace file location on disk
	#referenceTraceFiles - Dict mapping test case # to username path to absolute trace file location on disk

	print("  Compiling .java files:", flush=True)
	print("    Compiling submitted .java files...", flush=True)
	compiledSubmittedTup, failedS = Compile.javaFiles(submitted, verbose, mainConfig) #returns list of absolute paths to .class files
	print("      " + str(len(compiledSubmittedTup[1])) + " .java files compiled. " + str(len(failedS)) + " .java files failed to compile.")
	print("    Compiling reference .java files...", flush=True)
	compiledReferenceTup, failedR = Compile.javaFiles(reference, verbose, mainConfig) #returns list of absolute paths to .class files
	print("      " + str(len(compiledReferenceTup[1])) + " .java files compiled. " + str(len(failedR)) + " .java files failed to compile.")

	print("  Instrumenting:", flush=True)
	print("    Instrumenting compiled submitted class files...", flush=True)
	Instrument.javaClassFiles(compiledSubmittedTup[1], traceDir, verbose, mainConfig) #modifys the class files
	print("    Instrumenting compiled reference class files...", flush=True)
	Instrument.javaClassFiles(compiledReferenceTup[1], traceDir, verbose, mainConfig) #modifys the class files

	print("  Executing:", flush=True)
	print("    Executing Submitted...", flush=True)
	submittedTraceFiles, submittedJavaFileMap = RunFiles.javaClassFiles(compiledSubmittedTup, testCases, timeout, verbose, mainConfig) #runs the class files
	print("    Executing Reference...", flush=True)
	referenceTraceFiles, referenceJavaFileMap = RunFiles.javaClassFiles(compiledReferenceTup, testCases, timeout, verbose, mainConfig) #runs the class files

	print("Finished recording behaviors", flush=True)
	traceFiles = (submittedTraceFiles, referenceTraceFiles)
	javaFiles = (submittedJavaFileMap, referenceJavaFileMap)
	return (javaFiles, traceFiles)

def str2bool(v):
    return v.lower() in ("yes", "true", "1", "t")

def main():
	#used for testing the record behaviors
	
	#grab all the files
	pathToSubmissions = "/Users/andreleone/SeniorProject/Trace/fall2016_selection_sort/Submissions"
	pathToReferences = "/Users/andreleone/SeniorProject/Trace/fall2016_selection_sort/References"
	pathToTestCases = "/Users/andreleone/SeniorProject/Trace/fall2016_selection_sort/TestCases"

	submitted = FileFinder.getAll(pathToSubmissions, ".java")
	reference = FileFinder.getAll(pathToReferences, ".java")
	testCases = FileFinder.getAll(pathToTestCases, ".txt")

	javaFiles, traceFiles = recordBehaviors(submitted, reference, testCases)

	print(str(traceFiles))

if __name__ == "__main__":
    main()