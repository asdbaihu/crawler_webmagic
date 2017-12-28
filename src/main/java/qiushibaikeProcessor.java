import org.apache.commons.logging.impl.Log4JLogger;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.JsonFilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

/**
 * Created by TTTTT on 2017/12/26 0026.
 */
public class qiushibaikeProcessor implements PageProcessor {

    static Log4JLogger l = new Log4JLogger("log4j.properties");

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000).addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");

    public static void main(String[] args) {
        Spider.create(new qiushibaikeProcessor()).addUrl("https://www.qiushibaike.com/hot/").addPipeline(new JsonFilePipeline("D:\\fy\\"))
                .thread(5).run();
    }

    @Override
    public void process(Page page) {
        l.info(page.getHtml().xpath("//*[@class=\"contentHerf\"]//div[@class=\"content\"]//").toString());
//        System.out.println("抓取的内容：" + page.getHtml().xpath("//a[@class=\"contentHerf\"]//div[@class=\"content\"]//").toString());
//        page.addTargetRequests(page.getHtml().xpath("//*[@id=\"content-left\"]/ul/li/a/@href").all());
        page.addTargetRequests(page.getHtml().links().regex("(https://www\\.qiushibaike\\.com/\\w+/\\w+)").all());
        page.putField("content", page.getHtml().xpath("//*[@class=\"contentHerf\"]//div[@class=\"content\"]//").toString());

    }

    @Override
    public Site getSite() {
        return site;
    }
}
