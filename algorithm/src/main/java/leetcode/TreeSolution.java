/**
 * @description:
 * @author: fancying
 * @create: 2019-03-19 16:19
 **/
package leetcode;

import java.util.*;

public class TreeSolution {

    /**
     * 给定一棵二叉树，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
     *
     * 示例:
     *
     * 输入: [1,2,3,null,5,null,4]
     * 输出: [1, 3, 4]
     * 解释:
     *
     *    1            <---
     *  /   \
     * 2     3         <---
     *  \     \
     *   5     4       <---
     *
     *   思路 队列记录每一层的节点 层层遍历 知道最后没有节点
     * */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (root == null)
            return list;
        queue.add(root);
        TreeNode treeNode;
        while (true){
            int len = queue.size();
            if (len == 0){
                break;
            }
            list.add(((ArrayDeque<TreeNode>) queue).getLast().val);
            while (len > 0) {
                treeNode = queue.poll();
                len--;
                if (treeNode.left != null) {
                    queue.add(treeNode.left);
                }
                if (treeNode.right != null) {
                    queue.add(treeNode.right);
                }
            }
        }
        return list;
    }

    /**
     * 给出二叉树的根，找出出现次数最多的子树元素和。一个结点的子树元素和定义为以该结点为根的二叉树上所有结点的元素之和（包括结点本身）。然后求出出现次数最多的子树元素和。如果有多个元素出现的次数相同，返回所有出现次数最多的元素（不限顺序）。
     *
     *
     *
     * 示例 1
     * 输入:
     *
     *   5
     *  /  \
     * 2   -3
     *
     * 返回 [2, -3, 4]，所有的值均只出现一次，以任意顺序返回所有值。
     *
     * getSum 递归一遍求出所有的节点对应的 子树元素和
     * */
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<TreeNode,Integer> map = new HashMap<>();
        Map<Integer,Integer> res = new HashMap<>();
        if (root == null){
            return new int[0];
        }
        getSum(root,map);
        int maxTimes = 1;
        for ( Integer i : map.values()) {
            if (!res.containsKey(i)) {
                res.put(i,1);
            } else {
                res.put(i,res.get(i) + 1);
                if (maxTimes < res.get(i))
                    maxTimes = res.get(i);
            }
        }
        List<Integer> list = new ArrayList<>();
        for (Integer i : res.keySet()) {
            if (maxTimes == res.get(i))
                list.add(i);
        }
        int j = 0;
        int [] results = new int[list.size()];
        for (int i : list)
            results[j++] = i;
        return results;
    }
    private int getSum(TreeNode node,Map map) {
        if (node == null)
            return 0;
        if (node.right == null && node.left == null) {
            map.put(node,node.val);
            return node.val;
        }
        if (!map.containsKey(node))
            map.put(node,node.val + getSum(node.left,map) + getSum(node.right,map));
        return (Integer) map.get(node);
    }

    /**
     *  您需要在二叉树的每一行中找到最大的值。
     *
     * 示例：
     *
     * 输入:
     *
     *           1
     *          / \
     *         3   2
     *        / \   \
     *       5   3   9
     *
     * 输出: [1, 3, 9]
     *  思路 ： 记录每一层的节点 层层遍历 找出每一层的最大值 不需要先进先出的特性可以用List
     * */
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        List<TreeNode> nodeList = new ArrayList<>();
        if (root == null)
            return list;
        nodeList.add(root);
        while (true) {
            int max = Integer.MIN_VALUE;
            int len = nodeList.size();
            if (len == 0)
                break;
            while (len > 0) {
                TreeNode node = nodeList.remove(--len);
                max = max < node.val ? node.val : max;
                if (node.left != null)
                    nodeList.add(node.left);
                if (node.right != null)
                    nodeList.add(node.right);
            }
            list.add(max);
        }
        return list;
    }


    /**
     * 给定一个二叉树，返回它的 后序 遍历
     * */
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        postOrder(root,list);
        return list;
    }
    //递归实现
    private void post(TreeNode node, List list) {
        if (node != null) {
            post(node.left, list);
            post(node.right, list);
            list.add(node.val);
        }
    }
    //非递归实现
    private void postOrder(TreeNode node, List list) {
        Stack<TreeNode> stack = new Stack<>();
        List<TreeNode> nodeStatus = new ArrayList<>();
        stack.push(node);
        TreeNode cNode;
        while (!stack.empty()) {
            cNode = stack.peek();
            boolean canVisit = (cNode.left == null && cNode.right == null) ||
                    (cNode.right != null && nodeStatus.contains(cNode.right)) ||
                    ((cNode.left != null && nodeStatus.contains(cNode.left)) && cNode.right == null );
            if (canVisit) {
                list.add(cNode.val);
                nodeStatus.add(cNode);
                stack.pop();
                continue;
            }
            if (cNode.left != null && !nodeStatus.contains(cNode.left)) {
                stack.push(cNode.left);
                continue;
            }
            if (cNode.right != null && !nodeStatus.contains(cNode.right)) {
                stack.push(cNode.right);
            }
        }
    }


    /**
     * 给定一个二叉树，返回它的 中序 遍历
     * */
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (root == null)
            return list;
        inOrder(root, list);
        return list;
    }
    //递归算法
    private void inOrder(TreeNode node, List<Integer> list) {
        if (node == null)
            return;
        inOrder(node.left, list);
        list.add(node.val);
        inOrder(node.right, list);
    }
    //非递归算法
    private void inOrderTree(TreeNode root, List<Integer> list) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.empty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                list.add(node.val);
                node = node.right;
            }
        }
    }


    /**
     * 给定一个二叉树，返回它的 前序 遍历
     * */
    // 递归实现
    private void preOrder(TreeNode node, List list) {
        if (node == null)
            return;
        list.add(node.val);
        preOrder(node.left,list);
        preOrder(node.right,list);
    }
    //非递归实现
    private void preOrderTree(TreeNode node, List list) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode n = node;
        while (n != null || !stack.empty()) {
            if( n != null) {
                list.add(n.val);
                stack.push(n);
                n = n.left;
            } else {
                n = stack.pop();
                n = n.right;
            }
        }
    }


    /**
     * 给定一个二叉树，找出其最大深度。
     *
     * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
     * */
    public int maxDepth(TreeNode root) {
        if (root == null)
            return 0;
        List<TreeNode> list = new ArrayList<>();
        int depth = 1;
        int len ;
        list.add(root);
        while (list.size() != 0) {
            len = list.size();
            depth++;
            while (len > 0) {
                TreeNode node = list.remove(len -1 );
                if (node.left != null)
                    list.add(node.left);
                if (node.right != null)
                    list.add(node.right);
                len--;
            }
        }
        return depth;
        //return root == null ? 0 : Math.max(maxDepth(root.left) + 1, maxDepth(root.right) + 1);
    }

    /**
     *  二叉树的所有路径
     *
     * 思路： 典型的回溯遍历 当左右节点都为空的时候结束遍历 添加路径
     * */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> list = new ArrayList<>();
        if (root != null)
            preOrder(root, "", list);
        return list;
    }
    private void preOrder(TreeNode node, String s, List list) {
        if (node.left == null && node.right == null) {
            list.add(s + node.val);
            return;
        }
        if (node.left != null)
            preOrder(node.left, s + node.val + "->", list);
        if(node.right != null)
            preOrder(node.right, s + node.val + "->",list);
    }


    /**
     *给定一个大小为 n 的数组，找出其中所有出现超过 ⌊ n/3 ⌋ 次的元素。
     *
     * 说明: 要求算法的时间复杂度为 O(n)，空间复杂度为 O(1)。
     *
     * 示例 1:
     *
     * 输入: [3,2,3]
     * 输出: [3]
     *
     * 思路：摩尔投票法。该算法用于1/2情况，它说：“在任何数组中，出现次数大于该数组长度一半的值只能有一个。”
     *
     * 那么，改进一下用于1/3。可以着么说：“在任何数组中，出现次数大于该数组长度1/3的值最多只有两个。”
     * */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> list = new ArrayList<>();
        if (nums.length == 0)
            return list;
        int ans1 = nums[0];
        int ans2 = nums[0];
        int count1 = 0;
        for (int i = 0; i < nums.length; i++) {
            if (count1 ==  0) {
                ans1 = nums[i];
                count1 = 2;
            }  else
                count1 = ans1 == nums[i] ? count1 + 2 : count1-1;
        }
        int count2=0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == ans1)
                continue;
            if (count1 == 0){
                ans2 = nums[i];
                count2 = 1;
            } else
                count2 = ans2 == nums[i] ? count2 + 1 : count2-1;
        }
        count2 = 0;
        count1 = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == ans1)
                count1++;
            if(nums[i] == ans2)
                count2++;
        }
        if(count1 > nums.length/3)
            list.add(ans1);
        if(count2 > nums.length/3 && ans2 != ans1)
            list.add(ans2);
        return list;
    }



    public static void main(String[] args) {
/*        TreeSolution treeSolution = new TreeSolution();
        TreeNode t1 = new TreeNode(1);
        TreeNode t2 = new TreeNode(2);
        TreeNode t3 = new TreeNode(3);
        TreeNode t4 = new TreeNode(4);
        TreeNode t5 = new TreeNode(5);
        t1.left = t2;
        t1.right = t3;
        t3.right = t4;
        t2.right = t5;

        int nums [] = {1,2,2,3,2,1,1,3};
        System.out.println(treeSolution.majorityElement(nums));*/

    }
}

class TreeNode {
    int val;
    TreeNode left = null;
    TreeNode right = null;
    TreeNode(int x) { val = x; }

}