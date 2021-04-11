package leetcode.others;


import java.util.Arrays;

/**
 * description:
 *
 * @author fancying
 * create: 2021-03-28 15:34
 **/
public class Solution17_21 {

    public static void main(String[] args) {
        // 6
        int [] nums1 = {0,1,0,2,1,0,1,3,2,1,2,1};
        // 9
        int [] nums2 = {3,2,1,0,1,2,3};
        // 9
        int [] nums3 = {4,2,0,3,2,5};
        //83  43 38 + 2
        int [] nums4 = {6,4,2,0,3,2,0,3,1,4,5,3,2,7,5,3,0,1,2,1,3,4,6,8,1,3};

        // 12 * 6 = 72 -29 = 43
        int [] nums5 = {6,4,2,0,3,2,0,3,1,4,5,3,2,7};

        System.out.println(trap(nums1));
        System.out.println(trap(nums2));
        System.out.println(trap(nums3));
        System.out.println(trap(nums4));
        System.out.println(trap(nums5));
    }

    /**
    * 解题思路： 直方图的水量 遍历每一个位 依次找比自己大的柱子 找到则计算体积；没有比自己大的柱子则后面节点中最大的柱子 计算体积 继续跳跃
    */
    public static int trap(int[] height) {
        int length = height.length;
        if (length < 3) {
            return 0;
        }

        int result = 0;
        for (int i = 0; i < length - 1;) {

            int j = i + 1;
            int maxIndex = j;
            int max = height[j];
            int tmpV = 0;
            int midSum = 0;
            while (j < length) {
                midSum += height[j];
                if (height[j] >= height[i]) {
                    midSum -= height[j];
                    result += height[i] * (j - i - 1) - midSum;
                    i = j;
                    break;
                } else if (max <= height[j]){
                    max = height[j];
                    maxIndex = j;
                    tmpV = midSum -  height[j];
                }
                j++;
            }

            if (j == length) {
                result += max * (maxIndex - i - 1) -tmpV ;
                i = maxIndex;
            }

        }



        return result;
    }

}