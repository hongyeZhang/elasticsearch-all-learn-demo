package ai.yunxi.search.tree;

import java.util.*;

class TrieNode {
    // 子节点
    List<TrieNode> children;

    boolean isLeaf;

    // 包含字符
    char aChar;

    TrieNode(char c) {
        children = new LinkedList<>();
        isLeaf = false;
        aChar = c;
    }

    TrieNode contains(char c) {
        for (TrieNode node : children) {
            if (node.aChar == c)
                return node;
        }
        return null;
    }
}


public class TrieTree {
    // 字典树的根
    private TrieNode root;

    public TrieTree() {
        root = new TrieNode(' ');
    }

    // 插入词，形成数字搜索树
    void insert(String word) {
        TrieNode node = root;

        char[] charArray = word.toCharArray();
        for (char c : charArray) {
            TrieNode n = node.contains(c);
            if (n == null) {
                TrieNode tn = new TrieNode(c);
                node.children.add(tn);
                node = tn;
            } else {
                node = n;
            }

            node.isLeaf = true;
        }
    }

    // 判断TrieTree中是否包含词
    public boolean search(String word) {
        char[] charArray = word.toCharArray();
        TrieNode currentNode = root;
        for (int i = 0; i < charArray.length; i++) {
            TrieNode n = currentNode.contains(charArray[i]);
            if (n != null) {
                currentNode = n;
                // 判断是否为词的尾节点
                if (n.isLeaf) {
                    if (n.aChar == charArray[charArray.length - 1]) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static void main(String args[]) {
        TrieTree tree = new TrieTree();
        tree.insert("中国北京");
        tree.insert("中华人民共和国");
        tree.insert("北京");
        tree.insert("北京");
        tree.insert("北京紫禁城");
        tree.insert("紫金山");
        tree.insert("紫金山天文台");
        tree.insert("文台书院");
        tree.insert("北京故宫");
        tree.insert("故宫博物院");

        System.out.println(tree.search("天文"));
    }
}

