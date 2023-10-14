import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class Heap<T extends Comparable<T>> {

    public static int parent(int i) {
        return (i - 1) / 2;
    }

    public static int leftChild(int i) {
        return 2 * i + 1;
    }

    public static int rightChild(int i) {
        return 2 * i + 2;
    }

    public static <T extends Comparable<T>> void heapifyUp(List<T> heap, int i) {
        int parentIndex = parent(i);

        while (i > 0 && heap.get(i).compareTo(heap.get(parentIndex)) < 0) {
            swap(heap, i, parentIndex);
            i = parentIndex;
            parentIndex = parent(i);
        }
    }

    public static <T extends Comparable<T>> void heapifyDown(List<T> heap, int i, int heapSize) {
        int left = leftChild(i);
        int right = rightChild(i);
        int smallest = i;

        if (left < heapSize && heap.get(left).compareTo(heap.get(i)) < 0) {
            smallest = left;
        }

        if (right < heapSize && heap.get(right).compareTo(heap.get(smallest)) < 0) {
            smallest = right;
        }

        if (smallest != i) {
            swap(heap, i, smallest);
            heapifyDown(heap, smallest, heapSize);
        }
    }

    private static <T> void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }

    private List<T> heap;

    public Heap() {
        this.heap = new ArrayList<T>();
    }

    public void insert(T element) {
        heap.add(element);
        heapifyUp(heap, heap.size() - 1);
    }

    public T extractMin() {
        if (heap.isEmpty()) {
            throw new NoSuchElementException("Heap is empty");
        }

        T removed = heap.get(0);
        int lastIndex = heap.size() - 1;

        heap.set(0, heap.get(lastIndex));
        heap.remove(lastIndex);
        heapifyDown(heap, 0, lastIndex);

        return removed;
    }

    public boolean isEmpty() {
        return heap.isEmpty();
    }

    public int size() {
        return heap.size();
    }
}
