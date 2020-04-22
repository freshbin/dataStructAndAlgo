package com.freshbin.dataStructAndAlgo.chapter24.mycode;

/**
 * 二叉查找树，没有重复值
 * @author freshbin
 * @date 2020/4/20 16:16
 */
public class BinarySearchTree {
    private Node tree;

    /**
     * 插入步骤：
     * 1、根节点为空，直接插入
     * 2、循环判断当前节点是否为空以及需要插入的值与当前节点的大小，
     * 大，则判断右节点是否为空，为空，直接把当前节点的右指针指向需要插入的节点即可，否则，将当前节点赋为右节点；
     * 小，则判断右节点是否为空，为空，直接把当前节点的右指针指向需要插入的节点即可，否则，将当前节点赋为左节点；
     * 等于，就直接退出插入方法，表示该值已经存在
     * @param value
     */
    public void insert(int value) {
        Node node = new Node(value);

        if(tree == null) {
            tree = node;
            return;
        }

        Node pHead = tree;
        while(pHead != null) {
            if(value > pHead.data) {
                if(pHead.right == null) {
                    pHead.right = node;
                    return;
                }
                pHead = pHead.right;
            } else if(value < pHead.data) {
                if(pHead.left == null) {
                    pHead.left = node;
                    return;
                }
                pHead = pHead.left;
            } else {
                System.out.println("该值" + value + "已经存在，不在插入！");
                return;
            }
        }
    }

    /**
     * 查找操作
     * 步骤：1、将跟节点赋给当前节点，循环判断当前节点不等于空，并且不等于目标值
     * 2、如果目标值大于当前节点值，那么当前节点就等于右节点
     * 3、小于，那么当前节点就等于左节点
     * 4、返回该节点
     * @param targetValue
     * @return
     */
    public Node find(int targetValue) {
        Node pHead = tree;
        while(pHead != null && pHead.data != targetValue) {
            if(targetValue > pHead.data) {
                pHead = pHead.right;
            } else {
                pHead = pHead.left;
            }
        }

        return pHead;

        /*while(pHead != null) {
            if(targetValue > pHead.data) {
                pHead = pHead.right;
            } else if(targetValue < pHead.data){
                pHead = pHead.left;
            } else {
                return pHead;
            }
        }
        return null;
        */
    }

    /**
     * 删除操作
     * 三种删除情况：
     * 1、需要删除的节点为叶子节点，那么直接将父节点指向空即可；
     * 2、需要删除的节点只有一个左节点或者一个右节点，那么直接将父节点指向删除节点的子节点即可
     * （父节点指向的时候，需要先判断删除节点是父节点的左节点指针还是右节点指针，然后再相应的左右指针指向子节点）；
     * 3、需要删除的节点有左右节点，那么查找该节点右子节点中的最小值节点，
     * 将要删除的节点赋值为该最小值节点，同时要删除的节点的父节点赋值为最小值节点的父节点
     * 这时候，最小值节点的位置就成为了要删除的节点，因为最小值节点肯定没有左子节点，
     * 所以这时候又可以转变成第1种情况和第2种情况，即删除叶子节点或者只有一个子节点的情况。
     *
     * 因此，综上，我们先判断第3种情况。
     *
     * @param targetValue
     */
    public void deleteNode(int targetValue) {
        if(tree == null) {
            return;
        }
        Node deleteNode = tree;
        Node deleteParentNode = null;
        // 查找需要删除的节点
        while(deleteNode != null && deleteNode.data != targetValue) {
            deleteParentNode = deleteNode;
            if(targetValue > deleteNode.data) {
                deleteNode = deleteNode.right;
            } else {
                deleteNode = deleteNode.left;
            }
        }
        if(deleteNode == null) {
            return;
        }

        // 第3种情况
        if(deleteNode.left != null && deleteNode.right != null) {
            // 查找右节点最小值
            Node miniNode = deleteNode.right;
            Node miniParentNode = deleteNode;
            while(miniNode.left != null) {
                miniParentNode = miniNode;
                miniNode = miniNode.left;
            }
            deleteNode.data = miniNode.data;
            deleteNode = miniNode;
            deleteParentNode = miniParentNode;
        }

        // 第1，2种情况
        Node childNode;
        if(deleteNode.right != null) childNode = deleteNode.right;
        else if(deleteNode.left != null) childNode = deleteNode.left;
        else childNode = null;

        // 删除节点
        if(deleteParentNode == null) tree = childNode;
        else if(deleteParentNode.left == deleteNode) deleteParentNode.left = childNode;
        else deleteParentNode.right = childNode;
    }

    public class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int value) {
            this.data = value;
        }

        @Override
        public String toString() {
            StringBuffer sb = new StringBuffer();
            sb.append("data:" + data);
            return sb.toString();
        }
    }

    /**
     * 前序遍历
     * @param tree
     */
    public void preOrder(Node tree) {
        if(tree == null) return;
        System.out.println(tree.data);
        preOrder(tree.left);
        preOrder(tree.right);
    }

    public void inOrder(Node tree) {
        if(tree == null) return;

        preOrder(tree.left);
        System.out.println(tree.data);
        preOrder(tree.right);
    }

    public void postOrder(Node tree) {
        if(tree == null) return;

        preOrder(tree.left);
        preOrder(tree.right);
        System.out.println(tree.data);
    }

    public static void main(String[] arg) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        binarySearchTree.insert(1);
        binarySearchTree.insert(1);
        binarySearchTree.insert(5);
        binarySearchTree.insert(3);
        binarySearchTree.insert(2);
        binarySearchTree.insert(4);
        binarySearchTree.insert(0);
        int targetValue = 1;
        System.out.println("删除"+targetValue+"之前前序遍历：");
        binarySearchTree.preOrder(binarySearchTree.tree);
        System.out.println("查找"+targetValue+"的值：" + binarySearchTree.find(targetValue));
        binarySearchTree.deleteNode(targetValue);
        System.out.println("删除"+targetValue+"之后前序遍历：");
        binarySearchTree.preOrder(binarySearchTree.tree);
        System.out.println("查找"+targetValue+"的值：" + binarySearchTree.find(targetValue));
    }
}
