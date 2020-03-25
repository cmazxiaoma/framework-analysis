package com.cmazxiaoma.concurrent.lock;

import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/9/20 19:29
 */
public class Test {

    public static void main(String[] args) throws UnsupportedEncodingException {
        String originContent = "(activity:93500_15500150020_15_0)(split)crazyRedPacketContent=9457909,2";
        List<String> contentList = new ArrayList<String>();
        // 多个内容用"(split)"字符串分割
        if(StringUtils.isNotBlank(originContent) && originContent.contains("(split)")){
            String[] contentArr = originContent.split("\\(split\\)");
            for(String contentStr : contentArr){
                contentList.add(contentStr);
            }
        }else{
            contentList.add(originContent);
        }
        System.out.println(contentList.toString());

        for (String content : contentList) {

            // 疯狂红包
            if (StringUtils.isNotBlank(content) && content.startsWith("crazyRedPacketContent=")) {
                String crazyRedPacketContent = content.replace("crazyRedPacketContent=", "");
                if (!StringUtils.isEmpty(crazyRedPacketContent) && !StringUtils.isBlank(crazyRedPacketContent)) {
                    String[] contentArr = crazyRedPacketContent.split(",");

                    if (contentArr!=null && contentArr.length >= 2) {
                        try {
                            Long inviterUserId = Long.valueOf(contentArr[0].trim());
                            Integer inviterCurrentVersion = Integer.parseInt(contentArr[1].trim());
                            System.out.println(inviterUserId);
                            System.out.println(inviterCurrentVersion);
                        } catch (Exception ex) {
                            //
                        }
                    }
                }
            }
        }
    }
}
