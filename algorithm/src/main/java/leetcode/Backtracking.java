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

/**
 * 字母数字大小全排列
 * */
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


    /**
     * n 皇后问题研究的是如何将 n 个皇后放置在 n×n 的棋盘上，并且使皇后彼此之间不能相互攻击。
     *
     * 给定一个整数 n，返回 n 皇后不同的解决方案的数量。
     * */

    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        boolean[] isUsed = new boolean[n+1];
        int[] p = new int[n+1];
        for (int i = 0; i < n+1 ;i++) {
            isUsed[i] = false;
            p[i] = i;
        }
        generate(1, n, isUsed, p, ans);
        return ans;
    }
    private void generate(int index, int n, boolean[] isUsed, int[] p, List<List<String>> ans) {
        if(index == n + 1) {
            List<String> list = new ArrayList<>();
            for (int i = 1; i <= n; i++) {
                StringBuilder sb = new StringBuilder();
                for (int j = 1; j <= n; j++) {
                    if(j != p[i])
                        sb.append('.');
                    else
                        sb.append('Q');
                }
                list.add(sb.toString());
            }
            ans.add(list);
            return;
        }
        for (int i = index; i <= n; i++) { // 第i列
            if (!isUsed[i]) {  // 第i列还没有皇后
                boolean flag = true; //flag为true表示当前皇后不会与之前的皇后冲突
                for (int pre = 1; pre < index; pre++) { //遍历之前的皇后，查看是否会冲突
                    if (Math.abs(index - pre) == Math.abs(i-p[pre])) { // 在同一个对角线上会冲突
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    p[index] = i;
                    isUsed[i] = true;
                    generate(1 + index, n, isUsed, p, ans);
                    isUsed[i] = false;
                }
            }
        }
    }

    public static void main(String[] args) {
        Backtracking backtracking = new Backtracking();
        //backtracking.letterCasePermutation("a1b2");
/*        for (String s : backtracking.letterCasePermutation("a3124b")) {
            System.out.println(s);
        }*/

    }

}