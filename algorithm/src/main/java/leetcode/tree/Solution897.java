package leetcode.tree;


import org.junit.Test;

import java.util.*;

/**
 * description:
 *
 * @author fancying
 * create: 2021-03-28 15:34
 **/
public class Solution897 {

    public static void main(String[] args) {



    }

    public TreeNode increasingBST(TreeNode root) {
        List<TreeNode> treeNodes = new ArrayList<>();
        inOrder(root, treeNodes);
        int i = 0;
        while (i < treeNodes.size() - 1) {
            TreeNode treeNode = treeNodes.get(i);
            treeNode.left = null;
            treeNode.right = treeNodes.get(++i);
        }
        treeNodes.get(i).left = null;
        treeNodes.get(i).right = null;

        return treeNodes.get(0);
    }

    private void inOrder(TreeNode root, List<TreeNode> treeNodes) {
        if (root.left != null) {
            inOrder(root.left, treeNodes);
        }
        treeNodes.add(root);
        if (root.right != null) {
            inOrder(root.right, treeNodes);
        }

    }

    @Test
    public void testM(){
        long start = System.currentTimeMillis();

        TreeNode root = new TreeNode(5,
                new TreeNode(3,
                        new TreeNode(2,
                                new TreeNode(1, null, null), null),
                        new TreeNode(4, null, null)),
                new TreeNode(6, null,
                        new TreeNode(8,
                                new TreeNode(7 ,null, null),
                                new TreeNode(9 ,null, null) )));

        TreeNode node = increasingBST(root);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}