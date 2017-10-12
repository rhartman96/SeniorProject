import json
from bson import json_util

def storeDataInJson(data, fileName):
    with open(fileName, 'w') as fp:
        json.dump(data, fp, sort_keys=False, indent=4, default=json_util.default)

    print("Data saved in Json file.")

def readDataFromJson(fileName):
    data = None
    with open(fileName, 'r') as fp:
        data = json.load(fp, object_hook=json_util.object_hook)

    return data
