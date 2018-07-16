package link;

public class DeLink {

    static DeNode root;
    static DeNode tail;

    static void insert(int key) {
        if (root == null) {
            root = new DeNode();
            root.setKey(key);
            tail = root;
        } else {
            DeNode node = new DeNode();
            node.setKey(key);
            tail.setNext(node);
            node.setPre(tail);
            tail = node;
        }
    }


    static Integer del(int key) {
        DeNode deNode = root;
        while (null != deNode && deNode.getKey() != key) {
            deNode = deNode.getNext();
        }
        if (null != deNode) {
            deNode.getPre().setNext(deNode.getNext());
            deNode.getNext().setPre(deNode.getPre());
            return deNode.getKey();
        } else
            return null;
    }

    /**
     * 翻转链表
     */
    static void reverse() {
        if (root == null)
            return;
        DeNode first = root;
        DeNode next = first.getNext();
        first.setNext(null);
        tail = first;
        while (null != next) {
            DeNode nextChild = next.getNext();
            next.setPre(null);
            first.setPre(next);
            next.setNext(first);
            first = next;
            next = nextChild;
        }
        root = first;
    }

    /**
     * 构建有序链表
     *
     * @param key
     */
    static void sortLink(int key) {
        DeNode node = new DeNode();
        node.setKey(key);
        DeNode target = root;
        while (target != null && target.getKey() < key) {
            target = target.getNext();
        }
        if (null != target) {
            node.setPre(target.getPre());
            target.setPre(node);
            node.setNext(target);
            node.getPre().setNext(node);
        } else {
            if (null != tail) {
                tail.setNext(node);
                node.setPre(tail);
                tail = node;
            } else {
                tail = new DeNode();
                tail.setKey(key);
                root = tail;
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 5, 2, 6, 7};
        for (int item : arr)
            sortLink(item);

    }

}
