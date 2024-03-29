import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.downloader.Downloader;
import us.codecraft.webmagic.downloader.selenium.SeleniumDownloader;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class SeleniumCnblogsSpider implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000)
            .addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");

    public static final String URL_LIST = "https://www\\.cnblogs\\.com/#p\\d{1,3}";

    public static int pageNum = 1;

    public void process(Page page) {


        if (page.getUrl().regex("^https://www\\.cnblogs\\.com$").match()) {//爬取第一页
            try {
                page.addTargetRequests(page.getHtml().xpath("//*[@id=\"post_list\"]/div/div[@class='post_item_body']/h3/a/@href").all());

                pageNum++;
                page.addTargetRequest("https://www.cnblogs.com/#p2");
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (page.getUrl().regex(URL_LIST).match() && pageNum <= 200) {//爬取2-200页，一共有200页
            try {
                List<String> urls = page.getHtml().xpath("//*[@class='post_item']//div[@class='post_item_body']/h3/a/@href").all();
                page.addTargetRequests(urls);

                page.addTargetRequest("https://www.cnblogs.com/#p" + ++pageNum);
                System.out.println("CurrPage:" + pageNum + "#######################################");

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            // 获取页面需要的内容
            System.out.println("抓取的内容：" + page.getHtml().xpath("//a[@id='cb_post_title_url']/text()").get());
        }
    }

    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
//        System.setProperty("selenuim_config", "D:\\fy\\Code\\crawler_webmagic\\src\\main\\resources\\config.ini");
        System.setProperty("selenuim_config", "D:\\Code\\idea\\crawler_webmagic\\src\\main\\resources\\config.ini");
//        Downloader downloader = new SeleniumDownloader("D:\\fy\\chromedriver_win32\\chromedriver.exe");
        Downloader downloader = new SeleniumDownloader("D:\\software\\chromedriver_win32\\chromedriver.exe");
        downloader.setThread(10);
        Spider.create(new SeleniumCnblogsSpider()).setDownloader(downloader).addUrl("https://www.cnblogs.com").thread(2).runAsync();
    }

}