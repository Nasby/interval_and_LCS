JFLAGS = -g
JC = javac
.SUFFIXES: .java .class
.java.class:
	$(JC) $(JFLAGS) $*.java

CLASSES = \
	LCS.java \
	Interval.java \
	Mergesort.java 

default: classes

classes: $(CLASSES:.java=.class)

clean:
	$(RM) *.class
