import matplotlib.pyplot as plt
#from matplotlib.dates import date2num
#import datetime
import numpy as np
from matplotlib.backends.backend_pdf import PdfPages

def plotIndividualFeatureAnalysis():
	writeGraphToFile = True
	my_dpi = 100
	width = 950
	height = 670

	xLabels = ['',
				'Line\nDistance',
				'Method\nDistance',
				'Timeout\nDistance',
				'Exit\nStatus\nDistance',
				'Exception\nType\nDistance',
				'Program\nOutput\nDistance',
				'Edit\nDistance']

	#x = date2num(x)
	x = np.array([0.0,1.0,2.0,3.0,4.0,5.0,6.0])

	selectionY = np.array([2.0, 19.0, 6.0, 8.0, 4.0, 18.0, 20.0])
	selectionY *= 100/28

	binaryY = np.array([2.0, 6.0, 5.0, 5.0, 0.0, 8.0, 9.0])
	binaryY *= 100/17

	figure, ax = plt.subplots()
	figure.set_figwidth(width/my_dpi)
	figure.set_figheight(height/my_dpi)
	figure.set_dpi(my_dpi)

	ax.bar(x-0.2, selectionY,width=0.4,color='g',align='center', label='Selection Sort')
	ax.bar(x+0.2, binaryY,width=0.4,color='b',align='center', label='Binary Search')
	ax.set_xlim(-0.4, 6.4)
	ax.set_xticklabels(xLabels)

	ax.set_title('Individual Feature Analysis')
	ax.set_ylabel('Percent Classified Correctly')
	#ax.set_xlabel('Distance Metric')
	ax.legend(loc='best', framealpha=1.0, borderpad=0.75, fontsize=14)

	#save the plot to a file
	if(writeGraphToFile):
	    pp = PdfPages("BarGraphFeatures.pdf")
	    pp.savefig(figure)
	    pp.close()
	else:
	    plt.show()

def plotCombinationAnalysis():
	writeGraphToFile = True
	my_dpi = 100
	width = 1250
	height = 670

	xLabels = [ '', #blank line to shift over one
				'A, B, C,\nD, E, F, G', #A, B, C, D, E, F, G
				'A, B, C,\nD, E, G', #A, B, C, D, E, G
				'A, B, C,\nD, E, F', #A, B, C, D, E, F
				'B, C,\nD, E, F', #B, C, D, E, F
				'B, C,\nD, E, G', #B, C, D, E, G
				'B, D, E, F', #B, D, E, F
				'F, G'] #F, G

	#x = date2num(x)
	x = np.array([0.0,1.0,2.0,3.0,4.0,5.0,6.0])

	selectionY = np.array([24.0, 15.0, 24.0, 25.0, 21.0, 26.0, 23.0])
	selectionY *= 100/28

	binaryY = np.array([10.0, 7.0, 10.0, 10.0, 8.0, 10.0, 10.0])
	binaryY *= 100/17

	selectionYBWeights = np.array([23.0, 15.0, 21.0, 24.0, 20.0, 24.0, 23.0])
	selectionYBWeights *= 100/28

	binaryYBWeights = np.array([10.0, 5.0, 11.0, 11.0, 6.0, 11.0, 10.0])
	binaryYBWeights *= 100/17

	figure, ax = plt.subplots()
	figure.set_figwidth(width/my_dpi)
	figure.set_figheight(height/my_dpi)
	figure.set_dpi(my_dpi)

	b1 = ax.bar(x-0.3, selectionY,width=0.2,color='g',align='center', label='Selection Sort (S-Weights)', hatch='/')
	b3 = ax.bar(x+0.1, binaryY,width=0.2,color='b',align='center', label='Binary Search (S-Weights)', hatch='/')
	b2 = ax.bar(x-0.1, selectionYBWeights,width=0.2,color=(0.75, 1, 0.75),align='center', label='Selection Sort (B-Weights)', hatch='')
	b4 = ax.bar(x+0.3, binaryYBWeights,width=0.2,color=(0.65, 0.85, 1.0),align='center', label='Binary Search (B-Weights)', hatch='')
	ax.set_xlim(-0.4, 6.4)
	ax.set_xticklabels(xLabels)

	ax.set_title('Combination Feature Analysis')
	ax.set_ylabel('Percent Classified Correctly')
	ax.set_xlabel('Feature Combination')
	#ax.set_xlabel('Distance Metric')


	#Shrink the sub plot so that the legend fits on the side of the plot
	box = ax.get_position()
	ax.set_position([box.x0, box.y0, box.width * 0.75, box.height * 0.85])

	#legend2 = ax.legend([b1, b2, b3, b3, b3, b3, b3], [
	#									"A. Line, Method, Timeout, Exit Status, Exception Type, Program Output, Edit", 
	#									"B. Line, Method, Timeout, Exit Status, Exception Type, Edit", 
	#									"C. Line, Method, Timeout, Exit Status, Exception Type, Program Output", 
	#									"D. Method, Timeout, Exit Status, Exception Type, Program Output", 
	#									"E. Method, Timeout, Exit Status, Exception Type, Edit", 
	#									"F. Method, Exit Status, Exception Type, Program Output", 
	#									"G. Program Output, Edit"
	#									], handlelength=0, handletextpad=0, loc=1)
	legend2 = ax.legend([b1, b2, b3, b3, b3, b3, b3], [
										"A. Line Distance", 
										"B. Method Distance", 
										"C. Timeout Distance", 
										"D. Exit Status Distance", 
										"E. Exception Type Distance", 
										"F. Program Output Distance", 
										"G. Edit Distance"
										], handlelength=0, handletextpad=0, loc='center left', bbox_to_anchor=(1, 0.3))
	for item in legend2.legendHandles:
		item.set_visible(False)
	ax.legend(loc='upper left', framealpha=1.0, borderpad=0.75, fontsize=14, ncol=1, bbox_to_anchor=(1, 1.0))
	#pyplot.legend([l[0] for l in plot_lines], parameters, loc=4)
	plt.gca().add_artist(legend2)

	#save the plot to a file
	if(writeGraphToFile):
	    pp = PdfPages("BarGraphComboAnalysis.pdf")
	    pp.savefig(figure)
	    pp.close()
	else:
	    plt.show()

plotCombinationAnalysis()
writeGraphToFile = True
my_dpi = 100
width = 950
height = 670

xLabels = ['',
			'Line\nDistance',
			'Method\nDistance',
			'Timeout\nDistance',
			'Exit\nStatus\nDistance',
			'Exception\nType\nDistance',
			'Program\nOutput\nDistance',
			'Edit\nDistance']

#x = date2num(x)
x = np.array([0.0,1.0,2.0,3.0,4.0,5.0,6.0])

selectionY = np.array([2.0, 19.0, 6.0, 8.0, 4.0, 18.0, 20.0])
selectionY *= 100/28

binaryY = np.array([2.0, 6.0, 5.0, 5.0, 0.0, 8.0, 9.0])
binaryY *= 100/17

listY = np.array([20.0,49.0,20.0,46.0,46.0,50.0,50.0])
listY *= 100/58

figure, ax = plt.subplots()
figure.set_figwidth(width/my_dpi)
figure.set_figheight(height/my_dpi)
figure.set_dpi(my_dpi)

ax.bar(x-0.2, selectionY,width=0.2,color='g',align='center', label='Selection Sort')
ax.bar(x+0, listY, width = 0.2, color='#e2811f', align='center',label='Java List')
ax.bar(x+0.2, binaryY,width=0.2,color='b',align='center', label='Binary Search')
ax.set_xlim(-0.4, 6.4)
ax.set_xticklabels(xLabels)
# ax.grid(False)
ax.set_title('Individual Feature Analysis')
ax.set_ylabel('Percent Classified Correctly')
#ax.set_xlabel('Distance Metric')
ax.legend(loc='best', framealpha=1.0, borderpad=0.75, fontsize=8)
# plt.legend()
#save the plot to a file
if(writeGraphToFile):
    pp = PdfPages("BarGraphFeatures.pdf")
    pp.savefig(figure)
    pp.close()
else:
    plt.show()
>>>>>>> Stashed changes
