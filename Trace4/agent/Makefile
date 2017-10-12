default	:	agent

agent	:	compile jar

compile	:
	javac -cp .:$(HOME)/public_html/javassist.jar ArrayListAgent.java

jar	:	agent.jar

agent.jar	:	ArrayListAgent.class
	jar -cfm agent.jar manifest.mf ArrayListAgent*.class
