/**
 * @description: 动态规划专题
 *  重叠子问题 与 最优子结构
 * @author: fancying
 * @create: 2019-04-17 14:32
 **/
package leetcode;


import java.util.Arrays;

public class DynamicPrograming {
    /**
     * 有两种形状的瓷砖：一种是 2x1 的多米诺形，另一种是形如 "L" 的托米诺形。两种形状都可以旋转。
     *
     * XX  <- 多米诺
     *
     * XX  <- "L" 托米诺
     * X
     *
     * 给定 N 的值，有多少种方法可以平铺 2 x N 的面板？返回值 mod 10^9 + 7。
     *
     * （平铺指的是每个正方形都必须有瓷砖覆盖。两个平铺不同，当且仅当面板上有四个方向上的相邻单元中的两个，使得恰好有一个平铺有一个瓷砖占据两个正方形。）
     *
     * 示例:
     * 输入: 3
     * 输出: 5
     * 解释:
     * 下面列出了五种不同的方法，不同字母代表不同瓷砖：
     * XYZ XXZ XYY XXY XYY
     * XYZ YYZ XZZ XYY XXY
     *
     *
     * */
    public int numTilings(int N) {
        if(N == 1)
            return 1;
        if(N == 2)
            return 2;
        if(N == 3)
            return 5;
        int n_3 = 1;
        int n_2 = 2;
        int n_1 = 5;
        int temp = 0;
        for (int i = 4; i <= N; i++) {
            temp = (2 * n_1) % 1000000007  + n_3 % 1000000007 ;
            n_3 = n_2;
            n_2 = n_1;
            n_1 = temp % 1000000007 ;
        }

        return n_1;
    }

    /**
     * 有 n 个城市通过 m 个航班连接。每个航班都从城市 u 开始，以价格 w 抵达 v。
     *
     * 现在给定所有的城市和航班，以及出发城市 src 和目的地 dst，你的任务是找到从 src 到 dst 最多经过 k 站中转的最便宜的价格。 如果没有这样的路线，则输出 -1。
     *
     * 示例 1:
     * 输入:
     * n = 3, edges = [[0,1,100],[1,2,100],[0,2,500]]
     * src = 0, dst = 2, k = 1
     * 输出: 200
     * */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        /**
         动态规划解法, dp[i][k]表示经过k个中转站后到达站i的最低费用
         初始除了dp[src][0]~dp[src][k]之外所有的元素置为无穷大inf
         则状态方程为: 对于所有目标地位i的航班(flight[1] = i)
         只要dp[flight[0]][i-1] != inf 表示出发地可达
         就更新dp[i][k]
         dp[flight[1]][i] = Math.min(dp[flight[1]][i], dp[flight[0]][i-1] + flight[2])
         **/
        int [][] dp = new int[n][K+2];
        for (int i = 0; i < n ; i++)
            Arrays.fill(dp[i],Integer.MAX_VALUE);
        for (int i = 0; i <= K+1; i++)
            dp[src][i] = 0;
        for (int i = 1; i <= K+1; i++) {
            for (int[] flight : flights) {
                if (dp[flight[0]][i-1] != Integer.MAX_VALUE) {
                    dp[flight[1]][i] = Math.min(dp[flight[1]][i], dp[flight[0]][i-1] + flight[2]);
                }
            }
        }
        return dp[dst][K+1] == Integer.MAX_VALUE ? -1 : dp[dst][K+1];
    }

    public static void main(String[] args) {
        System.out.println(new DynamicPrograming().numTilings(70));
    }

}