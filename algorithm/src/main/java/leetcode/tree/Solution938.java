package leetcode.tree;


import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

/**
 * description:
 *
 * @author fancying
 * create: 2021-03-28 15:34
 **/
public class Solution938 {

    public static void main(String[] args) {

    }

    public int rangeSumBST(TreeNode root, int low, int high) {
        int sum = 0;
        List<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.add(root);
        while (!treeNodes.isEmpty()) {
            TreeNode cur = treeNodes.get(0);
            treeNodes.remove(0);

            if (cur.val < low) {
                if (cur.right != null) {
                    treeNodes.add(cur.right);
                }
            } else if (cur.val > high) {
                if (cur.left != null) {
                    treeNodes.add(cur.left);
                }
            } else  {
                sum += cur.val;
                if (cur.left != null) {
                    treeNodes.add(cur.left);
                }
                if (cur.right != null) {
                    treeNodes.add(cur.right);
                }
            }

        }
        return sum;
    }

    @Test
    public void testM(){
        long start = System.currentTimeMillis();




        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}