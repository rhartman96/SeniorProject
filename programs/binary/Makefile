# delete the default suffixes for obsolete suffix rules
.SUFFIXES       :

# explicitly declare some targets as phony
.PHONY          :       default all tar html dvi ps clean veryclean help forcegnatmake FORCE
.PHONY          :       trace diff

# special Makefile variables
# $@ target; $^ all dependencies; $< first dependency; $* stem
# $(@D) directory part of $@; $(@F) file-name part; $(@:A=B) replace "A" by "B"

PROGRAMS :=  $(wildcard Binary*.java)
CLASSES  :=  $(PROGRAMS:.java=)
TRACES   :=  $(wildcard *.trace)

RUN	:= /Users/ryan/utility_programs/bin/x86_64/run
CMP	:= java -cp testcases/*.jar -Dformat='%4$$-15s: %2$$12s  [%3$$s]%n'

DATA1	:= testcases/data1.inp
DATA2	:= testcases/data2.inp
DATA3	:= testcases/data3.inp
DATA4	:= testcases/data4.inp
DATA5	:= testcases/data5.inp
DATA6	:= testcases/data6.inp
DATA8	:= testcases/data8.inp
DATA9	:= testcases/data9.inp
DATAA	:= testcases/dataA.inp
DATAB	:= testcases/dataB.inp
DATAC	:= testcases/dataC.inp
DATAD	:= testcases/dataD.inp

define remove_target
@/bin/sh -c 'if [ -f $@ ]; then /bin/rm $@; fi'
endef

define remove_misc
@find . -name '*~' -exec rm {} \;
endef

define remove_java_class_files
@find . -name '*.class' -exec rm {} \;
endef

default	:
        # Even though they are in packages!
	javac *.java

instrument	: $(PROGRAMS:.java=.class)
	# get original class files; do not reinstrument them
	cd ../.. ; java -cp javassist.jar:. -Dinstrument.go -Dinstrument.inc -Dinstrument.cmp instrument.ClassTransformation2 $(addprefix data.binary.,$(^:.class=))

trace	:	trace1 trace2 trace3 trace4 trace5 trace6 trace8 trace9 traceA traceB traceC traceD
trace1	:	$(addprefix data1-,$(addsuffix .trace, $(CLASSES:Binary%=%)))
trace2	:	$(addprefix data2-,$(addsuffix .trace, $(CLASSES:Binary%=%)))
trace3	:	$(addprefix data3-,$(addsuffix .trace, $(CLASSES:Binary%=%)))
trace4	:	$(addprefix data4-,$(addsuffix .trace, $(CLASSES:Binary%=%)))
trace5	:	$(addprefix data5-,$(addsuffix .trace, $(CLASSES:Binary%=%)))
trace6	:	$(addprefix data6-,$(addsuffix .trace, $(CLASSES:Binary%=%)))
trace8	:	$(addprefix data8-,$(addsuffix .trace, $(CLASSES:Binary%=%)))
trace9	:	$(addprefix data9-,$(addsuffix .trace, $(CLASSES:Binary%=%)))
traceA	:	$(addprefix dataA-,$(addsuffix .trace, $(CLASSES:Binary%=%)))
traceB	:	$(addprefix dataB-,$(addsuffix .trace, $(CLASSES:Binary%=%)))
traceC	:	$(addprefix dataC-,$(addsuffix .trace, $(CLASSES:Binary%=%)))
traceD	:	$(addprefix dataD-,$(addsuffix .trace, $(CLASSES:Binary%=%)))

data1-%.trace	: Binary%.class
	$(RUN) -t 2 -i $(DATA1) -m data1-$* -c data1-$* -x java -- -cp ../.. data.binary.$(<:.class=)
	$(CMP) -Dcomment=data1-$*  cm.tc.CompareOutput binary data1 < data1-$*.out > data1-$*.pf
	-timeout 2 java -cp ../.. data.binary.$(<:.class=) < $(DATA1) > data1-$*.trace

data2-%.trace	: Binary%.class
	$(RUN) -t 2 -i $(DATA2) -m data2-$* -c data2-$* -x java -- -cp ../.. data.binary.$(<:.class=)
	$(CMP) -Dcomment=data2-$*  cm.tc.CompareOutput binary data2 < data2-$*.out > data2-$*.pf
	-timeout 2 java -cp ../.. data.binary.$(<:.class=) < $(DATA2) > data2-$*.trace

data3-%.trace	: Binary%.class
	$(RUN) -t 2 -i $(DATA3) -m data3-$* -c data3-$* -x java -- -cp ../.. data.binary.$(<:.class=)
	$(CMP) -Dcomment=data3-$*  cm.tc.CompareOutput binary data3 < data3-$*.out > data3-$*.pf
	-timeout 2 java -cp ../.. data.binary.$(<:.class=) < $(DATA3) > data3-$*.trace

data4-%.trace	: Binary%.class
	$(RUN) -t 2 -i $(DATA4) -m data4-$* -c data4-$* -x java -- -cp ../.. data.binary.$(<:.class=)
	$(CMP) -Dcomment=data4-$*  cm.tc.CompareOutput binary data4 < data4-$*.out > data4-$*.pf
	-timeout 2 java -cp ../.. data.binary.$(<:.class=) < $(DATA4) > data4-$*.trace

data5-%.trace	: Binary%.class
	$(RUN) -t 2 -i $(DATA5) -m data5-$* -c data5-$* -x java -- -cp ../.. data.binary.$(<:.class=)
	$(CMP) -Dcomment=data5-$*  cm.tc.CompareOutput binary data5 < data5-$*.out > data5-$*.pf
	-timeout 2 java -cp ../.. data.binary.$(<:.class=) < $(DATA5) > data5-$*.trace

data6-%.trace	: Binary%.class
	$(RUN) -t 2 -i $(DATA6) -m data6-$* -c data6-$* -x java -- -cp ../.. data.binary.$(<:.class=)
	$(CMP) -Dcomment=data6-$*  cm.tc.CompareOutput binary data6 < data6-$*.out > data6-$*.pf
	-timeout 2 java -cp ../.. data.binary.$(<:.class=) < $(DATA6) > data6-$*.trace

data8-%.trace	: Binary%.class
	$(RUN) -t 2 -i $(DATA8) -m data8-$* -c data8-$* -x java -- -cp ../.. data.binary.$(<:.class=)
	$(CMP) -Dcomment=data8-$*  cm.tc.CompareOutput binary data8 < data8-$*.out > data8-$*.pf
	-timeout 2 java -cp ../.. data.binary.$(<:.class=) < $(DATA8) > data8-$*.trace

data9-%.trace	: Binary%.class
	$(RUN) -t 2 -i $(DATA9) -m data9-$* -c data9-$* -x java -- -cp ../.. data.binary.$(<:.class=)
	$(CMP) -Dcomment=data9-$*  cm.tc.CompareOutput binary data9 < data9-$*.out > data9-$*.pf
	-timeout 2 java -cp ../.. data.binary.$(<:.class=) < $(DATA9) > data9-$*.trace

dataA-%.trace	: Binary%.class
	$(RUN) -t 2 -i $(DATAA) -m dataA-$* -c dataA-$* -x java -- -cp ../.. data.binary.$(<:.class=)
	$(CMP) -Dcomment=dataA-$*  cm.tc.CompareOutput binary dataA < dataA-$*.out > dataA-$*.pf
	-timeout 2 java -cp ../.. data.binary.$(<:.class=) < $(DATAA) > dataA-$*.trace

dataB-%.trace	: Binary%.class
	$(RUN) -t 2 -i $(DATAB) -m dataB-$* -c dataB-$* -x java -- -cp ../.. data.binary.$(<:.class=)
	$(CMP) -Dcomment=dataB-$*  cm.tc.CompareOutput binary dataB < dataB-$*.out > dataB-$*.pf
	-timeout 2 java -cp ../.. data.binary.$(<:.class=) < $(DATAB) > dataB-$*.trace

dataC-%.trace	: Binary%.class
	$(RUN) -t 2 -i $(DATAC) -m dataC-$* -c dataC-$* -x java -- -cp ../.. data.binary.$(<:.class=)
	$(CMP) -Dcomment=dataC-$*  cm.tc.CompareOutput binary dataC < dataC-$*.out > dataC-$*.pf
	-timeout 2 java -cp ../.. data.binary.$(<:.class=) < $(DATAC) > dataC-$*.trace

dataD-%.trace	: Binary%.class
	$(RUN) -t 2 -i $(DATAD) -m dataD-$* -c dataD-$* -x java -- -cp ../.. data.binary.$(<:.class=)
	$(CMP) -Dcomment=dataD-$*  cm.tc.CompareOutput binary dataD < dataD-$*.out > dataD-$*.pf
	-timeout 2 java -cp ../.. data.binary.$(<:.class=) < $(DATAD) > dataD-$*.trace


diff	:	diff1 diff2 diff3 diff4 diff5 diff6 diff8 diff9 diffA diffB diffC diffD
diff1	:	$(addprefix data1-,$(addsuffix .diff, $(CLASSES:Binary%=%)))
diff2	:	$(addprefix data2-,$(addsuffix .diff, $(CLASSES:Binary%=%)))
diff3	:	$(addprefix data3-,$(addsuffix .diff, $(CLASSES:Binary%=%)))
diff4	:	$(addprefix data4-,$(addsuffix .diff, $(CLASSES:Binary%=%)))
diff5	:	$(addprefix data5-,$(addsuffix .diff, $(CLASSES:Binary%=%)))
diff6	:	$(addprefix data6-,$(addsuffix .diff, $(CLASSES:Binary%=%)))
diff8	:	$(addprefix data8-,$(addsuffix .diff, $(CLASSES:Binary%=%)))
diff9	:	$(addprefix data9-,$(addsuffix .diff, $(CLASSES:Binary%=%)))
diffA	:	$(addprefix dataA-,$(addsuffix .diff, $(CLASSES:Binary%=%)))
diffB	:	$(addprefix dataB-,$(addsuffix .diff, $(CLASSES:Binary%=%)))
diffC	:	$(addprefix dataC-,$(addsuffix .diff, $(CLASSES:Binary%=%)))
diffD	:	$(addprefix dataD-,$(addsuffix .diff, $(CLASSES:Binary%=%)))

data1-%.diff	:
	java -cp ../.. analyze.Difference data1-02.trace data1-$*.trace > $@
data2-%.diff	:
	java -cp ../.. analyze.Difference data2-02.trace data2-$*.trace > $@
data3-%.diff	:
	java -cp ../.. analyze.Difference data3-02.trace data3-$*.trace > $@
data4-%.diff	:
	java -cp ../.. analyze.Difference data4-02.trace data4-$*.trace > $@
data5-%.diff	:
	java -cp ../.. analyze.Difference data5-02.trace data5-$*.trace > $@
data6-%.diff	:
	java -cp ../.. analyze.Difference data6-02.trace data6-$*.trace > $@
data8-%.diff	:
	java -cp ../.. analyze.Difference data8-02.trace data8-$*.trace > $@
data9-%.diff	:
	java -cp ../.. analyze.Difference data9-02.trace data9-$*.trace > $@
dataA-%.diff	:
	java -cp ../.. analyze.Difference dataA-02.trace dataA-$*.trace > $@
dataB-%.diff	:
	java -cp ../.. analyze.Difference dataA-02.trace dataB-$*.trace > $@
dataC-%.diff	:
	java -cp ../.. analyze.Difference dataA-02.trace dataC-$*.trace > $@
dataD-%.diff	:
	java -cp ../.. analyze.Difference dataA-02.trace dataD-$*.trace > $@

clean   :
	$(remove_misc)
	$(remove_java_class_files)
