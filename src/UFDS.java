import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UFDS<E> {
    private Map<E, E> parent;
    private Map<E, Integer> rank;
    private int numSets;

    public UFDS(List<E> elements) {
        parent = new HashMap<>();
        rank = new HashMap<>();
        numSets = elements.size();
        for (E e : elements) {
            parent.put(e, e);
            rank.put(e, 0);
        }
    }

    public E findSet(E e) {
        if (parent.get(e) == e) {
            return e;
        }
        E root = findSet(parent.get(e));
        parent.put(e, root);
        return root;
    }

    public boolean isSameSet(E e1, E e2) {
        return findSet(e1) == findSet(e2);
    }

    public void union(E e1, E e2) {
        if (isSameSet(e1, e2)) {
            return;
        }
        E root1 = findSet(e1);
        E root2 = findSet(e2);
        if (rank.get(root1) > rank.get(root2)) {
            parent.put(root2, root1);
        } else {
            parent.put(root1, root2);
            if (rank.get(root1) == rank.get(root2)) {
                rank.put(root2, rank.get(root2) + 1);
            }
        }
        numSets--;
    }

    public int numSets() {
        return numSets;
    }
}