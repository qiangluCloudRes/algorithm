package sort;

public class SortAlgorithm {

    public static void swap(int[] arr,int from,int to){
        int temp = arr[to];
        arr[to] = arr[from];
        arr[from] = temp;
    }

    public static void selectSort(int[] arr){
        for (int i = 0; i < arr.length; i ++){
            int min = i;
            for (int j = i + 1; j < arr.length ; j++){
                if (arr[j] < arr[min] ){
                   min = j;
                }
            }
            if (i != min)
                swap(arr,i,min);
        }

    }

    public static void bubbleSort(int[] arr){
        for (int i = 0; i < arr.length ; i ++){
            for (int j = i; j < arr.length; j ++){
                if (arr[i] > arr[j])
                    swap(arr,i,j);
            }
        }
    }


    public static void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i ++){
            int j = i;
            while (j > 0 && j < arr.length && arr[j] < arr[j - 1]){
                swap(arr,j - 1,j);
                j--;
            }
        }
    }

    /**
     * 希尔排序，插入排序的改进版
     * @param arr
     */
    public static void shellSort(int[] arr){
       for (int gap = arr.length/2;gap > 0 ; gap = gap/ 2){
           for (int i = arr.length /2 ; i < arr.length; i ++){
               int j = i;
               while (j- gap > 0 && arr[j] < arr[j -gap]){
                   swap(arr,j,j-gap);
                   j = j - gap;
               }
           }
       }
    }



    public static void fastSort(int[] arr,int start, int end){
        if (start < end) {
            int i = start, j = end;
            int temp = arr[start];
            while (i < j) {

                while (i < j && arr[j] > temp)
                    j--;
                if (i < j)
                    arr[i] = arr[j];

                while (i < j && arr[i] < temp)
                    i++;
                if (i < j)
                    arr[j] = arr[i];
            }
            arr[i] = temp;
            fastSort(arr,start,i-1 );
            fastSort(arr,i + 1,end);
        }
    }



//    public static void sort(int[] arr,int start,int end){
//        if (start < end){
//             result = fastSort(arr,start,end);
//            sort(arr,start,result -1);
//            sort(arr,result + 1,end);
//        }
//    }


    public static void adjustHeap(int[] arr,int position,int len){
        int left = getLeft(position);
        int temp = arr[position];
        if (left <= len -1) {
            if (left + 1 <= len -1 && arr[left] < arr[left + 1])
                left++;
            if (temp < arr[left]) {
                arr[position] = arr[left];
                arr[left] = temp;
            }
            adjustHeap(arr, left, len);
        }
    }

    public static void HeapSort(int[] arr){
        for (int i = arr.length / 2 - 1; i >= 0; i --)
            adjustHeap(arr,i,arr.length);//建堆
        for (int j = arr.length - 1; j > 0; j --){
            swap(arr,0,j);
            adjustHeap(arr,0,j); //每次交换结束，从堆顶点开始调整堆
        }
    }




    public static int getLeft(int i){
        return 2 * i + 1;
    }


    public static void main(String[] args){
        int[] arr = {3,6,2,8,1,9,10,45,12};
        HeapSort(arr);
        for (int item : arr)
            System.out.println(item);
    }
}
