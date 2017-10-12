import os

def getAll(folderPath, extension):
    #returns all of the absolute file paths that lie in or underneath the folderPath ending in extension
    fileList = list()
    for root, dirs, files in os.walk(folderPath):
        for file in files:
            if file.endswith(extension):
                 # print(os.path.join(root, file))
                 fileList.append(os.path.join(root, file))
    return fileList