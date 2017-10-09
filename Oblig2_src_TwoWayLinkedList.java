import java.util.AbstractSequentialList;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class TwoWayLinkedList<E> extends AbstractSequentialList<E> {
    private Node<E> head, tail;
    private int size = 0;

    public TwoWayLinkedList(){

    }

    @Override
    public int size() {
        return size;
    }

    public E getFirst(){
        if(size == 0){
            return null;
        } else {
            return head.element;
        }
    }

    public E getLast(){
        if(size == 0){
            return null;
        } else {
            return tail.element;
        }
    }

    public void addFirst(E e){
        Node<E> newNode = new Node<>(e);
        newNode.next = head;
        head.previous = newNode;
        head = newNode;
        size++;

        if (tail == null){
            tail = head;
        }
    }

    public void addLast(E e){
        Node<E> newNode = new Node<>(e);

        if (tail == null){
            this.head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.previous = tail;
            tail = tail.next;
        }

        size++;
    }

    @Override
    public void add(int index, E e){
        if (index == 0) addFirst(e);
        else if (index >= size) addLast(e);
        else {
            Node<E> current = head;
            for (int i = 1; i < index; i++){
                current = current.next;
            }
            Node<E> temp = current.next;
            current.next = new Node<>(e);
            (current.next).next = temp;
            (current.next).previous = current;
            temp.previous = current.next;
            size++;
        }

    }

    public E removeFirst(){
        if (size == 0) return null;
        else {
            Node<E> temp = head;
            head = head.next;
            head.previous = null;
            size--;
            if (head == null) tail = null;
            return temp.element;
        }
    }

    public E removeLast(){
        if (size == 0) return null;
        else if (size == 1){
            Node<E> temp = head;
            head = tail = null;
            size = 0;
            return temp.element;
        } else {
            Node<E> current = tail;

            tail = tail.previous;
            tail.next = null;
            size--;

            return current.element;
        }
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) return null;
        else if (index == 0) return removeFirst();
        else if (index == size-1) return removeLast();
        else {
            Node<E> previous = head;

            for (int i=1; i < index; i++)
                previous = previous.next;

            Node<E> current = previous.next;
            previous.next = current.next;
            (current.next).previous = previous;

            size--;

            return current.element;
        }
    }

    @Override
    public String toString() {
        StringBuilder result =new StringBuilder("[");

        Node<E> current = head;
        for (int i = 0; i < size; i++){
            result.append(current.element);
            current = current.next;
            if (current != null) {
                result.append(", ");
            } else {
                result.append("]");
            }
        }

        return result.toString();
    }

    @Override
    public void clear() {
        size = 0;
        head = tail = null;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) return null;
        else if (index == 0) return head.element;
        else if (index == size-1) return tail.element;
        else {
            Node<E> current = head;
            for (int i = 0; i < index; i++){
                current = current.next;
            }
            return current.element;
        }

    }

    @Override
    public E set(int index, E e) {
        if (index < 0 || index >= size) return null;
        else if (index == 0){
            Node<E> current = head;
            head.element = e;
            return current.element;
        }
        else if (index == size-1) {
            Node<E> current = tail;
            tail.element = e;
            return current.element;
        }
        else {
            Node<E> current = head;
            for (int i = 0; i < index; i++){
                current = current.next;
            }
            E element = current.element;
            current.element = e;
            return element;
        }
    }

    @Override
    public ListIterator<E> listIterator() {
        return new TwoWayListIterator();
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new TwoWayListIterator(index);
    }

    private class TwoWayListIterator implements ListIterator<E>{
        private Node<E> current = null;
        private Node<E> lastRead = null;

        private int index = 0;

        public TwoWayListIterator() {
            this(0);
        }

        public TwoWayListIterator(int index) {
            this.index = index;
            setCurrent(index);
        }

        public void setCurrent(int index){
            for (int i=0; i<=index; i++){
                if (current == null){
                    current = head;
                } else {
                    current = current.next;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public boolean hasPrevious() {
            return index > 0;
        }

        @Override
        public int previousIndex() {
            return index - 1;
        }

        @Override
        public int nextIndex() {
            return index;
        }

        @Override
        public E next() {
            if (!hasNext()) throw new NoSuchElementException();
            lastRead = current;
            E e = current.element;
            current = current.next;
            index++;
            return e;
        }

        @Override
        public E previous() {
            if (!hasPrevious()) throw new NoSuchElementException();
            if (current == null){
                current = tail;
                index --;
            }
            current = current.previous;
            index--;
            lastRead = current;
            return current.element;

        }

        @Override
        public void set(E e) {
            if (lastRead == null) throw new IllegalStateException();
            lastRead.element = e;
        }

        @Override
        public void remove() {
            if (lastRead == null) throw new IllegalStateException();
            Node<E> x = lastRead.previous;
            Node<E> y = lastRead.next;
            x.next = y;
            y.previous = x;
            size--;
            if (current == lastRead)
                current = y;
            else
                index--;
            lastRead = null;
        }

        @Override
        public void add(E e) {
            Node<E> x = current.previous;
            Node<E> y = new Node(e);
            Node<E> z = current;
            x.next = y;
            y.next = z;
            z.previous = y;
            y.previous = x;
            size++;
            index++;
            lastRead = null;
        }
    }

    private static class Node<E>{
        E element;
        Node<E> next = null, previous = null;

        public Node(E element){
            this.element = element;
        }
    }
}
