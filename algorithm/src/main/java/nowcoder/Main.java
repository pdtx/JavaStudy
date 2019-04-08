/**
 * @description: 腾讯牛客笔试
 * @author: fancying
 * @create: 2019-04-08 20:53
 **/
package nowcoder;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static int count(int n, String str) {
        char[] num = str.toCharArray();
        int num1 = 0;
        int num2 = 0;
        for (int i = 0; i < n  ;i++) {
            if (num[i] == '1')
                num1++;
            else
                num2++;
        }
        return Math.abs(num1 - num2);
    }

    public int count(int m, int n, int[] coins) {
        int ans = 0;
        int sum = 0;
        Arrays.sort(coins);
        if(coins[1] != 1){
            return -1;
        }
        while (true){
            if(sum >= m){
                return ans;
            }
            for(int i = n; i >= 1; i--) {
                if(coins[i] <= sum+1) {
                    sum += coins[i];
                    ans++;
                    break;
                }
            }
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[] coins = new int[n + 1];
        coins[0] = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++){
            coins[i] = sc.nextInt();
        }
        Main main = new Main();
        System.out.println(main.count(m,n,coins));
    }
}