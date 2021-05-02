package leetcode.dynamicprograming;


import org.junit.Assert;
import org.junit.Test;

import java.util.*;

/**
 * description:
 *
 * @author fancying
 * create: 2021-03-28 15:34
 **/
public class Solution544 {

    public static void main(String[] args) {

    }

    public int leastBricks(List<List<Integer>> wall) {
        int tmp;
        Map<Integer, Integer> compose = new HashMap<>(16);

        int index = 0;
        for(List<Integer> list : wall) {
            tmp = 0;
            for (int i = 0; i < list.size() - 1; i++){
                tmp += list.get(i);
                if (!compose.containsKey(tmp)) {
                    compose.put(tmp, index++);
                }
            }
        }

        int[] dp = new int [compose.size() + 1] ;
        Arrays.fill(dp, wall.size());

        for(List<Integer> list : wall) {
            tmp = 0;
            for(int i : list) {
                tmp += i;
                dp[compose.get(tmp)] -= 1;
            }


        }

//        int res = wall.size();
//
//        for (int i = 0; i < compose.size() + 1; i++) {
//            if(dp[i] < res) {
//                res = dp[i];
//            }
//        }
//        Arrays.stream(dp).min().getAsInt()
        return Arrays.stream(dp).min().getAsInt();
    }



    @Test
    public void testM(){
        long start = System.currentTimeMillis();

        List<Integer> l1 = Arrays.asList(1,1,1,1,1,1,1,1,1,1);
        List<List<Integer>> wall2 = new ArrayList<>();
        wall2.add(l1);
        wall2.add(l1);
        wall2.add(l1);
        Assert.assertEquals(0, leastBricks(wall2));

        List<Integer> list1 = Arrays.asList(1, 2, 2, 1);
        List<Integer> list2 = Arrays.asList(3,1,2);
        List<Integer> list3 = Arrays.asList(1,3,2);
        List<Integer> list4 = Arrays.asList(2,4);
        List<Integer> list5 = Arrays.asList(3,1,2);
        List<Integer> list6 = Arrays.asList(1,3,1,1);

        List<List<Integer>> wall = new ArrayList<>();
        wall.add(list1);
        wall.add(list2);
        wall.add(list3);
        wall.add(list4);
        wall.add(list5);
        wall.add(list6);

        Assert.assertEquals(2, leastBricks(wall));


        l1 = Collections.singletonList(1);
        wall2 = new ArrayList<>();
        wall2.add(l1);
        wall2.add(l1);
        wall2.add(l1);
        Assert.assertEquals(3, leastBricks(wall2));

        l1 = Collections.singletonList(1);
        wall2 = new ArrayList<>();
        wall2.add(l1);
        Assert.assertEquals(1, leastBricks(wall2));

        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}