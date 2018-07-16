package link;

public class DeNode {

    private int key;

    private DeNode pre;

    private DeNode next;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public DeNode getPre() {
        return pre;
    }

    public void setPre(DeNode pre) {
        this.pre = pre;
    }

    public DeNode getNext() {
        return next;
    }

    public void setNext(DeNode next) {
        this.next = next;
    }
}
