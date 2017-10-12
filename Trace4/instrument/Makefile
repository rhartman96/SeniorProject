# delete the default suffixes for obsolete suffix rules
.SUFFIXES       :

# explicitly declare some targets as phony
.PHONY          :       default all tar html dvi ps clean veryclean help forcegnatmake FORCE

# special Makefile variables
# $@ target; $^ all dependencies; $< first dependency; $* stem
# $(@D) directory part of $@; $(@F) file-name part; $(@:A=B) replace "A" by "B"

define remove_class
@find . -name '*.class' -exec rm {} \;
endef

define remove_backup
@find . -name '*~' -delete
endef

default	:	compile

compile	:
	javac -cp ..:$(HOME)/public_html/javassist.jar C*.java

clean	:
	$(remove_class)
	$(remove_backup)

