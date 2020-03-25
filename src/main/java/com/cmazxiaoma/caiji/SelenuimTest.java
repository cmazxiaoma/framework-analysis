package com.cmazxiaoma.caiji;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/11/24 3:08
 */
public class SelenuimTest {
    private static ChromeDriverService service;

    static {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("E:\\chromedriver_win32\\chromedriver.exe"))
                .usingAnyFreePort()
                .build();
    }

    /**
     * 总共爬取的数据:5347,耗时=137967ms
     * 5345,耗时=145783ms
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        String url = "https://search.jd.com/Search?keyword=iphone";
        // 等待页面全部加载好
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);

        ChromeDriver webDriver = new ChromeDriver(service, chromeOptions);
        webDriver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        webDriver.get(url);


        Boolean flag = true;

        LongAdder longAdder = new LongAdder();

        long start = System.currentTimeMillis();

        for (int i = 0; flag; i++) {
            System.out.println("开始获得第" + i +  "页数据...");
            // 模拟滑动
            webDriver.executeScript("var q=document.documentElement.scrollTop=8000");
            TimeUnit.MILLISECONDS.sleep(500);
            List<Product> productList = parseProduct(webDriver.getPageSource());
            if (CollectionUtils.isEmpty(productList)) {
                flag = false;
            }

            try {
                WebElement webElement = webDriver.findElementByClassName("pn-next");
                if (StringUtils.isBlank(webElement.getAttribute("onclick"))) {
                    break;
                }
                webElement.click();
                TimeUnit.MILLISECONDS.sleep(500);
            } catch (Exception ex) {
                break;
            }

            longAdder.add(productList.size());
        }
        long end = System.currentTimeMillis();
        System.out.println("总共爬取的数据:" + longAdder.intValue() + ",耗时=" + (end - start) + "ms");
    }

    public static List<Product> parseProduct(String html) {
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("gl-i-wrap");

        List<Product> productList = Lists.newArrayList();

        try {
            for (Element element : elements) {
                // 过滤广告
                if (!element.getElementsByClass("p-promo-flag").text().trim().equalsIgnoreCase("广告")) {
                    Product product = new Product();
                    product.setTitle(element.getElementsByClass("p-name p-name-type-2").first().getElementsByTag("em").text());
                    Element imgElemnt = element.getElementsByClass("p-img").first().getElementsByTag("img").first();
                    product.setImgUrl(imgElemnt.attr("src"));
                    if (StringUtils.isBlank(product.getImgUrl())) {
                        product.setImgUrl(imgElemnt.attr("source-data-lazy-img"));
                    }
                    if (StringUtils.isBlank(product.getImgUrl())) {
                        product.setImgUrl(imgElemnt.attr("data-lazy-img"));
                    }
                    product.setPrice(element.getElementsByClass("p-price").first().getElementsByTag("i").text());
                    product.setPromoWords(element.getElementsByClass("p-name p-name-type-2")
                            .first().getElementsByTag("i").text());
                    product.setCommit(element.getElementsByClass("p-commit")
                            .first().getElementsByTag("strong").first().getElementsByTag("a").text());
                    product.setShopName(element.getElementsByClass("p-shop")
                            .first().getElementsByTag("a").first().text());

                    productList.add(product);
                    System.out.println(JSON.toJSONString(product));
                }
            }
        } catch (Exception ex) {
            //
        }

        return productList;
    }
}
