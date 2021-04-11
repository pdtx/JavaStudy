//编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
//
//
// 每行中的整数从左到右按升序排列。
// 每行的第一个整数大于前一行的最后一个整数。
//
//
//
//
// 示例 1：
//
//
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
//输出：true
//
//
// 示例 2：
//
//
//输入：matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
//输出：false
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
// -104 <= matrix[i][j], target <= 104
//
// Related Topics 数组 二分查找

package leetcode.others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * description:
 *
 * @author fancying
 * create: 2021-03-28 15:34
 **/
public class Solution74 {



    public static void main(String[] args) {
        int[][] matrix =  {{1,3,5,7},{10,11,16,20},{23,30,34,60}};

        System.out.println(searchMatrix(matrix, 1));
        System.out.println(searchMatrix(matrix, 10));
        System.out.println(searchMatrix(matrix, 23));
        System.out.println(searchMatrix(matrix, 60));

        System.out.println(searchMatrix(matrix, 61));
        System.out.println(searchMatrix(matrix, 0));
        System.out.println(searchMatrix(matrix, 8));
    }

    public static boolean searchMatrix(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;

        int begin = -1;
        int end = m - 1;
        while (begin < end ){
            int row = (end + begin + 1)/2 ;
            if (matrix[row][0] == target) {
                return true;
            } else if (matrix[row][0] < target) {
                begin = row;
            }else if (matrix[row][0] > target) {
                end = row - 1;
            }

        }
        if (begin < 0) {
            return false;
        }

        int[] nums = matrix[begin];
        begin = 0;
        end = n - 1;

        while (begin <= end) {
            //int mid = (end - begin + 1)/2 + begin;
            int mid = (end + begin)/2;

            if (nums[mid] == target) {
                return true;
            }

            if (nums[mid] < target) {
                begin = mid + 1;
            }

            if (nums[end] > target){
                end = mid - 1;
            }

        }

        return false;
    }


}
