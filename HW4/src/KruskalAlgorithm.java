import java.util.*;

/**
 * Created by AlinaCh on 18.04.2017.
 * The Kruskal's Algorithm of getting the minimum spanning tree,
 * by sorting edges in the increasing order and adding it to the tree,
 * in such way, so the new graph will not contain cycle and will have minimum weight
 */
public class KruskalAlgorithm {

    private MyGraph<String, Weight> start;
    private MyGraph<String, Weight> result;
    private Edge[] edges;
    private Map<String, String> parent = new HashMap<>();

    /**
     * the initializing of the algorithm, where the given graph duplicates
     * @param graph which would be worked on
     */
    KruskalAlgorithm(MyGraph graph) {
        this.start = graph;
    }

    /**
     * creates the minimum spanning tree and adds vertices
     * and adds edges to the new graph
     * @return minimum spanning tree
     * @throws MyException if there're some mistakes with work of the graph
     */
    public MyGraph<String, Weight> execute() throws MyException {
        sortEdges();
        result = new MyGraph<>(start.isDirected());
        insertVertices();
        int i = 0;
        while (result.numEdges() < result.numVertices() - 1) {
            Edge e = edges[i];
            if (!hasCycle(e)) {
                result.insertEdge((String)e.endVertices[0], (String)e.endVertices[1],
                        (Weight)e.weight);
                unite((String)e.endVertices[0], (String)e.endVertices[1]);
            }
            i++;
        }
        return result;
    }

    /**
     * checks whether adding the certain edge will conclude in the tree containing cycle
     * @param e edge which may be inserted
     * @return true or false
     */
    private boolean hasCycle(Edge e) {
        String origin = find((String) e.endVertices[0]);
        String dest = find((String) e.endVertices[1]);
        if (origin.equals(dest))
            return true;
        else
            return false;
    }

    /**
     * unites the new edge with the main set
     * @param origin the source vertex of edge
     * @param dest the sink vertex of edge
     */
    private void unite(String origin, String dest) {
        String xorigin = find(origin);
        String ydest = find(dest);
        parent.replace(xorigin, ydest);
    }

    /**
     * finds whether the vertex is already in the main set
     * @param vertex
     * @return the 'parent' vertex, returns itself if such vertex is not in the main set
     */
    private String find(String vertex) {
        if (parent.get(vertex).equals("-1"))
            return vertex;
        return find(parent.get(vertex));
    }

    /**
     * sorts edges in the increasing order using bubble sort
     */
    private void sortEdges() {
        edges = (Edge[]) start.getEdges().toArray(new Edge[0]);
        boolean swapped = true;
        int j = 0;
        while (swapped) {
            swapped = false;
            j++;
            for (int i = 0; i < edges.length - j; i++) {
                Weight w1 = (Weight) edges[i].weight;
                Weight w2 = (Weight) edges[i + 1].weight;
                if (w1.distanceCompareTo(w2) > 0) {
                    swap(i, i+1);
                    swapped = true;
                }
            }
        }
    }

    /**
     * swaps two edges in the still unsorted array
     * @param i the index of first edge
     * @param j the index of second edge
     */
    private void swap(int i, int j){
        Edge temp = edges[i];
        edges[i] = edges[j];
        edges[j] = temp;
    }

    /**
     * insert all vertices in the minimum spanning tree
     * makes them without edges, by making their 'parent' vertex in the main set '-1'
     * '-1' shows that there's no edge which connects vertices
     * @throws MyException
     */
    private void insertVertices() throws MyException {
        Set<String> v = start.getVertices();
        for (String vertex : v) {
            result.insertVertex(vertex);
            parent.put(vertex, "-1");
        }
    }
}
