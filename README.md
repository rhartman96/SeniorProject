# Introduction

In computer science classes it is hard to provide meaningful feedback to students on where their program went wrong and why it is wrong. Currently, a graduate student or professor needs to manually look through all programs submitted and identify if the program outputted correctly or not. If time permits they may try and identify where the program went wrong and what caused this problem. This is a very time-consuming process that effects introductory level courses where there are a large number of students. To help alleviate this problem, we created a tool set that provides some feedback on what the potential problem might be. This will then allow all students to receive some kind of meaningful feedback for their project while lowering the time required for grading.

# Approach

To try and sovle this problem we broke the process up into three components:

1. Record Behaviors
2. Generate Feedback
3. Evaluate Feedback

Our algorithm design can be seen [here](pdfs/algorithm-design.pdf)
## Record Feedback

To record the behaviors of programs we first had to compile the Java programs to create class files. We then instrumented the class files to print the state of a list and the call made to the list. The programs are then run and a trace file is created from these print statements for each test case.

## Generate Feedback

A distance vector is computed from each submitted program to each reference program for every test case. For a single pair of submitted and reference programs, all distance vectors are summed, resulting in a single distance vector for each pair. The magnitude of each distance vector is then taken in order to obtain a single distance value for every pair of submitted and reference programs. Each submitted program is then assigned the feedback from the reference program it is closest to.

## Evaluate Feedback

Each submitted program is checked to see if the feedback assigned to it is the same as the expected feedback. The number of correct matches was then compared to the total number of submitted programs

# Figures
[Individual Feature Analysis](pdfs/BarGraphFeatures.pdf)

[Combination Feature Analysis](pdfs/BarGraphComboAnalysis_with_javalist.pdf)

[Selection Sort Results Heatmap](pdfs/selectionSort.pdf)

[Binary Search Results Heatmap](pdfs/binarySearch.pdf)

[Java List Results Heatmap](pdfs/javaList.pdf)
# Conclusions
- Using feature combinations outperforms any single individual feature
- Method distance is able to capture the majority of information gained from edit distance in a fraction of the time
- The system achieved a high probability of matching a submission with the correct feedback
- With little computational overhead students can receive personalized feedback compared to current feedback systems (ex. test cases)
