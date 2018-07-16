package graph;


import java.util.Queue;
import java.util.Scanner;
import java.util.concurrent.LinkedBlockingQueue;

public class BFSGraph {

    static Scanner scanner = new Scanner(System.in);

    public static Graph createGraph() { //无向图

        System.out.println("输入顶点数，边数");
        String info = scanner.next();
        String[] infoArr = info.split(",");
        if (info.length() < 2) {
            System.out.println("输入非法");
            return null;
        }
        Graph graph = new Graph(Integer.parseInt(infoArr[0]));
        graph.verNum = Integer.parseInt(infoArr[1]);
        System.out.println("输入节点信息");
        String nodeInfo = scanner.next();
        String[] nodeArr = nodeInfo.split(",");
        if (nodeArr.length < graph.node) {
            System.out.println("定点数非法");
            return null;
        }
        for (int i = 0; i < graph.node; i++) {//初始化节点信息
            graph.nodeValue[i] = nodeArr[i];
        }
//        matrix(graph);
        table(graph);
        return graph;
    }

    public static  void matrix(Graph graph){
        System.out.println("输入数边信息");
        for (int j = 0; j < graph.verNum; j++) {
            String vex = scanner.next();
            String[] vexArr = vex.split(",");
            if (vexArr.length < 2) {
                System.out.println("边信息非法");
                return;
            }
            int row = Integer.parseInt(vexArr[0]);
            int col = Integer.parseInt(vexArr[1]);
            graph.matrix[row][col] = 1;
            graph.matrix[col][row] = 1;
        }
    }

    public static void table(Graph graph){
        System.out.println("输入数边信息");
        for (int i = 0; i < graph.verNum;i ++){
            String vex = scanner.next();
            String[] vexArr = vex.split(",");
            if (vexArr.length < 2) {
                System.out.println("边信息非法");
                return;
            }
            Node node = new Node();
            int from = Integer.parseInt(vexArr[0]);
            int to = Integer.parseInt(vexArr[1]);
            node.value = to;
            node.next = graph.nodeTables[from];
            graph.nodeTables[from] = node;

            Node node1 = new Node();
            node1.value = from;
            node1.next = graph.nodeTables[to];
            graph.nodeTables[to] = node1;
        }
    }

    public static void DFSTraverse(Graph graph){
        for (int i = 0; i < graph.node; i ++)
            if (!graph.visited[i])//未遍历
                DFSTraverse(graph,i);
    }

    public static void DFSTraverse(Graph graph, int position) {//邻接矩阵存储方式搜索
        graph.visited[position] = true;
        System.out.print(graph.nodeValue[position] + "->");
        for (int i = 0; i < graph.node; i ++){
            if (graph.matrix[position][i] == 1 && !graph.visited[i])
                DFSTraverse(graph,i);
        }
    }

    public static void DFSTraverseTable(Graph graph){
        for (int i = 0; i< graph.node;i ++)
            if (!graph.visited[i])
                DFSTraverseTable(graph,i);
    }

    public static void DFSTraverseTable(Graph graph,int position){//邻接表存储方式搜索
        graph.visited[position] = true;
        Node node = graph.nodeTables[position];
        System.out.print(graph.nodeValue[position] + "->");
        while (null != node){
            if (!graph.visited[node.value])
                DFSTraverseTable(graph,node.value);
            else
                node = node.next;
        }
    }


    public static void BFSTraverse(Graph graph){
        Queue<Integer> queue = new LinkedBlockingQueue();
        for (int i= 0 ;i<graph.node;i ++){
            if (!graph.visited[i]){
                queue.add(i);
                graph.visited[i] = true;
                while (!queue.isEmpty()){
                    int temp = queue.poll();
                    System.out.print(graph.nodeValue[temp] + "->");
                    for (int j = 0;j < graph.node;j++){
                        if (graph.matrix[temp][j] == 1 && !graph.visited[j]){
                            queue.add(j);
                            graph.visited[j] = true;
                        }
                    }
                }
            }
        }
    }


    public static void BFSTraverseTable(Graph graph){
        Queue<Integer> queue = new LinkedBlockingQueue<Integer>();
        for (int i = 0; i < graph.node; i ++){
            if (!graph.visited[i]){
                graph.visited[i] = true;
                queue.add(i);
                while (!queue.isEmpty()){
                    int temp = queue.poll();
                    System.out.print(graph.nodeValue[temp] + "->");
                    Node node = graph.nodeTables[temp];
                    while (null != node){
                        if (!graph.visited[node.value]){
                            graph.visited[node.value] = true;
                            queue.add(node.value);
                        }
                        node = node.next;
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Graph graph = createGraph();
        BFSTraverseTable(graph);
    }

    static class Graph {

        int node, verNum;//图的节点数、边数
        boolean[] visited;//节点访问标记
        String[] nodeValue;//节点值
        int[][] matrix;//图的邻接矩阵
        Node[] nodeTables;//邻接表

        public Graph(int nodes){
            this.node = nodes;
            this.visited = new boolean[node];//节点访问标记
            this.nodeValue = new String[node];//节点值
            this.matrix = new int[node][node];//图的邻接矩阵
            this.nodeTables = new Node[node];//邻接表
        }

        public int getNode() {
            return node;
        }

        public int getVerNum() {
            return verNum;
        }

        public boolean[] getVisited() {
            return visited;
        }

        public String[] getNodeValue() {
            return nodeValue;
        }

        public int[][] getMatrix() {
            return matrix;
        }

        public Node[] getNodeTables() {
            return nodeTables;
        }
    }

    static class Node {
        int value;
        Node next;

    }
}
