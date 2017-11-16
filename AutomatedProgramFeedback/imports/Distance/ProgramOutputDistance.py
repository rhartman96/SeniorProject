import sys
sys.path.insert(0, "../")

import util
""" Get the output distance between two programs that were ran. If output doesn't match, distance is 1.0. Otherwise it is 0.0. """


# THIS PATH NEEDS TO BE AN INPUT
#trace_file_path = "/Users/calvinwinget/SeniorProject/programs/selection_sort_calvin/References/ryan_hartman/submission/run1.trace"



def get_output(trace_file_path, verbose):
    """ returns the output of this run """
    # read lines of file into string array data
    data = ""
    with open(trace_file_path+"_stdOut", "r") as read_file:
        data = read_file.readlines()
        
    return data


def get_distance(tfp_a, tfp_b, main_config = dict()):
    verbose = False
    if "verbose" in main_config:
        verbose = util.str2bool(main_config["verbose"])

    output_a = get_output(tfp_a, verbose)
    output_b = get_output(tfp_b, verbose)

    if(output_a == output_b):
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