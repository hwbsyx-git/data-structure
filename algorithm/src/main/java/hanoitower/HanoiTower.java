package hanoitower;

public class HanoiTower {

    public static void main(String[] args) {
        int n = 5;  //盘子个数
        char a = 'A';
        char b = 'B';
        char c = 'C';
        hanoiTower(n,a,b,c);
    }

    /**
     * 汉诺塔，分治算法。汉诺塔移动次数。最少为2*n-1
     * 当只有1个盘子时，直接从A移动到C。
     * 当有2个或者2个以上时候，看做2部分，最下面一个盘子和上面所有盘子，分3步
     * 1.将上面所有盘子移动到B
     * 2.将最下面的盘子从A移动到C
     * 3.将B的所有盘子移动到C
     * @param n 盘子的个数，默认都在A上
     * @param a A柱子    开始盘子全在A
     * @param b B柱子
     * @param c C柱子    最终盘子要全在C切按大小排好顺序
     */
    public static void hanoiTower(int n,char a,char b ,char c) {
        if (n <= 0) {
            System.out.println("盘子数目最少为1");
        }
        if (n == 1) {  //如果只有一个盘子
            System.out.println("移动步骤为" + a + "=>" + c );
        }
        if (n >= 2) {
            //将上面所有盘子移动到B
            hanoiTower(n-1,a,c,b);
            //将A最下面的盘子移动到C
            hanoiTower(1,a,b,c);
            //将B上所有的盘子移动到C
            hanoiTower(n-1,b,a,c);
        }
    }
}
