/**
 * @description: 腾讯牛客笔试
 * @author: fancying
 * @create: 2019-04-08 20:53
 **/
package nowcoder;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static double ans(int n, int w, int [] a) {
        Arrays.sort(a);
        double x = (double)w / 3 / n;
        double min = a[1] <= (double)a[n+1]/2 ? a[1] : (double)a[n+1]/2;
        if (min <= x)
            return 3 * min * n;
        else
            return w;
    }

    private static void process(int num,int[] v,int[] step){
        int p=num;
        int tmp=0;
        while(p<=100000){
            v[p]++;
            step[p]+=tmp++;
            p*=2;
        }
        tmp=1;
        while(num > 0){
            if(num%2==1&&num!=1){
                num/=2;
                v[num]++;
                step[num]+=tmp++;

                int ff=num*2;
                int ftemp=tmp;
                while(ff<=100000){
                    v[ff]++;
                    step[ff]+=ftemp++;
                    ff*=2;
                }
            }
            else{
                num/=2;
                v[num]++;
                step[num]+=tmp++;
            }
        }
    }

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

    public static String ans(String s) {
        StringBuilder sb = new StringBuilder();
        StringBuilder appendStr = new StringBuilder();
        StringBuilder num = new StringBuilder();
        char [] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >= '0' && chars[i] <= '9') {
                num.append(chars[i]);
            }else if (chars[i] == '(') {
                i++;
                while (chars[i] != ')')
                    appendStr.append(chars[i++]);

                for (int j = 1;j <= Integer.valueOf(num.toString()); j++)
                    sb.append(appendStr);
                appendStr.setLength(0);
                num.setLength(0);
            }else {
                sb.append(chars[i]);
            }
        }

        return sb.reverse().toString();
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

    /**
     *  第一行包含空格分隔的两个数字N 和 D
     *
     *  第二行 包含N 个建筑物的位置，每一个位置用一个整数表示字节跳动大街看做一条数轴
     *
     *  输出一个数字表示不同方案的数量 。结果可能溢出，请对99997867取模
     *
     *  思路： 一个数组总任选三个数字， 最大的数字减去最小的数字 需要小于 d
     *      二分搜索 以及累加求和
     * */
    public int answer(int n, int d, int [] num) {
        if (n <= 2)
            return 0;
        if ( n == 3 && num[2] - num[0] <= d)
            return 1;
        int sum = 0;
        for (int i = 0; i < n - 2; ++i) {
            int end = n - 1;
            int start = i + 1;
            int mid = end;
            if (num[end] - num[i] > d){
                mid--;
                while ( end > start) {
                    if (num[mid] - num[i] <= d && num[mid + 1] - num[i] > d) {
                        break;
                    }
                    mid = (start + end) / 2;
                    if (num[mid] - num[i] > d) {
                        end = mid ;
                    } else {
                        start = mid;
                    }
                }
            }

            sum += (mid - i) * (mid - i -1) / 2;
            if (sum >= 99997867)
                sum = sum % 99997867;
        }

        return sum;
    }


    public static void main(String[] args) {

/*        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n + 1];
        a[0] = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++){
            a[i] = sc.nextInt();
        }*/
        //DecimalFormat format   =   new   DecimalFormat("0.000000");
        System.out.println(ans("abc3(A)"));

    }
}

/*        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt();
        int n = sc.nextInt();
        int[] coins = new int[n + 1];
        coins[0] = Integer.MIN_VALUE;
        for(int i = 1; i <= n; i++){
            coins[i] = sc.nextInt();
        }
        Main main = new Main();
        System.out.println(main.count(m,n,coins));*/