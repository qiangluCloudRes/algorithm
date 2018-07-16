package tree;


/**
 * 二叉排序树，节点数据分布可能比较极端。比如当root节点的值为1时，根节点左子树为空，右子数满
 */
public class SortTree {

    private TreeNode<Integer> root;

    private TreeNode insert(TreeNode<Integer> node, int key) {
        if (null == node) {
            node = new TreeNode();
            node.setData(key);
            return node;
        }
        TreeNode newNode = null;
        if (key < node.getData()) {
            newNode = insert(node.getLeftChild(), key);
            node.setLeftChild(newNode);
        } else {
            newNode = insert(node.getRightChild(), key);
            node.setRightChild(newNode);
        }
        newNode.setParent(node);
        return node;
    }


    public TreeNode search(TreeNode<Integer> node, int key) {
        if (node == null)
            return null;
        if (node.getData() == key)
            return node;
        else if (key < node.getData()) {
            return search(node.getLeftChild(), key);
        } else
            return search(node.getRightChild(), key);
    }

    /**
     * 查找要删除的节点p
     * 1. 如果p为叶子节点，直接删除
     * 2。如果p只有右子树或左子树，直接用右子树或左子树代替p
     * 3。如果p同时存在右子树或左子树 1）：找到左子树最右边的节点r，用r代替p。 2）：找到右子树最左边边的节点r，用r代替p
     *
     * @param
     * @param key
     * @return
     */
    public boolean delete(TreeNode<Integer> node, int key) {
        if (node == null)
            return false;
        if (key < node.getData()) {
            delete(node.getLeftChild(), key);
        } else if (key > node.getData()) {
            delete(node.getRightChild(), key);
        } else {
            deleteNode(node);
        }
        return true;
    }

    private void deleteNode(TreeNode<Integer> node) {
        boolean isLeft = (null != node.getParent().getLeftChild() && node.getParent().getLeftChild().equals(node))
                ? true : false; //当前节点在父节点中是否是左子树
        if (null == node.getLeftChild() && null == node.getRightChild()) {//左右子树均为空,直接删除节点
            if (isLeft)
                node.getParent().setLeftChild(null);
            else
                node.getParent().setRightChild(null);
        } else if (null == node.getLeftChild()) { //左子树为空，用右子树替换当前节点
            if (isLeft)
                node.getParent().setLeftChild(node.getRightChild());
            else
                node.getParent().setRightChild(node.getRightChild());
            node.getRightChild().setParent(node.getParent());
        } else if (null == node.getRightChild()) {//右子树为空，用左子树替换当前节点
            if (isLeft)
                node.getParent().setLeftChild(node.getLeftChild());
            else
                node.getParent().setRightChild(node.getLeftChild());
            node.getLeftChild().setParent(node.getParent());
        } else {


        }
    }

    public void del(int key) {
        delete(root, key);
    }

    public Integer search(int key) {
        return (Integer) search(root, key).getData();
    }

    public boolean insert(int key) {
        if (null == root) {
            root = new TreeNode<Integer>();
            root.setData(key);
        } else
            insert(root, key);
        return true;
    }

    public static void main(String[] args) {
        SortTree tree = new SortTree();
        int[] arr = {1, 4, 3, 6, 2, 7, 9};
        for (int key : arr) {
            tree.insert(key);
        }
        tree.del(9);
        BinaryTree binaryTree = new BinaryTree(args);
        binaryTree.inOrder(tree.root);
    }
}
