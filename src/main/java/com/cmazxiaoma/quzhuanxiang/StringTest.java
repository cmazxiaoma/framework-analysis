package com.cmazxiaoma.quzhuanxiang;

import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/10/23 18:33
 */
public class StringTest {


    public static String encodeUserIdText(Long userId) {
        if (Objects.isNull(userId)) {
            return "10****43";
        }
        try {
            String userIdText = String.valueOf(userId);
            StringBuffer sb = new StringBuffer();
            int x = 0;
            char fone = '1';
            char ftwo = '1';
            char ltwo = '1';
            char lone = '1';

            if (userIdText.length() == 1) {
                x = 3;
                fone = userIdText.charAt(0);
                ftwo = fone;
                ltwo = fone;
                lone = fone;
            } else if (userIdText.length() == 2) {
                x = 3;
                fone = userIdText.charAt(0);
                ftwo = fone;
                ltwo = userIdText.charAt(1);
                lone = ltwo;
            } else if (userIdText.length() == 3) {
                x = 3;
                fone = userIdText.charAt(0);
                ftwo = fone;
                ltwo = userIdText.charAt(1);
                lone = userIdText.charAt(2);
            } else if (userIdText.length() >= 4 && userIdText.length() <= 6) {
                x = 3;
                fone = userIdText.charAt(0);
                ftwo = userIdText.charAt(1);
                ltwo = userIdText.charAt(2);
                lone = userIdText.charAt(3);
            } else {
                x = userIdText.length() - 4;
                fone = userIdText.charAt(0);
                ftwo = userIdText.charAt(1);
                ltwo = userIdText.charAt(userIdText.length() - 2);
                lone = userIdText.charAt(userIdText.length() - 1);
            }

            sb.append(fone).append(ftwo);
            for (int i = 1; i <= x; i++) {
                sb.append("*");
            }
            sb.append(ltwo).append(lone);
            return sb.toString();
        } catch (Exception ex) {
            return "10****43";
        }
    }

    public static void main(String[] args) {
        System.out.println(encodeUserIdText(7L));
        System.out.println(encodeUserIdText(70L));
        System.out.println(encodeUserIdText(704L));
        System.out.println(encodeUserIdText(7049L));
        System.out.println(encodeUserIdText(70492L));
        System.out.println(encodeUserIdText(704929L));
        System.out.println(encodeUserIdText(7049294L));
        System.out.println(encodeUserIdText(70492943L));
        System.out.println(encodeUserIdText(70492943L));
    }
}
