import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import javax.swing.text.html.CSS;

import org.checkerframework.checker.units.qual.s;
import org.jspecify.annotations.Nullable;
import org.openqa.selenium.By;
import org.openqa.selenium.By.ByCssSelector;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class myTestCase extends parameters {

	@BeforeTest
	public void mySetup() {

		driver.get("https://magento.softwaretestingboard.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}

	@Test(priority = 1, enabled = false)
	public void createAnAccount() {

		System.out.println("************ First Test : createAnAccount ************");

		WebElement accountCreation = driver.findElement(By.partialLinkText("Create an Accou"));
		accountCreation.click();
	}

	@Test(priority = 2, enabled = false)
	public void registerInfo() {

		System.out.println("************ second Test : registerInfo ************");

		WebElement firstName = driver.findElement(By.id("firstname"));
		firstName.sendKeys(firstRandomName);

		WebElement lastName = driver.findElement(By.id("lastname"));
		lastName.sendKeys(lastRandomName);

		WebElement email_address = driver.findElement(By.id("email_address"));
		email_address.sendKeys(firstRandomName + lastRandomName + "@gmail.com");

		WebElement password = driver.findElement(By.id("password"));
		password.sendKeys("1234HGrtr#@_1E");

		WebElement passConf = driver.findElement(By.id("password-confirmation"));
		passConf.sendKeys("1234HGrtr#@_1E");

		WebElement createButton = driver.findElement(By.cssSelector("button[title='Create an Account']"));
		createButton.click();

		WebElement MessageAsWebElement = driver.findElement(By.cssSelector(".message-success.success.message"));
		List<WebElement> text = MessageAsWebElement
				.findElements(By.cssSelector("div[data-bind='html: $parent.prepareMessageForHtml(message.text)']"));

		String TheActualMessage = text.getLast().getText();

		String ExpectedMessage = "Thank you for registering with Main Website Store.";

		Assert.assertEquals(TheActualMessage, ExpectedMessage);
	}

	@Test(priority = 3, enabled = false)
	public void sigOut() throws InterruptedException {

		System.out.println("************ third Test : sigOut ************");

		WebElement arrow = driver.findElement(By.cssSelector("div[class='panel header'] button[type='button']"));
		arrow.click();

		WebElement menu = driver.findElement(By.cssSelector("div[aria-hidden='false'] ul[class='header links']"));
		List<WebElement> menuList = menu.findElements(By.tagName("li"));

		WebElement out = menuList.get(2);
		out.click();

		String expectedValue = "You are signed out";
		String actualValue = driver.findElement(By.className("base")).getText();
		Assert.assertEquals(actualValue, expectedValue);
		System.out.println("confirm : " + actualValue);

	}

	@Test(priority = 4, enabled = false)
	public void signIn() throws InterruptedException {

		System.out.println("************ forth Test : signIn ************");

		WebElement LoginPage = driver.findElement(By.linkText("Sign In"));
		LoginPage.click();

		WebElement EmailLoginInput = driver.findElement(By.id("email"));
		WebElement passwordInput = driver.findElement(By.id("pass"));
		WebElement LoginButton = driver.findElement(By.cssSelector(".action.login.primary"));

		EmailLoginInput.sendKeys(firstRandomName + lastRandomName + "@gmail.com");
		passwordInput.sendKeys("1234HGrtr#@_1E");
		LoginButton.click();

	}

	@Test(priority = 5, enabled = false)
	public void women() throws InterruptedException {

		Thread.sleep(5000);

		System.out.println("****** fifth Test : women *******");

		// Locating the Main Menu (Parent element)
		WebElement womenMenu = driver.findElement(By.id("ui-id-4"));

		// Instantiating Actions class
		Actions actions = new Actions(driver);

		// Hovering on main menu
		actions.moveToElement(womenMenu);

		// Locating the element from Sub Menu
		WebElement topsMenu = driver.findElement(By.id("ui-id-9"));

		// To mouseover on sub menu
		actions.moveToElement(topsMenu);

		// Locating the element from Sub Menu
		WebElement teesMenu = driver.findElement(By.id("ui-id-13"));

		// To mouseover on sub menu
		actions.moveToElement(teesMenu);

		// build()- used to compile all the actions into a single step
		actions.click().build().perform();

		WebElement teesContainer = driver.findElement(By.cssSelector(".products.wrapper.grid.products-grid"));
		List<WebElement> teesContainerList = teesContainer.findElements(By.tagName("li"));

		for (int i = 0; i < 3; i++) {

			WebElement size = teesContainerList.get(i)
					.findElement(By.cssSelector(".swatch-attribute-options.clearfix"));
			List<WebElement> sizeList = size.findElements(By.tagName("div"));

			int numberOfSize = sizeList.size();
			int randomSize = rand.nextInt(numberOfSize);

			sizeList.get(randomSize).click();

			WebElement outsideColor = teesContainerList.get(i).findElement(By.cssSelector(".swatch-attribute.color"));
			WebElement color = outsideColor.findElement(By.cssSelector(".swatch-attribute-options.clearfix"));
			List<WebElement> colorList = color.findElements(By.tagName("div"));

			int numberOfColor = colorList.size();
			int randomColor = rand.nextInt(numberOfColor);

			colorList.get(randomColor).click();

			System.out.println("Clicked on color");

			WebElement button = teesContainerList.get(i).findElement(By.cssSelector(".actions-primary"));

			button.click();

			System.out.println("Clicked on women button");

			System.out.println("The random size for item " + (i + 1) + " is : " + sizeList.get(randomSize).getText()
					+ ", and the random color is : " + colorList.get(randomColor).getAttribute("aria-label"));

		}

		Thread.sleep(5000);

		driver.findElement(By.cssSelector(".action.showcart")).click();

		System.out.println("Clicked on cart");

		String actualItems = driver
				.findElement(By.cssSelector("span[data-bind=\"text: getCartParam('summary_count')\"]")).getText();

		String expectedItems = "3";

		System.out.println("The Number of items : " + actualItems);

		Assert.assertEquals(actualItems, expectedItems);

		String totalItemPrice = driver.findElement(By.cssSelector(".amount.price-container")).getText();

		totalItemPrice = totalItemPrice.toString().replace("$", "");

		System.out.println("Total price is : " + totalItemPrice);

		WebElement cart = driver.findElement(By.id("mini-cart"));
		List<WebElement> cartList = cart.findElements(By.tagName("li"));

		int cartItems = cartList.size();

		double totalPrice = 0.00;

		for (int i = 0; i < cartItems; i++) {

			String priceOfSingleItem = cartList.get(i).findElement(By.className("price")).getText();

			priceOfSingleItem = priceOfSingleItem.replace("$", "");

			double price = Double.parseDouble(priceOfSingleItem);
			totalPrice += price;

			System.out.println("The price of item " + (i + 1) + " is: " + price);
		}

		System.out.println("The total price of all items in the cart is: $" + totalPrice);

		String expectedPrice = "70.0";
		String actualPrice = Double.toString(totalPrice);

		Assert.assertEquals(actualPrice, expectedPrice);

	}

	@Test(priority = 6, enabled = false)
	public void men() throws InterruptedException {

		Thread.sleep(5000);

		System.out.println("****** sixth Test : men *******");

		WebElement men = driver.findElement(By.id("ui-id-5"));
		men.click();

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,1800)");

		WebElement menItem = driver.findElement(By.cssSelector(".product-items.widget-product-grid"));
		List<WebElement> menItemList = menItem.findElements(By.tagName("li"));

		int itemSize = menItemList.size();

		int randomItem = rand.nextInt(itemSize);

		System.out.println("The item is : " + (randomItem + 1));

		WebElement sizes = menItemList.get(randomItem)
				.findElement(By.cssSelector(".swatch-attribute-options.clearfix"));
		List<WebElement> sizesList = sizes.findElements(By.tagName("div"));

		sizesList.get(randomItem).click();

		System.out.println("The size is : " + sizesList.get(randomItem).getText());

		WebElement outsideColor = menItemList.get(randomItem).findElement(By.cssSelector(".swatch-attribute.color"));
		WebElement color = outsideColor.findElement(By.cssSelector(".swatch-attribute-options.clearfix"));
		List<WebElement> colorList = color.findElements(By.tagName("div"));

		int colorSize = colorList.size();
		int randomColor = rand.nextInt(colorSize);

		String colorName = colorList.get(randomColor).getAttribute("option-label");

		System.out.println("The color is " + colorName);

		colorList.get(randomColor).click();

		WebElement button = menItemList.get(randomItem).findElement(By.cssSelector(".action.tocart.primary"));

		button.click();

		WebElement cart = driver.findElement(By.id("mini-cart"));
		List<WebElement> cartList = cart.findElements(By.tagName("li"));
		System.out.println("cart size : " + cartList.size());

		WebElement checkCart = driver.findElement(By.cssSelector(".action.showcart"));
		checkCart.click();

		WebElement itemsInCart = driver.findElement(By.id("ui-id-1"));
		WebElement subTotal = itemsInCart.findElement(By.cssSelector(".price"));
		System.out.println("Subtotal is : " + subTotal.getText());

	}

	@Test(priority = 7, enabled = false)
	public void bags() throws InterruptedException {

		System.out.println("****** seventh Test : bags *******");

		// Locating the Main Menu (Parent element)
		WebElement mainMenu = driver.findElement(By.id("ui-id-6"));

		// Instantiating Actions class
		Actions actions = new Actions(driver);

		// Hovering on main menu
		actions.moveToElement(mainMenu);

		// Locating the element from Sub Menu
		WebElement subMenu = driver.findElement(By.id("ui-id-25"));

		// To mouseover on sub menu
		actions.moveToElement(subMenu);

		// build()- used to compile all the actions into a single step
		actions.click().build().perform();

		WebElement bagsContainer = driver.findElement(By.cssSelector(".products.list.items.product-items"));
		List<WebElement> bagsContainerList = bagsContainer.findElements(By.tagName("li"));

		System.out.println("Number of bags : " + bagsContainerList.size());

		int bagsQty = bagsContainerList.size();

		for (int i = 0; i < bagsQty; i++) {

			if (i % 2 == 0) {

				WebElement button = bagsContainerList.get(i).findElement(By.cssSelector(".action.tocart.primary"));
				((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);
			}
		}

		Thread.sleep(10000);

		WebElement cartMenu = driver.findElement(By.cssSelector(".action.showcart"));
		cartMenu.click();

		WebElement cartSubTotal = driver.findElement(By.id("ui-id-1"));

		WebElement checkout = cartSubTotal.findElement(By.id("top-cart-btn-checkout"));
		checkout.click();

		Thread.sleep(5000);

	}

	@Test(priority = 8, enabled = false)
	public void orderSummaryAddress() throws InterruptedException {

		Thread.sleep(5000);

		System.out.println("****** eighth Test : orderSummaryAddress *******");

		js.executeScript("window.scrollTo(0,300)");

		WebElement address = driver.findElement(By.cssSelector(".field.street.admin__control-fields.required"));
		WebElement innerAddress = address.findElement(By.className("control"));
		List<WebElement> innerAddressList = innerAddress.findElements(By.tagName("input"));

		String firstAddressID = innerAddressList.get(0).getAttribute("id");
		String secondAddressID = innerAddressList.get(1).getAttribute("id");
		String thirdAddressID = innerAddressList.get(2).getAttribute("id");

		WebElement firstAddress = innerAddress.findElement(By.id(firstAddressID));
		WebElement secondAddress = innerAddress.findElement(By.id(secondAddressID));
		WebElement thirdAddress = innerAddress.findElement(By.id(thirdAddressID));

		firstAddress.sendKeys("Austria");
		secondAddress.sendKeys("Carynthia");
		thirdAddress.sendKeys("Villach");

	}

	@Test(priority = 9, enabled = false)
	public void orderSummaryCity() throws InterruptedException {

		System.out.println("****** ninth Test : orderSummaryCity *******");

		WebElement city = driver.findElement(By.cssSelector("#co-shipping-form"))
				.findElement(By.cssSelector("div[name='shippingAddress.city']"));
		WebElement innerCity = city.findElement(By.className("control"));
		List<WebElement> innerCityList = innerCity.findElements(By.tagName("input"));

		String cityID = innerCityList.get(0).getAttribute("id");

		WebElement cityName = city.findElement(By.id(cityID));

		cityName.sendKeys("Amman");
	}

	@Test(priority = 10, enabled = false)
	public void orderSummaryZipCode() throws InterruptedException {

		System.out.println("****** 10th Test : orderSummaryZipCode *******");

		js.executeScript("window.scrollTo(0,500)");

		WebElement zipCode = driver.findElement(By.cssSelector("#co-shipping-form"))
				.findElement(By.cssSelector("div[name='shippingAddress.postcode']"));
		WebElement innerZipCode = zipCode.findElement(By.className("control"));
		List<WebElement> innerZipCodeList = innerZipCode.findElements(By.tagName("input"));

		String zipCodeID = innerZipCodeList.get(0).getAttribute("id");

		WebElement zipCodeNumber = zipCode.findElement(By.id(zipCodeID));

		zipCodeNumber.sendKeys("11181");
	}

	@Test(priority = 11, enabled = false)
	public void orderSummaryCountry() throws InterruptedException {

		System.out.println("****** 11th Test : orderSummaryCountry *******");

		js.executeScript("window.scrollTo(0,700)");

		WebElement country = driver.findElement(By.cssSelector("#co-shipping-form"))
				.findElement(By.cssSelector("div[name='shippingAddress.country_id']"));

		WebElement innerCountry = country.findElement(By.className("control"));
		WebElement innerCountryList = innerCountry.findElement(By.tagName("select"));

		List<WebElement> option = innerCountryList.findElements(By.tagName("option"));

		option.get(115).click();

		Thread.sleep(5000);
	}

	@Test(priority = 12, enabled = false)
	public void orderSummaryphoneNumber() throws InterruptedException {

		System.out.println("****** 12th Test : orderSummaryphoneNumber *******");

		WebElement phoneNumber = driver.findElement(By.cssSelector("#co-shipping-form"))
				.findElement(By.cssSelector("div[name='shippingAddress.telephone']"));

		WebElement innerPhoneNumber = phoneNumber.findElement(By.tagName("input"));
		String phoneID = innerPhoneNumber.getAttribute("id");

		WebElement telephoneNumber = phoneNumber.findElement(By.id(phoneID));
		telephoneNumber.sendKeys("0797047908");

		WebElement nextButton = driver.findElement(By.cssSelector(".button.action.continue.primary"));
		nextButton.click();

	}

	@Test(priority = 13, enabled = false)
	public void placeOrder() throws InterruptedException {

		Thread.sleep(5000);

		System.out.println("****** 13th Test : placeOrder *******");

		WebElement placeOrder = driver.findElement(By.id("maincontent"))
				.findElement(By.cssSelector("button[title='Place Order']"));

		placeOrder.click();

	}

	@Test(priority = 14, enabled = false)
	public void success() {

		System.out.println("****** 14th Test : success *******");

		String expectedSuccessMessage = driver.findElement(By.cssSelector(".checkout-success"))
				.findElement(By.cssSelector("p:nth-child(2)")).getText();
		String actualSuccessMessage = "We'll email you an order confirmation with details and tracking info.";

		Assert.assertEquals(actualSuccessMessage, expectedSuccessMessage);

	}

	@Test(priority = 15, enabled = false)
	public void subscribe() throws InterruptedException {

		js.executeScript("window.scrollTo(0,10000)");

		WebElement footer = driver.findElement(By.cssSelector("div[class='widget block block-static-block'] ul"));
		List<WebElement> links = footer.findElements(By.tagName("li"));

		WebElement subscribe = links.get(3).findElement(By.linkText("Subscribe"));
		subscribe.click();

	}

	@Test(priority = 16, enabled = false)
	public void subscription() throws InterruptedException {

		driver.get(
				"https://softwaretestingboard.com/subscribe/?utm_source=magento_store&utm_medium=banner&utm_campaign=notes_promo&utm_id=email_subscribe");

		WebElement email = driver.findElement(By.id("mce-EMAIL"));
		email.sendKeys(firstRandomName + lastRandomName + "@gmail.com");

		WebElement button = driver.findElement(By.id("mc-embedded-subscribe"));
		button.click();

		Thread.sleep(10000);

		String actualText = driver.findElement(By.id("mce-success-response")).getText();
		String expectedText = "Almost finished... We need to confirm your email address. To complete the subscription process, please click the link in the email we just sent you.";
		System.out.println(actualText);

		Assert.assertEquals(actualText, expectedText);
	}

	@Test(priority = 17, enabled = true)
	public void fitness() throws InterruptedException {

		// Locating the Main Menu (Parent element)
		WebElement gear = driver.findElement(By.id("ui-id-6"));

		// Instantiating Actions class
		Actions actions = new Actions(driver);

		// Hovering on main menu
		actions.moveToElement(gear);

		// Locating the element from Sub Menu
		WebElement fitness = driver.findElement(By.id("ui-id-26"));

		// To mouseover on sub menu
		actions.moveToElement(fitness);

		// build()- used to compile all the actions into a single step
		actions.click().build().perform();
	}

	@Test(priority = 18, enabled = true)
	public void select() throws InterruptedException {

		// Find the container that holds the products
		WebElement fitnessContainer = driver.findElement(By.cssSelector(".products.list.items.product-items"));
		List<WebElement> fitnessContainerList = fitnessContainer.findElements(By.tagName("li"));

		System.out.println("Number of gears: " + fitnessContainerList.size());

		// Ensure that we only try to add up to the number of available items
		int itemsToAdd = Math.min(3, fitnessContainerList.size());

		// Loop to add up to 3 random items to the cart
		for (int i = 0; i < itemsToAdd; i++) {
			// Get a random index within the range of available items
			int randomIndex = rand.nextInt(fitnessContainerList.size());
			WebElement button = fitnessContainerList.get(randomIndex)
					.findElement(By.cssSelector(".action.tocart.primary"));

			// Scroll to the element to ensure visibility
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", button);

			// Click using JavaScript to handle any potential intercept issues
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", button);

			System.out.println("Clicked on 'Add to Cart' for item at index: " + randomIndex);

			// Optional: Add a small wait to ensure each item is added successfully before
			// adding the next one
			Thread.sleep(3000);

			// Remove the clicked item from the list to avoid clicking it again
			fitnessContainerList.remove(randomIndex);
		}
	}
}