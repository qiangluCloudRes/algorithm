package tree;

/**
 * 二叉树
 * @param <T>
 */
public class BinaryTree<T> {

    TreeNode<T>[] treeNodes;
    int treeSize;
    TreeNode<T>   root;

    public BinaryTree(T[] data){
        int len = data.length;
        treeSize = len;
        treeNodes = new TreeNode[len];
        for (int i =0; i < len; i++){
            TreeNode node = new TreeNode();
            node.setData(data[i]);
            treeNodes[i] = node;
            if (i == 0)
                root = node;
        }
        for (int j = 0;j <= (len - 2) / 2; j++ ){
            if ((2 * j + 1) < len)//防止数组越界
                treeNodes[j].setLeftChild(treeNodes[2 * j + 1]);
            if ((2 * j + 2) < len)
                treeNodes[j].setRightChild(treeNodes[2 * j + 2]);
        }
    }


    public void preOrder(TreeNode root){
        TreeNode node = root;
        if (null != node){
            printNode(node);
            preOrder(node.getLeftChild());
            preOrder(node.getRightChild());
        }
    }


    public void inOrder(TreeNode root){
        TreeNode node = root;
        if (null != node){
            inOrder(node.getLeftChild());
            printNode(node);
            inOrder(node.getRightChild());
        }
    }

    public void postOrder(TreeNode root){
        TreeNode node = root;
        if (null != node){
            postOrder(node.getLeftChild());
            postOrder(node.getRightChild());
            printNode(node);
        }
    }


    private void printNode(TreeNode treeNode){
        System.out.println(treeNode.getData());
    }

    public static void main(String[] args){
        Integer[] arr = {1,2,3,4,5,6,7,8,9,10};
        BinaryTree tree = new BinaryTree(arr);
        tree.postOrder(tree.root);
    }

}
