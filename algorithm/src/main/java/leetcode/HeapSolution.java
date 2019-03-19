/**
 * @description:
 * @author: fancying
 * @create: 2019-03-18 13:08
 **/
package leetcode;

import java.util.*;

public class HeapSolution {


    /**
     * 我们有一个由平面上的点组成的列表 points。需要从中找出 K 个距离原点 (0, 0) 最近的点。
     *
     * （这里，平面上两点之间的距离是欧几里德距离。）
     *
     * 你可以按任何顺序返回答案。除了点坐标的顺序之外，答案确保是唯一的。
     *
     *
     *
     * 示例 1：
     *
     * 输入：points = [[1,3],[-2,2]], K = 1
     * 输出：[[-2,2]]
     * 解释：
     * (1, 3) 和原点之间的距离为 sqrt(10)，
     * (-2, 2) 和原点之间的距离为 sqrt(8)，
     * 由于 sqrt(8) < sqrt(10)，(-2, 2) 离原点更近。
     * 我们只需要距离原点最近的 K = 1 个点，所以答案就是 [[-2,2]]。
     *
     * 注意：默认是小顶堆 大顶堆 需要重写 Comparator  queue.add() 以及 queue.poll()
     * */
    public int[][] kClosest(int[][] points, int K) {
        PriorityQueue<int []> queue = new PriorityQueue<>((int [] o1, int [] o2) -> (distance(o2) - distance(o1)));
        for (int [] point : points) {
            queue.add(point);
            if (queue.size() > K) {
                queue.poll();
            }
        }
        int [][] answer = new int[K][2];
        int i = 0;
        while (!queue.isEmpty()) {
            answer[i++] = queue.poll();
        }
        return answer;
    }

    private Integer distance(int [] point){
        return point[0] * point[0] + point[1] * point[1] ;
    }


    // 以下的main函数供测试
    public static void main(String[] args) {
        int k = 3;
        int[] arr = {4,5,8,2} ;
        KthLargest kthLargest = new KthLargest(3, arr);
        System.out.println();

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