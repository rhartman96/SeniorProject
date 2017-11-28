import sys
sys.path.insert(0, "../")

import util
""" count the number of gets, sets, adds, and removes in a trace file """


# THIS PATH NEEDS TO BE AN INPUT
#trace_file_path = "/Users/calvinwinget/SeniorProject/programs/selection_sort_calvin/References/ryan_hartman/submission/run1.trace"



def get_command_counts(trace_file_path, verbose):
    """ returns a list of the counts of gets, sets, adds, and removes respectively """
    # read lines of file into string array data
    with open(trace_file_path, "r") as read_file:
        data = read_file.readlines()

    get_count = 0
    set_count = 0

    add_count = 0
    remove_count = 0

    max_lines = 500
    line_count = 0

    for line in data:
        # trace files should only have 1 command per line
        # if you look at the trace files add is not separated by a space
        # if we change that we could break the string into an array and only
        # check the part that matters eliminating the possibility of a match
        # in the file or method name

        if line_count >= max_lines:
            break
        else:
            line_count += 1

        # split the string up so we know to look at the right place
        # this way the file path can't cause it to find a command
        split_string = line.split()

        if len(split_string) > 1: # some of the trace files have inconsistencies that need this check
        	line = split_string[1]

        if "get" in line:
            get_count += 1
        elif "set" in line:
            set_count += 1
        elif "add" in line:
            add_count += 1
        elif "remove" in line:
            remove_count += 1
        else:
            if verbose:
                print(line + "NOTHING FOUND")

    if verbose:
        print("Get count: " + str(get_count))
        print("Set count: " + str(set_count))
        print("Add count: " + str(add_count))
        print("Remove count: " + str(remove_count))
        
    return [get_count, set_count, add_count, remove_count]


def get_distance(tfp_a, tfp_b, main_config = dict()):
    """ get the cosine distance between the two trace files counts """

    verbose = False
    if "verbose" in main_config:
        verbose = util.str2bool(main_config["verbose"])

    vector_a = get_command_counts(tfp_a, verbose)
    vector_b = get_command_counts(tfp_b, verbose)

    return util.cosin_dist(vector_a, vector_b)





def main():

    tfp = "/Users/calvinwinget/SeniorProject/programs/selection_sort_calvin/References/gluck_jeremy/run1.trace"
    ryan = "/Users/calvinwinget/SeniorProject/programs/selection_sort_calvin/References/ryan_hartman/run1.trace"

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