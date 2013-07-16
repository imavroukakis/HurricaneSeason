How to run
==========
Run the main class (HurricaneSeason) providing the following three params VM params

* -DdataFile=src/main/resources/hurdat-all.txt (the hurdat2 formatted data file)
* -DregexFile=src/main/resources/regex.properties (the regex patterns to scan the data file)
* -Dyear=2009 (the year to report for)

To run with the above as default parameters you can use the maven exec goal by invoking
mvn exec:exec