package PageObjectModel;

import Utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class orderHistoryPage extends AbstractClass{

    WebDriver driver;
    public orderHistoryPage(){

        driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }


    @FindBy(xpath = "//span[text()='Tugba Aydin']")
    private WebElement clickOnTugbaAydin;

    public void clickOnTugbaAydinTab(){
        clickOnFunctionalities(clickOnTugbaAydin);
    }

    @FindBy(xpath = "//span[text()='Order history and details']")
    private WebElement orderHistory;

    public void clickOnOrderHistoryTab(){
        scrollDown();
        clickOnFunctionalities(orderHistory);
    }

    //to get the reference number in text

//    @FindBy(xpath = "//div[@class='box']/text()[11]")
//    private WebElement referenceNum;
    
    public String getTheReferenceNumber(){

        sleep(3);

        WebElement element = driver.findElement(By.xpath(getXpathForTitle(11)));

        Pattern p = Pattern.compile("reference\\W+(\\w+)");

        Matcher m = p.matcher(element.getText());
        Assert.assertTrue(m.find(), "Reference number not found!");
        String referenceNumber = m.group(1);
        System.out.println(referenceNumber);
        return  referenceNumber;
    }

    //to get the column of reference list

    @FindAll({
            @FindBy(xpath = "//table[@id='order-list']/tbody//td[1]")
    })
    private List<WebElement> firstColumnlist;

    public void getTheColumnReferenceNum(String value){

        verifyOrderNumber(firstColumnlist,value );



    }
}
