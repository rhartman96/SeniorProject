import sys
sys.path.insert(0, "../")

import util
""" Get the distance between two files based on the exit status that was returned when executing the programs """


# THIS PATH NEEDS TO BE AN INPUT
#trace_file_path = "/Users/calvinwinget/SeniorProject/programs/selection_sort_calvin/References/ryan_hartman/submission/run1.trace"



def get_exitStatus(trace_file_path, verbose):
    """ returns a list of the counts of gets, sets, adds, and removes respectively """
    # read lines of file into string array data
    data = ""
    with open(trace_file_path+"_status", "r") as read_file:
        data = read_file.readlines()
        
    return data


def get_distance(tfp_a, tfp_b, main_config = dict()):
    """ If the exit status of program A does not equal the exit status of program B, then these programs are distance 1 apart. Otherwise they are distance 0. """

    verbose = False
    if "verbose" in main_config:
        verbose = util.str2bool(main_config["verbose"])

    exitStatus_a = get_exitStatus(tfp_a, verbose)
    exitStatus_b = get_exitStatus(tfp_b, verbose)

    if(exitStatus_a == exitStatus_b):
        return 0.0

    return 1.0


def main():

    tfp = "/Users/andreleone/SeniorProject/programs/selection_sort_calvin/References/gluck_jeremy/submission/run1.trace"
    ryan = "/Users/andreleone/SeniorProject/programs/selection_sort_calvin/References/ryan_hartman/submission/run1.trace"

    try:

        mconf = dict()
        mconf["verbose"] = "True"

        dist = get_distance(tfp, ryan, mconf)
        print(str(dist))

    except FileNotFoundError as fnfe:
        print("File not found")
        print(fnfe)
    # except Exception as e:
    #     print("Exception")
    #     print(e)


if __name__ == "__main__":
    main()