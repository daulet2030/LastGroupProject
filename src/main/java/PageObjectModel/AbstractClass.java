package PageObjectModel;

import Utilities.Driver;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.util.List;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.UUID;

public abstract class AbstractClass {
    WebDriver driver;
    WebDriverWait wait;



    public void waitMethod(){

        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }


    public AbstractClass() {
        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    public void clickOnFunctionalities(WebElement clickElement) {
        waitMethod();

        try {
            wait.until(ExpectedConditions.elementToBeClickable(clickElement));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        clickElement.click();
    }

    public void sendKeysFunction(WebElement sendkeysElement, String value) {

        waitMethod();

        try {
            wait.until(ExpectedConditions.visibilityOf(sendkeysElement));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        sendkeysElement.sendKeys(value);
    }

    public void waitUntilVisibility(WebElement waitElement) {
        try {
            wait.until(ExpectedConditions.visibilityOf(waitElement));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void waitUntilInVisibility(WebElement waitElement) {

        try {
            wait.until(ExpectedConditions.invisibilityOf(waitElement));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }




    public void randomMethodForClothes( List<WebElement> produckts) {

        int randomNum = randomGenerator(produckts.size());
        clickOnFunctionalities(produckts.get(randomNum));


    }

    public int randomGenerator(int max) {

        Random random = new Random();
        int randomNum = random.nextInt(max);
        return randomNum;
    }




    public void scrollDown() {


        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,270)", "");
    }


    public void selectInDropDown(WebElement dropdown){
        Select select = new Select(dropdown);
        List<WebElement> list = select.getOptions();

            select.selectByIndex(39);

    }


    public void verifyURL(String expectedResult){


        String URL = driver.getCurrentUrl();

        Assert.assertTrue(URL.contains( expectedResult )  );

    }

    public void confirmAlert(){

        driver.switchTo().alert().accept();  //to accept alert

    }


    public void verifyOrder(WebElement element, String exoected) {
        String actual=element.getText();
        Assert.assertEquals(actual,exoected);
    }

    //after this point need to be checked

    public static String getXpathForTitle(int num){
        return "//div[@class='box']";
    }

    public static String injectWrappedText(String xpath, WebDriver driver){
        String randomId = UUID.randomUUID().toString();
        StringBuilder script = new StringBuilder();
        script
                .append("var webElementWrapper = document.createElement(\"span\");").append("\n")
                .append("var text = document.evaluate(\"" + xpath + "\", document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue.cloneNode(true);").append("\n")
                .append("var body = document.getElementsByTagName(\"body\");").append("\n")
                .append("webElementWrapper.appendChild(text);").append("\n")
                .append("body.item(0).appendChild(webElementWrapper);").append("\n")
                .append("var att = document.createAttribute(\"id\");").append("\n")
                .append("att.value = \""+randomId+"\";").append("\n")
                .append("webElementWrapper.setAttributeNode(att);");
        ((JavascriptExecutor)driver).executeScript(script.toString());
        return randomId;
    }

    public String  getReferenceNumber(String element){

        String text = getXpathForTitle(11);

        System.out.println(text+"<<<<<<<<<<<_-----------------text is here ");

        String[] s = text.split(" ", 10);

        System.out.println(text);

        String myelement = s[9];

        System.out.println(myelement);

        return myelement;
//        boolean result = false;

//        for (int i = 0; i < s.length; i++) {
//            if (s[i].trim().equalsIgnoreCase(value)){
//                result = true;
//                break;
//            }
//        }
//        if(result==false){
//            Assert.fail(  );
//        }else{
//            System.out.println(value + " is displayed");
//        }

    }

    public void verifyOrderNumber( List<WebElement> elementList, String expectedText){
        boolean found = false;
        for (int i = 0; i < elementList.size(); i++) {
            String actualText = elementList.get(i).getText();
            if(actualText.trim().equalsIgnoreCase(expectedText)){
                found = true;
                break;
            }
        }
        Assert.assertTrue(found, "The expected text was not found in the element list");
    }

    public void sleep(int num){

        try{
            Thread.sleep(num * 1000);
        }catch(Exception e){
            System.out.println(e.getMessage());

        }

    }
}

