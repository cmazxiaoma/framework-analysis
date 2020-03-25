package com.cmazxiaoma.caiji;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.sun.org.apache.regexp.internal.RE;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.util.CollectionUtils;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: 多线程爬虫
 * @date 2019/11/24 11:20
 */
public class MultiSelenuimDemoTest {

    private static String url = "https://search.jd.com/Search?keyword=iphone";

    private static LongAdder longAdder = new LongAdder();

    public static void main(String[] args) throws InterruptedException, MalformedURLException {
        int coreSize = 2;
        int part = 50;

        final CountDownLatch countDownLatch = new CountDownLatch(coreSize);

        RemoteWebDriverPool pool = new RemoteWebDriverPool(coreSize);

        long startTime = System.currentTimeMillis();
        for (int i = 1; i <= coreSize; i++) {
            int start = (i - 1) * part + 1;
            int end =  start + part - 1;
            RemoteWebDriver remoteWebDriver = pool.getIdleDriver();
            new Thread(new CustomRunable(countDownLatch, remoteWebDriver, start ,end)).start();
        }

        countDownLatch.await();
        long endTime = System.currentTimeMillis();
        System.out.println("总共爬取的数据:" + longAdder.intValue() + ",耗时=" + (endTime - startTime) + "ms");
    }


    public static class CustomRunable implements Runnable {
        private CountDownLatch countDownLatch;
        private RemoteWebDriver webDriver;
        private int startPageNo;
        private int endPageNo;

        public CustomRunable(CountDownLatch countDownLatch, RemoteWebDriver webDriver, int startPageNo, int endPageNo) {
            this.countDownLatch = countDownLatch;
            this.webDriver = webDriver;
            this.startPageNo = startPageNo;
            this.endPageNo = endPageNo;
        }

        @Override
        public void run() {
            try {
                webDriver.get(url);

                for (int g = startPageNo; g <= endPageNo; g++) {
                    WebDriverWait subWait =
                            new WebDriverWait(webDriver, 40);

                    System.out.println("开始爬:" + g + "页数据...");

                    webDriver.executeScript("var q=document.documentElement.scrollTop=7000");

                    WebElement subInputElement =
                            subWait.until(ExpectedConditions.
                                    presenceOfElementLocated
                                            (By.xpath("/html/body/div[6]/div[3]/div[2]/div[1]/div/div[3]/div/span[2]/input")));
                    subInputElement.clear();
                    subInputElement.sendKeys(g + "");

                    WebElement subClickElement = subWait.until(ExpectedConditions.
                            presenceOfElementLocated(
                                    By.xpath("/html/body/div[6]/div[3]/div[2]/div[1]/div/div[3]/div/span[2]/a")
                            ));

                    WebElement webElement = webDriver.findElementByClassName("pn-next");

                    // 到了尾页
                    if (StringUtils.isBlank(webElement.getAttribute("onclick"))) {
                        return;
                    }

                    subClickElement.click();

                    List<Product> productList = parseProduct(webDriver.getPageSource());

                    if (CollectionUtils.isEmpty(productList)) {
                        return;
                    }

                    longAdder.add(productList.size());
                    System.out.println("爬出:" + g + "页数据:" + productList.size());
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                countDownLatch.countDown();
            }
        }
    }

    public static List<Product> parseProduct(String html) {
        Document document = Jsoup.parse(html);
        Elements elements = document.getElementsByClass("gl-i-wrap");

        System.out.println("预期数量:" + elements.size());

        List<Product> productList = Lists.newArrayList();

        try {
            for (Element element : elements) {
                // 过滤广告
                if (!element.getElementsByClass("p-promo-flag").text().trim()
                        .equalsIgnoreCase("广告")) {
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
                    // System.out.println(JSON.toJSONString(product));
                }
            }
        } catch (Exception ex) {
            //
        }

        return productList;
    }
}
