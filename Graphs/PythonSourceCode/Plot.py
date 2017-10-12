import numpy as np
import matplotlib.pyplot as plt
import matplotlib.dates as mdates
import matplotlib.ticker
from datetime import datetime
from matplotlib.colors import LogNorm
from matplotlib.colors import LinearSegmentedColormap

from mpl_toolkits.axes_grid1.inset_locator import zoomed_inset_axes
from mpl_toolkits.axes_grid1.inset_locator import mark_inset

from matplotlib.patches import Rectangle

from matplotlib.backends.backend_pdf import PdfPages

import math

def plotLineGraph(title="My Plot Title", xLabel='X Label', yLabel='Y Label', xArr=[], yArr=[], legendArr=[], whiteSpace=0.1, useLogYAxis=False, usingDates=False, xMax=None, xMin=None, yAxisMin=None, yAxisMax=None, useLogXAxis=False, useScatter=False, colors=None, symbols=None, secondAsLine=False, saveFileLocation=None, linewidth=1, legendFontSize=14):
	#xMax = 0
	#xMin = 0
	yMax = 0
	yMin = 0
	if(not xMax and not xMin and len(xArr) > 0 and len(xArr[0]) > 0):
		xMax = xArr[0][0]
		xMin = xArr[0][0]

	if(len(yArr) > 0 and len(yArr[0]) > 0):
		yMax = yArr[0][0]
		yMin = yArr[0][0]

	if(not xMin and not xMax):
		for x in xArr:
			for num in x:
				if(num > xMax):
					xMax = num
				if(num < xMin):
					xMin = num

	for y in yArr:
		for num in y:
			if(num > yMax):
				yMax = num
			if(num < yMin):
				yMin = num

	#lineA = plt.plot(xArr[0], yArr[0], '--go', linewidth=2, label=legendArr[0])
	#lineB = plt.plot(xArr[1], yArr[1], ':r*', linewidth=2, label=legendArr[1])
	useColorMod = False
	if(not colors):
		colors = [(31, 119, 180),(174, 199, 232),(255, 127, 14),(255, 187, 120),(44, 160, 44),(152, 223, 138),(214, 39, 40),(255, 152, 150),(148, 103, 189),(197, 176, 213),(140, 86, 75),(196, 156, 148),(227, 119, 194),(247, 182, 210),(127, 127, 127),(199, 199, 199),(188, 189, 34),(219, 219, 141),(23, 190, 207),(158, 218, 229)]
		useColorMod = True

	useSymbolMod = False
	if(not symbols):
		symbols = ["", "<", "o", "1", "^", "v", ",", ".", ">", "2", "3"]
		useSymbolMod = True

	scatterColors = ["b", "r", "g", "k", "m"]
	shapes = ["--"]
	#symbols = ["", "<", "o", "1", "^", "v", ",", ".", ">", "2", "3"]
	#symbols = ["", "^", "<", "", "", "", ""]
	
	my_dpi = 100
	width = 950
	height = 670
	figure = plt.figure(figsize=(width/my_dpi, height/my_dpi), dpi=my_dpi)

	for i in range(len(xArr)):
		shape = shapes[i % len(shapes)]
		color=(0, 0, 0)
		if(useColorMod):
			color = colors[i % len(colors)]
		else:
			color = colors[i]
		scatterColor = scatterColors[i % len(scatterColors)]

		symbol = symbols[0]
		if(useSymbolMod):
			symbol = symbols[i % len(symbols)]
		else:
			symbol = symbols[i]

		if(useScatter):
			plt.scatter(xArr[i],yArr[i],c=scatterColor, s=10, label=legendArr[i], figure=figure, linewidth=linewidth)
			if(secondAsLine):
				useScatter = False
		else:
			tmpColor = "r"
			r, g, b = color
			line = plt.plot(xArr[i], yArr[i], shape+tmpColor+symbol, linewidth=linewidth, label=legendArr[i], figure=figure, color=(r/255, g/255, b/255))

	plt.legend(loc='best', framealpha=1.0, borderpad=0.75, fontsize=legendFontSize) #add "ncol=2" for a two column legend
	#plt.legend(loc='best', fancybox=True, shadow=True, borderpad=1.5) #add "ncol=2" for a two column legend
	#plt.legend(loc='top left', bbox_to_anchor=(1, 0.5), fancybox=True, shadow=True)
	#plt.legend(loc='upper center', bbox_to_anchor=(0.5, 1.05), ncol=8, framealpha=1.0, borderpad=1.0, fontsize=legendFontSize)

	#plt.title(title, fontsize=12)
	plt.xlabel(xLabel, fontsize=16)
	plt.ylabel(yLabel, fontsize=16)

	#plt.gca().xaxis.grid(True) #vertical gridlines
	plt.gca().yaxis.grid(True) #horizontal gridlines

	if(usingDates):
		plt.gca().xaxis.set_major_formatter(mdates.DateFormatter('%Y'))
		plt.gca().xaxis.set_major_locator(mdates.YearLocator())
		plt.gcf().autofmt_xdate()

	if(useLogYAxis):
		plt.yscale('log', nonposy='clip', basey=10, labelOnlyBase=False)
		plt.gca().yaxis.set_major_formatter(matplotlib.ticker.ScalarFormatter(useOffset=False))
		#plt.gca().yaxis.set_minor_locator(matplotlib.ticker.MultipleLocator(2))
		#plt.gca().yaxis.set_minor_formatter(matplotlib.ticker.ScalarFormatter(useOffset=False))

	if(useLogXAxis):
		plt.xscale('log', nonposy='clip', basey=10, labelOnlyBase=False)
		plt.gca().xaxis.set_major_formatter(matplotlib.ticker.ScalarFormatter(useOffset=False))
		#plt.gca().xaxis.set_minor_locator(matplotlib.ticker.MultipleLocator(2))
		#plt.gca().xaxis.set_minor_formatter(matplotlib.ticker.ScalarFormatter(useOffset=False))

	if(not usingDates):
		domainX = xMax - xMin
		xAxisMax = (float(domainX)*whiteSpace)+xMax
		if(xMax):
			xAxisMax = xMax

		plt.xlim([xMin, xAxisMax])
	else:
		#xAxisMax = datetime.strptime("2007-03-30", "%Y-%m-%d") #cuts off the chart at 3/30/2007
		#xAxisMax = datetime.strptime("2016-09-30", "%Y-%m-%d") #cuts off the chart at 09/30/2016
		#xMin = datetime.strptime("2007-03-30", "%Y-%m-%d") #cuts off the chart at 3/30/2007
		xAxisMax = xMax
		xMin = xMin
		plt.xlim([xMin, xAxisMax])
	
	rangeY = yMax - yMin
	if(not yAxisMin):
		yAxisMin = yMin-(float(rangeY)*whiteSpace)
	if(not yAxisMax):
		yAxisMax = (float(rangeY)*whiteSpace)+yMax

	#yAxisMin = 0.1 #sets the yMin when using log y scaling
	#yAxisMin = 1.0 #sets the yMin when using log y scaling
	plt.ylim([yAxisMin, yAxisMax])

	# We change the fontsize of minor ticks label 
	plt.tick_params(axis='both', which='major', labelsize=12)
	plt.tick_params(axis='both', which='minor', labelsize=10)


	#create the zoomed image below
	#showZoomed(plt, xArr, yArr, shape, color, symbol)

	if(saveFileLocation):
		#save the plot to a file
		pp = PdfPages(saveFileLocation)
		pp.savefig(figure)
		pp.close()
	else:
		plt.show()

def showZoomed(plt, xArr, yArr, shape, color, symbol):
	# this is an inset axes over the main axes
	a2 = plt.axes([.465, .4025, .425, .35], facecolor='w')
	#plt.plot(xArr[0], yArr[0], shape+color+symbol, linewidth=2)
	plt.scatter(xArr[0],yArr[0],shape+color+symbol)
	plt.xlim([0, 40])
	plt.ylim([-0.002, 0.005])
	plt.gca().yaxis.grid(True) #horizontal gridlines

	a1 = plt.axes([.125, .76, .01875, 0.015], facecolor='w')
	#plt.plot(xArr[0], yArr[0], shape+color+symbol, linewidth=2)
	plt.scatter(xArr[0],yArr[0],shape+color+symbol)
	plt.xlim([0, 40])
	plt.ylim([-0.002, 0.006])
	plt.xticks([])
	plt.yticks([])
	
	plt.xscale('log', nonposy='clip', basey=10, labelOnlyBase=False)
	plt.gca().xaxis.set_major_formatter(matplotlib.ticker.ScalarFormatter(useOffset=False))
	#plt.gca().xaxis.grid(False) #horizontal gridlines
	#plt.gca().yaxis.grid(False) #horizontal gridlines

	# draw a bbox of the region of the inset axes in the parent axes and
	# connecting lines between the bbox and the inset axes area
	mark_inset(a1, a2, loc1=2, loc2=3, fc="none", ec="0.5")


def plotHeatMap(title="My Plot Title", xLabel='X Label', yLabel='Y Label', zLabel='Z Label', xs=list(), ys=list(), intensity=list(), zLevels=None, yLog=True, saveFileLocation=None):
	#here's our data to plot, all normal Python lists
	#xs = [datetime.strptime("2000-06-30", "%Y-%m-%d"), datetime.strptime("2000-09-30", "%Y-%m-%d"), datetime.strptime("2001-03-30", "%Y-%m-%d")] # Time
	#ys = [0, 1, 2, 3, 4] # Degree

	#if(pltPassed):
		#plt = pltPassed
		#a1 = plt.axes([.125, .76, .01875, 0.015])

	isDates = True
	#convert dates to numbers
	for i in range(len(xs)):
		try:
			xs[i] = mdates.date2num(xs[i])
		except:
			isDates = False
			pass

	#column1 is xs[0]
	#intensity = [
	    #[250, 150, 190],
	    #[150, 200, 160],
	    #[80, 75, 77],
	    #[60, 65, 58],
	    #[20, 50, 24],
	#]

	verticalMax = max(map(max, intensity))
	verticalMin = min(map(min, intensity))


	#setup the 2D grid with Numpy
	x, y = np.meshgrid(xs, ys)

	#z=np.array(zs)
	#z=z.reshape(len(y),len(x))

	#convert intensity (list of lists) to a numpy array for plotting
	intensity = np.array(intensity)


	#plt size
	my_dpi = 100
	width = 950
	height = 670
	figure = plt.figure(figsize=(width/my_dpi, height/my_dpi), dpi=my_dpi)

	#assign locator and formatter for the xaxis ticks.
	#plt.gca().xaxis.set_major_locator(mdates.AutoDateLocator())
	#plt.gca().xaxis.set_major_formatter(mdates.DateFormatter('%Y.%m.%d'))

	if(isDates):
		plt.gca().xaxis.set_major_locator(mdates.YearLocator())
		plt.gca().xaxis.set_major_formatter(mdates.AutoDateFormatter(mdates.AutoDateLocator()))

	#plt.title(title, fontsize=13, fontweight='bold')
	plt.xlabel(xLabel, fontsize=16)
	plt.ylabel(yLabel, fontsize=16)

	#plt.xlim([0, 40])
	plt.ylim([1, 1800])
	if(yLog):
		plt.yscale('log', nonposy='clip', basey=10, labelOnlyBase=False)


	zColors = list()
	#zColors.append((0.0, 0.0, 0.8))
	#zColors.append((0.0, 0.0, 0.8))

	#zColors.append((0.0, 0.3, 0.8))
	#zColors.append((0.0, 0.0, 0.8)) # original blue background
	zColors.append((0.9, 0.9, 0.9)) # light grey background - option 1
	#zColors.append((0.8, 0.8, 1.0)) # light blue background - option 2
	#zColors.append((0.0, 0.8, 0.8))

	#zColors.append((0.5, 1.0, 0.5)) #light green - original
	#zColors.append((0.5, 1.0, 0.5)) #light green - option 2
	
	#zColors.append((1.0, 1.0, 0.0)) #yellow
	zColors.append((1.0, 0.7, 0.0)) #yellow - option 1

	zColors.append((1.0, 0.6, 0.0))
	zColors.append((1.0, 0.5, 0.0))
	zColors.append((1.0, 0.4, 0.0))
	zColors.append((1.0, 0.3, 0.0))
	zColors.append((1.0, 0.2, 0.0))
	#zColors.append((1.0, 0.1, 0.0))
	zColors.append((1.0, 0.0, 0.0))
	zColors.append((0.8, 0.0, 0.0))
	zColors.append((0.7, 0.0, 0.0))
	zColors.append((0.6, 0.0, 0.0))
	zColors.append((0.5, 0.0, 0.0))
	zColors.append((0.4, 0.0, 0.0))
	zColors.append((0.3, 0.0, 0.0))
	zColors.append((0.2, 0.0, 0.0))
	zColors.append((0.1, 0.0, 0.0))
	zColors.append((0.0, 0.0, 0.0))

	if(not zLevels):
		#axisZBase = 1.15
		axisZBase = 1.325
		zLevels = list()
		#zLevelsIntegers = list()
		numberOfLevels = len(zColors)
		zLevels.append(0)
		zLevels.append(1)
		zLevels.append(2)

		startLoop = 0
		endLoop = numberOfLevels+1
		startLoop += 5
		endLoop += 5 - len(zLevels)
		for i in range(startLoop, endLoop):
			zLevels.append(int(np.power(axisZBase, i)))
			#zLevelsIntegers.append(int(np.power(axisZBase, i)))
	
	verticalMin = -1 * verticalMax
	#now just plug the data into pcolormesh, it's that easy!
	#plt.pcolormesh(xs, ys, intensity, cmap="jet", vmin=verticalMin, vmax=verticalMax)
	pp = plt.contourf(xs, ys, intensity, cmap=None, levels=zLevels, colors=zColors)
	colorbar = plt.colorbar(pp, format="%.1f") #need a colorbar to show the intensity scale (pp, ticks=..., format=...) #format="%0.1f" or format="%d"
	colorbar.set_label(zLabel, fontsize=16)
	#colorbar.set_ticklabels(zLevelsIntegers)
	plt.xticks(rotation=45)
	
	if(saveFileLocation):
		#save the plot to a file
		pp = PdfPages(saveFileLocation)
		pp.savefig(figure)
		pp.close()
	else:
		plt.show()
