package selenium.testng;

import static org.testng.Assert.assertEquals;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.Select;

public class PageObject extends LoadableComponent<PageObject>{
	private WebDriver driver;
	private Actions action;
	private Select selectDropList;
	
	@FindBy(xpath="//html//body//div[1]//div//div[1]//main//article//div//div//div[1]//div//p//b")
	private WebElement boldText;
	
	@FindBy(css="p>a")
	private WebElement linkText;
	
	@FindBy(name="firstName")
	private WebElement textBox;
	
	@FindBy(id="idOfButton")
	private WebElement submitButton;
	
	@FindBy(id="dblClkBtn")
	private WebElement doubleClickButton;
	
	@FindBys({
		@FindBy(name="gender")
	})
	private List<WebElement> genderRadioButtons;
	
	@FindBy(className="Automation")
	private WebElement automationCheckBox;

	@FindBy(className="Performance")
	private WebElement performanceCheckBox;
	
	private WebElement selDrop;
	
	@FindBy(css="#AlertBox > button:nth-child(2)")
	private WebElement alertBoxButton;
	
	@FindBy(css="#ConfirmBox > button:nth-child(2)")
	private WebElement confirmBoxButton;
	
	public PageObject(WebDriver driver) {
		this.driver = driver;
		
		PageFactory.initElements(driver, this);
		
		action = new Actions(driver);
	}
	
	@Override
	protected void load() {
		driver.get("https://artoftesting.com/samplesiteforselenium");
	}
	
	@Override
	protected void isLoaded() throws Error {
		String url = driver.getCurrentUrl();
		assertEquals(url, "https://artoftesting.com/samplesiteforselenium");
	}
	
	public String getBoldText() {
		return boldText.getText();
	}
	
	public String getLinkAttrib() {
		return linkText.getAttribute("href");
	}
	
	public void inputTextBox(String text) {
		textBox.sendKeys(text);
	}
	
	public String getTextBoxValue() {
		return textBox.getAttribute("value");
	}
	
	public void clickSubmitButton() {
		submitButton.click();
	}
	
	public String tooltipSubmitButton() {
		return submitButton.getAttribute("title");
	}
	
	public String colorSubmitButton() {
		return submitButton.getCssValue("background");
	}
	
	public String doubleClickButton() {
		action.doubleClick(this.doubleClickButton).perform();
		
		// Jump to newly opened Alert Box
		Alert alert = driver.switchTo().alert();
		
		String txtMsg = alert.getText();
		// System.out.println(txtMsg);
		
		// Close the alert box
		alert.accept();
		
		return txtMsg;
	}
	
	public boolean toggleRadioButtons(String gender) {
		if (gender.equalsIgnoreCase("male")) {
			genderRadioButtons.get(0).click();
			return genderRadioButtons.get(0).isSelected();
		}
		genderRadioButtons.get(1).click();
		return genderRadioButtons.get(1).isSelected();
	}
	
	// Returns true if the checkbox is checked
	// otherwise returns false
	public boolean toggleCheckBox(String identifier) {
		if (identifier.equalsIgnoreCase("automation")) {
			automationCheckBox.click();
			return automationCheckBox.isSelected();
		}
		performanceCheckBox.click();
		return performanceCheckBox.isSelected();
	}
	
	public void setupDropCheck() {
		selDrop = driver.findElement(By.id("testingDropdown"));
		selectDropList = new Select(selDrop);
	}
	
	public boolean isDropDownMultiple() {
		// Center the dropdown list
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", selectDropList);
		return selectDropList.isMultiple();
	}
	
	public int getDropDownSize() {
		return selectDropList.getOptions().size();
	}
	
	public String selectDropDownItem(String val) {
		selectDropList.selectByValue(val);
		return selectDropList.getFirstSelectedOption().getText();
	}
	
	public String clickAlertBox() {
		alertBoxButton.click();
		Alert alert = driver.switchTo().alert();
		String strMsg = alert.getText();
		alert.accept();
		return strMsg;
	}
	
	public String clickConfirmBox(String opt) {
		confirmBoxButton.click();
		Alert alert = driver.switchTo().alert();
		if (opt.equalsIgnoreCase("OK")) {
			alert.accept();
		} else {
			alert.dismiss();
		}
		return driver.findElement(By.id("demo")).getText();
	}
}
