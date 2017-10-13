import os
import sys
sys.path.insert(0, "../")

import util

def fileLineCount(filename, verbose=False):
    (shotname,extension) = os.path.splitext(filename)
    if extension == '.java' : # file type 
        file = open(filename,'r')
        allLines = file.readlines()
        file.close()

        lineCount    = 0
        commentCount = 0
        blankCount   = 0
        codeCount    = 0
        for eachLine in allLines:
            if (eachLine.find('//')  > 0):  #LINECOMMENT
                commentCount += 1
            else:
                if eachLine.strip() == "":
                    blankCount += 1
                else :
                    codeCount += 1
            lineCount += 1
        
        if(verbose):
	        print(filename)
	        print('  Total      :',lineCount)
	        print('  Comment    :',commentCount)
	        print('  Blank      :',blankCount)
	        print('  Source Code:',codeCount)

        return codeCount

    return -1

def getDistance(javaFileA, javaFileB, mainConfig):
    verbose = False
    if(util.str2bool(mainConfig['verbose'])):
        verbose = True

    lineCountA = fileLineCount(javaFileA, verbose)
    lineCountB = fileLineCount(javaFileB, verbose)
    distance = abs(lineCountA - lineCountB)

    if(distance < 0 and verbose):
        print("There was a problem calculating line distance between " + str(javaFileA) + " and " + str(javaFileB) + ". Negative one was outputted as a distance.")

    if(verbose):
        print("Distance: " + str(distance))

    return distance

def main():
	programA = "/Users/andreleone/SeniorProject/programs/selection_sort_calvin/References/gluck_jeremy/submission/Selection.java"
	programB = "/Users/andreleone/SeniorProject/programs/selection_sort_calvin/References/ryan_hartman/submission/Selection.java"

	mainConfig = dict()
	mainConfig['verbose'] = "True"

	getDistance(programA, programB, mainConfig)

if __name__ == "__main__":
	main()
