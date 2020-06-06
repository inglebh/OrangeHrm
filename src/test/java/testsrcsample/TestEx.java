package testsrcsample;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestEx {
	
	@Test
	@Parameters("appURL")
	public void TestCase1(String appURL)
	{
		WebDriver driver ;
		
		System.setProperty("webdriver.chrome.driver", "F:\\Orange HRM\\OramgeHRM\\drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(appURL);
		driver.manage().window().maximize();
		driver .quit();
	}

}
