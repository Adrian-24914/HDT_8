package uvg.com;

import java.util.ArrayList;

public class VectorHeap<E extends Comparable<E>> {
    private ArrayList<E> heap;

    public VectorHeap() {
        heap = new ArrayList<>();
    }

    private void siftUp(int index) {
        while (index > 0) {
            int parent = (index - 1) / 2;
            if (heap.get(index).compareTo(heap.get(parent)) < 0) {
                E temp = heap.get(index);
                heap.set(index, heap.get(parent));
                heap.set(parent, temp);
            }
            index = parent;
        }
    }

    private void siftDown(int index) {
        int size = heap.size();
        while (index * 2 + 1 < size) {
            int leftChild = index * 2 + 1;
            int rightChild = index * 2 + 2;
            int min = leftChild;
            if (rightChild < size && heap.get(rightChild).compareTo(heap.get(leftChild)) < 0) {
                min = rightChild;
            }
            if (heap.get(index).compareTo(heap.get(min)) <= 0) {
                break;
            }
            E temp = heap.get(index);
            heap.set(index, heap.get(min));
            heap.set(min, temp);
            index = min;
        }
    }

    public void insert(E item) {
        heap.add(item);
        siftUp(heap.size() - 1);
    }

    public E remove() {
        if (heap.isEmpty()) return null;
        E min = heap.get(0);
        E last = heap.remove(heap.size() - 1);
        if (!heap.isEmpty()) {
            heap.set(0, last);
            siftDown(0);
        }
        return min;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }
}
