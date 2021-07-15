package com.Basic.Selenium;

import static org.testng.Assert.assertNotNull;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class firstSeleniumProjectjQuery {
	private WebDriver driver;

	@BeforeClass
	private void setup() {
		// set up steps to prepare selenium
	//	System.setProperty("webdriver.chrome.driver", "src/test/resources/browserDrivers/chromedriver.exe");
		
		WebDriverManager.chromedriver().setup();
		ChromeOptions options = new ChromeOptions();
	//	options.addArguments("--headless");
		driver = new ChromeDriver(options);
		
		
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}

	@AfterClass
	private void close() {
		if (driver != null) {
			driver.close();
			driver.quit();
		}
	}

	@Test//(priority = 1, enabled = true)
	public void iFramesLinksNumber() {
		try {
			driver.get("https://jqueryui.com/");
			System.out.println("Title" + driver.getTitle());
			Thread.sleep(3000);

			List<WebElement> links = driver.findElements(By.tagName("a"));
			int numOfLinks = links.size();
			System.out.println("Total Links: " + numOfLinks);

			List<WebElement> iframes = driver.findElements(By.tagName("iframe"));
			int iframeNum = iframes.size();
			System.out.println("Total iframes: " + iframeNum);

			int i = 1;
			for (WebElement link : links) {
				String linkTxt = link.getText();
				System.out.println(i + ")link text is: [" + linkTxt + "]");
				i++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test//(priority = 2, enabled = true)
	public void Interactions_Draggable() {
		try {
			driver.get("https://jqueryui.com/");
			System.out.println("Title" + driver.getTitle());
			Thread.sleep(3000);

			WebElement Draggable = driver.findElement(By.xpath("//*[@id='sidebar']/aside[1]/ul/li[1]/a"));
			Draggable.click();
			Thread.sleep(3000);

			System.out.println("Page synch ---> waiting for 'Draggable' page is visible.");
			WebDriverWait wait = new WebDriverWait(driver, 15);
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > iframe")));
			assertNotNull(Draggable, "Synchronizing for Draggable page not visible, test timed out!");
			Thread.sleep(3000);

			driver.switchTo().frame(0);
			WebElement DragMeArnd = driver.findElement(By.id("draggable"));
			Actions action = new Actions(driver);
			action.dragAndDropBy(DragMeArnd, 188, 221).build().perform();
			System.out.println("Drag the samll window around.");
			Thread.sleep(3000);

			driver.switchTo().parentFrame();
			System.out.println("Leaving out of iframe");
			driver.navigate().back();
			Thread.sleep(3000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test//(priority = 3, enabled = true)
	public void Droppable() {
		try {
			driver.get("https://jqueryui.com/");
			System.out.println("Title" + driver.getTitle());
			Thread.sleep(2000);

			WebElement Droppable = driver.findElement(By.xpath("//*[@id='sidebar']/aside[1]/ul/li[2]/a"));
			Droppable.click();
			Thread.sleep(2000);

			System.out.println("Page synch ---> waiting for 'Droppable' page is visible.");
			WebDriverWait waitTo = new WebDriverWait(driver, 15);
			waitTo.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > iframe")));
			assertNotNull(Droppable, "Synchronizing for Droppable page not visible, test timed out!");
			Thread.sleep(2000);

			driver.switchTo().frame(0);
			WebElement DragToTarget = driver.findElement(By.id("draggable"));
			Actions action = new Actions(driver);
			action.dragAndDropBy(DragToTarget, 178, 43).build().perform();
			System.out.println("Drag the small window and drop inside the bigger window.");
			Thread.sleep(3000);

			driver.switchTo().parentFrame();
			System.out.println("Leaving out of iframe");
			driver.navigate().back();
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test//(priority = 4, enabled = true)
	public void Resizable() {
		try {
			driver.get("https://jqueryui.com/");
			System.out.println("Title" + driver.getTitle());
			Thread.sleep(3000);

			WebElement Resizable = driver
					.findElement(By.cssSelector("#sidebar > aside:nth-child(1) > ul > li:nth-child(3) > a"));
			Resizable.click();
			Thread.sleep(3000);

			System.out.println("Page synch ---> waiting for 'Resizable' page is visible.");
			WebDriverWait waitTo = new WebDriverWait(driver, 15);
			waitTo.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > iframe")));
			assertNotNull(Resizable, "Synchronizing for Resizable page not visible, test timed out!");
			Thread.sleep(3000);

			driver.switchTo().frame(0);
			WebElement ResizAble = driver.findElement(By.xpath("//*[@id='resizable']/div[1]"));
			Actions action = new Actions(driver);
			action.clickAndHold(ResizAble).moveByOffset(150, 300).release(ResizAble).build().perform();
			Thread.sleep(3000);

			WebElement ResizHorizontal = driver.findElement(By.xpath("//*[@id='resizable']/div[2]"));
			Actions actionH = new Actions(driver);
			actionH.clickAndHold(ResizHorizontal).moveByOffset(150, 40).release(ResizHorizontal).build().perform();
			System.out.println("Drag right side to resize the window horizontally.");
			Thread.sleep(3000);

			WebElement ResizVertical = driver.findElement(By.xpath("//*[@id='resizable']/div[3]"));
			Actions actionV = new Actions(driver);
			actionV.clickAndHold(ResizVertical).moveByOffset(114, 112).release(ResizVertical).build().perform();
			System.out.println("Drag the button side to resize the window virtically.");
			Thread.sleep(3000);

			driver.switchTo().parentFrame();
			System.out.println("Leaving out of iframe");
			driver.navigate().back();
			Thread.sleep(3000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test//(priority = 5, enabled = true)
	public void Selectable() {
		try {
			driver.get("https://jqueryui.com/");
			System.out.println("Title" + driver.getTitle());
			Thread.sleep(3000);

			WebElement Selectable = driver
					.findElement(By.cssSelector("#sidebar > aside:nth-child(1) > ul > li:nth-child(4) > a"));
			Selectable.click();
			Thread.sleep(3000);

			System.out.println("Page synch ---> waiting for 'Selectable' page is visible.");
			WebDriverWait waitTo = new WebDriverWait(driver, 10);
			waitTo.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > iframe")));
			assertNotNull(Selectable, "Synchronizing for Selectable page not visible, test timed out!");
			Thread.sleep(3000);

			driver.switchTo().frame(0);

			WebElement SelectItem1 = driver.findElement(By.xpath("//*[@id='selectable']/li[1]"));
			SelectItem1.click();
			System.out.println("Click Item1");
			Thread.sleep(1000);

			WebElement SelectItem2 = driver.findElement(By.xpath("//*[@id='selectable']/li[2]"));
			SelectItem2.click();
			System.out.println("Click Item2");
			Thread.sleep(1000);

			WebElement SelectItem7 = driver.findElement(By.xpath("//*[@id='selectable']/li[7]"));
			SelectItem7.click();
			System.out.println("Click Item7");
			Thread.sleep(3000);

			WebElement SelectItem3 = driver.findElement(By.xpath("//*[@id='selectable']/li[3]"));
			Actions action = new Actions(driver);
			action.clickAndHold(SelectItem3).dragAndDrop(SelectItem3, SelectItem7).build().perform();
			System.out.println("Click Item 3 till 7");
			Thread.sleep(3000);

			WebElement Select7to1 = driver.findElement(By.xpath("//*[@id='selectable']/li[7]"));
			Actions action7 = new Actions(driver);
			action7.clickAndHold(Select7to1).dragAndDrop(Select7to1, SelectItem1).build().perform();
			System.out.println("Click Item from 7 till 1");
			Thread.sleep(3000);

			Actions RandomClick = new Actions(driver);

			RandomClick.click(SelectItem2).build().perform();
			RandomClick.keyDown(Keys.CONTROL).build().perform();
			RandomClick.click(SelectItem7).build().perform();
			RandomClick.keyUp(Keys.CONTROL).build().perform();

			System.out.println("Click Item2 hold it and click Item7");
			Thread.sleep(3000);

			driver.switchTo().parentFrame();
			System.out.println("Leaving out of iframe");
			driver.navigate().back();
			Thread.sleep(3000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test//(priority = 6, enabled = true)
	public void Sortable() {
		try {
			driver.get("https://jqueryui.com/");
			System.out.println("Title" + driver.getTitle());
			Thread.sleep(3000);

			WebElement Sortable = driver
					.findElement(By.cssSelector("#sidebar > aside:nth-child(1) > ul > li:nth-child(5) > a"));
			Sortable.click();
			Thread.sleep(3000);

//			System.out.println("Page synch ---> waiting for 'Sortable' page is visible.");
//			WebDriverWait waitTo = new WebDriverWait(driver, 10);
//			//waitTo.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > iframe")));
//			waitTo.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='content']/iframe")));
//			assertNotNull(Sortable, "Synchronizing for Sortable page not visible, test timed out!");
//			Thread.sleep(3000);
			
			driver.switchTo().frame(0);

			WebElement SortItem_1 = driver.findElement(By.cssSelector("#sortable > li:nth-child(1)"));
			WebElement SortItem_2 = driver.findElement(By.cssSelector("#sortable > li:nth-child(2)"));
			WebElement SortItem_4 = driver.findElement(By.cssSelector("#sortable > li:nth-child(4)"));
			WebElement SortItem_6 = driver.findElement(By.cssSelector("#sortable > li:nth-child(6)"));
			WebElement SortItem_7 = driver.findElement(By.cssSelector("#sortable > li:nth-child(7)"));

			Actions SortAction = new Actions(driver);
			highlightElement(SortItem_1);
			SortAction.clickAndHold(SortItem_1).build().perform();
			System.out.println("Click and hold Item 1 and highlight it");
			Thread.sleep(2000);

			SortAction.dragAndDropBy(SortItem_1, 7, 212).build().perform();
			System.out.println("Drag Item 1 to the location of Item 6");
			Thread.sleep(3000);

			highlightElement(SortItem_6);
			SortAction.dragAndDrop(SortItem_1, SortItem_6).build().perform();
			System.out.println("Drop Item 1 to the location of Item 6 and Item 6 auto moves to one step above");
			Thread.sleep(3000);

			highlightElement(SortItem_7);
			SortAction.clickAndHold(SortItem_7).build().perform();
			System.out.println("Click and hold Item 7 and highlight it");
			Thread.sleep(2000);

			SortAction.dragAndDropBy(SortItem_7, 287, 51).build().perform();
			System.out.println("Drag Item 7 to the location of Item 2");
			Thread.sleep(3000);

			SortAction.dragAndDrop(SortItem_7, SortItem_2).build().perform();
			System.out.println("Drop Item 7 to the location of Item 2 and Item 2 auto moves to one step below");
			Thread.sleep(3000);

			highlightElement(SortItem_4);
			Thread.sleep(2000);
			System.out.println("Highlight Item 4");
			SortAction.clickAndHold(SortItem_4).moveByOffset(4, 350).build().perform();
			SortAction.dragAndDrop(SortItem_4, SortItem_6).build().perform();
			SortAction.release();
			System.out.println("Drag Item 4 to the bottom of frame and release it");
			Thread.sleep(2000);

			driver.switchTo().parentFrame();
			System.out.println("Leaving out of iframe");
			driver.navigate().back();
			Thread.sleep(3000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test//(priority = 7, enabled = true)
	public void Widgets_Accordion() {
		try {
			driver.get("https://jqueryui.com/");
			System.out.println("Title" + driver.getTitle());
			Thread.sleep(3000);

			WebElement Accordion = driver
					.findElement(By.cssSelector("#sidebar > aside:nth-child(2) > ul > li:nth-child(1) > a"));
			Accordion.click();
			Thread.sleep(3000);

			System.out.println("Page synch ---> waiting for 'Accordion' page is visible.");
			WebDriverWait waitTo = new WebDriverWait(driver, 10);
			waitTo.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > iframe")));
			assertNotNull(Accordion, "Synchronizing for Accordion page not visible, test timed out!");
			Thread.sleep(3000);

			driver.switchTo().frame(0);

			WebElement Section_3 = driver.findElement(By.id("ui-id-5"));
			highlightElement(Section_3);
			Section_3.click();
			System.out.println("Click on Section 3 and section head moves up to next to section 2, shows contents");
			Thread.sleep(2000);

			WebElement Section_2 = driver.findElement(By.id("ui-id-3"));
			highlightElement(Section_2);
			Section_2.click();
			System.out.println("Click on Section 2 and it opens the content part");
			Thread.sleep(2000);

			driver.switchTo().parentFrame();
			System.out.println("Leaving out of iframe");
			driver.navigate().back();
			Thread.sleep(3000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 8, enabled = true)
	public void Autocomplete() {
		try {
			driver.get("https://jqueryui.com/");
			System.out.println("Title" + driver.getTitle());
			Thread.sleep(2000);

			scrollUpDown(200);
			WebElement Autocomplete = driver
					.findElement(By.cssSelector("#sidebar > aside:nth-child(2) > ul > li:nth-child(2) > a"));
			Autocomplete.click();
			Thread.sleep(2000);

			System.out.println("Page synch ---> waiting for 'Autocomplete' page is visible.");
			WebDriverWait waitTo = new WebDriverWait(driver, 10);
			waitTo.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > iframe")));
			assertNotNull(Autocomplete, "Synchronizing for Autocomplete page not visible, test timed out!");
			Thread.sleep(2000);

			driver.switchTo().frame(0);

			WebElement TagsTypeIn = driver.findElement(By.id("tags"));
			TagsTypeIn.sendKeys("Java");
			Thread.sleep(2000);
			TagsTypeIn.clear();
			TagsTypeIn.sendKeys("Project");
			Thread.sleep(1000);
			TagsTypeIn.clear();
			TagsTypeIn.sendKeys("Nice");
			Thread.sleep(1000);

			driver.switchTo().parentFrame();
			System.out.println("Leaving out of iframe");
			driver.navigate().back();
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 9, enabled = true)
	public void Button() {
		try {
			driver.get("https://jqueryui.com/");
			System.out.println("Title" + driver.getTitle());
			Thread.sleep(2000);

			scrollUpDown(200);
			WebElement Button = driver
					.findElement(By.cssSelector("#sidebar > aside:nth-child(2) > ul > li:nth-child(3) > a"));
			Button.click();
			Thread.sleep(2000);

			System.out.println("Page synch --> waiting for 'Button' page is visible.");
			WebDriverWait waitTo = new WebDriverWait(driver, 10);
			waitTo.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > iframe")));
			assertNotNull(Button, "Synchronizing for Button page not visible, test timed out!");
			Thread.sleep(2000);

			driver.switchTo().frame(0);

			WebElement WidgetBtnElem = driver.findElement(By.cssSelector("body > div > button"));
			highlightElement(WidgetBtnElem);
			Thread.sleep(2000);
			WidgetBtnElem.click();
			Thread.sleep(2000);

			WebElement cssSubmitBtnElem = driver.findElement(By.cssSelector("body > input"));
			highlightElement(cssSubmitBtnElem);
			Thread.sleep(2000);
			cssSubmitBtnElem.click();
			Thread.sleep(2000);

			WebElement WidgetAnchorElem = driver.findElement(By.cssSelector("body > div > a"));
			highlightElement(WidgetAnchorElem);
			Thread.sleep(2000);
			WidgetAnchorElem.click();
			Thread.sleep(2000);

			driver.switchTo().parentFrame();
			System.out.println("Leaving out of iframe");
			driver.navigate().back();
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 10, enabled = true)
	public void Checkboxradio() {
		try {
			driver.get("https://jqueryui.com/");
			System.out.println("Title" + driver.getTitle());
			Thread.sleep(2000);

			scrollUpDown(300);
			WebElement Checkboxradio = driver
					.findElement(By.cssSelector("#sidebar > aside:nth-child(2) > ul > li:nth-child(4) > a"));
			Checkboxradio.click();
			System.out.println("Fine the Checkboxradio element and click it");
			Thread.sleep(2000);

			System.out.println("Page synch ---> waiting for 'Checkboxradio' page is visible.");
			WebDriverWait waitTo = new WebDriverWait(driver, 10);
			waitTo.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > iframe")));
			assertNotNull(Checkboxradio, "Synchronizing for Checkboxradio page not visible, test timed out!");
			Thread.sleep(2000);

			scrollUpDown(200);
			driver.switchTo().frame(0);

			WebElement RadioGroupArea = driver.findElement(By.cssSelector("body > div > fieldset:nth-child(3)"));
			highlightElement(RadioGroupArea);
			System.out.println("Highlight Radio group select location field");
			Thread.sleep(2000);

			List<WebElement> RadioGroupN = driver.findElements(By.cssSelector("span.ui-checkboxradio-icon-space"));
			WebElement NewYork = RadioGroupN.get(0);
			highlightElement(NewYork);
			NewYork.click();
			System.out.println("Click radio button of New York");
			Thread.sleep(2000);

			List<WebElement> RadioGroupL = driver.findElements(By.cssSelector("span.ui-checkboxradio-icon-space"));
			WebElement London = RadioGroupL.get(2);
			highlightElement(London);
			London.click();
			System.out.println("Click radio button of London");
			Thread.sleep(2000);

			List<WebElement> RadioGroupP = driver.findElements(By.cssSelector("span.ui-checkboxradio-icon-space"));
			WebElement Paris = RadioGroupP.get(1);
			highlightElement(Paris);
			Paris.click();
			System.out.println("Click radio button of Paris");
			Thread.sleep(2000);

			WebElement CheckboxArea = driver.findElement(By.cssSelector("body > div > fieldset:nth-child(5)"));
			highlightElement(CheckboxArea);
			System.out.println("Highlight Checkbox Hotel Ratings Field");
			Thread.sleep(2000);

			List<WebElement> HT2Star = driver
					.findElements(By.cssSelector("body > div > fieldset:nth-child(5) > label:nth-child(2)"));
			WebElement TwoStar = HT2Star.get(0);
			highlightElement(TwoStar);
			TwoStar.click();
			System.out.println("Checked the Box of 2 Star");
			Thread.sleep(2000);

			List<WebElement> HT2StarOff = driver
					.findElements(By.cssSelector("body > div > fieldset:nth-child(5) > label:nth-child(2)"));
			WebElement TwoStarUnCheck = HT2StarOff.get(0);
			highlightElement(TwoStarUnCheck);
			TwoStarUnCheck.click();
			System.out.println("UnChecked the Box of 2 Star");
			Thread.sleep(2000);

			List<WebElement> HT3Star = driver
					.findElements(By.cssSelector("body > div > fieldset:nth-child(5) > label:nth-child(4)"));
			WebElement ThreeStar = HT3Star.get(0);
			highlightElement(ThreeStar);
			ThreeStar.click();
			System.out.println("Checked the Box of 3 Star");
			Thread.sleep(2000);

			List<WebElement> HT4Star = driver
					.findElements(By.cssSelector("body > div > fieldset:nth-child(5) > label:nth-child(6)"));
			WebElement FourStar = HT4Star.get(0);
			highlightElement(FourStar);
			FourStar.click();
			System.out.println("Checked the Box of 4 Star");
			Thread.sleep(2000);

			List<WebElement> HT4StarOff = driver
					.findElements(By.cssSelector("body > div > fieldset:nth-child(5) > label:nth-child(6)"));
			WebElement FourStarUnCheck = HT4StarOff.get(0);
			highlightElement(FourStarUnCheck);
			FourStarUnCheck.click();
			System.out.println("UnChecked the Box of 4 Star");
			Thread.sleep(2000);

			List<WebElement> HT5Star = driver
					.findElements(By.cssSelector("body > div > fieldset:nth-child(5) > label:nth-child(8)"));
			WebElement FiveStar = HT5Star.get(0);
			highlightElement(FiveStar);
			FiveStar.click();
			System.out.println("Checked the Box of 5 Star");
			Thread.sleep(2000);

			scrollUpDown(200);
			Thread.sleep(2000);

			WebElement CheckboxNestedInLabel = driver.findElement(By.cssSelector("body > div > fieldset:nth-child(7)"));
			highlightElement(CheckboxNestedInLabel);
			System.out.println("Highlight Checkbox nested in label Field");
			Thread.sleep(2000);

			List<WebElement> BedTypeD = driver
					.findElements(By.cssSelector("body > div > fieldset:nth-child(7) > label:nth-child(2)"));
			WebElement Double2 = BedTypeD.get(0);
			highlightElement(Double2);
			Double2.click();
			System.out.println("Checked Bed Type 2 Double");
			Thread.sleep(2000);

			List<WebElement> BedTypeDOff = driver
					.findElements(By.cssSelector("body > div > fieldset:nth-child(7) > label:nth-child(2)"));
			WebElement Double2UnCheck = BedTypeDOff.get(0);
			highlightElement(Double2UnCheck);
			Double2UnCheck.click();
			System.out.println("Unchecked Bed Type 2 Double");
			Thread.sleep(2000);

			List<WebElement> BedTypeQ1 = driver
					.findElements(By.cssSelector("body > div > fieldset:nth-child(7) > label:nth-child(4)"));
			WebElement Queen1 = BedTypeQ1.get(0);
			highlightElement(Queen1);
			Queen1.click();
			System.out.println("Checked Bed Type 1 Queen");
			Thread.sleep(2000);

			List<WebElement> BedTypeK1 = driver
					.findElements(By.cssSelector("body > div > fieldset:nth-child(7) > label:nth-child(5)"));
			WebElement King1 = BedTypeK1.get(0);
			highlightElement(King1);
			King1.click();
			System.out.println("Checked Bed Type 1 King");
			Thread.sleep(2000);

			List<WebElement> BedTypeK1Off = driver
					.findElements(By.cssSelector("body > div > fieldset:nth-child(7) > label:nth-child(5)"));
			WebElement King1UnCheck = BedTypeK1Off.get(0);
			highlightElement(King1UnCheck);
			King1UnCheck.click();
			System.out.println("UnChecked Bed Type 1 King");
			Thread.sleep(2000);

			driver.switchTo().parentFrame();
			System.out.println("Leaving out of iframe");
			driver.navigate().back();
			Thread.sleep(2000);

			List<WebElement> BedTypeQ2 = driver
					.findElements(By.cssSelector("body > div > fieldset:nth-child(7) > label:nth-child(3)"));
			WebElement Queen2 = BedTypeQ2.get(0);
			highlightElement(Queen2);
			Queen2.click();
			System.out.println("Checked Bed Type 2 Queen");
			Thread.sleep(2000);

			List<WebElement> BedTypeQ2Off = driver
					.findElements(By.cssSelector("body > div > fieldset:nth-child(7) > label:nth-child(3)"));
			WebElement Queen2UnCheck = BedTypeQ2Off.get(0);
			highlightElement(Queen2UnCheck);
			Queen2UnCheck.click();
			System.out.println("UnChecked Bed Type 2 Queen");
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 11, enabled = true)
	public void Controlgroup() {
		try {
			driver.get("https://jqueryui.com/");
			System.out.println("Title" + driver.getTitle());
			Thread.sleep(2000);

			scrollUpDown(300);
			WebElement Controlgroup = driver
					.findElement(By.cssSelector("#sidebar > aside:nth-child(2) > ul > li:nth-child(5) > a"));
			Controlgroup.click();
			Thread.sleep(2000);

			System.out.println("Page synch ---> waiting for 'Autocomplete' page is visible.");
			WebDriverWait waitTo = new WebDriverWait(driver, 10);
			waitTo.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > iframe")));
			assertNotNull(Controlgroup, "Synchronizing for Controlgroup page not visible, test timed out!");
			Thread.sleep(2000);

			scrollUpDown(200);
			driver.switchTo().frame(0);

			WebElement HorizontalRentCar = driver
					.findElement(By.cssSelector("body > div.widget > fieldset:nth-child(2)"));
			highlightElement(HorizontalRentCar);
			System.out.println("Highlight Rental Car Horizontal Field");

			WebElement CarTypeDropDown = driver.findElement(By.id("car-type"));
			Select intDropDown = new Select(CarTypeDropDown);
			intDropDown.selectByIndex(3);
			System.out.println("Drop down and select SUV");
			Thread.sleep(2000);

			WebElement StandardTransmit = driver.findElement(By.id("transmission-standard"));
			StandardTransmit.click();
			System.out.println("Click to check Standard option");
			Thread.sleep(2000);

			WebElement InsuranceBtn = driver.findElement(By.id("insurance"));
			InsuranceBtn.click();
			System.out.println("Click Insurance button to choose");
			Thread.sleep(2000);

			WebElement AutoTransmit = driver.findElement(By.id("transmission-automatic"));
			AutoTransmit.click();
			System.out.println("Click to check Automatic option");
			Thread.sleep(2000);

			WebElement NumOfCars = driver.findElement(By.id("horizontal-spinner"));
			NumOfCars.sendKeys("2");
			System.out.println("Input the number of cars you need to book.");
			Thread.sleep(2000);

			WebElement VirticalRentCarField = driver
					.findElement(By.cssSelector("body > div.widget > fieldset:nth-child(4)"));
			highlightElement(VirticalRentCarField);
			System.out.println("Highlight Rental Car Virtical Field");
			Thread.sleep(3000);

//			WebElement CarTypeDropDownElem = driver.findElement(By.id("ui-id-8-button"));
//			Select intDropDownOption = new Select(CarTypeDropDownElem); 
//			intDropDownOption.selectByIndex(6);
//			System.out.println("Drop down and select VAN");
//			Thread.sleep(2000);

			WebElement BookNowInH = driver.findElement(By.cssSelector("body > div.widget > fieldset:nth-child(2)"));
			BookNowInH.click();
			System.out.println("Click (Horizontally) Book Now!");
			Thread.sleep(2000);

			WebElement StandardInV = driver.findElement(By.id("transmission-standard-v"));
			StandardInV.click();
			System.out.println("Click to check Virtical Standard option");
			Thread.sleep(2000);

			WebElement InsuranceInV = driver.findElement(By.id("insurance-v"));
			InsuranceInV.click();
			System.out.println("Click Insurance button in Virtical to choose");
			Thread.sleep(2000);

			WebElement AutomaticInV = driver.findElement(By.id("transmission-automatic-v"));
			AutomaticInV.click();
			System.out.println("Click to check Virtical Automatic option");
			Thread.sleep(2000);

			WebElement NumOfCarsInV = driver.findElement(By.id("vertical-spinner"));
			NumOfCarsInV.sendKeys("1");
			System.out.println("Input Virtically the number of cars you need to book.");
			Thread.sleep(2000);

			WebElement BookNowInV = driver.findElement(By.id("book"));
			BookNowInV.click();
			System.out.println("Click (Virtically) Book Now!");
			Thread.sleep(2000);

			driver.switchTo().parentFrame();
			System.out.println("Leaving out of iframe");
			driver.navigate().back();
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 12, enabled = true)
	public void Datepicker() {
		try {
			driver.get("https://jqueryui.com/");
			System.out.println("Title" + driver.getTitle());
			Thread.sleep(2000);

			scrollUpDown(300);

			WebElement Datepicker = driver
					.findElement(By.cssSelector("#sidebar > aside:nth-child(2) > ul > li:nth-child(6) > a"));
			highlightElement(Datepicker);
			System.out.println("Highlight the Datepicker link");
			Thread.sleep(3000);
			Datepicker.click();
			Thread.sleep(2000);

			System.out.println("Page synch --> waiting for 'Datepicker' page is visible.");
			WebDriverWait waitTo = new WebDriverWait(driver, 10);
			waitTo.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > iframe")));
			assertNotNull(Datepicker, "Synchronizing for Datepicker page not visible, test timed out!");
			Thread.sleep(2000);

			driver.switchTo().frame(0);

			WebElement PickDate = driver.findElement(By.id("datepicker"));
			PickDate.clear();
			PickDate.click();
			PickDate.sendKeys("12/24/2020");
			PickDate.sendKeys(Keys.ENTER);

			driver.switchTo().parentFrame();
			System.out.println("Leaving out of iframe");
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 13, enabled = true)
	public void Dialog() {
		try {
			driver.get("https://jqueryui.com/");
			System.out.println("Title" + driver.getTitle());
			Thread.sleep(2000);

			scrollUpDown(300);
			WebElement Dialog = driver
					.findElement(By.cssSelector("#sidebar > aside:nth-child(2) > ul > li:nth-child(7) > a"));
			Dialog.click();
			Thread.sleep(2000);

			System.out.println("Page synch ---> waiting for 'Dialog' page is visible.");
			WebDriverWait waitTo = new WebDriverWait(driver, 10);
			waitTo.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > iframe")));
			assertNotNull(Dialog, "Synchronizing for Dialog page not visible, test timed out!");

			WebElement DialogField = driver.findElement(By.cssSelector("#content > iframe"));
			highlightElement(DialogField);
			System.out.println("Highlight the Basic Dialog window.");
			Thread.sleep(2000);
			
			driver.switchTo().frame(0);
		
			WebElement ResizeToSW = driver.findElement(By.xpath("//*[@id='ui-id-1']"));
			Actions action = new Actions(driver);
			action.clickAndHold(ResizeToSW).moveByOffset(14, 18).build().perform();
			action.release();
			System.out.println("Resize the window to south west direction.");
			Thread.sleep(2000);

			WebElement DragDialogWindow = driver.findElement(By.cssSelector("body > div"));
			Actions actionDrag = new Actions(driver);
			actionDrag.dragAndDropBy(DragDialogWindow, 25, 10).build().perform();
			System.out.println("Move the Dialog window around.");
			Thread.sleep(3000);
			
			WebElement CloseX = driver.findElement(By.cssSelector("body > div > div.ui-dialog-titlebar.ui-corner-all > button"));
			CloseX.click();                                      
			System.out.println("Click x button to close the dialog window.");
			Thread.sleep(2000);

			driver.switchTo().parentFrame();
			System.out.println("Leaving out of iframe");
			driver.navigate().back();
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 14, enabled = true)
	public void Menu() {
		try {
			driver.get("https://jqueryui.com/");
			System.out.println("Title" + driver.getTitle());
			Thread.sleep(2000);

			scrollUpDown(400);
			WebElement Menu = driver    
					.findElement(By.cssSelector("#sidebar > aside:nth-child(2) > ul > li:nth-child(8) > a"));
			Menu.click();
			Thread.sleep(2000);

			System.out.println("Page synch ---> waiting for 'Menu' page is visible.");
			WebDriverWait waitTo = new WebDriverWait(driver, 10);
			waitTo.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > iframe"))); 
			assertNotNull(Menu, "Synchronizing for Menu page not visible, test timed out!");
			Thread.sleep(2000);

			driver.switchTo().frame(0);

			WebElement BooksElem = driver.findElement(By.id("ui-id-2"));
			WebElement ClothingElem = driver.findElement(By.id("ui-id-3"));
			WebElement ElectronicsElem = driver.findElement(By.id("ui-id-4"));
			WebElement ElectronicsCarHifi = driver.findElement(By.id("ui-id-6"));
			WebElement ElectronicsUtilities = driver.findElement(By.id("ui-id-7"));
			
			WebElement Movies = driver.findElement(By.id("ui-id-8"));
			WebElement Music = driver.findElement(By.id("ui-id-9"));
			WebElement Rock = driver.findElement(By.id("ui-id-10"));
			WebElement Alternative = driver.findElement(By.id("ui-id-11"));
			WebElement Classic = driver.findElement(By.id("ui-id-12"));
			WebElement Jazz = driver.findElement(By.id("ui-id-13"));
			WebElement Freejazz = driver.findElement(By.id("ui-id-14"));
			WebElement BigBand = driver.findElement(By.id("ui-id-15"));
			WebElement Modern = driver.findElement(By.id("ui-id-16"));
			WebElement Pop = driver.findElement(By.id("ui-id-17"));
			
			Actions HoverOver = new Actions(driver);
			HoverOver.moveToElement(BooksElem).build().perform();
			HoverOver.moveToElement(ClothingElem).build().perform();
			Thread.sleep(2000);
			
			HoverOver.moveToElement(ElectronicsElem).build().perform();
			HoverOver.moveToElement(ElectronicsCarHifi).build().perform();
			HoverOver.moveToElement(ElectronicsUtilities).build().perform();
			Thread.sleep(2000);
			
			HoverOver.moveToElement(Movies).build().perform();
			
			HoverOver.moveToElement(Music).build().perform();
			HoverOver.moveToElement(Rock).build().perform();
			HoverOver.moveToElement(Alternative).build().perform();
			HoverOver.moveToElement(Classic).build().perform();
			Thread.sleep(2000);
			
			HoverOver.moveToElement(Jazz).build().perform();
			HoverOver.moveToElement(Freejazz).build().perform();
			HoverOver.moveToElement(BigBand).build().perform();
			HoverOver.moveToElement(Modern).build().perform();
			Thread.sleep(2000);
			
			HoverOver.moveToElement(Pop).build().perform();
			
			
			System.out.println("Clicking Books on Menu.");
			Thread.sleep(2000);
			
//			WebElement ClothingElem = driver.findElement(By.id("ui-id-3"));
//			ClothingElem.click();
//			System.out.println("Clicking Clothing on Menu.");
//			Thread.sleep(2000);
//			
//			WebElement ElectronicsElem = driver.findElement(By.id("ui-id-4"));
//			ElectronicsElem.click();
//			System.out.println("Clicking Electronics on Menu.");
//			Thread.sleep(2000);
//			
//			WebElement ElectronicsCarHifi = driver.findElement(By.id("ui-id-6"));
//			ElectronicsCarHifi.click();
//			System.out.println("Clicking Car Hifi inside the Electronics option on Menu.");
//			Thread.sleep(2000);
//			
//			WebElement ElectronicsUtilities = driver.findElement(By.id("ui-id-7"));
//			Actions MoveTo = new Actions(driver);
//			MoveTo.click(ElectronicsUtilities);
//			System.out.println("Clicking Utilities inside the Electronics option on Menu.");
//			Thread.sleep(2000);
//			
//			Actions moveBack = new Actions(driver);
//			moveBack.moveToElement(ElectronicsElem).build().perform();
//			moveBack.click(ElectronicsElem).build().perform();
//			Thread.sleep(2000);
//			
//			WebElement Movies = driver.findElement(By.id("ui-id-8"));
//			Movies.click();
//			System.out.println("Clicking Movies option on Menu.");
//			Thread.sleep(2000);
//			
//			WebElement Music = driver.findElement(By.id("ui-id-9"));
//			Music.click();
//			System.out.println("Clicking Music option on Menu.");
//			Thread.sleep(2000);
//			
//			WebElement Rock = driver.findElement(By.id("ui-id-10"));
//			Rock.click();
//			System.out.println("Clicking Rock inside the Music option on Menu.");
//			Thread.sleep(2000);
//			
//			WebElement Alternative = driver.findElement(By.id("ui-id-11"));
//			Alternative.click();
//			System.out.println("Clicking Alternative inside the Rock Music option on Menu.");
//			Thread.sleep(2000);
//			
//			WebElement Classic = driver.findElement(By.id("ui-id-12"));
//			Classic.click();
//			System.out.println("Clicking Classic inside the Rock Music option on Menu.");
//			Thread.sleep(2000);
//			
//			WebElement Classic = driver.findElement(By.id("ui-id-12"));
//			WebElement Jazz = driver.findElement(By.id("ui-id-13"));
//			System.out.println("Clicking Jazz inside the Music option on Menu.");
//			Thread.sleep(2000);
//			
//			WebElement Classic = driver.findElement(By.id("ui-id-12"));
//			WebElement Jazz = driver.findElement(By.id("ui-id-13"));
//			WebElement Freejazz = driver.findElement(By.id("ui-id-14"));
//			WebElement BigBand = driver.findElement(By.id("ui-id-15"));
//			System.out.println("Clicking Freejazz inside the Jazz Music option on Menu.");
//			Thread.sleep(2000);
//			
//			WebElement BigBand = driver.findElement(By.id("ui-id-15"));
//			System.out.println("Clicking BigBand inside the Jazz Music option on Menu.");
//			Thread.sleep(2000);
			
			Actions moveBackTo = new Actions(driver);
			moveBackTo.moveToElement(Rock).build().perform();
			moveBackTo.moveToElement(Freejazz).build().perform();
			moveBackTo.moveToElement(BigBand).build().perform();
			Thread.sleep(2000);
			
			//WebElement Jazz = driver.findElement(By.id("ui-id-13"));
			//Jazz.click();
			
			
			//WebElement Freejazz = driver.findElement(By.id("ui-id-14"));
			//Freejazz.click();
			
			
			//WebElement BigBand = driver.findElement(By.id("ui-id-15"));
			//BigBand.click();
			
			
//			WebElement Modern = driver.findElement(By.id("ui-id-16"));
//			WebElement Pop = driver.findElement(By.id("ui-id-17"));
//			Modern.click();
//			System.out.println("Clicking Modern inside the Jazz Music option on Menu.");
//			Thread.sleep(2000);
//			
//			Actions moveToPop = new Actions(driver);
//			moveToPop.moveToElement(Jazz).build().perform();
//			Thread.sleep(2000);
//			
//			WebElement Pop = driver.findElement(By.id("ui-id-17"));
//			Pop.click();
//			System.out.println("Clicking Pop inside the Music option on Menu.");
//			Thread.sleep(2000);
			
			driver.switchTo().parentFrame();
			System.out.println("Leaving out of iframe");
			driver.navigate().back();
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 15, enabled = true)
	public void Selectmenu() {
		try {
			driver.get("https://jqueryui.com/");
			System.out.println("Title" + driver.getTitle());
			Thread.sleep(2000);

			scrollUpDown(450);
			WebElement Selectmenu = driver
					.findElement(By.cssSelector("#sidebar > aside:nth-child(2) > ul > li:nth-child(10) > a"));
			Selectmenu.click();                 
			Thread.sleep(2000);

			System.out.println("Page synch ---> waiting for 'Selectmenu' page is visible.");
			WebDriverWait waitTo = new WebDriverWait(driver, 10);                         
			waitTo.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > iframe"))); 
			assertNotNull(Selectmenu, "Synchronizing for Selectmenu page not visible, test timed out!");
			highlightElement(Selectmenu);
			Thread.sleep(2000);

			driver.switchTo().frame(0); 

			WebElement SelectSpeed = driver.findElement(By.cssSelector("body > div.demo > form > fieldset > label:nth-child(1)"));
			SelectSpeed.click();
			
			//WebElement FastElem = driver.findElement(By.id("ui-id-4"));
			WebElement FastElem = driver.findElement(By.cssSelector("#ui-id-4"));
			Actions moveToFastElem = new Actions(driver);
			moveToFastElem.moveToElement(FastElem).click().build().perform();
			System.out.println("Click and choose Fast speed.");
			Thread.sleep(2000);
			
			WebElement SelectFile = driver.findElement(By.cssSelector("body > div.demo > form > fieldset > label:nth-child(4)"));
			SelectFile.click();
			
			WebElement uiJqueryJs = driver.findElement(By.xpath("//*[@id='ui-id-7']"));
			Actions fileDropDown = new Actions(driver);
			fileDropDown.moveToElement(uiJqueryJs);
			Thread.sleep(2000);
			
			driver.switchTo().parentFrame();
			System.out.println("Leaving out of iframe");
			driver.navigate().back();
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 16, enabled = true)
	public void Slider() {
		try {
			driver.get("https://jqueryui.com/");
			System.out.println("Title" + driver.getTitle());
			Thread.sleep(2000);

			scrollUpDown(450);
			WebElement Slider = driver
					.findElement(By.cssSelector("#sidebar > aside:nth-child(2) > ul > li:nth-child(11) > a"));
			Slider.click();                      
			Thread.sleep(2000);

//			System.out.println("Page synch ---> waiting for 'Slider' page is visible.");
//			WebDriverWait waitTo = new WebDriverWait(driver, 10);
//			waitTo.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > iframe")));
//			assertNotNull(Slider, "Synchronizing for Slider page not visible, test timed out!");
//			highlightElement(Slider);
//			Thread.sleep(2000);

			driver.switchTo().frame(0);
			
			WebElement SliderElem = driver.findElement(By.id("slider"));
			highlightElement(SliderElem);
			Actions MoveSlider = new Actions(driver);
			MoveSlider.dragAndDropBy(SliderElem, 0, 39).build().perform();
			System.out.println("Drag the slider to the right bit.");
			Thread.sleep(2000);
			MoveSlider.dragAndDropBy(SliderElem, 39, 81).build().perform();
			MoveSlider.release();
			System.out.println("Drag the slider to the right more.");
			Thread.sleep(2000);
			
			driver.switchTo().parentFrame();
			System.out.println("Leaving out of iframe");
			driver.navigate().back();
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 17, enabled = true)
	public void Spinner() {
		try {
			driver.get("https://jqueryui.com/");
			System.out.println("Title" + driver.getTitle());
			Thread.sleep(2000);

			scrollUpDown(500);
			WebElement Spinner = driver
					.findElement(By.cssSelector("#sidebar > aside:nth-child(2) > ul > li:nth-child(12) > a"));
			Spinner.click();                
			Thread.sleep(2000);

			System.out.println("Page synch ---> waiting for 'Spinner' page is visible.");
			WebDriverWait waitTo = new WebDriverWait(driver, 10);
			waitTo.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > iframe"))); 
			assertNotNull(Spinner, "Synchronizing for Spinner page not visible, test timed out!");
			Thread.sleep(2000);

			driver.switchTo().frame(0);
			
			WebElement SelectValue = driver.findElement(By.id("spinner"));
			SelectValue.clear();
			SelectValue.sendKeys("14");
			System.out.println("Select a value of 14.");
			Thread.sleep(2000);
			
			WebElement disableBtn = driver.findElement(By.cssSelector("#disable"));
			disableBtn.click();
			System.out.println("Click Toggle disable/enable button.");
			Thread.sleep(2000);
			
			WebElement destroyBtn = driver.findElement(By.id("destroy"));
			destroyBtn.click();
			System.out.println("Click Toggle widget button.");
			Thread.sleep(2000);
			
			WebElement getvalueBtn = driver.findElement(By.id("getvalue"));
			getvalueBtn.click();
			System.out.println("Click Get Value button.");
			Thread.sleep(2000);
			
			WebElement setvalueBtn = driver.findElement(By.id("setvalue"));
			setvalueBtn.click();
			System.out.println("Click Set value to 5 button.");
			Thread.sleep(2000);
			
			driver.switchTo().parentFrame();
			System.out.println("Leaving out of iframe");
			driver.navigate().back();
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 18, enabled = true)
	public void Tabs() {
		try {
			driver.get("https://jqueryui.com/");
			System.out.println("Title" + driver.getTitle());
			Thread.sleep(2000);

			scrollUpDown(500);
			WebElement Tabs = driver
					.findElement(By.cssSelector("#sidebar > aside:nth-child(2) > ul > li:nth-child(13) > a"));
			Tabs.click();      
			Thread.sleep(2000);

			System.out.println("Page synch ---> waiting for 'Tabs' page is visible.");
			WebDriverWait waitTo = new WebDriverWait(driver, 10);
			waitTo.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > iframe")));
			assertNotNull(Tabs, "Synchronizing for Tabs page not visible, test timed out!");
			Thread.sleep(2000);

			driver.switchTo().frame(0);
			
			WebElement NuncTincidunt = driver.findElement(By.cssSelector("#ui-id-1"));
			NuncTincidunt.click();
			System.out.println("Click on Nunc tincidunt");
			Thread.sleep(2000);
			
			WebElement ProinDolor = driver.findElement(By.cssSelector("#ui-id-2"));
			ProinDolor.click();
			System.out.println("Click on Proin dolor");
			Thread.sleep(2000);

			WebElement AeneanLacinia = driver.findElement(By.cssSelector("#ui-id-3"));
			AeneanLacinia.click();
			System.out.println("Click on Aenean lacinia");
			Thread.sleep(2000);
			
			driver.switchTo().parentFrame();
			System.out.println("Leaving out of iframe");
			driver.navigate().back();
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test(priority = 19, enabled = true)
	public void Tooltip() {
		try {
			driver.get("https://jqueryui.com/");
			System.out.println("Title" + driver.getTitle());
			Thread.sleep(2000);

			scrollUpDown(500);
			WebElement Tooltip = driver
					.findElement(By.cssSelector("#sidebar > aside:nth-child(2) > ul > li:nth-child(14) > a"));
			Tooltip.click();                     
			Thread.sleep(2000);

			System.out.println("Page synch ---> waiting for 'Tooltip' page is visible.");
			WebDriverWait waitTo = new WebDriverWait(driver, 10);
			waitTo.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#content > iframe")));
			assertNotNull(Tooltip, "Synchronizing for Tooltip page not visible, test timed out!");
			Thread.sleep(2000);                 

			driver.switchTo().frame(0);

			WebElement ToolTipsWidget = driver.findElement(By.cssSelector("body > p:nth-child(1) > a"));
			WebElement ThemeRoller = driver.findElement(By.cssSelector("body > p:nth-child(2) > a"));
			Actions HoverOver = new Actions(driver);
			HoverOver.moveToElement(ToolTipsWidget).build().perform();
			System.out.println("Hover Over to element Tooltips.");
			
			HoverOver.moveToElement(ThemeRoller).build().perform();
			System.out.println("Hover Over to element ThemeRoller.");
			Thread.sleep(2000); 
			
     		WebElement ageInput = driver.findElement(By.id("age"));
			ageInput.sendKeys("28");
			System.out.println("Input age of 28");
			Thread.sleep(2000); 
			
			driver.switchTo().parentFrame();
			System.out.println("Leaving out of iframe");
			driver.navigate().back();
			Thread.sleep(2000);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void scrollUpDown(int pixels) {
		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("scroll(0," + pixels + ")");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void highlightElement(WebElement HiLiteElem) {
		try {
			for (int i = 0; i < 4; i++) {
				JavascriptExecutor js = (JavascriptExecutor) driver;
				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", HiLiteElem,
						"color: blue; border: 4px solid yellow");

				js.executeScript("arguments[0].setAttribute('style', arguments[1]);", HiLiteElem, "");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
