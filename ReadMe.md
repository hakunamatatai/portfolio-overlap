# Project Description

Equity mutual funds are a collection of individual stocks. Fund overlap is the term used to quantify the percentage of common stocks between different mutual funds. Investors prefer to have funds with as low an overlap as possible, since investing in different mutual funds with a high overlap will not give the benefits of diversification.
Available mutual funds and their respective stocks are mentioned here: https://geektrust.s3.ap-southeast-1.amazonaws.com/portfolio-overlap/stock_data.json

Portfolio Overlap: Overlap is defined as a measure of the commonality between portfolios.
Suppose there are 2 funds A & B. The overlap between them is given as the number of stocks in common, counting both portfolio A and B( (i.e. double counting the common elements) divided by the total number of elements in portfolio A and portfolio B.

Overlap (A,B) = 2*(No of common stocks in A & B)/ (No of stocks in A + No of stocks in B) * 100

The program take as input:
1. Name of current funds the user holds in his/her portfolio.
2. New funds to be added.
3. Stock to be added for existing fund.

The output should be
1. Overlapping stocks of the new fund with the current funds.
2. If a given fund name is not present while calculating overlap or adding stocks to existing fund, the output should be FUND_NOT_FOUND.


# Pre-requisites
* Java 1.8 / 1.17 
* (Note: My local Java version is 17, but I’ve configured IDEA to compile and run with Java 8 compatibility. If you 
* encounter errors or issues when running, please check your Java version settings.
* If your JVM is not running Java 8/17, please check your pom.xml and update the Java version accordingly.)
* Maven

# How to run the code

We have provided scripts to execute the code. 

Use `run.sh` if you are Linux/Unix/macOS Operating systems and `run.bat` if you are on Windows. 

command: bash run.sh (for macOS)

Both the files run the commands silently and prints only output from the input file `sample_input/input1.txt`. You are supposed to add the input commands in the file from the appropriate problem statement. 

Internally both the scripts run the following commands 

 * `mvn clean install -DskipTests assembly:single -q` - This will create a jar file `geektrust.jar` in the `target` folder.
 * `java -jar target/geektrust.jar sample_input/input1.txt` - This will execute the jar file passing in the sample input file as the command line argument

 We expect your program to take the location to the text file as parameter. Input needs to be read from a text file, and output should be printed to the console. The text file will contain only commands in the format prescribed by the respective problem.

 Use the pom.xml provided along with this project. Please change the main class entry (`<mainClass>com.example.portfolio.Main</mainClass>`) in the pom.xml if your main class has changed.

 # Running the code for multiple test cases

 Please fill `input1.txt` and `input2.txt` with the input commands and use those files in `run.bat` or `run.sh`. Replace `java -jar target/geektrust.jar sample_input/input1.txt` with `java -jar target/geektrust.jar sample_input/input2.txt` to run the test case from the second file. 

 # How to execute the unit tests

 `mvn clean test` will execute the unit test cases.

# Help

You can refer our help documents [here](https://help.geektrust.com)
You can read build instructions [here](https://github.com/geektrust/coding-problem-artefacts/tree/master/Java)
