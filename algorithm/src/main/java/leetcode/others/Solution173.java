package leetcode.others;

import java.util.*;

/**
 * description:
 *
 * @author fancying
 * create: 2021-03-28 16:55
 **/
public class Solution173 {

    public static void main(String[] args) {

        TreeNode n1 = new TreeNode(3,null,null);
        TreeNode n2 = new TreeNode(9,null,null);
        TreeNode n3 = new TreeNode(20,null,null);
        TreeNode n4 = new TreeNode(15,n2,n3);
        TreeNode root = new TreeNode(7,n1,n4);

        BSTIterator bstIterator = new BSTIterator(root);

        System.out.println(bstIterator.next() );
        System.out.println( bstIterator.next() );
        System.out.println(bstIterator.hasNext());
        System.out.println(bstIterator.next());
        System.out.println(bstIterator.hasNext());
        System.out.println(bstIterator.next());
        System.out.println(bstIterator.hasNext());
        System.out.println(bstIterator.next());
        System.out.println(bstIterator.hasNext());

    }

    static class BSTIterator {

        private int idx;
        private List<Integer> arr;

        public BSTIterator(TreeNode root) {
            idx = 0;
            arr = new ArrayList<Integer>();
            inorderTraversal(root, arr);
        }

        public int next() {
            return arr.get(idx++);
        }

        public boolean hasNext() {
            return idx < arr.size();
        }

        private void inorderTraversal(TreeNode root, List<Integer> arr) {
            if (root == null) {
                return;
            }
            inorderTraversal(root.left, arr);
            arr.add(root.val);
            inorderTraversal(root.right, arr);
        }
    }
}
