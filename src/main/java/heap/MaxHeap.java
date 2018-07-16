package heap;

import java.util.Random;

public class MaxHeap {

    static int[] arr = new int[20];
    static int len = 0;

    public static void buildHeap(int[] arr){
        for (int i = arr.length/2 - 1; i >= 0; i --){
            adjustHeap(arr,i);
        }
    }


    public static void adjustHeap(int[] arr,int position){
        int temp;
        int child;
        for (temp = arr[position]; getLeft(position) <= arr.length -1; position = child){
            child = getLeft(position);
            if (child != arr.length -1 && arr[child] > arr[child + 1]){
                child ++;
            }
            if (temp > arr[child])
                arr[position] = arr[child];
            else
                break;
        }
        arr[position] = temp;
    }


    public static boolean insert(int key){
        if (len > arr.length)
            return false;
        arr[len] = key;
        int parent = len /2 -1;
        int current = len - 1;
        while ( parent >=0){
            swap(parent,current);
            current = parent;
            parent = parent /2 -1;
        }
        return true;
    }

    public static void swap(int from,int to){
        if (arr[from] < arr[to]){
            int tem = arr[from];
            arr[from]= arr[to];
            arr[to] = tem;
        }
    }

    public static int getLeft(int position){
        return position * 2 + 1;
    }

    public static void main(String[] args){

        Random random = new Random();
        for (int i = 0;i< 10; i ++){
            len ++;
            arr[i] = random.nextInt(110) + 1;
        }
        buildHeap(arr);
        for (int i : arr){
            System.out.println(i);
        }
        insert(10);
        for (int i : arr){
            System.out.println(i);
        }
    }
}
