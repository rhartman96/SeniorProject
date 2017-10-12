import os
import json_storage

def evaluateFeedback(feedback, mainConfig=dict(), verbose=False):
	#how good is our feedback?

    evaluation_diagnostics = False
    if("evaluation_diagnostics" in mainConfig):
        evaluation_diagnostics = str2bool(mainConfig["evaluation_diagnostics"])

	#INPUT
	#feedback - Dict mapping username to feedback
	#correctFeedback - Dict mapping username to correctFeedback
	#verbose - When True, print out the username and feedback that is incorrect while evaluating
    # if verbose:
    #     print("feedback:")
    #     print(feedback)

    # if comparePoints:
    #     return hackerRank_scores(feedback, verbose)

    number_correct = 0
    total = 0
    evalDiagnostics = list()
    for user_name_path in feedback.keys():
        # is their feedback correct?
        #print(user_name_path)
        correct_feedback_file = os.path.join(user_name_path, "summary.txt")
        #correct_feedback_set = set()
        mapped_feedback = os.path.join(feedback[user_name_path][0], "summary.txt")
        feedback_a_set = dict()
        feedback_b_set = dict()
        with open(correct_feedback_file) as fileObject:
            #feedback_a = fileObject.read().rstrip()
            for lineA in fileObject:
                feedback_a_set[lineA.lower().rstrip()] = True
        with open(mapped_feedback) as fileObject:
            #feedback_b = fileObject.read().rstrip()
            for lineB in fileObject:
                feedback_b_set[lineB.lower().rstrip()] = True

        matched = False
        #if feedback_a.lower() == feedback_b.lower():
            #number_correct += 1
            #matched = True
        for feedbackStr in feedback_a_set.keys():
            if(feedbackStr in feedback_b_set.keys()):
                matched = True
                number_correct += 1
                break

        if(evaluation_diagnostics):
            diagTuple = (user_name_path, feedback[user_name_path][0], feedback[user_name_path][1], feedback[user_name_path][2], feedback_a_set, feedback_b_set, matched)
            evalDiagnostics.append(diagTuple)

        total += 1

    if(evaluation_diagnostics):
        json_storage.storeDataInJson(evalDiagnostics, mainConfig["evalDiagnosticsFileName"])

    if(total <= 0):
        return (0.0, number_correct, total)

    percent_correct = (float(number_correct) / float(total)) * 100
    if verbose:
        print("number correct: " + str(number_correct))
        print("total: " + str(total))
        print("percent correct: " + str(percent_correct))

    return (percent_correct, number_correct, total)


def hackerRank_scores(feedback, mainConfig=dict(), verbose = False):

    evaluation_diagnostics = False
    if("evaluation_diagnostics" in mainConfig):
        evaluation_diagnostics = str2bool(mainConfig["evaluation_diagnostics"])

    number_correct = 0
    total = 0
    evalDiagnostics = list()
    for user_name_path in feedback.keys():
        # is their feedback correct?
        #print(user_name_path)
        correct_feedback_file = os.path.join(user_name_path, "Score.txt")
        #correct_feedback_set = set()
        mapped_feedback = os.path.join(feedback[user_name_path][0], "Score.txt")
        score_a = ""
        score_b = ""
        with open(correct_feedback_file) as fileObject:
            score_a = fileObject.read().replace('\n', "")
        with open(mapped_feedback) as fileObject:
            score_b = fileObject.read().replace('\n', "")

        matched = False
        if score_a == score_b:
            number_correct += 1
            matched = True

        if(evaluation_diagnostics):
            diagTuple = (user_name_path, feedback[user_name_path][0], feedback[user_name_path][1], feedback[user_name_path][2], score_a, score_b, matched)
            evalDiagnostics.append(diagTuple)

        total += 1

    if(evaluation_diagnostics):
        json_storage.storeDataInJson(evalDiagnostics, mainConfig["evalDiagnosticsFileName"])

    if(total <= 0):
        return (0.0, number_correct, total)

    percent_correct = (float(number_correct) / float(total)) * 100
    if verbose:
        print("number correct: " + str(number_correct))
        print("total: " + str(total))
        print("percent correct: " + str(percent_correct))

    return (percent_correct, number_correct, total)


def str2bool(v):
    return v.lower() in ("yes", "true", "1", "t")

def main():
	#used for testing the evaluate feedback
	verbose = True
	evaluation = evaluateFeedback(feedback, correctFeedback, verbose)

	print(str(evaluation))

if __name__ == "__main__":
    main()
