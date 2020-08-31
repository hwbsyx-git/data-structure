package dynamicproblem;

public class PackageProblem {
    //01背包问题，指定背包最大容量，指定物品重量和价值，每件物品只能放入一件
    public static void main(String[] args) {
        int[] w = {1, 4, 2, 3}; //物品重量
        int[] value = {1500, 3000, 2000, 1800}; //物品对应的价格
        int m = 4; //背包容量
        int n = value.length; //物品种类
        //构建二维数组v保存各种情况的最优解
        int[][] v = new int[n + 1][m + 1]; //表示前i个物品能够装入容量为j的背包中的最大价值
        //构建二维数组p保存放入商品的情况
        int[][] p = new int[n + 1][m + 1];
        //将第一行第一列设置为0
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }
        //开始循环往v中存入最优解.不考虑第一行和第一列，所以i，j初始值从1开始
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (w[i - 1] > j) { //当前商品重量大于背包容量，直接取上一次的最优解
                    v[i][j] = v[i - 1][j];
                } else { //当前商品重量小于等于，取上一次最优解和当前最优解的最大值作为这次的最优解
                    if (v[i - 1][j] < value[i - 1] + v[i - 1][j - w[i - 1]]) { //上一次最优解比当前最优解小
                        v[i][j] = value[i - 1] + v[i - 1][j - w[i - 1]];
                        p[i][j] = 1;    //记录放入背包
                    } else { //上一次最优解依然是这一次最优解
                        v[i][j] = v[i - 1][j];  //此时没有记录放入背包是因为上一次最优解走的是if中的语句，已经存入过。
                    }
                }
            }
        }

        //输出每次记录表格
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[0].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        //输出最优解放入情况
        int i = p.length - 1;
        int j = p[0].length - 1;
        //从后往前输出
        while (i >= 0 && j >= 0) {
            if (p[i][j] == 1) {
                System.out.printf("第%d件商品放入背包\n", i);
                j -= w[i - 1];  //计算放入当前商品后背包剩余容量，当前商品的下标为i-1，不是i。
            }
            i--;
        }
    }


}
