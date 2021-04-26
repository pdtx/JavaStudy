package leetcode.dynamicprograming;

//给你一个 m x n 的矩阵 matrix 和一个整数 k ，找出并返回矩阵内部矩形区域的不超过 k 的最大数值和。
//
// 题目数据保证总会存在一个数值和不超过 k 的矩形区域。
//
//
//
// 示例 1：
//
//
//输入：matrix = [[1,0,1],[0,-2,3]], k = 2
//输出：2
//解释：蓝色边框圈出来的矩形区域 [[0, 1], [-2, 3]] 的数值和是 2，且 2 是不超过 k 的最大数字（k = 2）。
//
//
// 示例 2：
//
//
//输入：matrix = [[2,2,-1]], k = 3
//输出：3
//
//
//
//
// 提示：
//
//
// m == matrix.length
// n == matrix[i].length
// 1 <= m, n <= 100
// -100 <= matrix[i][j] <= 100
// -105 <= k <= 105
//
//
//
//
// 进阶：如果行数远大于列数，该如何设计解决方案？
// Related Topics 队列 二分查找 动态规划
import org.junit.Assert;
import org.junit.Test;

/**
 * description:
 *
 * @author fancying
 * create: 2021-03-28 15:34
 **/
public class Solution363 {

    public static void main(String[] args) {


    }

    /**
     * des:
     * 横竖遍历每一种情况 因为raw 远大于按 Column 所以先做列的保存  dp 记录上一次的结果
     *
     */
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int row = matrix.length;
        int column = matrix[0].length;

        int kColumn = column * (1 + column) / 2;
        int[][] dp = new int[row][kColumn];
        int start, pre, res = -1000;
        for (int i = 0; i < row; i++) {
            start = 0;
            for (int j = 0; j < column; j++) {
                if (matrix[i][j] == k) {
                    return k;
                }
                dp[i][start++] = matrix[i][j];
                pre = matrix[i][j];
                for (int r = j + 1; r < column; r++) {
                    dp[i][start] = matrix[i][r] + pre;
                    pre = dp[i][start];
                    if (dp[i][start++] == k) {
                        return k;
                    }
                }
            }
        }

        int tmp = -100;
        for (int j = 0; j < kColumn; j++) {
            for (int i = 0; i < row; i++) {
                pre = dp[i][j];
                if (pre < k && pre > res) {
                    res = pre;
                }
                for (int r = i + 1; r < row; r++) {
                    tmp = dp[r][j] + pre;
                    pre = tmp;
                    if (tmp == k) {
                        return k;
                    }
                    if (tmp < k && tmp > res) {
                     res = tmp;
                    }
                }
            }

        }


        return res;
    }

    @Test
    public void test(){
        int[][] nums = {{1,0,1},{0,-2,3}};

        int[][] nums2 = {{1,0,1,12,45,-2,44,-88,70,4,8},
                {1,6,1,12,45,-32,34,-88,70,4,8},
                {1,0,1,12,45,-32,44,-88,70,4,8},
                {1,0,1,-9,45,-32,4,-88,0,4,8},
                {1,0,1,12,5,-32,44,18,70,4,8},
                {11,0,1,12,45,-32,44,78,70,4,8}};
        int[][] nums3 = {{2,2,-1}};
        Assert.assertEquals(-1, maxSumSubmatrix(nums3 , 0));
        Assert.assertEquals(4, maxSumSubmatrix(nums, 4));
        Assert.assertEquals(2, maxSumSubmatrix(nums, 2));

        Assert.assertEquals(100, maxSumSubmatrix(nums2, 100));

    }

}