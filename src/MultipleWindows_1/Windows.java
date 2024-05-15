package MultipleWindows_1;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;

public class Windows {
	
	public static void main(String[] args) throws InterruptedException
	{
		System.setProperty("webdriver.edge.driver", "C:\\Users\\2317595\\eclipse-workspace1\\MultipleWindows\\Browser\\msedgedriver.exe");
		WebDriver driver=new EdgeDriver();
		
		
		
		driver.get("https://www.rediff.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		System.out.println("Title of the home page: "+driver.getTitle());
		System.out.println("Current URL of the home page: "+driver.getCurrentUrl());
		
		
	
		driver.findElement(By.linkText("Create Account")).click();
		String p=driver.getTitle();
		System.out.println('\n'+"Title of the create account page: " +p);
		System.out.println("Current URL of the create account page: "+driver.getCurrentUrl());
		WebElement createRediff=driver.findElement(By.xpath("//td[@class='f22']"));
		if(createRediff.isDisplayed())
		{
			System.out.println('\n'+"Create Rediff account page is displayed");
			
			
			List<WebElement> links=driver.findElements(By.tagName("a"));
			System.out.println('\n'+"Total number of links in the Create Rediffmail account: "+links.size());
			System.out.println("Links in Create Rediffmail account: ");
			for(WebElement a:links)
			{
				System.out.println(a.getText()+"-"+a.getAttribute("href"));
				
			}
			
			
			
			String parentWindow=driver.getWindowHandle();
			driver.findElement(By.linkText("terms and conditions")).click();
			Set<String> windows=driver.getWindowHandles();
			
			
//			String childWindow=windows.toArray()[1].toString();
//			driver.switchTo().window(childWindow);
//			System.out.println("Title of the child Window " + driver.getTitle());
			
			
			String s;
			for(int i=0;i<windows.size();i++)
			{
				s=windows.toArray()[i].toString();
				driver.switchTo().window(s);
				
				if(driver.getCurrentUrl().contains("terms"))
				{
					System.out.println('\n'+"Title of the child Window " + driver.getTitle());
				    break;
				}
				
		
			}
			driver.manage().window().maximize();
			//Thread.sleep(6000);
			driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
			if(driver.getTitle().equals("Rediffmail: Terms and Conditions"))
			{
				System.out.println('\n'+"Current title of the webpage-"+driver.getTitle()+" matches with the Expected title-Rediffmail: Terms and Conditions");
				driver.close();
			}
			else
			{
				System.out.println("Not Matches");
			}
		   driver.switchTo().window(parentWindow);
		   //Thread.sleep(6000);
		   driver.manage().timeouts().implicitlyWait(60,TimeUnit.SECONDS);
		   System.out.println('\n'+"Title of the Parent Window: " + driver.getTitle());
		   driver.quit();
			
		
	}
}
}


