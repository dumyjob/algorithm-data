package com.markbolo.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

// 排序算法
public class Sorts {

    // 冒泡排序
    public static int[] bubbleSort(int[] array){
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i -1; j++){
                if(array[j] > array[j+1]){
                    int temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
        return array;
    }


    // 快速排序
    public static void quickSort(int[] array){
        // 快排思想: 分区 + 双指针 + 分治思想
        quickSort(array, 0, array.length-1);
    }

    private static void quickSort(int[] array, int start, int end){
        // 快排思想: 分区 + 双指针 + 分治思想
        if(start >= end){
            return;
        }

        int pivot = array[start]; // 基准值
        int i,j;
        for(i = start, j= end; i < j; ){
            while(array[i] < pivot && i< j){
                i++;
            }

            while(array[j] >= pivot && i< j){
                j--;
            }

            if(i<j){
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // 分治   ps: 处理好临界情况
        quickSort(array, start, i-1);
        quickSort(array, i+1, end);
    }

    // 简单插入排序
    public static int[] insertSort(int[] array){
        // 感觉有点理不过来,简单插入和简单选择排序了
        // 假设第一个元素有序, 从第二个元素开始, 在有序区中找新元素的位置i, 位置i以及之后的元素向后顺移
        for (int i = 1; i < array.length; i++) {

            int j = i -1;
            // 找到位置插入
            while (j >= 0 && array[i] < array[j]){
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;

                i--; j--;
            }
        }

        return array;
    }

    // 希尔排序
    public static int[] hillSort(int[] array){
        return null;
    }

    // 简单选择排序
    public static int[] selectSort(int[] array){
        // 算法思想: 将[r0, rn]划分为2块, 有序区: [r0,ri), 初始为空 ;无序区: [ri,rn]. 选出[ri,rn]中的最小元素和ri交换
        for(int i = 0; i<array.length ; i++){
            int minIndex = i;
            for(int j = i; j < array.length ; j++){
                if(array[minIndex] > array[j]){
                    minIndex = j;
                }
            }

            // swap i with minIndex
            int temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
        return array;
    }

    // 堆排序  ps: PriorityQueue优先级队列就是使用的二叉堆
    // 使用数组(广度优先遍历),不是直接维护一个树的结构
    public static int[] heapSort(int[] array){
        // 堆排序思想: 构建一个大顶堆[r0,..., rn] , r0和rn交换, rn就是最大的. 然后[r0, rn-1]重新构建大顶堆,重复前面的过程,可以得到一个有序的数组
        heapify(array);

        for(int i = array.length-1 ; i >= 1 ;){
            // 顶堆和无序区最后一个元素交换
            int temp = array[i];
            array[i] = array[0];
            array[0] = temp;

            // 无序区变小, 重新堆化
            heapify(array, 0, --i);
        }

        return array;
    }

    private static void heapify(int[] array) {
        int length = array.length;
        for(int i = length /2 ; i >=0 ; i--){
            heapify(array, i, length-1);
        }
    }

    // i: 堆顶点  lastIndex: 堆最后一个节点
    private static void heapify(int[] array, int i, int lastIndex) {
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int top = i;

        // 选举子节点中的大节点上位
        if (left <= lastIndex && array[left] > array[top]) {
            top = left;
        }
        if (right <= lastIndex && array[right] > array[top]) {
            top = right;
        }

        if (top != i) {
            // i 不符合大顶堆规则,下沉
            int temp = array[i];
            array[i] = array[top];
            array[top] = temp;

            // 下沉之后继续堆化,不行继续下沉
            heapify(array, top, lastIndex);
        }
    }


    // 归并排序
    public static int[] mergeSort(int[] array){
        // 怎么归并??  空间复杂度 O(n)
        int middle =array.length/2 ;
        if(array.length < 2){
            // 递归出口
            return array;
        }

        int[] left = Arrays.copyOfRange(array, 0, middle);
        int[] right = Arrays.copyOfRange(array, middle, array.length);

        return merge(mergeSort(left), mergeSort(right));
    }


    private static int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];

        int i = 0;
        int j = 0;
        int k = 0;
        while(i< result.length && j < left.length &&  k < right.length){
            if(left[j] < right[k]){
                result[i++] = left[j++];
            }else {
                result[i++] = right[k++];
            }
        }

        while(i<result.length && j < left.length){
            result[i++] = left[j++];
        }

        while(i<result.length && k < right.length){
            result[i++] = right[k++];
        }

        return result;
    }


    // 计数排序
    public static int[] countSort(int[] array){
        int max =array[0];
        for(int i = 1; i< array.length ; i ++){
            if(array[i] > max){
                max = array[i];
            }
        }

        int bucketLen = max + 1; // ps:注意要+1
        int[] bucket = new int[bucketLen];
        for(int i = 0; i< array.length ; i++){
           bucket[array[i]] ++;
        }

        for (int i = 0, j = 0; i < array.length && j < bucketLen; j++) {
            while (bucket[j]-- > 0) {
                array[i++] = j;
            }
        }

        return array;
    }

    // 桶排序
    public static int[] bucketSort(int[] array){


        return null;
    }

    // 基数排序
    public static int[] radixSort(int[] array){
        List<Queue<Integer>> vector = new ArrayList<>();








        return null;
    }
}
