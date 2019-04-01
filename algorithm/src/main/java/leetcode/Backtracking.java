/**
 * @description: 回溯法一般都用在要给出多个可以实现最终条件的解的最终形式。回溯法要求对解要添加一些约束条件。总的来说，如果要解决一个回溯法的问题，通常要确定三个元素：
 *
 * 1、选择。对于每个特定的解，肯定是由一步步构建而来的，而每一步怎么构建，肯定都是有限个选择，要怎么选择，这个要知道；同时，在编程时候要定下，
 *      优先或合法的每一步选择的顺序，一般是通过多个if或者for循环来排列。
 * 2、条件。对于每个特定的解的某一步，他必然要符合某个解要求符合的条件，如果不符合条件，就要回溯，其实回溯也就是递归调用的返回。
 * 3、结束。当到达一个特定结束条件时候，就认为这个一步步构建的解是符合要求的解了。把解存下来或者打印出来。对于这一步来说，有时候也可以另外写一个issolution函数来进行判断。
 *      注意，当到达第三步后，有时候还需要构建一个数据结构，把符合要求的解存起来，便于当得到所有解后，把解空间输出来。这个数据结构必须是全局的，作为参数之一传递给递归函数。
 * @author: fancying
 * @create: 2019-03-31 19:07
 **/
package leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 在包含问题的所有解的解空间树中，按照深度优先搜索的策略，从根结点出发深度探索解空间树。
 * 当探索到某一结点时，要先判断该结点是否包含问题的解，如果包含，就从该结点出发继续探索下去，如果该结点不包含问题的解，则逐层向其祖先结点回溯。
 * （其实回溯法就是对隐式图的深度优先搜索算法）。 若用回溯法求问题的所有解时，要回溯到根，且根结点的所有可行的子树都要已被搜索遍才结束。
 * 而若使用回溯法求任一个解时，只要搜索到问题的一个解就可以结束。
 * */

public class Backtracking {
    /**
     * 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
     *
     * 说明：解集不能包含重复的子集。
     *
     * 示例:
     *
     * 输入: nums = [1,2,3]
     * 输出:
     * [
     *   [3],
     *   [1],
     *   [2],
     *   [1,2,3],
     *   [1,3],
     *   [2,3],
     *   [1,2],
     *   []
     * ]
     *
     * 思路：回溯算法
     * */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length == 0)
            return list;
        backTracking(list, new ArrayList<>(), nums, 0);
        return list;
    }
    private void backTracking(List<List<Integer>> list, List<Integer> temp, int[] nums, int begin) {
        list.add(new ArrayList<Integer>(temp));
        for (int i = begin; i < nums.length; i++) {
            temp.add(nums[i]);
            backTracking(list, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }

    /**
     * 给定一个没有重复数字的序列，返回其所有可能的全排列。
     *
     * 示例:
     *
     * 输入: [1,2,3]
     * 输出:
     * [
     *   [1,2,3],
     *   [1,3,2],
     *   [2,1,3],
     *   [2,3,1],
     *   [3,1,2],
     *   [3,2,1]
     * ]
     *
     * 思路 ： 交换回溯
     * */
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        if (nums.length == 0)
            return list;
        List<Integer> temp = new ArrayList<>();
        for (int i : nums)
            temp.add(i);
        backTrackingFullPermutation(list, temp, nums.length - 1);
        return list;
    }
    private void backTrackingFullPermutation(List<List<Integer>> list, List<Integer> temp, int begin) {
        list.add(new ArrayList<>(temp));
        while (begin > 0) {
            for (int i = begin - 1; i >= 0; i--) {
                int val = temp.get(i);
                temp.set(i, temp.get(begin));
                temp.set(begin, val);
                backTrackingFullPermutation(list, temp, begin - 1);
                // 递归函数返回后，状态可以恢复到递归前，以此达到真正回溯
                temp.set(begin, temp.get(i));
                temp.set(i, val);
            }
            begin--;
        }
    }


    public List<String> letterCasePermutation(String S) {
        List<String> list = new ArrayList<>();
        if (S.length() == 0) {
            list.add("");
            return list;
        }
        char[] s = S.toCharArray();
        List<Integer> index = new ArrayList<>();
        for (int i = 0;i < s.length ; i++)
            if (s[i] < '0' || s[i] > '9')
                index.add(i);

        if (index.size() == 0)
            list.add(S);
        else
            backTracking(list, s, index, 0);
        return list;
    }
    private void backTracking(List<String> list, char[] s, List<Integer> index, int begin) {
        list.add(String.valueOf(s));
        while (begin < index.size()) {
            s[index.get(begin)] = charInverse(s[index.get(begin)]);
            backTracking(list, s, index, begin + 1);
            s[index.get(begin)] = charInverse(s[index.get(begin)]);
            begin++;
        }
    }
    private char charInverse(char ch) {
        if (ch >= 'a' && ch <= 'z')
            return Character.toUpperCase(ch);
        return Character.toLowerCase(ch);
    }


    public static void main(String[] args) {
        Backtracking backtracking = new Backtracking();
        //backtracking.letterCasePermutation("a1b2");
        for (String s : backtracking.letterCasePermutation("a3124b")) {
            System.out.println(s);
        }
    }

}