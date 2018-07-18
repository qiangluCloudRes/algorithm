package graph;


import java.util.*;
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
        matrix(graph);
//        table(graph);
        return graph;
    }

    public static void matrix(Graph graph) {
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
            int weight = Integer.parseInt(vexArr[2]);
//            graph.matrix[row][col] = 1;
//            graph.matrix[col][row] = 1;

            graph.matrix[row][col] = weight;
            graph.matrix[col][row] = weight;
        }
    }

    public static void table(Graph graph) {
        System.out.println("输入数边信息");
        for (int i = 0; i < graph.verNum; i++) {
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

    public static void DFSTraverse(Graph graph) {
        for (int i = 0; i < graph.node; i++)
            if (!graph.visited[i])//未遍历
                DFSTraverse(graph, i);
    }

    public static void DFSTraverse(Graph graph, int position) {//邻接矩阵存储方式搜索
        graph.visited[position] = true;
        System.out.print(graph.nodeValue[position] + "->");
        for (int i = 0; i < graph.node; i++) {
            if (graph.matrix[position][i] == 1 && !graph.visited[i])
                DFSTraverse(graph, i);
        }
    }

    public static void DFSTraverseTable(Graph graph) {
        for (int i = 0; i < graph.node; i++)
            if (!graph.visited[i])
                DFSTraverseTable(graph, i);
    }

    public static void DFSTraverseTable(Graph graph, int position) {//邻接表存储方式搜索
        graph.visited[position] = true;
        Node node = graph.nodeTables[position];
        System.out.print(graph.nodeValue[position] + "->");
        while (null != node) {
            if (!graph.visited[node.value])
                DFSTraverseTable(graph, node.value);
            else
                node = node.next;
        }
    }


    public static void BFSTraverse(Graph graph) {
        Queue<Integer> queue = new LinkedBlockingQueue();
        for (int i = 0; i < graph.node; i++) {
            if (!graph.visited[i]) {
                queue.add(i);
                graph.visited[i] = true;
                while (!queue.isEmpty()) {
                    int temp = queue.poll();
                    System.out.print(graph.nodeValue[temp] + "->");
                    for (int j = 0; j < graph.node; j++) {
                        if (graph.matrix[temp][j] == 1 && !graph.visited[j]) {
                            queue.add(j);
                            graph.visited[j] = true;
                        }
                    }
                }
            }
        }
    }


    public static void BFSTraverseTable(Graph graph) {
        Queue<Integer> queue = new LinkedBlockingQueue<Integer>();
        for (int i = 0; i < graph.node; i++) {
            if (!graph.visited[i]) {
                graph.visited[i] = true;
                queue.add(i);
                while (!queue.isEmpty()) {
                    int temp = queue.poll();
                    System.out.print(graph.nodeValue[temp] + "->");
                    Node node = graph.nodeTables[temp];
                    while (null != node) {
                        if (!graph.visited[node.value]) {
                            graph.visited[node.value] = true;
                            queue.add(node.value);
                        }
                        node = node.next;
                    }
                }
            }
        }
    }


    public static List<Point> getPointSortWeight(Graph graph){
        List<Point> points = new ArrayList<Point>();
        for (int i = 0;i < graph.node; i ++){
            for (int j = 0; j < i;j ++){
                if (graph.matrix[i][j] == 0)
                    continue;
                Point point = new Point();
                point.vi = i;
                point.vj = j;
                point.weight = graph.matrix[i][j];
                points.add(point);
            }
        }
        Collections.sort(points, new Comparator<Point>() {
            public int compare(Point o1, Point o2) {
                return o1.weight < o2.weight ? -1 : 1;
            }
        });
        return points;
    }

    public static boolean findTree(List<Integer>[] tree,int vi,int vj){
        int viIndex = Integer.MAX_VALUE;
        int vjIndex = Integer.MAX_VALUE;
        for (int i = 0 ; i < tree.length;i++){//找到节点所在的数
            for (int treeNode : tree[i]){
                if (treeNode == vi)
                    viIndex = i;
                if (treeNode == vj)
                    vjIndex = i;
            }
        }
        if (vjIndex != viIndex && viIndex != Integer.MAX_VALUE && vjIndex != Integer.MAX_VALUE) // 两个节点不在一棵树上
        {
            tree[viIndex].addAll(tree[vjIndex]);//合并两颗子树
            tree[vjIndex].clear();
            return false;
        }
        return true;
    }

    /**
     * 最小生成树 kruskal，适合稀疏图 适合稀疏图 时间负复杂度：elge，其中e为边数
     * @param graph
     */
    public static void Kruskal(Graph graph){
        List<Point> points = getPointSortWeight(graph);//获取排序后边
        List<Integer>[]  childTree = new List[graph.node];
        for (int i = 0; i < graph.node;i ++){//初始化独立数
            List<Integer> tree = new ArrayList<Integer>();
            tree.add(i);
            childTree[i] = tree;
        }
        for (Point point : points){
            if (!findTree(childTree,point.vi,point.vj)){
                System.out.println(point.vi + ":" + point.vj);
            }
        }
    }


    /**
     * 最小生成数。 适合稠密图 复杂度：O(n2) n 为定点数
     * @param graph
     * @param index 从当前指定的节点开始遍历
     */
    public static void prim(Graph graph,int index){
        int current = index;
        System.out.print(graph.nodeValue[index] + "->");
        Point[] dist = new Point[graph.node];
        for (int j = 0; j < graph.node; j ++) {//初始化其他节点到当前节点的距离
            Point point = new Point();
            point.vi = j;
            point.weight = graph.matrix[current][j];
            dist[j] = point;
        }
        for (int i = 1; i < graph.node; i ++){
            int min = Integer.MAX_VALUE;
            for (int j =0;j < graph.node;j ++){
                if (0 != dist[j].weight && !graph.visited[j] && dist[j].weight < min) {//找到当前距离最小的点
                    current = dist[j].vi;
                    min = dist[j].weight;
                }
            }
            System.out.print(graph.nodeValue[current] + "->");
            for (int j = 0; j < graph.node; j ++){
               if (0 != graph.matrix[current][j] && dist[current].weight > graph.matrix[current][j]){
                    dist[j].weight = graph.matrix[current][j];
                    dist[j].vi = j;
               }
            }
        }
    }


    public static void main(String[] args) {
        Graph graph = createGraph();
//        BFSTraverseTable(graph);
//        Kruskal(graph);
        prim(graph,0);
    }

    static class Graph {

        int node, verNum;//图的节点数、边数
        boolean[] visited;//节点访问标记
        String[] nodeValue;//节点值
        int[][] matrix;//图的邻接矩阵
        Node[] nodeTables;//邻接表

        public Graph(int nodes) {
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

    static class Point {
        int vi;
        int vj;
        int weight;
    }
}
