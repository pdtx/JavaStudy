package leetcode.others;


import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * description:
 *
 * @author fancying
 * create: 2021-03-28 15:34
 **/
public class Solution1011 {

    public static void main(String[] args) {

    }

    public int shipWithinDays(int[] weights, int D) {
        int max = weights[0];
        int avg = 0;
        for (int w : weights) {
            avg += w;
            max = Math.max(max, w);
        }
        avg = avg % D == 0 ? avg / D : avg / D + 1;
        avg = Math.max(avg, max);

        int low = avg;
        int high = avg * 2;



        while (!judge(weights, D, high)) {
            high = high + avg;
        }

        int mid = (high - low)/2 + low;
        while (low < high) {
            if (judge(weights, D, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
            mid = (high - low)/2 + low;
        }

        return low;
    }

    private boolean judge(int[] weights, int D, int high) {
        int tmp, start = 0;
        for (int i = 0; i < D && start < weights.length; i++) {
            tmp = 0;
            while (tmp <= high && start < weights.length) {
                tmp += weights[start++];
            }
            if (tmp > high) {
                start--;
            }
        }
        return start == weights.length;
    }

    @Test
    public void testM(){
        long start = System.currentTimeMillis();

        Assert.assertEquals(660, shipWithinDays( new int[] {361,321,186,186,67,283,36,471,304,218,60,78,149,166,282,384,61,242,426,275,236,221,27,261,487,90,468,19,453,241},15));
        Assert.assertEquals(15, shipWithinDays( new int[] {1,2,3,4,5,6,7,8,9,10},5));
        Assert.assertEquals(6, shipWithinDays( new int[] {3,2,2,4,1,4},3));
        Assert.assertEquals(3, shipWithinDays( new int[] {1,2,3,1,1},4));


        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}