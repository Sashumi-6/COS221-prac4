#Add with your JAR path
# C:/Program Files/Java/jdk-22/bin/ => usually default for jdk 22
JAR_PATH = "C:/Program Files/Java/jdk-22/bin/"
MYSQL_JAR = "lib\mysql-connector-j-9.3.0.jar"

build:
	- make clean
	mkdir build
	javac -d build src/*.java
	$(JAR_PATH)jar cfm COS221_Assignment4_u24772756_u24658198.jar manifest.txt -C build .
	java -cp $(MYSQL_JAR);COS221_Assignment4_u24772756_u24658198.jar src.Main
	- make clean

clean:
	- rmdir /s /q build
	- del /q COS221_Assignment4_u24772756_u24658198.jar
