/**
 * @description:
 * @author: fancying
 * @create: 2019-03-18 13:08
 **/
package leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class HeapSolution {

    public int[][] kClosest(int[][] points, int K) {

    }

    public static void main(String[] args) {
        int k = 3;
        int[] arr = {4,5,8,2} ;
        KthLargest kthLargest = new KthLargest(3, arr);
        System.out.println(kthLargest.add(3) + " " + kthLargest.add(5) + "  "+ kthLargest.add(10));
        ;   // returns 5
        ;  // returns 5
        kthLargest.add(9);   // returns 8
        kthLargest.add(4);   // returns 8
    }

}

/**
 * 设计一个找到数据流中第K大元素的类（class）。注意是排序后的第K大元素，不是第K个不同的元素。
 *
 * 你的 KthLargest 类需要一个同时接收整数 k 和整数数组nums 的构造器，它包含数据流中的初始元素。每次调用 KthLargest.add，返回当前数据流中第K大的元素。
 *
 * 示例:
 *
 * int k = 3;
 * int[] arr = [4,5,8,2];
 * KthLargest kthLargest = new KthLargest(3, arr);
 * kthLargest.add(3);   // returns 4
 * kthLargest.add(5);   // returns 5
 * kthLargest.add(10);  // returns 5
 * kthLargest.add(9);   // returns 8
 * kthLargest.add(4);   // returns 8
 *
 * note : 排序：Collections.sort(); list.sort()
 * */
class KthLargest {

    int k;
    int [] nums;
    List<Integer> list = new ArrayList<>();

    public KthLargest(int k, int[] nums) {
        this.k = k;
        this.nums = nums;
        for (int i : nums) {
            list.add(i);
        }
    }

    public int add(int val) {
        list.add(val);
       // Comparator<Integer> sort =  (Integer o1, Integer o2) -> (o2.compareTo(o1));
        // list.sort(sort);
        list.sort((Integer o1, Integer o2) -> (o2.compareTo(o1)));
        return list.get(k-1);
    }
}