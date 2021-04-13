package leetcode.tree;


/**
 * description:
 *
 * @author fancying
 * create: 2021-03-28 15:34
 **/
public class Solution783 {

    public static void main(String[] args) {

        TreeNode rLeft = new TreeNode(69, new TreeNode(49, null, new TreeNode(52, null, null)), new TreeNode(89, null, null));
        TreeNode root = new TreeNode(90, rLeft, null);
        System.out.println(minDiffInBST(root));

    }

    public static int minDiffInBST(TreeNode root) {
        if (root == null) {
            return 100000;
        }

        int leftMax = -100000,rightMin = 100000;

        // 左子树的最大值
        if (root.left != null) {
            TreeNode left = root.left;
            while (left.right != null) {
                left = left.right;
            }
            leftMax = left.val;
        }


        // 右子树的最小值
        if (root.right != null) {
            TreeNode right = root.right;
            while (right.left != null) {
                right = right.left;
            }
            rightMin = right.val;
        }

        return Math.min(Math.min(root.val - leftMax, rightMin - root.val), Math.min(minDiffInBST(root.left),minDiffInBST(root.right)));
    }


}