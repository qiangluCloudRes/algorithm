package search;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;

public class GraphSearch {


    public static void BFSTraverse(Graph graph) {
        Queue queue = new LinkedBlockingQueue();
        for (int i = 0; i < graph.vexNum; i++)
            if (graph.visited[i] == false) {
                queue.add(i);
                graph.visited[i] = true;
                while (!queue.isEmpty()) {
                    int item = (Integer) queue.poll();
                    System.out.println("广度优先遍历：" + graph.arr[item]);
                    for (int j = 0; j < graph.vexNum; j++) {
                        if (graph.matrix[item][j] == 1 && graph.visited[j] == false){
                            queue.add(j);
                            graph.visited[j] = true;
                        }
                    }
                }
            }
    }


    public static void DFSTraverse(Graph graph){
        for (int i = 0; i < graph.vexNum; i ++)
            if (graph.visited[i] == false)
                DFSTraverse(graph,i);
    }

    public static void DFSTraverse(Graph graph,int item){
        graph.visited[item] = true;
        System.out.println("深度优先遍历:" + graph.arr[item]);
        for (int j = 0; j < graph.vexNum; j ++){
            if (graph.matrix[item][j] == 1 && graph.visited[j] == false){
                DFSTraverse(graph,j);
            }
        }
    }



    public static void main(String[] args) {
        Graph graph = new Graph();
        DFSTraverse(graph);

    }


    static class Graph {
        int[][] matrix = new int[7][7];
        int vexNum = 7;
        boolean[] visited = new boolean[vexNum];
        char[] arr = {'a','b','c','d','e','f','g'};
        Graph() {
            matrix[0][3] = 1;
            matrix[0][2] = 1;
            matrix[0][1] = 1;
            matrix[1][0] = 1;
            matrix[2][0] = 1;
            matrix[2][5] = 1;
            matrix[3][0] = 1;
            matrix[4][5] = 1;
            matrix[5][2] = 1;
            matrix[5][4] = 1;
            matrix[5][6] = 1;
            matrix[6][5] = 1;

        }

        public int getVexNum() {
            return vexNum;
        }
    }
}
