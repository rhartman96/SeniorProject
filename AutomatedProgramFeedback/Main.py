import sys
sys.path.insert(0, 'imports')
sys.path.insert(0, 'imports/RecordBehaviors')
sys.path.insert(0, 'imports/GenerateFeedback')
sys.path.insert(0, 'imports/EvaluateFeedback')

mainConfig = dict()
with open("main.config") as fileObject:
    for line in fileObject:
        lineSplit = line.split("=")
        if(len(lineSplit) > 1):
            mainConfig[lineSplit[0]] = lineSplit[1].rstrip()

import FileFinder
import RecordBehaviors as RB
import GenerateFeedback as GF
import GenerateFeedbackParallel as GFP
import EvaluateFeedback as EF


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
    if("traceDir" in mainConfig):
        traceDir = pathToProjectFolder + mainConfig["traceDir"]
    
    timeout = 5 #5 second timeout for each test case run
    comparePoints = False
    if("compare_points" in mainConfig):
        comparePoints = str2bool(mainConfig["compare_points"])

    verbose = False
    if("verbose" in mainConfig):
        verbose = str2bool(mainConfig["verbose"])


    submitted = FileFinder.getAll(pathToSubmissions, ".java")
    reference = FileFinder.getAll(pathToReferences, ".java")
    testCases = FileFinder.getAll(pathToTestCases, ".txt")

    traceFiles = RB.recordBehaviors(submitted, reference, testCases, traceDir, timeout, verbose, mainConfig)
    feedback = GF.generateFeedback(traceFiles,pathToProjectFolder, traceDir, verbose)
    evaluation = None
    if comparePoints:
        evaluation = EF.hackerRank_scores(feedback, mainConfig, verbose)
    else:
        evaluation = EF.evaluateFeedback(feedback, mainConfig, verbose)

    print("Percent Correct: " + str(round(evaluation[0], 2)) + "%")
    print("Number Correct: " + str(evaluation[1]))
    print("Number Total: " + str(evaluation[2]))

def str2bool(v):
    return v.lower() in ("yes", "true", "1", "t")

if __name__ == "__main__":
    main()
