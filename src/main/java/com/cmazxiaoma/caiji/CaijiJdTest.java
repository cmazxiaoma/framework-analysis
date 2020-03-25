package com.cmazxiaoma.caiji;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Map;
import java.util.Objects;

import static com.dyuproject.protostuff.MapSchema.MessageFactories.HashMap;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: 采集京东
 * @date 2019/11/23 23:58
 */
public class CaijiJdTest {

    public static void main(String[] args) throws Exception {
        String url = "https://search.jd.com/Search?keyword=iphone";
        Document document = Jsoup.connect(url).get();
        /**
         *
         * select: document.querySelector("#J_goodsList")
         * js path: document.querySelector("#J_goodsList")
         * xpath: //*[@id="J_goodsList"]
         */

        Elements doc = document.getElementsByClass("gl-i-wrap");

        System.out.println(doc.toString());

        for (Element element : doc) {
            // 过滤广告
            if (!element.getElementsByClass("p-promo-flag").text().trim().equalsIgnoreCase("广告")) {
                Product product = new Product();
                product.setTitle(element.getElementsByClass("p-name p-name-type-2").first().getElementsByTag("em").text());
                product.setImgUrl(element.getElementsByClass("p-img").first().getElementsByTag("img").attr("source-data-lazy-img"));
                product.setPrice(element.getElementsByClass("p-price").first().getElementsByTag("i").text());
                product.setPromoWords(element.getElementsByClass("p-name p-name-type-2")
                        .first().getElementsByTag("i").text());
                product.setCommit(element.getElementsByClass("p-commit")
                        .first().getElementsByTag("strong").first().getElementsByTag("a").text());
                product.setShopName(element.getElementsByClass("p-shop")
                        .first().getElementsByTag("a").first().text());
                System.out.println(JSON.toJSONString(product));
            }
        }

    }

}
