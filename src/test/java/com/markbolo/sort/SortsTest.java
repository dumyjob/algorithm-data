package com.markbolo.sort;

import org.junit.Assert;
import org.junit.Test;

public class SortsTest {

    int[] array = new int[]{9,8,7,6,7,4,3,2,1,0};
    int[] dest = new int[]{0,1,2,3,4,6,7,7,8,9};

    @Test
    public void testBubbleSort(){
        array = Sorts.bubbleSort(array);
        Assert.assertArrayEquals(array, dest);
    }


    @Test
    public void testQuickSort(){
        Sorts.quickSort(array);
        Assert.assertArrayEquals(array, dest);
    }

    @Test
    public void testSelectSort(){
        array = Sorts.selectSort(array);

        Assert.assertArrayEquals(array, dest);
    }


    @Test
    public void testHeapSort(){
        array = Sorts.heapSort(array);
        Assert.assertArrayEquals(array, dest);
    }


    @Test
    public void testInsertSort(){
        array = Sorts.insertSort(array);

        Assert.assertArrayEquals(array,dest);
    }


    @Test
    public void testMergeSort(){
        array = Sorts.mergeSort(array);
        Assert.assertArrayEquals(array,dest);
    }


    @Test
    public void testCountSort(){
        array = Sorts.countSort(array);
        Assert.assertArrayEquals(array,dest);
    }
}
