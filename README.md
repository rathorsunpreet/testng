# Selenium - TestNG Project

Selenium project to perform cross-browser test of DOM Elements of the site [Art of Testing](https://artoftesting.com/samplesiteforselenium) using TestNG and Maven.

Selenium IDE Demo: https://www.youtube.com/watch?v=uk01c8UST4g  
Selenium Demo: https://www.youtube.com/watch?v=sryHxDWeVfA  

## Requirements

This project requires the following to execute:

+ Eclipse (with TestNG Plugin)
+ Maven 3.9.5
+ Java 21

## Installation

To install the project, perform the following steps:

1. Go to [Repository](https://github.com/rathorsunpreet/testng).
2. Click on Code -> Download as Zip.
3. Unzip the file.
4. Open Eclipse.
5. Click on `File -> Import -> Existing Projects into Workspace`.
6. In the new Dialog Box, select "Select Root Directory".
7. Click on Browse and set the root as the unzipped directory.
8. Make sure that the project is selected under "Projects" list.
9. Click Finish.


## Usage

To execute the project, perform the following steps:

1. Open Eclipse and make sure the project is active.
2. Click on `Run -> Run Configurations...` .
3. Select TestNG and then either right click and select "New Configuration" or click on the button "New Launch Configuration" located above the search bar.
4. On the newly opened dialog on the right-hand side, give the config a "Name".
5. Under the "Test" tab, select the "Project" using the "Browse" button.
6. Under "Run...", select "Suite" and set it to "TestNGSuite.xml" provided with the project using "Browse".
7. Click Apply.
8. Click Run.

To execute the project anytime, just go to `Run -> Run Configurations... -> (Your new Test Config) -> Run`.

## Videos

+ [Selenium Web Driver](https://www.youtube.com/watch?v=sryHxDWeVfA)
+ [Selenium IDE](https://www.youtube.com/watch?v=uk01c8UST4g)

## Missing Features

Drag and Drop Test is extremely hard to implement on Gecko (Mozilla Firefox) & Chrome Drivers, as per the following posts and several others like it:

+ [element is dragged correctly but not dropped at the specified target instead, displayed with the mouse pointer
#7744](https://github.com/SeleniumHQ/selenium/issues/7744)
+ [Actions class methods for dragging and dropping does not work #7782](https://github.com/SeleniumHQ/selenium/issues/7782)
+ [Unable to perform drag and drop with Selenium (python) #8003](https://github.com/SeleniumHQ/selenium/issues/8003)

There is however a roundabout solution, by using an external javascript file [druska/native_js_drag_and_drop_helper.js](https://gist.github.com/druska/624501b7209a74040175#file-native_js_drag_and_drop_helper-js) . This is however not a reliable solution and may need to be modified based on framework used to build the site.

Another solution is to use the [Selenium IDE](https://www.selenium.dev/selenium-ide/) addon/plugin for the web browser for these kind of tests.

## License

[MIT](https://choosealicense.com/licenses/mit/)