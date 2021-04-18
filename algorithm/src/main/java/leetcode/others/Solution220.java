package leetcode.others;


//给你一个整数数组 nums 和两个整数 k 和 t 。请你判断是否存在 两个不同下标 i 和 j，使得 abs(nums[i] - nums[j]) <=
//t ，同时又满足 abs(i - j) <= k 。
//
// 如果存在则返回 true，不存在返回 false。
//
//
//
// 示例 1：
//
//
//输入：nums = [1,2,3,1], k = 3, t = 0
//输出：true
//
// 示例 2：
//
//
//输入：nums = [1,0,1,1], k = 1, t = 2
//输出：true
//
// 示例 3：
//
//
//输入：nums = [1,5,9,1,5,9], k = 2, t = 3
//输出：false
//
//
//
// 提示：
//
//
// 0 <= nums.length <= 2 * 104
// -231 <= nums[i] <= 231 - 1
// 0 <= k <= 104
// 0 <= t <= 231 - 1
import java.util.TreeSet;

/**
 * description:
 *
 * @author fancying
 * create: 2021-03-28 15:34
 **/
public class Solution220 {

    public static void main(String[] args) {
        int[] nums = {1,2,3,1};
        int k = 3;
        int t = 0;
        System.out.println(containsNearbyAlmostDuplicate(nums, k, t));
    }

    /**
    * 对于序列中每一个元素 xx 左侧的至多 kk 个元素，如果这 kk 个元素中存在一个元素落在区间 [x - t, x + t][x−t,x+t] 中，我们就找到了一对符合条件的元素。注意到对于两个相邻的元素，它们各自的左侧的 kk 个元素中有 k - 1k−1 个是重合的。于是我们可以使用滑动窗口的思路，维护一个大小为 kk 的滑动窗口，每次遍历到元素 xx 时，滑动窗口中包含元素 xx 前面的最多 kk 个元素，我们检查窗口中是否存在元素落在区间 [x - t, x + t][x−t,x+t] 中即可。
     *
     * 如果使用队列维护滑动窗口内的元素，由于元素是无序的，我们只能对于每个元素都遍历一次队列来检查是否有元素符合条件。如果数组的长度为 nn，则使用队列的时间复杂度为 O(nk)O(nk)，会超出时间限制。
     *
     * 因此我们希望能够找到一个数据结构维护滑动窗口内的元素，该数据结构需要满足以下操作：
     *
     * 支持添加和删除指定元素的操作，否则我们无法维护滑动窗口；
     *
     * 内部元素有序，支持二分查找的操作，这样我们可以快速判断滑动窗口中是否存在元素满足条件，具体而言，对于元素 xx，当我们希望判断滑动窗口中是否存在某个数 yy 落在区间 [x - t, x + t][x−t,x+t] 中，只需要判断滑动窗口中所有大于等于 x - tx−t 的元素中的最小元素是否小于等于 x + tx+t 即可。
     *
     * 我们可以使用有序集合来支持这些操作。
     *
     * 实现方面，我们在有序集合中查找大于等于 x - tx−t 的最小的元素 yy，如果 yy 存在，且 y \leq x + ty≤x+t，我们就找到了一对符合条件的元素。完成检查后，我们将 xx 插入到有序集合中，如果有序集合中元素数量超过了 kk，我们将有序集合中最早被插入的元素删除即可。
     *
    */
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        int n = nums.length;
        TreeSet<Long> set = new TreeSet<Long>();
        for (int i = 0; i < n; i++) {
            Long ceiling = set.ceiling((long) nums[i] - (long) t);
            if (ceiling != null && ceiling <= (long) nums[i] + (long) t) {
                return true;
            }
            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }

}