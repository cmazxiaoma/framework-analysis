package com.cmazxiaoma.lotteryTest;

import com.alibaba.fastjson.JSON;
import com.cmazxiaoma.retry.HttpUtil;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.CollectionUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/11/28 10:44
 */
public class PieceTest {

    public static void main(String[] args) {
        String requestTemplate = "{\n" +
                "    \"data\": {\n" +
                "        \"phead\": {\n" +
                "            \"pversion\": 82,\n" +
                "            \"phoneid\": \"PHONE_ID\",\n" +
                "            \"phone\": \"MI 8\",\n" +
                "            \"imei\": \"860758047970837\",\n" +
                "            \"cversion\": 123,\n" +
                "            \"cversionname\": \"2.16.0\",\n" +
                "            \"channel\": \"91000\",\n" +
                "            \"lang\": \"zh_cn\",\n" +
                "            \"sdk\": 28,\n" +
                "            \"imsi\": \"46002\",\n" +
                "            \"sys\": \"9\",\n" +
                "            \"lng\": -1,\n" +
                "            \"lat\": -1,\n" +
                "            \"cityid\": -1,\n" +
                "            \"gcityid\": -1,\n" +
                "            \"platform\": \"android\",\n" +
                "            \"prdid\": \"14000\",\n" +
                "            \"time_zone\": \"GMT+08:00\",\n" +
                "            \"timezoneid\": \"Asia/Shanghai\",\n" +
                "            \"dpi\": \"1080*2028\",\n" +
                "            \"user_create_time\": \"2019-04-28 12:52:45\",\n" +
                "            \"phone_number\": \"15827557996\",\n" +
                "            \"access_token\": \"ACCESS_TOKEN_TEMPLATE\",\n" +
                "            \"net\": \"WIFI\",\n" +
                "            \"mac\": \"48:2C:A0:63:64:FE\",\n" +
                "            \"shumei_deviceid\": \"20190427133939788a78fe6509fb131c3c8d48d8027285010f1eabfc5d7c4c\"\n" +
                "        },\n" +
                "        \"id\": \"138\",\n" +
                "        \"pageEntrance\": \"123\"\n" +
                "    },\n" +
                "    \"shandle\": \"0\",\n" +
                "    \"handle\": \"0\"\n" +
                "}";

        List<String> accessTokenList = Lists.newArrayList();

        List<Integer> userIdList = Lists.newArrayList();

        try {
            String pathname = "D:\\user.txt";
            File filename = new File(pathname); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename), "UTF-8");
            BufferedReader br = new BufferedReader(reader);

            String line = null;


            while ((line = br.readLine()) != null) {
                if (!StringUtils.isEmpty(line)) {
                    userIdList.add(Integer.valueOf(line));
                }
            }

            br.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


        for (Integer userId : userIdList) {
            String accessToken = AESUtils.encrypt(userId + ":123"  + ":123" , "vzcW>2t@(0Wt41<6c%5#8,X<Xip8?2iq");
            accessTokenList.add(accessToken);
        }

        String pieceJoinUrl = "http://dev.vipgift.gmilesquan.com/qu_exchange/api/v1/pieceLottery/join";

        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch end = new CountDownLatch(accessTokenList.size());

        ExecutorService executorService = new LotteryTest.CustomThreadPoolExecutor(100,
                100, 0L,
                TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());


        for (int i = 0; i < accessTokenList.size(); i++) {
            String requestTemp = requestTemplate.replace("ACCESS_TOKEN_TEMPLATE", "");
            String request = requestTemp.replace("PHONE_ID", userIdList.get(i) + "");

            LotteryTest.CustomThreadPoolExecutor.CustomTask task = new LotteryTest.CustomThreadPoolExecutor.CustomTask(new Runnable() {
                @Override
                public void run() {
                    try {
                        start.await();
                        String json = HttpUtil.doPostJson(pieceJoinUrl, request);
                        Payload payload = JSON.parseObject(json, Payload.class);
                        if (!Objects.isNull(payload)) {
                            System.out.println("参加结果:" + json);
                        }
                        TimeUnit.MILLISECONDS.sleep(200);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    } finally {
                        end.countDown();
                    }
                }
            }, "success");
            executorService.submit(task);
        }

        start.countDown();
        try {
            end.await();
            executorService.shutdown();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}
