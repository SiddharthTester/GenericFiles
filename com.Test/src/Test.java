import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String args[])throws IOException, InterruptedException, IOException {
        System.setProperty("webdriver.chrome.driver","path");
        WebDriver driver = new ChromeDriver();
        driver.get("https://makemysushi.com/404");
        Thread.sleep(3000);

        List<WebElement> lst=driver.findElements(By.tagName("a"));
        lst.addAll(driver.findElements(By.tagName("img")));

        List<WebElement> actLink=new ArrayList<WebElement>();

        for(int i=0;i<lst.size();i++)
        {

            if(lst.get(i).getAttribute("href")!= null && !lst.get(i).getAttribute("href").contains("mail"))
            {
                actLink.add(lst.get(i));
                System.out.println(lst.get(i).getAttribute("href"));
            }
        }
        System.out.println("All link size is :- "+lst.size());
        System.out.println("Active links size is :- "+actLink.size());

        for(int i=0;i<actLink.size();i++)
        {
            HttpURLConnection connection=(HttpURLConnection)new URL(actLink.get(i).getAttribute("href")).openConnection();
            connection.connect();
            String responce=connection.getResponseMessage();
            connection.disconnect();
            System.out.println(actLink.get(i).getAttribute("href")+"---->"+responce);
        }
    }
}
