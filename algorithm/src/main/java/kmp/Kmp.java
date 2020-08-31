package kmp;

public class Kmp {
    public static void main(String[] args) {
        String str1 = "BBC ABCDAB ABCDABCDABDE";
        String str2 = "ABCDABD";
        int[] next = kmpNext(str2);
        int index = kmp(str1, str2, next);
        System.out.print("部分匹配表为: ");
        for (int i = 0; i < next.length; i++) {
            System.out.print(next[i]+" ");
        }
        System.out.println();
        System.out.println("目标串在长串出现的索引为" + index);
        /*
        *
        *
        * */
    }

    /**
     * KMP算法  遇到不匹配时候回溯：移动位数 = 已匹配的字符数 - 对应的部分匹配数
     * @param str1  长串
     * @param str2  目标串
     * @param next  部分匹配表
     * @return
     */
    public static int kmp(String str1, String str2, int[] next) {
        //遍历长串查找子串
        for (int i = 0, j = 0; i < str1.length(); i++) {  //i是长串索引，j是目标串索引
            //如果str.charAt(i) != str.charAt(j)需要重新处理j
            while (j > 0 && str1.charAt(i) != str2.charAt(j)) {
                j = next[j - 1];
            }
            if (str1.charAt(i) == str2.charAt(j)) {
                j++;
            }
            if (j == str2.length()) {
                return i - j + 1; //这里由于j已经+1.而i还没有进行下一次循环+1，所以i-j+！
            }
        }
        return -1; //长串遍历完还没返回，没找到，返回-1
    }

    //获取目标子串的部分匹配表.部分匹配表就是前缀和后缀中重复元素的个数，
    // 如AB,前缀为A,后缀为B。 部分匹配数为0
    //ABC,前缀为A AB，后缀为BC C。部分匹配数为0
    //ABCA 前缀为A AB ABC 后缀为A CA BCA.其中A重复。部分匹配数为1.
    public static int[] kmpNext(String str) {
        //创建一个数组保存部分匹配表
        int[] next = new int[str.length()];
        //如果字符串长度为1，部分匹配数就为0
        next[0] = 0;
        for (int i = 1, j = 0; i < str.length(); i++) { //i从1开始，因为i=0时候next[0]=0
            //当str.charAt(i) != str.charAt(j)时候，需要从next[j-1]获取新的j，直到发现str.charAt(i) == str.charAt(j)才退出
            while (j > 0 && str.charAt(i) != str.charAt(j)) {
                j = next[j - 1];
            }
            //当str.charAt(i) == str.charAt(j)时，部分匹配值就+1
            if (str.charAt(i) == str.charAt(j)) {
                j++;
            }
            next[i] = j;
        }
        return next;
    }
}
