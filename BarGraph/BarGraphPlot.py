import matplotlib.pyplot as plt
#from matplotlib.dates import date2num
#import datetime
import numpy as np
from matplotlib.backends.backend_pdf import PdfPages

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
