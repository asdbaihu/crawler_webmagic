import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by TTTTT on 2018/1/2 0002.
 */
public class QzoneProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000).addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");

    public static void main(String[] args) {
        Spider.create(new QzoneProcessor()).addUrl("https://i.qq.com/").thread(1).runAsync();
    }

    @Override
    public void process(Page page) {
        System.setProperty("webdriver.chrome.driver", "D:\\software\\chromedriver_win32\\chromedriver.exe");
        WebDriver driver = new ChromeDriver();
        driver.get("https://i.qq.com/");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //切换账号密码登陆
        driver.switchTo().frame("login_frame");
        driver.findElement(By.id("switcher_plogin")).click();
        driver.findElement(By.id("u")).sendKeys("");
        driver.findElement(By.id("p")).sendKeys("");
        driver.findElement(By.id("login_button")).click();
        //登陆over
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //留言板
        driver.findElement(By.xpath("//*[@id=\"menuContainer\"]/div/ul/li[4]/a")).click();


        //*[@id="ulCommentList"]
        //*[@id="commentContentDiv_1000050020"]/table/tbody/tr/td
        //*[@id="commentContentDiv_1000050019"]/table/tbody/tr/td
        page.putField("content", page.getHtml().xpath("//*[@id=\"ulCommentList\"]").toString());


        //*[@id="pager_next_0"] 下一页
        page.addTargetRequests(page.getHtml().xpath("//*[@id=\"pager_next_0\"]").all());

    }

    @Override
    public Site getSite() {
        return site;
    }
}
