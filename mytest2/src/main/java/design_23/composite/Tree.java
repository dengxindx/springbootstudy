package design_23.composite;

public class Tree {

    TreeNode root = null;

    public Tree(String name){
        root = new TreeNode(name);
    }

    public static void main(String[] args) {
        Tree tree = new Tree("A");  // 根节点

        TreeNode nodeB = new TreeNode("B");
        TreeNode nodeC = new TreeNode("C");

        nodeB.setParent(tree.root);

        nodeB.add(nodeC);
        nodeC.setParent(nodeB);

        tree.root.add(nodeB);
        System.out.println("build the tree finished!");
    }
}
