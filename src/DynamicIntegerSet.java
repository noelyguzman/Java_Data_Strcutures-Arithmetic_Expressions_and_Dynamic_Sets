import java.util.ArrayList;
import java.util.List;
public class DynamicIntegerSet implements DyanmicSet {
    private Node root;
    public static class Node implements PrintableNode {
        Integer data;
        Node left, right;

        Node(int x) {
            this(x, null, null);
        }
        Node(int x, Node left, Node right) {
            this.data = x;
            this.left = left;
            this.right = right;
        }
        @Override
        public String getValueAsString() {
            return data.toString();
        }
        @Override
        public PrintableNode getLeft() {
            return left;
        }
        @Override
        public PrintableNode getRight() {
            return right;
        }
    }
    // this method must be there exactly in this form
        public Node root() {
            return this.root;
        }
// rest of your code for this class, including the size, contains, add, and remove methods
    //I am implementing a binary search tree
    /**
     * @return the number of elements in this set
     */
    @Override
    public int size() {
        return sizeRecursion(root);
    }
    public int sizeRecursion(Node BSTNodes){
        if(BSTNodes == null){
            return 0;
        }
        return 1 + sizeRecursion(BSTNodes.left) + sizeRecursion(BSTNodes.right);
    }
    /**
     * Checks if a specified element is a member of this set.
     *
     * @param value the specified element to be checked for set membership
     * @return <code>true</code> if and only if this set contains the specified element
     */
    public boolean contains(Integer value) {
        return containsRecursion(root, value);
    }
    //should have time complexity of log(n)
    private boolean containsRecursion(Node root, int value) {
        if (root == null) {
            return false;
        }
        if (root.data == value) {
            return true;
        }
        if (value < root.data) {
            return containsRecursion(root.left, value);
        }
        return containsRecursion(root.right, value);
    }
    /**
     * Adds a specified element to this set. Addition is successful unless the set already contains
     * this element.
     *
     * @param x the specified element to be added to this set
     * @return <code>true</code> if and only if the specified element was successfully added
     */
    @Override
    public boolean add(Integer x) {
        if(contains(x))
            return false;
        else
            root = add(root, x);
            return true;
    }
    private Node add(Node node, Integer value) {
        if (node == null) {
            return new Node(value);
        }
        if (value < node.data) {
            node.left = add(node.left, value);
        } else if (value > node.data) {
            node.right = add(node.right, value);
        }
        return node;
    }
    /**
     * Removes a specified element from this set.
     *
     * @param x the specified element to be removed from this set
     * @return <code>true</code> if and only if this element was successfully removed
     */
    @Override
    public boolean remove(Integer x) {
        if (!contains(x)) {
            return false; // Element not found in the tree, removal fails
        } else {
            root = remove(root, x);
            return true; // Element successfully removed
        }
    }
    private Node remove(Node root, Integer x) {
        if (root == null) {
            return null;
        }
        if (x < root.data) {
            root.left = remove(root.left, x);
        } else if (x > root.data) {
            root.right = remove(root.right, x);
        } else {
            // Node with the value x found, perform the removal
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }
            // Node has two children, find the in-order successor (smallest value in the right subtree)
            root.data = Min(root.right);
            root.right = remove(root.right, root.data);
        }
        return root;
    }
    private Integer Min(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node.data;
    }
    public static void printTree(PrintableNode node) {
        List<List<String>>  lines = new ArrayList<>();
        List<PrintableNode> level = new ArrayList<>();
        List<PrintableNode> next  = new ArrayList<>();

        level.add(node);
        int nn = 1;
        int widest = 0;
        while (nn != 0) {
            List<String> line = new ArrayList<>();
            nn = 0;
            for (PrintableNode n : level) {
                if (n == null) {
                    line.add(null);
                    next.add(null);
                    next.add(null);
                } else {
                    String aa = n.getValueAsString();
                    line.add(aa);
                    if (aa.length() > widest)
                        widest = aa.length();
                    next.add(n.getLeft());
                    next.add(n.getRight());
                    if (n.getLeft() != null)
                        nn++;
                    if (n.getRight() != null)
                        nn++;
                }
            }
            if (widest%2 == 1)
                widest++;
            lines.add(line);
            List<PrintableNode> tmp = level;
            level = next;
            next = tmp;
            next.clear();
        }

        int perpiece = lines.get(lines.size() - 1).size() * (widest + 4);
        for (int i = 0; i < lines.size(); i++) {
            List<String> line = lines.get(i);
            int hpw = (int) Math.floor(perpiece / 2f) - 1;

            if (i > 0) {
                for (int j = 0; j < line.size(); j++) {

                    // split node
                    char c = ' ';
                    if (j % 2 == 1) {
                        if (line.get(j - 1) != null) {
                            c = (line.get(j) != null) ? '┴' : '┘';
                        } else {
                            if (j < line.size() && line.get(j) != null) c = '└';
                        }
                    }
                    System.out.print(c);

                    // lines and spaces
                    if (line.get(j) == null) {
                        for (int k = 0; k < perpiece - 1; k++) {
                            System.out.print(" ");
                        }
                    } else {
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? " " : "─");
                        }
                        System.out.print(j % 2 == 0 ? "┌" : "┐");
                        for (int k = 0; k < hpw; k++) {
                            System.out.print(j % 2 == 0 ? "─" : " ");
                        }
                    }
                }
                System.out.println();
            }

            // print line of numbers
            for (String f : line) {
                if (f == null) f = "";
                final float a    = perpiece / 2f - f.length() / 2f;
                int   gap1 = (int) Math.ceil(a);
                int   gap2 = (int) Math.floor(a);

                // a number
                for (int k = 0; k < gap1; k++) {
                    System.out.print(" ");
                }
                System.out.print(f);
                for (int k = 0; k < gap2; k++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
            perpiece /= 2;
        }
    }
}