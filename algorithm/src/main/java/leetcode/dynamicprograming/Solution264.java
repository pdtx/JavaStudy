package leetcode.dynamicprograming;


/**
 * description:
 *
 * @author fancying
 * create: 2021-03-28 15:34
 **/
public class Solution264 {

    public static void main(String[] args) {
        System.out.println(nthUglyNumber(12));
    }

    /**
    *  三个指针 每个记录 2 3 5 的位置
    */
    public static int nthUglyNumber(int n) {
        int[] dp = new int[n + 1];

        dp[1] = 1;
        int min = 1;
        int j = 1,k = 1,l = 1;
        for(int i = 2; i <= n; i++) {
            int n2 = dp[j] * 2 == min ? dp[++j] * 2 : dp[j] * 2;
            int n3 = dp[k] * 3 == min ? dp[++k] * 3 : dp[k] * 3;
            int n5 = dp[l] * 5 == min ? dp[++l] * 5 : dp[l] * 5;
            if(n2 <= n3 && n2 <= n5) {
                dp[i] = n2;
                min = n2;
                j++;
            } else if (n3 <= n2 && n3 <= n5){
                dp[i] = n3;
                min = n3;
                k++;
            } else{
                dp[i] = n5;
                min = n5;
                l++;
            }
        }

        return dp[n];
    }


}