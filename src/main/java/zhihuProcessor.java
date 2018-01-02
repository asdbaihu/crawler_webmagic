import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by TTTTT on 2017/12/27 0027.
 */
public class zhihuProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000)
//            .setDomain("www.zhihu.com")
//            .addCookie("__utma","")
//            .addCookie("__utmc","")
//            .addCookie("__utmv","")
//            .addCookie("__utmz","")
//            .addCookie("_xsrf","")
//            .addCookie("_zap","")
//            .addCookie("aliyungf_tc","")
//            .addCookie("cap_id","")
//            .addCookie("capsion_ticket","")
//            .addCookie("d_c0","")
//            .addCookie("l_cap_id","")
//            .addCookie("q_c1","")
//            .addCookie("r_cap_id","")
//            .addCookie("z_c0","")
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");


    public static void main(String[] args) {
//        System.setProperty("selenuim_config", "D:\\Code\\idea\\crawler_webmagic\\src\\main\\resources\\config.ini");
//        Downloader downloader = new SeleniumDownloader("D:\\software\\chromedriver_win32\\chromedriver.exe");
//        downloader.setThread(10);
        Spider.create(new zhihuProcessor())
//                .setDownloader(downloader)
                .addUrl("https://www.zhihu.com/").thread(1).runAsync();
    }

    @Override
    public void process(Page page) {
//        System.setProperty("selenuim_config", "D:\\Code\\idea\\crawler_webmagic\\src\\main\\resources\\config.ini");
//        Downloader downloader = new SeleniumDownloader("D:\\software\\chromedriver_win32\\chromedriver.exe");
        System.setProperty("webdriver.chrome.driver", "D:\\software\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://www.zhihu.com/signin");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //找到登录按钮，点击
//        driver.findElement(By.id("headerLogin")).click();
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        driver.findElement(By.id("dialogLWrap")).findElement(By.id("dialogLPlatform")).findElement(By.tagName("a")).click();
//        driver.findElement(By.id("userId")).sendKeys("账号");
//        driver.findElement(By.id("passwd")).sendKeys("密码");
//        driver.findElement(By.xpath("//p[@class='oauth_formbtn']/a[@node-type='submit']")).click();
        //-------------------------------------------//
        driver.findElement(By.xpath("//div[@class='SignFlow-accountInput Input-wrapper']/input")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div/div/div[2]/div[1]/div/form/div[2]/div/div[1]/input")).sendKeys("");
        driver.findElement(By.xpath("//*[@id=\"root\"]/div/main/div/div/div/div[2]/div[1]/div/form/button")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//p[@class='oauth_formbtn']/a[@node-type='submit']")).click();


    }

    @Override
    public Site getSite() {
        return site;
    }
}
