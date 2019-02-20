package seleniumTesting;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.Set;


public class TestAssignment {

    WebDriver driver;

    @BeforeMethod
    public void BeforeTestRun(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\W3E39\\Downloads/chromedriver.exe");
        driver=new ChromeDriver();
        driver.manage().window().maximize();
    }
    @Test
    public void Test() throws InterruptedException, MalformedURLException {
        WebDriverWait wait = new WebDriverWait(driver,10);

        driver.get("https://www.rentalhomes.com/listing?q=Berlin,+MD,+USA&utm_source");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@id='datepicker_start_details_overlay']")));
        driver.findElement(By.xpath("//input[@id='datepicker_start_details_overlay']")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[10]//div[1]//table[2]//tbody[1]//tr[3]//td[1]//div[1]")));
        driver.findElement(By.xpath("//div[10]//div[1]//table[2]//tbody[1]//tr[3]//td[1]//div[1]")).click();


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[10]//div[1]//table[2]//tbody[1]//tr[3]//td[5]//div[1]")));
        driver.findElement(By.xpath("//div[10]//div[1]//table[2]//tbody[1]//tr[3]//td[5]//div[1]")).click();
        Thread.sleep(5000);

        try
        {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='IM_overlay_pane IM_overlay_im_pane']")));
            driver.findElement(By.xpath("//span[@class='IM_close_text']")).click();
            Thread.sleep(2000);
        }
        catch(Exception e)
        {
            System.out.println("Overlay window not found...");
        }


        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@value='Next']")));
        driver.findElement(By.xpath("//input[@value='Next']")).click();
        Thread.sleep(6000);

        driver.get("https://www.airbnb.com/s/Berlin--MD--USA/homes?refinement_paths%5B%5D=%2Fhomes&irgwc=1&irclid=3iHxdqzjHxyJUHZ0O7XQxx3QUkgSQWxSW0ge2I0&ircid=4273&sharedid=3&af=131739828&iratid=9627&c=.pi73.pk4273_385749&irparam1=&checkin=2019-03-10&checkout=2019-03-14&allow_override%5B%5D=&s_tag=7OVUmq6C");
        String switchWindow=driver.getWindowHandle();
        Set<String> set =driver.getWindowHandles();
        Iterator<String> itr= set.iterator();
        while(itr.hasNext()){

            String childWindow=itr.next();

            if(switchWindow.equals(childWindow)){
                driver.switchTo().window(childWindow);
                String url=driver.getCurrentUrl();
                URL aURL = new URL(url);
                String query = aURL.getQuery();
                String[] params = query.split("&");

                for (String a : params){
                    String[] param = a.split("=");

                    if (param.length == 2){
                        if (param[0].equals("sharedid")){
                        System.out.println("Share_id = " + param[1]);
                        break;
                        }
                    }

                }
            }
            }
    }


    @AfterMethod
    public void BrowserClose(){
        driver.close();
    }

}




