package leetcode.tree;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * description:
 *
 * @author fancying
 * create: 2021-03-28 15:34
 **/
public class Solution208 {

    public static void main(String[] args) {

//        TreeNode rLeft = new TreeNode(69, new TreeNode(49, null, new TreeNode(52, null, null)), new TreeNode(89, null, null));
//        TreeNode root = new TreeNode(90, rLeft, null);
       // System.out.println(minDiffInBST(root));


        Trie trie = new Trie();
        trie.insert("apple");
        trie.insert("app");
          // 返回 True
        System.out.println(trie.search("apps"));
         // 返回 False
        System.out.println(trie.search("appp"));
        // 返回 True
        System.out.println(trie.startsWith("app"));
        trie.insert("app");

         // 返回 True
        System.out.println(trie.search("app"));

    }

    static class Trie{

        private Trie[] children;
        private boolean isEnd;

        public Trie() {
            children = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    node.children[index] = new Trie();
                }
                node = node.children[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trie node = searchPrefix(word);
            return node != null && node.isEnd;
        }

        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }

        private Trie searchPrefix(String prefix) {
            Trie node = this;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                int index = ch - 'a';
                if (node.children[index] == null) {
                    return null;
                }
                node = node.children[index];
            }
            return node;
        }


    }




}