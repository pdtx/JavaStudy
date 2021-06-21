package leetcode.others;


import org.junit.Test;

/**
 * description:
 *
 * @author fancying
 * create: 2021-03-28 15:34
 **/
public class Solution {

    public static void main(String[] args) {
        double firstPrice = 0.38;
        double boughtNum = 1200;
        double  totalLoseRate = 0.55;
        double lostPriceRate = 0.45;
        double endPrice = 0.05   ;

        cal(firstPrice, boughtNum, totalLoseRate, lostPriceRate, endPrice);

    }



    static void cal(double firstPrice, double boughtNum, double  totalLoseRate, double lostPriceRate, double endPrice) {

        double total = firstPrice * boughtNum;
        double cru = firstPrice;

        // 余额
        double left = 0;
        // 第一次购买
        System.out.println("购买单价 " + cru + "  购买数量 " + boughtNum + "  当前总花费 USTD" + cru * boughtNum + "  历史总花费 USTD " + total );

        while(cru > endPrice) {

            cru *= lostPriceRate;
            left *= lostPriceRate;
            boughtNum = (totalLoseRate * total - left) / (1 - totalLoseRate) / cru;
            total += boughtNum * cru;
            if (boughtNum < 0) {
                boughtNum = 0;
                total = 0;
            }
            left += cru * boughtNum;
            System.out.println("购买单价 " + cru + "  购买数量 " + boughtNum + "  当前总花费 USTD " + cru * boughtNum + "  历史总花费 USTD " + total );
        }

    }





    @Test
    public void testM(){
        long start = System.currentTimeMillis();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}