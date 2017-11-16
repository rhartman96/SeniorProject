# delete the default suffixes for obsolete suffix rules
.SUFFIXES       :

# explicitly declare some targets as phony
.PHONY          :       default all tar html dvi ps clean veryclean help FORCE

PROBLEM :=      binary
COMPARE :=	'-Dcompare.1=tokenize' '-Dcompare.2=delimiter="[\p{Space}\p{Punct}]+",ignore'
COMPARE :=	'-Dcompare.1=tokenize' '-Dcompare.2=delimiter="[\p{Space}a-zA-Z\p{Punct}]+"'
COMPARE :=	'-Dcompare.1=delimiter="[\p{Space}a-zA-Z\p{Punct}]+"'
COMPARE :=	'-Dcompare.1=tokenize'
TESTS   :=	$(wildcard data?.inp)
SOLS	:=	$(TESTS:.inp=.sol)

UNAME := $(shell uname -n)
ifeq ($(UNAME),hc210-059044)
  HOME := /media/files.cs.fit.edu
endif

UTIL    :=      $(HOME)/courses/management/UTILITIES
JAR     :=      $(HOME)/public_html/java/jars/cm-0.5.jar
PYDIR   :=	$(HOME)/shared/python/


default :  testcases

testcases : testcases-${PROBLEM}.jar

xml	: testcases-${UNAME}.xml

testcases-${UNAME}.xml   :       FORCE
	find . -name $@ -exec rm {} \;
	java -Dprint.details $(COMPARE) -cp $(JAR) -Dproblem=${PROBLEM} cm.tc.CreateXML $(TESTS) > $@


jar	: testcases-${PROBLEM}.jar

# we try to make the jar file dependent on the Internet and not on the machine that created it
testcases-${PROBLEM}.jar   :       testcases-${UNAME}.xml
	find . -name $@ -exec rm {} \;
	java -Dproblem=${PROBLEM} -cp $(JAR) -Djar=$@ cm.tc.CreateJarFromXML $<


solutions	:	$(TESTS:.inp=.sol)
%.sol	:	%.inp
	find . -name $@ -exec rm {} \;
	java -cp ../../.. data.binary.Binary02 < $^ > $@

EXEC := java -ea -cp ../../.. data.binary.Binary02

check	:
	@python3 $(PYDIR)/runall.py3 --no --exec "$(EXEC)" *.jar

clean	:
	@find . -name '*~' -delete
