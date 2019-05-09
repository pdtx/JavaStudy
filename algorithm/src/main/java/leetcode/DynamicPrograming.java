/**
 * @description: 动态规划专题
 *  重叠子问题 与 最优子结构
 * @author: fancying
 * @create: 2019-04-17 14:32
 **/
package leetcode;


import nowcoder.Main;

import java.util.*;

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

    /**
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
     *
     * 示例 1:
     *
     * 输入: [2,3,2]
     * 输出: 3
     * 解释: 你不能先偷窃 1 号房屋（金额 = 2），然后偷窃 3 号房屋（金额 = 2）, 因为他们是相邻的。
     *
     * 分成两种情况选第一个或者不选第一个
     * 选不选第i 家 由 i-1 家的总和以及i-2
     * */
    public int rob(int[] nums) {
        if (nums.length == 0)
            return 0;
        if (nums.length == 1)
            return nums[0];

        int dpPrePre = nums[0];
        int dpPre = nums[0];
        for (int i = 2; i < nums.length - 1; i++) {
            int temp = Math.max(dpPrePre + nums[i], dpPre);
            dpPrePre = dpPre;
            dpPre = temp;
        }
        int include = dpPre;

        dpPrePre = 0;
        dpPre = nums[1];
        for (int i = 2; i < nums.length ; i++) {
            int temp = Math.max(dpPrePre + nums[i], dpPre);
            dpPrePre = dpPre;
            dpPre = temp;
        }

        return Math.max(include,dpPre);
    }


    /**
     * 给出一个由无重复的正整数组成的集合，找出其中最大的整除子集，子集中任意一对 (Si，Sj) 都要满足：Si % Sj = 0 或 Sj % Si = 0。
     *
     * 如果有多个目标子集，返回其中任何一个均可。
     *
     *
     *
     * 示例 1:
     *
     * 输入: [1,2,3]
     * 输出: [1,2] (当然, [1,3] 也正确)
     *
     * 排序，dp记录最大整除子集个数
     * */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);
        int[] dp = new int[nums.length];
        Map<Integer, List<Integer>> map = new HashMap<>();
        Arrays.fill(dp, 0);
        List<Integer> ans = new ArrayList<>();
        if (nums.length == 0)
            return ans;
        ans.add(nums[0]);
        map.put(0,ans);
        dp[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            int max = 0;
            int index = i;
            for (int j = i - 1; j >=0 ;j--) {
                if (nums[i] % nums[j] == 0 && max < dp[j]) {
                    max =  dp[j];
                    index = j;
                }
            }
            dp[i] = max + 1;
            List<Integer> temp;
            if (index != i)
                temp = new ArrayList<>(map.get(index));
            else
                temp = new ArrayList<>();
            temp.add(nums[i]);
            map.put(i,temp);
        }

        int max = dp[0];
        int index = 0;
        for (int i = 1 ;i < dp.length; i++) {
            if (max < dp[i]) {
                max = dp[i];
                index = i;
            }
        }
        ans = map.get(index);
        ans.sort(Integer::compareTo);
        return ans;
    }


    /**
     * 把字符串 s 看作是“abcdefghijklmnopqrstuvwxyz”的无限环绕字符串，所以 s 看起来是这样的："...zabcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyzabcd....".
     *
     * 现在我们有了另一个字符串 p 。你需要的是找出 s 中有多少个唯一的 p 的非空子串，尤其是当你的输入是字符串 p ，你需要输出字符串 s 中 p 的不同的非空子串的数目。
     *
     * 注意: p 仅由小写的英文字母组成，p 的大小可能超过 10000。
     *
     *
     *
     * 示例 1:
     *
     * 输入: "a"
     * 输出: 1
     * 解释: 字符串 S 中只有一个"a"子字符。
     * */
    public int findSubstringInWraproundString(String p) {
        return 0;
    }


    /**
     * LCS
     * */
    public static int lcs (String str1, String str2) {
        int dp[][] = new int[str1.length()+1][str2.length()+1];
/*        int dp1[] = new int[str1.length() + 1];
        int dp2[] = new int[str2.length() + 1];
        Arrays.fill(dp1,0);
        Arrays.fill(dp2,0);*/
        for (int i = 0; i <= str1.length(); i++) {
            dp[i][0] = 0;
        }
        for (int j = 0; j <= str2.length(); j++) {
            dp[0][j] = 0;
        }
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++){
                if (str1.charAt(i-1) == str2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                }
            }
        }
        return dp[str1.length()][str2.length()];
    }


    /**
     * 最长回文子串
     * */
    public static String longestPalindrome(String s) {
        if (s.length() == 0)
            return "";

        int dp[][]  = new int[s.length()][s.length()];
        int max = 1;
        int start = 0;
        int end = 0;
        dp[s.length()-1][s.length()-1] = 1;
        for (int i = 0; i < s.length() - 1; i++) {
            dp[i][i] = 1;
            if (s.charAt(i) == s.charAt(i+1)) {
                dp[i][i + 1] = 1;
                max = 2;
                start = i;
                end = i + 1;
            }else
                dp[i][i+1] = 0;
        }

        for (int L = 3; L <= s.length(); L++) {
            for (int i = 0; i + L -1 < s.length(); i++) {
                int j = i + L -1;
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i+1][j-1] == 0 ? 0 : 1;
                    if (dp[i][j] == 1 && j - i + 1 > max){
                        max = j - i + 1;
                        start = i;
                        end = j;
                    }
                }else {
                    dp[i][j] = 0;
                }
            }
        }

        return s.substring(start, end + 1);
    }

    /**
     * 最长上升子序列
     * 给定一个无序的整数数组，找到其中最长上升子序列的长度。
     *
     * */
    public static int lengthOfLIS(int[] nums) {
        int max = 0;
        int dp[] = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            dp[i] = 1;
            for (int j = i - 1; j >= 0; j--) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }

    /**
     * 01 背包问题
     * */
    public static int package01(int w[], int c[], int V) {
        int dp[] = new int[V+1];
        Arrays.fill(dp, 0);

        for (int i = 0; i < w.length; i++) {
            for (int v = V; v >= w[i]; v--) {
                dp[v] = Math.max(dp[v], dp[v - w[i]] + c[i]);
            }
        }

        int max = 0;
        for (int v = 0; v <= V; v++) {
            max = Math.max(max, dp[v]);
        }

        return max;
    }

    /**
     * 完全背包问题
     * */
    public static int packa() {
        return 0;
    }

    public static void main(String[] args) {
/*        int [] nums = {472,105,328,922,963,625,827,232,913,832,730,152,697,614,570,639,459,895,70,488,239,596,930,402,904,197,911,126,876,535,597,332,863,870,646,839,878,751,528,959,929,679,7,543,248,353,494,67,841,732,662,122,485,565,318,747,42,545,282,638,228,496,860,874,334,556,103,437,83,61,365,761,259,361,64,612,206,131,452,938,144,573,774,243,521,102,787,154,92,15,649,951,240,506,342,770,940,135,700,820,393,412,884,356,618,366,807,907,861,380,32,921,622,23,760,57,466,416,391,273,706,512,212,998,263,279,743,27,514,98,996,231,905,902,91,636,285,364,609,189,738,368,203,846,6,970,210,748,594,268,157,726,257,419,999,797,869,96,792,451,352,693,894,987,978,595,518,346,315,114,900,160,119,721,218,814,988,477,66,733,740,421,537,642,178,882,21,337,868,350,945,815,219,290,129,684,640,155,409,712,372,222,848,765,932,283,14,819,544,146,425,958,644,903,458,473,85,627,845,641,340,198,961,456,992,495,413,170,390,559,143,166,428,100,809,647,354,619,435,250,837,533,530,883,948,816,432,261,623,560,503,406,916,147,826,277,312,253,729,251,434,443,686,683,944,186,829,898,63,457,405,417,811,579,957,404,532,621,8,260,445,45,887,296,728,236,591,447,422,696,481,893,264,974,701,714,379,12,482,541,942,385,849,345,469,705,527,571,799,962,650,387,552,97,808,971,242,803,36,505,777,89,507,52,47,436,703,716,794,229,628,108,800,779,69,449,165,973,897,324,943,689,917,745,76,873,164,616,9,24,237,739,892,818,718,351,617,420,744,314,687,956,782,781,297,424,737,912,522,272,408,369,645,275,611,840,382,125,890,162,568,295,349,301,997,38,908,179,333,439,899,329,65,159,142,121,923,247,854,802,330,335,43,843,254,137,954,515,790,950,566,857,490,822,519,286,150,583,796,291,941,37,742,723,784,976,374,780,888,569,879,805,88,468,463,969,258,877,176,564,654,798,789,793,401,493,557,592,202,418,115,852,462,953,717,338,480,981,396,634,836,681,629,935,553,540,397,665,758,817,224,821,158,968,323,17,3,776,177,81,906,749,344,169,140,926,118,501,927,504,598,875,34,643,548,813,470,508,736,602,378,59,652,994,915,249,213,661,450,39,267,586,55,727,25,113,141,734,972,872,300,516,526,270,112,555,171,766,256,127,10,316,554,194,448,657,847,174,241,620,68,562,426,133,265,491,909,989,168,173,167,928,41,567,986,724,658,208,699,709,306,933,2,653,881,298,750,577,702,319,694,394,601,651,309,16,310,668,486,192,136,33,856,134,563,370,358,498,593,230,484,308,851,235,51,551,536,980,415,810,246,307,79,221,580,576,101,438,791,455,673,75,610,376,44,624,399,294,271,195,725,637,78,773,806,967,163,871,389,453,558,925,193,467,274,371,788,599};
        for (Integer i : new DynamicPrograming().largestDivisibleSubset(nums)) {
            System.out.println(i);
        }*/
        int [] nums = {3,5,1,2,2};
        int [] nums2 = {4,5,2,1,3};
        System.out.println(package01(nums, nums2, 8));

    }

}