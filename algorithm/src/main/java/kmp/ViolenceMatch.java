package kmp;

public class ViolenceMatch {
    //字符串匹配。匹配成功返回第一个位置的索引，匹配失败返回-1
    public static void main(String[] args) {
        String str1 = "ABCD DDSF FABDC C";
        String str2 = "BDC";
        int result = violenceMatch(str1, str2);
        System.out.println("第一次匹配到的索引为" + result);
    }

    public static int violenceMatch(String str1, String str2) {
        char[] chars1 = str1.toCharArray();
        char[] chars2 = str2.toCharArray();
        int i = 0; //char1的指针
        int j = 0; //char2的指针
        int len1 = chars1.length;
        int len2 = chars2.length;
        while (i < len1 && j < len2) {
            if (chars1[i] == chars2[j]) { //匹配成功，继续移位匹配
                i++;
                j++;
            } else { //匹配失败，将i返回匹配开始的i的后一位，j重置为0
                i = i - j + 1;
                j = 0;
            }
        }
        //判断是否匹配到
        if (j == len2) { //说明子串已经遍历完
            return i - j;
        } else { //说明s1已经遍历完
            return -1;
        }
    }
}
