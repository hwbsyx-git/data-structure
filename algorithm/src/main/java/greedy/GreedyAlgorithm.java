package greedy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class GreedyAlgorithm {
    //贪心算法，要求广播覆盖所有地区，并且选取的广播站最少
    public static void main(String[] args) {
        HashMap<String, HashSet<String>> broadcast = new HashMap<String, HashSet<String>>();
        //创建HashSet存放各电台覆盖的区域
        HashSet hashSet1 = new HashSet();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet hashSet2 = new HashSet();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("上海");

        HashSet hashSet3 = new HashSet();
        hashSet3.add("成都");
        hashSet3.add("广州");

        HashSet hashSet4 = new HashSet();
        hashSet4.add("上海");
        hashSet4.add("天津");
        hashSet4.add("大连");

        HashSet hashSet5 = new HashSet();
        hashSet5.add("杭州");
        hashSet5.add("大连");

        HashSet hashSet6 = new HashSet();
        hashSet6.add("深圳");
        hashSet6.add("大连");

        broadcast.put("K1", hashSet1);
        broadcast.put("K2", hashSet2);
        broadcast.put("K3", hashSet3);
        broadcast.put("K4", hashSet4);
        broadcast.put("K5", hashSet5);
        broadcast.put("K6", hashSet6);

        //定义HashSet存放所有地区
        HashSet<String> allAreas = new HashSet<>();
        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("天津");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("成都");
        allAreas.add("大连");
        allAreas.add("杭州");

        //定义ArrayList存放挑选的广播站
        ArrayList<String> select = new ArrayList<>();

        //定义临时set存放当前遍历的广播站覆盖区与还没有覆盖的区域的交集
        HashSet<String> tempSet = new HashSet<>();

        //定义maxKey保存当前覆盖区域最多的广播站,如果maxKey ！=null 加入select
        String maxKey = null;
        int count = 0; //记录当前广播台包含的未覆盖区域
        HashSet<String> area =null; //当前key的覆盖区
        HashSet<String> maxArea = null; //当前maxKey的覆盖区

        while (allAreas.size() != 0) {  //只要还存在未覆盖区
            //每一次while需要将maxKey重置
            maxKey = null;
            //遍历broadcast取出对应的key
            for (String key : broadcast.keySet()) {
                //每次将tempSet清空
                tempSet.clear();
                //当前key覆盖的区域
                area = broadcast.get(key);
                tempSet.addAll(area);
                //求出tempSet和allArea的交集赋给tempSet
                tempSet.retainAll(allAreas);
                //记录当前覆盖最多的广播站与未覆盖区域的交集
                if (maxKey != null) {
                    maxArea = broadcast.get(maxKey);
                    maxArea.retainAll(allAreas);
                }

                count = tempSet.size();  //当前广播站覆盖区与未覆盖区的交集数目
                //如果当前广播站覆盖区与未覆盖区的交集大于maxKey广播站的交集，就讲maxKey替换为当前广播站
                //count > maxArea.size()  此处体现了贪心算法
                if (count > 0 && (maxKey == null || count > maxArea.size())) {
                    maxKey = key;
                }
            }
            //如果maxKey ！= null就加入到select、中，
            if (maxKey != null) {
                select.add(maxKey);
                //将maxKey广播站覆盖的区域从未覆盖区域中移除，表示已经覆盖。
                allAreas.removeAll(broadcast.get(maxKey));
                broadcast.remove(maxKey);
            }
        }
        System.out.println("选择的结果为" + select);

    }
}
