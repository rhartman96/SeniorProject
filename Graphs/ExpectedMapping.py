
# map submissions to expected references
def getSelectionMap():
    selection_map = dict()

    selection_map["AE2"] = ["AE"]
    selection_map["AF2"] = ["AF"]
    selection_map["AG2"] = ["AG"]
    selection_map["AH2"] = ["AH"]
    selection_map["AQ2"] = ["AQ","skuhersk_max"]
    selection_map["AR2"] = ["AR"]
    selection_map["autry_john"] = ["bounds"]
    selection_map["bounds2"] = ["bounds"]
    selection_map["C"] = ["AD"]
    selection_map["cambron_chri"] = ["empty"]
    selection_map["craven_matth"] = ["ryan_hartman"]
    selection_map["D"] = ["AE"]
    selection_map["dubois_conno"] = ["insertion"]
    selection_map["E"] = ["AF"]
    selection_map["empty2"] = ["empty"]
    selection_map["F"] = ["AG"]
    selection_map["G"] = ["AH"]
    selection_map["li1"] = ["AQ", "variation", "skuhersk_max"]
    selection_map["li2"] = ["AQ", "variation", "skuhersk_max"]
    selection_map["nika"] = ["insertion"]
    selection_map["P"] = ["AQ","skuhersk_max"]
    selection_map["Q"] = ["AR"]
    selection_map["ref1"] = ["gluck_jeremy"]
    selection_map["ref2"] = ["gluck_jeremy"]
    selection_map["ryan_hartman"] = ["ryan_hartman"]
    selection_map["swap"] = ["AD"]
    selection_map["var1"] = ["variation", "skuhersk_max"]
    selection_map["var2"] = ["variation", "skuhersk_max"]
    return selection_map


# map submissions to expected references

def getBinaryMap():
    binary_map = dict()

    binary_map["Binary02"] = ["AndreCorrectSolution", "CalvinIterative", "CalvinRecursive", "JavaCorrectBSearch"]
    binary_map["Binary03"] = ["IncorrectMidpoint"]
    binary_map["Binary04"] = ["Initialization_error"]
    binary_map["Binary05"] = ["IncorrectMidpoint"]
    binary_map["Binary06"] = ["IncorrectMidpoint"]
    binary_map["Binary07"] = ["AndreCorrectSolution", "CalvinIterative", "CalvinRecursive", "JavaCorrectBSearch"]
    binary_map["Binary08"] = ["WrongComparisonTest"]
    binary_map["Binary09"] = ["AndreCorrectSolution", "CalvinIterative", "CalvinRecursive", "JavaCorrectBSearch"]
    binary_map["Binary10"] = ["AndreCorrectSolution", "CalvinIterative", "CalvinRecursive", "JavaCorrectBSearch"]
    binary_map["Binary11"] = ["Forgot"]
    binary_map["Binary12"] = ["OffByOne"]
    binary_map["Binary13"] = ["OffByOne"]
    binary_map["Binary14"] = ["updateHigh"]
    binary_map["Binary15"] = ["updateLow"]
    binary_map["Binary16"] = ["HighLowSwap"]
    binary_map["Binary17"] = ["AndreCorrectSolution", "CalvinIterative", "CalvinRecursive", "JavaCorrectBSearch"]
    binary_map["Binary18"] = ["AndreCorrectSolution", "CalvinIterative", "CalvinRecursive", "JavaCorrectBSearch"]

