package link;

public class Linked {

    static Node root;
    static Node tail;
    public static void insert(int key){
        if (root == null){
            root = new Node();
            root.setValue(key);
            tail = root;
        }else {
            Node node = new Node();
            node.setValue(key);
            tail.setNext(node);
            tail = node;
        }
    }

    public static Node reverse(Node node){
        if (node == null)
            return null;

        return null;
    }


    /**
     * 对已排序链表去重
     * @param node
     */
    public static Node duplicate(Node node){
        Node first = node;
        Node next = node.getNext();
        while (null != next){
            if (first.getValue() == next.getValue()){
                first.setNext(next.getNext());
                next = next.getNext();
            }else {
                first = next;
                next = next.getNext();
            }
        }
        return first;
    }


    /**
     * 原地迭代反转
     * @param node
     * @return
     */
    public static Node nodeReverse(Node node){
        if (node == null)
            return null;
        Node first = node;
        Node next = node.getNext();
        tail = first;
        first.setNext(null);
        while (null != next){
            Node nextChild = next.getNext();
            next.setNext(first);
            first = next;
            next = nextChild;
        }
        node = first;
        return first;
    }

    public static void main(String[] args){
        int[] aa = {1,3,4,4,4,5,5};
        for (int key : aa)
            insert(key);
        Node node = duplicate(root);
        System.out.println();
    }

}
