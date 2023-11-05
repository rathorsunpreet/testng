package selenium.testng;

import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertNotEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PageTest {
  WebDriver driver;
  PageObject pageObj;
  
  @BeforeTest
  @Parameters("browser")
  public void setup(String browser) throws Exception{
	  if(browser.equalsIgnoreCase("firefox")) {
		  driver = new FirefoxDriver();
		  driver.manage().window().maximize();
	  } else if (browser.equalsIgnoreCase("chrome")) {
		  ChromeOptions options = new ChromeOptions();
		  options.addArguments("--headless");
		  options.addArguments("start-maximized");
		  driver = new ChromeDriver(options);
	  } else {
		  throw new Exception("Incorrect browser value!");
	  }
	  
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
	  
	  pageObj = new PageObject(driver).get();
  }
  
  @AfterTest
  public void tearDown() throws Exception {
	  driver.quit();
  }
  
  @Test
  public void checkBoldText() throws Exception {
	  assertEquals(pageObj.getBoldText(), "This is sample text!");
//	  System.out.println("checkBoldText: " + driver.getCurrentUrl());
  }
  
  @Test
  public void checkLinkAddress() throws Exception {
	  assertEquals(pageObj.getLinkAttrib(), "http://www.artoftesting.com/sampleSiteForSelenium.html");
//	  System.out.println("checkLinkAddress: " + driver.getCurrentUrl());
  }
  
  @Test
  public void setupTextBox() throws Exception {
	  pageObj.inputTextBox("Hello there; General Kenobi!");
//	  System.out.println("setupTextBox: " + driver.getCurrentUrl());
  }
  
  @Test(dependsOnMethods = {"setupTextBox"})
  public void checkTextBox() throws Exception {
	  assertEquals(pageObj.getTextBoxValue(), "Hello there; General Kenobi!");
//	  System.out.println("checkTextBox: " + driver.getCurrentUrl());
  }
  
  @Test
  public void checkHoverSubmitButton() throws Exception {
	  assertEquals(pageObj.tooltipSubmitButton(), "Hovering over me!!");
//	  System.out.println("checkHoverSubmitButton: " + driver.getCurrentUrl());
  }
  
  @Test 
  public void checkOnClickSubmitButton() throws Exception {
	  // Add WebDriverWait for chrome check
	  String beforeColor = pageObj.colorSubmitButton();
	  pageObj.clickSubmitButton();
	  assertNotEquals(beforeColor, pageObj.colorSubmitButton());
//	  System.out.println("checkOnClickSubmitButton: " + driver.getCurrentUrl());
  }
  
  @Test
  public void checkDoubleClickButton() throws Exception {
	  assertEquals("Hi! Art Of Testing, Here!", pageObj.doubleClickButton());
//	  System.out.println("checkDoubleClickButton: " + driver.getCurrentUrl());
  }
  
  @Test
  public void checkMaleRadioButton() throws Exception {
	  // Add WebDriveWait for Chrome Check
	  assertTrue(pageObj.toggleRadioButtons("male"));
//	  System.out.println("checkMaleRadioButton: " + driver.getCurrentUrl());
  }
  
  @Test
  public void checkFemaleRadioButton() throws Exception {
	  assertTrue(pageObj.toggleRadioButtons("female"));
//	  System.out.println("checkFemaleRadioButton: " + driver.getCurrentUrl());
  }
  
  @Test
  public void checkAutomationCheckBox() throws Exception {
	  assertTrue(pageObj.toggleCheckBox("automation"));
	  assertFalse(pageObj.toggleCheckBox("automation"));
//	  System.out.println("checkAutomationCheckBox: " + driver.getCurrentUrl());
  }
  
  @Test
  public void checkPerformanceCheckBox() throws Exception {
	  assertTrue(pageObj.toggleCheckBox("performance"));
	  assertFalse(pageObj.toggleCheckBox("performance"));
//	  System.out.println("checkPerformanceCheckBox: " + driver.getCurrentUrl());
  }

  @Test
  public void checkDropDown() throws Exception {
	  pageObj.setupDropCheck();
	  assertFalse(pageObj.isDropDownMultiple());
	  assertEquals(pageObj.getDropDownSize(), 4);
	  
	  List<String> listOptions = new ArrayList<>();
	  listOptions.add("Automation");
	  listOptions.add("Performance");
	  listOptions.add("Manual");
	  listOptions.add("Database");
	  
	  for (int i = 0; i < listOptions.size(); i++) {
		  String visibleText = listOptions.get(i) + " Testing";
		  assertEquals(pageObj.selectDropDownItem(listOptions.get(i)), visibleText);
	  }
//	  System.out.println("checkDropDown: " + driver.getCurrentUrl());
  }
  
  @Test
  public void checkAlertBoxButton() throws Exception {
	  assertEquals(pageObj.clickAlertBox(), "Hi! Art Of Testing, Here!");
//	  System.out.println("checkAlertBoxButton: " + driver.getCurrentUrl());
  }
  
  @Test
  public void checkConfirmBoxButton() throws Exception {
	  assertEquals(pageObj.clickConfirmBox("OK"), "You pressed OK!");
	  assertEquals(pageObj.clickConfirmBox("Cancel"), "You pressed Cancel!");
//	  System.out.println("checkConfirmBoxButton: " + driver.getCurrentUrl());
  }
  
  @Test
  public void checkDragNDrop() throws Exception {
	  // Reset implicitWait to normal (zero seconds)
	  driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
	  
	  WebElement source = driver.findElement(By.id("myImage"));
	  WebElement target = driver.findElement(By.id("targetDiv"));
	  
	  // Create explicit wait and wait until source is clickable
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  wait.until(ExpectedConditions.elementToBeClickable(source));
	  
	  Actions actions = new Actions(driver);
	  
	  // Bring target into the viewport fully, this also brings the source into viewport as well
	  ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", target);
	  
	  // Perform the drag and drop operation
	  actions.clickAndHold(source).build().perform();
	  actions.moveToElement(target).build().perform();
	  actions.release(target).build().perform();
//	  actions.dragAndDropBy(source, 0, -169).build().perform();
	  
//	  wait.until(ExpectedConditions.presenceOfNestedElementsLocatedBy(By.id("targetDiv"), By.id("myImage")));
  }
}
