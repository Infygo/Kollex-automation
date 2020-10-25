# Kollex-automation
# Overview 
The test methods have been designed and implemented for each step that was described in the pdf. Every test will have its starting point from the google page(Test1) and then navigates to the Giga berlin wikipedia page(Test2), collects the coordinate, logistics and site concerns data and will copy to the csv file,also takes a screenshot and open maps in new tab(Test3).

![Alt text](https://github.com/Infygo/Kollex-automation/blob/master/Project%20folder%20structure%20screenshot.png)

### *Page class* 
Each page in have the automation project suite has dedicated class ,where the locators and elements methods specific to that page will be implemented. The page class files are under the pageObjects package.

### *Googlepage.java*
This class file will have all the elements methods and locators needed in the google page 

### *Wikipage.java*
This class file will have all the elements methods and locators needed in the Gigaberlin Wikipedia page 

### *Test class - GooglepageTest.java* 
### *Test methods* 
#### *googleToWiki()* 
Tests the opening of google page and navigation from google home page to Wikipedia page. Asserts the test case with the title of the Wikipedia page indicating the transition of google page to wikipedia page is successful

### *Test class - WikipageTest.java*
### *Test methods* 
####  *searchGigaBerlin()*
Tests the search of Giga berlin text in the wikipedia search box. Test is asserted with the title of the Giga berlin wikipedia page 
####  *getGigaBerlinData()*
This test method will handle the retrieval of the coordinate, Logistics and Siteconcerns data in the Giga berlin wiki page. The data is also then copied to the csv file. The test also will open a new tab and search google map with the coordinate data from the wikipedia.

### **CSV data & Screenshots**
The CSV data and screenshots can be found under src/main/java/csvdata_screenshot folder. 
The contents of this folder will be deleted before every execution run.

### Installation & Execution 
*Method1* - execution will make the tests to run in both chrome and firefox browser
- Download / clone the project from Git 
- Import project in IDE of your choice 
- Open testng.xml -> Run as TestNG Suite 
- Open test output folder -> index.html -> to view the Test Results in consolidated manner

*Method2* 
- Download / clone the project from Git 
- Open command prompt - change directory to the Kollex-automation-master\Automation
- mvn compile 

(To run the test in chrome use the command below in command prompt)
- mvn test -Dbrowser=chrome

(To run the test in firefox use the command below in command prompt)
- mvn test -Dbrowser=firefox 

