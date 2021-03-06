package com.manhpd;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LevelOrderTraversal {

    private static int maxLevel = Integer.MIN_VALUE;

    private static Map<Integer, List<Integer>> nodesPerLevel = new HashMap<>();

    public static void main(String[] args) {
        TreeNode first = new TreeNode(1);
        TreeNode second = new TreeNode(2);
        TreeNode third = new TreeNode(3);
        TreeNode four = new TreeNode(4);
        TreeNode five = new TreeNode(5);
        TreeNode six = new TreeNode(6);
        TreeNode seven = new TreeNode(7);
        TreeNode eight = new TreeNode(8);
        TreeNode nine = new TreeNode(9);
        TreeNode ten = new TreeNode(10);

        first.left = second;
        first.right = third;

        second.left = four;
        second.right = five;

        third.left = six;
        third.right = seven;

        //        four.right = eight;
//
//        five.left = nine;
//        five.right = ten;

        // recursive version
//        List<Integer> res = new ArrayList<>();
//        inorderTraversal(first, res);

        // iterative version
        List<Integer> res = levelOrderTraversalRecursiveVersion(first);
//        List<Integer> res = levelOrderTraversalRecursiveVersion1(first);

        res.stream().map(item -> item + " --> ").forEach(item -> System.out.print(item));
        System.out.println("null");
    }

    public static List<Integer> levelOrderTraversalIterativeVersion(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();

        return nodes;
    }

    /**
     * Using recursive version
     *
     * @param root
     */
    public static List<Integer> levelOrderTraversalRecursiveVersion(TreeNode root) {
        List<Integer> nodes = new ArrayList<>();

//        int height = getMaxLevelBottomUp(root);
        getMaxLevelTopDown(root, 1);

        for (int level = 1; level <= maxLevel; ++level) {
            getNodesInSameLevel(root, level, 1, nodes);
        }

        return nodes;
    }

    public static void getNodesInSameLevel(TreeNode root, int currentLevel, int level, List<Integer> nodes) {
        if (root == null) {
            return;
        }

        if (level == currentLevel) {
            nodes.add(root.val);
        }

        getNodesInSameLevel(root.left, currentLevel, level + 1, nodes);
        getNodesInSameLevel(root.right, currentLevel, level + 1, nodes);
    }

    private static void getMaxLevelTopDown(TreeNode root, int level) {
        if (root == null) {
            return;
        }

        if (root.left == null && root.right == null) {
            maxLevel = Math.max(maxLevel, level);
        }

        getMaxLevelTopDown(root.left, level + 1);
        getMaxLevelTopDown(root.right, level + 1);
    }

    private static int getMaxLevelBottomUp(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int leftHeight = getMaxLevelBottomUp(root.left);
        int rightHeight = getMaxLevelBottomUp(root.right);

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public static List<Integer> levelOrderTraversalRecursiveVersion1(TreeNode root) {
        recursiveVersion(root, 0);

        // convert hash map to list
        if (nodesPerLevel.isEmpty()) {
            return Collections.emptyList();
        }

        return nodesPerLevel.values().stream().flatMap(valNodes -> Stream.of(valNodes.toArray())).map(value -> (Integer) value).collect(Collectors.toList());
    }

    public static void recursiveVersion(TreeNode root, int level) {
        if (root == null) {
            return;
        }

        if (nodesPerLevel.size() == level) {
            nodesPerLevel.put(level, new ArrayList<>());
        }

        // add the value of current node with the same level
        nodesPerLevel.get(level).add(root.val);

        recursiveVersion(root.left, level + 1);
        recursiveVersion(root.right, level + 1);
    }

}
