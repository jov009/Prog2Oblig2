import java.util.ArrayList;
import java.util.Collections;

public class BST<E extends Comparable<E>> extends AbstractTree<E> {
    protected TreeNode<E> root;
    protected int size = 0;

    public BST() {
    }

    public BST(E[] objects) {
        for (int i = 0; i < objects.length; i++)
            insert(objects[i]);
    }

    @Override
    public boolean search(E e) {
        TreeNode<E> current = root;

        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }
            else
                return true;
        }

        return false;
    }

    @Override
    public boolean insert(E e) {
        TreeNode<E> newNode = createNewNode(e);
        if (root == null) {
            root = newNode;
            root.parent = null;
        } else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null)
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                }
                else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                }
                else
                    return false;

            newNode.parent = parent;
            if (e.compareTo(parent.element) < 0) {
                parent.left = newNode;
            } else {
                parent.right = newNode;
            }
        }

        size++;
        return true;
    }

    protected TreeNode<E> createNewNode(E e) {
        return new TreeNode<>(e);
    }

    @Override
    public void inorder() {
        inorder(root);
    }

    protected void inorder(TreeNode<E> root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.element + " ");
        inorder(root.right);
    }

    @Override
    public void postorder() {
        postorder(root);
    }

    protected void postorder(TreeNode<E> root) {
        if (root == null) return;
        postorder(root.left);
        postorder(root.right);
        System.out.print(root.element + " ");
    }

    @Override
    public void preorder() {
        preorder(root);
    }

    protected void preorder(TreeNode<E> root) {
        if (root == null) return;
        System.out.print(root.element + " ");
        preorder(root.left);
        preorder(root.right);
    }

    public static class TreeNode<E extends Comparable<E>> {
        public E element;
        public TreeNode<E> left;
        public TreeNode<E> right;
        public TreeNode<E> parent;

        public TreeNode(E e) {
            element = e;
        }
    }

    @Override
    public int getSize() {
        return size;
    }

    public TreeNode<E> getRoot() {
        return root;
    }

    public ArrayList<TreeNode<E>> path(E e) {
        ArrayList<TreeNode<E>> list =
                new ArrayList<>();
        TreeNode<E> current = root;

        while (current != null) {
            list.add(current);
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }
            else
                break;
        }

        return list;
    }

    @Override
    public boolean delete(E e) {
        if (e == null) return false;
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            }
            else if (e.compareTo(current.element) > 0) {
                current = current.right;
            }
            else
                break;
        }

        if (current == null)
            return false;

        if (current.left == null) {
            if (current.parent == null) {
                root = current.right;
                root.parent = null;
            }
            else {
                if (e.compareTo(current.parent.element) < 0) {
                    current.parent.left = current.right;
                } else if (e.compareTo(current.parent.element) > 0){
                    current.parent.right = current.right;
                }
                if (current.right != null)
                    (current.right).parent = current.parent;
            }
        }
        else {
            TreeNode<E> rightMost = current.left;

            while (rightMost.right != null) {
                rightMost = rightMost.right;
            }

            current.element = rightMost.element;

            if (rightMost.parent.right == rightMost) {
                rightMost.parent.right = rightMost.left;
                rightMost.left.parent = rightMost.parent;
            } else {
                rightMost.parent.left = rightMost.left;
                rightMost.left.parent = rightMost.parent;
            }
        }

        size--;
        return true;
    }

    private TreeNode<E> getNode(E element){
        if (root == null) return null;

        TreeNode<E> current = root;

        while (current != null){
            if (element.compareTo(current.element) < 0) {
                current = current.left;
            }
            else if (element.compareTo(current.element) > 0) {
                current = current.right;
            }
            else{
                return current;
            }
        }
        return null;
    }

    private boolean isLeaf(E element) {
        TreeNode<E> node = getNode(element);
        return node != null && node.right == null && node.left == null;
    }

    public ArrayList<E> getPath(E e){
        TreeNode<E> start = getNode(e);
        if (start == null) return null;

        ArrayList<E> path = new ArrayList<>();

        do {
            path.add(start.element);
            start = start.parent;
        } while (start != null);

        Collections.reverse(path);

        return path;

    }

    @Override
    public java.util.Iterator<E> iterator() {
        return new PreorderIterator();
    }

    private class PreorderIterator implements java.util.Iterator<E> {

        private TwoWayLinkedList<E> list = new TwoWayLinkedList<>();
        private int current = 0;

        public PreorderIterator() {
            preorder();
        }

        private void preorder() {
            preorder(root);
        }

        private void preorder(TreeNode<E> root) {
            if (root == null)return;
            list.add(root.element);
            preorder(root.left);
            preorder(root.right);
        }

        @Override
        public boolean hasNext() {
            return current < list.size();

        }

        @Override
        public E next() {
            if (hasNext()){
                return list.get(current++);
            }
            return null;
        }

        @Override
        public void remove() {
            delete(list.get(current));
            list.clear();
            inorder();
        }
    }

    public void clear() {
        root = null;
        size = 0;
    }
}

