package com.cmazxiaoma.concurrent.aqs;

import java.text.DecimalFormat;
import java.util.Calendar;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2019/12/8 1:11
 */
public class Test3 {

   public static void main(String[] args) {
       // DecimalFormat df1 = new DecimalFormat("0.00");
       DecimalFormat df =new DecimalFormat("000000000");
       System.out.println(df.format(110757970));
   }
}
