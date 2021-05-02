package leetcode.others;

import org.junit.Assert;
import org.junit.Test;

/**
 * description:
 *
 * @author fancying
 * create: 2021-03-28 15:34
 **/
public class Solution633 {

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
    }

    public boolean judgeSquareSum(int c) {

        int end = (int)Math.sqrt(c/2);
        int y = 0;
        for (int i = 0; i <= end + 1; i++) {
            y = (int) Math.sqrt(c - i*i);
            if (i*i + y*y == c) {
                return true;
            }
        }
        return false;
    }

    @Test
    public void testM(){
        long start = System.currentTimeMillis();
        Assert.assertTrue(judgeSquareSum(0));
        Assert.assertTrue(judgeSquareSum(16));

        Assert.assertFalse(judgeSquareSum(3));
        Assert.assertTrue(judgeSquareSum(5));
        Assert.assertTrue(judgeSquareSum(4));
        Assert.assertTrue(judgeSquareSum(2));
        Assert.assertTrue(judgeSquareSum(1));


        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}