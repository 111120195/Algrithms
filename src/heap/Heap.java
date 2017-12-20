package heap;

//max_heap

import java.util.Arrays;

public class Heap {
    int[] heapArr;
    int heapSize;

    public Heap(int[] heap) {
        heapSize = heap.length;
        heapArr = heap;
    }

    public void shiftDown(int i, int heapSize) {
        int cur = i;
        int left = cur * 2 + 1;
        int right = cur * 2 + 2;
        int temp = heapArr[cur], largest;
        while (left < heapSize) {
            largest = right < heapSize && heapArr[left] < heapArr[right] ?
                    right : left;
            if (temp >= heapArr[largest])
                break;
            heapArr[cur] = heapArr[largest];
            cur = largest;
            right = cur * 2 + 2;
            left = cur * 2 + 1;
        }
        heapArr[cur] = temp;
    }

    public void buildHeap() {
        for (int i = (heapSize - 2) / 2; i >= 0; i--) {
            shiftDown(i, heapSize);
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(heapArr);
    }

    public static void main(String args[]) {
        int[] arr = {0, 1, 2, 3, 4, 4, 5, 6, 7, 8, 9};
        Heap heap = new Heap(arr);
        heap.buildHeap();
        System.out.println(heap);
    }
}