# Selenium - TestNG Project

Selenium project to test the DOM Elements of the site [Art of Testing](https://artoftesting.com/samplesiteforselenium) using TestNG and Maven.

## Requirements

This project requires the following to execute:

+ Eclipse
+ Maven 3.9.5
+ Selenium 4.14.1
+ Java 21

## Installation

ToAdd

## Videos

+ 1
+ 2

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