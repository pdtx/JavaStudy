/**
 * @description: java 实现各种排序算法
 * @author: fancying
 * @create: 2019-03-28 20:56
 **/
package leetcode;

public class SortAlgorithm {


    /**
     * 快速排序
     * */
    public void quickSort(int [] array, int first, int end) {
        if (first < end) {
            int medium = partition(array, first ,end);
            quickSort(array, first, medium -1);
            quickSort(array, medium + 1, end);
        }
    }
    private int partition(int[] array, int first, int end) {
        int key = array[first];
        while (first < end) {
            while (first < end && array[end] >= key) {
                end--;
            }
            array[first] = array[end];
            while (first < end && array[first] <= key) {
                first++;
            }
            array[end] = array[first];
        }
        array[first] = key;
        return first;
    }

    /**
     * 归并排序
     * */
    public void mergeSort(int [] array, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(array, start, mid);
            mergeSort(array, mid + 1, end);
            merge(array, start, mid, end);
        }
    }

    private void merge(int[] array, int start, int mid, int end) {

    }


    public static void main(String[] args) {
        int array[] = {-1,1,2,3,4,6,6,7};
        SortAlgorithm  sortAlgorithm = new SortAlgorithm();
        sortAlgorithm.quickSort(array,0,array.length - 1);
        for (int i : array)
            System.out.println(i);

    }
}