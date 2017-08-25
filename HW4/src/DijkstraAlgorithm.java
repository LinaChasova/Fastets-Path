import java.util.*;

/**
 * Created by AlinaCh on 18.04.2017.
 * The algorithm for finding the 'shortest' path of the graph
 */
public class DijkstraAlgorithm {

    private MyGraph<String, Weight> map;
    private Set<String> shortestPathTree;
    private Set<String> unsettled;
    private Map<String, Weight> distance;

    /**
     * the initializing of the algorithm, where the given graph duplicates
     * @param graph which would be worked on
     */
    DijkstraAlgorithm(MyGraph<String, Weight> graph) {
        this.map = graph;
    }

    /**
     * finds the 'shortest' (fastest for given assignment)
     * path from origin to destination vertex
     * @param origin vertex
     * @param destination vertex
     * @return the information of the taken weight from origin vertex
     * to the destination vertex
     * @throws MyException if there's some mistake with work of the graph
     */
    public Weight findShortestPath(String origin, String destination) throws MyException {
        execute(origin);
        return distance.get(destination);
    }

    /**
     * finds 'shortest' path from origin vertex to its adjacent vertices of the graph
     * creates the shortestPathTree, stores information about the overall 'distance'
     * in the specific hashmap
     * @param origin vertex
     * @throws MyException if there's some mistake in the work of the graph
     */
    public void execute(String origin) throws MyException {
        shortestPathTree = new HashSet<>();
        unsettled = new HashSet<>();
        distance = new HashMap<>();
        distance.put(origin, new Weight());
        unsettled.add(origin);
        while (unsettled.size() > 0) {
            String node = findMin(unsettled);
            shortestPathTree.add(node);
            unsettled.remove(node);
            findFastestRoad(node);
        }
    }

    /**
     * finds the next vertex which takes the minimum weight to reach
     * @param vertices set of adjacent vertices
     * @return vertex with minimum weight
     */
    private String findMin(Set<String> vertices) {
        String minimum = null;
        for (String vertex : vertices) {
            if (minimum == null)
                minimum = vertex;
            else
                if (getFastestRoad(vertex).hoursCompareTo(getFastestRoad(minimum)) < 0)
                    minimum = vertex;
        }
        return minimum;
    }

    /**
     * adds vertices which is not in the shortest path
     * and if their weight is less than the one in the distance hashmap
     * (the original weigth for unreached vertices is the maximum double value)
     * @param vertex origin vertex
     * @throws MyException if there's some mistakes with the work of the graph
     */
    private void findFastestRoad(String vertex) throws MyException {
        List<String> adjacentNodes = getIncident(vertex);
        for (String destination : adjacentNodes) {
            Weight temp = getFastestRoad(vertex).plus(getWeight(vertex, destination));
            if (getFastestRoad(destination).hoursCompareTo(temp) > 0) {
                distance.put(destination, temp);
                unsettled.add(destination);
            }
        }
    }

    /**
     * @param node origin vertex
     * @param target sink vertex
     * @return the weight of the edge
     * @throws MyException if there's no such edge
     */
    private Weight getWeight(String node, String target) throws MyException {
        if (map.hasEdge(node, target)) {
            return (Weight) map.getEdge(node, target).weight;
        } else
            throw new MyException("There's no such edge");
    }

    /**
     * adds to the unsettled set vertices, which is adjacent to given vertex,
     * unless they are already in the shortest path tree
     * @param vertex origin vertex
     * @throws MyException if there's some mistakes with the work of the graph
     */
    private List<String> getIncident(String vertex) throws MyException {
        List<String> neighbours = new ArrayList<>();
        String[] temp = (String[]) map.getOutgoingVertices(vertex).toArray(new String[0]);
        for (int i = 0; i < temp.length; i++) {
            if (!isSettled(temp[i]))
                neighbours.add(temp[i]);
        }
        return neighbours;
    }

    /**
     * @param vertex
     * @return whether the vertex in the shosrtest path tree
     */
    private boolean isSettled(String vertex) {
        return shortestPathTree.contains(vertex);
    }

    /**
     * checks whether the given vertex was already reached
     * and whether the new path is shorter than the original one
     * @param destination vertex
     * @return the minimum weight of the paths
     */
    private Weight getFastestRoad(String destination) {
        Weight d = distance.get(destination);
        if (d == null) {
            return new Weight(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        } else
            return d;
    }
}
