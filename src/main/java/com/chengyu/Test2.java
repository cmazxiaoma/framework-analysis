package com.chengyu;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.*;
import com.google.common.reflect.TypeToken;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.StringUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author xiaoma
 * @version V1.0
 * @Description: TODO
 * @date 2020/2/14 20:24
 */
public class Test2 {

    public static String levelText;
    public static List<NormalLevelVo> levelVoList;
    public static Map<String, NormalLevelVo> levelMap;

    public static String chengyuText;
    public static List<ChengyuVo> chengyuVoList;
    public static Map<String, ChengyuVo> chengyuVoMap;


    static {
        StringBuilder levelSb = new StringBuilder();
        try {
            String pathname = "C:\\Users\\cmazxiaoma\\Desktop\\大富豪调研\\excel\\cdn\\json\\xls\\cn\\levels2.json";
            File filename = new File(pathname); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename), "UTF-8");
            BufferedReader br = new BufferedReader(reader);

            String line = null;


            while ((line = br.readLine()) != null) {
                if (!StringUtils.isBlank(line)) {
                    levelSb.append(line);
                }
            }

            br.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        levelText = levelSb.toString();
        levelVoList = JSON.parseObject(levelText, new TypeToken<List<NormalLevelVo>>() {
        }.getType());
        levelMap = levelVoList.stream().collect(Collectors.toMap(NormalLevelVo::getId, Function.identity(), (old, newB) -> newB));


        StringBuilder chengyuSb = new StringBuilder();
        try {
            String pathname = "C:\\Users\\cmazxiaoma\\Desktop\\大富豪调研\\excel\\cdn\\json\\xls\\cn\\Chengyu.json";
            File filename = new File(pathname); // 要读取以上路径的input。txt文件
            InputStreamReader reader = new InputStreamReader(
                    new FileInputStream(filename), "UTF-8");
            BufferedReader br = new BufferedReader(reader);

            String line = null;


            while ((line = br.readLine()) != null) {
                if (!StringUtils.isBlank(line)) {
                    chengyuSb.append(line);
                }
            }

            br.close();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        chengyuText = chengyuSb.toString();
        chengyuVoList = JSON.parseObject(chengyuText, new TypeToken<List<ChengyuVo>>() {
        }.getType());
        chengyuVoMap = chengyuVoList.stream().collect(Collectors.toMap(ChengyuVo::getId, Function.identity(), (old, newB) -> newB));

    }

    @Data
    public static class NormalLevelVo {
        private String id;
        private String name;
        private String num;
        private String map;
        private String pos;
    }

    @Data
    public static class ChengyuVo {
        private String id;
        private String name;
        private String info;
        private String solitaire;
    }

    @Data
    @AllArgsConstructor
    public static class MapVo {
        // 0是横 1是竖
        private Integer type;
        private ChengyuVo chengyuVo;
        // 有序
        private List<WordPosVo> chengyuWordPosVoList;
        // 无序
        private List<WordPosVo> whitePosVoList;
        private Integer product;
        private Integer consume;
    }

    @Data
    @EqualsAndHashCode(callSuper = true)
    public static class WordPosVo extends PosVo {

        private String currentWord;

        public WordPosVo(Integer x, Integer y, Integer xy) {
            super(x, y, xy);
        }

        public WordPosVo(Integer x, Integer y, Integer xy, String currentWord) {
            super(x, y, xy);
            this.currentWord = currentWord;
        }

        @Override
        public String toString() {
            return "WordPosVo{" +
                    "currentWord='" + currentWord + '\'' +
                    ", x=" + super.x +
                    ", y=" + super.y +
                    ", xy=" + super.xy +
                    '}';
        }
    }

    @Data
    @AllArgsConstructor
    public static class PosVo {
        private Integer x;
        private Integer y;
        private Integer xy;
    }

    public static void main(String[] args) {
        select("899");
    }

    public static void select(String id) {
        NormalLevelVo levelVo = levelMap.get(id);
        String map = levelVo.getMap();
        String pos = levelVo.getPos();
        String newPos = StringUtils.substring(pos, StringUtils.indexOf(pos, 'A') + 1, pos.length());

        List<PosVo> posVoList = Lists.newArrayList();
        Map<String, PosVo> posVoMap = Maps.newHashMap();

        for (int i = 0; i < newPos.length() - 1;) {
            posVoList.add(new PosVo(
                    Integer.valueOf(newPos.substring(i, i + 1)),
                    Integer.valueOf(newPos.substring(i + 1, i + 2)),
                    Integer.valueOf(newPos.substring(i, i + 2))
            ));
            i += 2;
        }

        posVoList = posVoList.stream().sorted(Comparator.comparing(PosVo::getXy)).collect(Collectors.toList());

        for (PosVo posVo : posVoList) {
            posVoMap.put(posVo.getX() + "|" + posVo.getY(), posVo);
        }

        String[][] a = {
                {"😶", "😶", "😶", "😶", "😶", "😶", "😶", "😶", "😶"},
                {"😶", "😶", "😶", "😶", "😶", "😶", "😶", "😶", "😶"},
                {"😶", "😶", "😶", "😶", "😶", "😶", "😶", "😶", "😶"},
                {"😶", "😶", "😶", "😶", "😶", "😶", "😶", "😶", "😶"},
                {"😶", "😶", "😶", "😶", "😶", "😶", "😶", "😶", "😶"},
                {"😶", "😶", "😶", "😶", "😶", "😶", "😶", "😶", "😶"},
                {"😶", "😶", "😶", "😶", "😶", "😶", "😶", "😶", "😶"},
                {"😶", "😶", "😶", "😶", "😶", "😶", "😶", "😶", "😶"},
                {"😶", "😶", "😶", "😶", "😶", "😶", "😶", "😶", "😶"},
                {"😶", "😶", "😶", "😶", "😶", "😶", "😶", "😶", "😶"}
        };

        for (int x = 0; x < a.length; x++) {
            for (int y = 0; y < a[x].length; y++) {
                if (posVoMap.containsKey((x + 1) + "|" + (y + 1))) {
                    a[x][y] = "-1";
                }
                System.out.print(a[x][y] + " ");

            }
            System.out.println();
        }

        String[] levelMapList = StringUtils.split(map, "_");

        List<WordPosVo> chengyuWordPosVoList = Lists.newArrayList();

        Map<String, WordPosVo> wordPosVoMap = Maps.newHashMap();



        for (String chengyu : levelMapList) {
            int va1Index = StringUtils.indexOf(chengyu, "~");

            // 竖
            if (va1Index == -1) {
                String[] item = chengyu.split("\\$");
                String chengyuId = item[0];

                Integer chengyuFirstWordPosIndex = Integer.valueOf(item[1]);
                ChengyuVo chengyuVo = chengyuVoMap.get(chengyuId);

                String name = chengyuVo.getName();

                Integer defaultX = chengyuFirstWordPosIndex / 10;
                Integer defaultY = chengyuFirstWordPosIndex - defaultX * 10;


                chengyuWordPosVoList.add(new WordPosVo(defaultX, defaultY, chengyuFirstWordPosIndex, name.charAt(0) + ""));
                chengyuWordPosVoList.add(new WordPosVo(defaultX + 1, defaultY, chengyuFirstWordPosIndex + 10, name.charAt(1) + ""));
                chengyuWordPosVoList.add(new WordPosVo(defaultX + 2, defaultY, chengyuFirstWordPosIndex + 20, name.charAt(2) + ""));
                chengyuWordPosVoList.add(new WordPosVo(defaultX + 3, defaultY, chengyuFirstWordPosIndex + 30, name.charAt(3) + ""));



            } else {
                // 横
                String[] item = chengyu.split("~");
                String chengyuId = item[0];
                ChengyuVo chengyuVo = chengyuVoMap.get(chengyuId);
                Integer chengyuFirstWordPosIndex = Integer.valueOf(item[1]);
                String name = chengyuVo.getName();

                Integer defaultX = chengyuFirstWordPosIndex / 10;
                Integer defaultY = chengyuFirstWordPosIndex - defaultX * 10;

                chengyuWordPosVoList.add(new WordPosVo(defaultX, defaultY, chengyuFirstWordPosIndex, name.charAt(0) + ""));
                chengyuWordPosVoList.add(new WordPosVo(defaultX, defaultY + 1, chengyuFirstWordPosIndex + 1, name.charAt(1) + ""));
                chengyuWordPosVoList.add(new WordPosVo(defaultX, defaultY + 2, chengyuFirstWordPosIndex + 2, name.charAt(2) + ""));
                chengyuWordPosVoList.add(new WordPosVo(defaultX, defaultY + 3, chengyuFirstWordPosIndex + 3, name.charAt(3) + ""));

            }
        }

        for (WordPosVo wordPosVo : chengyuWordPosVoList) {
            wordPosVoMap.putIfAbsent(wordPosVo.getX() + "|" + wordPosVo.getY(), wordPosVo);
        }

        System.out.println("===============================");

        for (int x = 0; x < a.length; x++) {
            for (int y = 0; y < a[x].length; y++) {
                if (posVoMap.containsKey((x + 1) + "|" + (y + 1))) {
                    if (!StringUtils.isBlank(a[x][y])) {
                        a[x][y] = wordPosVoMap.get((x + 1) + "|" + (y + 1)).getCurrentWord();
                    }
                }
                System.out.print(a[x][y] + "");

            }
            System.out.println();
        }

        System.out.println("");

        for (int x = 0; x < a.length; x++) {
            for (int y = 0; y < a[x].length; y++) {
                if (wordPosVoMap.containsKey((x + 1) + "|" + (y + 1))) {
                    if (!StringUtils.isBlank(a[x][y])) {
                        a[x][y] = wordPosVoMap.get((x + 1) + "|" + (y + 1)).getCurrentWord();
                    }
                }
                System.out.print(a[x][y] + "");

            }
            System.out.println();
        }

        System.out.println("=========render===============");


        System.out.println("==========空白的词如下=============");
        Collections.shuffle(posVoList);

        posVoList.forEach(item-> System.out.print(wordPosVoMap.get(item.getX() + "|" + item.getY()).getCurrentWord() + " "));

        System.out.println("");

        // 一个词 对应多个下标 优化
        Multimap<String, WordPosVo> wordPosMultiMap = LinkedHashMultimap.create();

        posVoList.forEach(item-> {
            WordPosVo posVo = wordPosVoMap.get(item.getX() + "|" + item.getY());
            wordPosMultiMap.put(posVo.getCurrentWord(), posVo);
        });

        System.out.println("");
        System.out.println(wordPosMultiMap);


        System.out.println("");

        System.out.println("=========解析map===================");
        StringBuilder mapBuilder = new StringBuilder();

        for (String chengyu : levelMapList) {
            int va1Index = StringUtils.indexOf(chengyu, "~");

            // 竖
            if (va1Index == -1) {
                String[] item = chengyu.split("\\$");
                String chengyuId = item[0];
                ChengyuVo chengyuVo = chengyuVoMap.get(chengyuId);
                Integer chengyuFirstWordPosIndex = Integer.valueOf(item[1]);
                String name = chengyuVo.getName();
                Integer defaultX = chengyuFirstWordPosIndex / 10;
                Integer defaultY = chengyuFirstWordPosIndex - defaultX * 10;
                mapBuilder.append(name).append("$").append(wordPosVoMap.get(defaultX + "|" + defaultY).getCurrentWord()).append("_");
            } else {
                // 横
                String[] item = chengyu.split("~");
                String chengyuId = item[0];
                ChengyuVo chengyuVo = chengyuVoMap.get(chengyuId);
                Integer chengyuFirstWordPosIndex = Integer.valueOf(item[1]);
                String name = chengyuVo.getName();
                Integer defaultX = chengyuFirstWordPosIndex / 10;
                Integer defaultY = chengyuFirstWordPosIndex - defaultX * 10;
                mapBuilder.append(name).append("~").append(wordPosVoMap.get(defaultX + "|" + defaultY).getCurrentWord()).append("_");
            }
        }
        String result = mapBuilder.toString();
        int index = result.lastIndexOf("_");
        result = StringUtils.substring(result, 0, index);
        System.out.println("map解析如下:" + result);
        System.out.println("map源数据如下:" + map);

        System.out.println("=================解析pos======================");
        String newPos1 = StringUtils.substring(pos, StringUtils.indexOf(pos, 'A') + 1, pos.length());

        List<PosVo> posVoList1 = Lists.newArrayList();

        for (int i = 0; i < newPos1.length() - 1;) {
            posVoList1.add(new PosVo(
                    Integer.valueOf(newPos.substring(i, i + 1)),
                    Integer.valueOf(newPos.substring(i + 1, i + 2)),
                    Integer.valueOf(newPos.substring(i, i + 2))
            ));
            i += 2;
        }

        posVoList1.forEach(item-> System.out.print(wordPosVoMap.get(item.getX() + "|" + item.getY()).getCurrentWord() + " "));
        System.out.println(posVoList1.toString());

        System.out.println("==============猜想路径==============");


    }

}
