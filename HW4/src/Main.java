import java.io.*;
import java.text.*;
import java.util.*;

/**
 * Created by AlinaCh on 17.04.2017.
 */
public class Main {

    public static MyGraph<String, Weight> map;
    public static MyGraph<String, Weight> minimumSpanningTree;

    /**
     * creates map from given file.
     * inserts vertices and edges
     * @throws FileNotFoundException if there's no file in the directory
     * @throws MyException if there's some mistake with the work of graph
     * @throws ParseException if there's some mistake with turning string into double
     */
    public static void createMap() throws FileNotFoundException, MyException, ParseException {
        Scanner sc = new Scanner(new File("russia.txt"));
        String vertices = sc.nextLine();
        map = new MyGraph<>(false);
        for (String temp : vertices.split(" ")) {
            map.insertVertex(temp);
        }
        String[] edges = sc.nextLine().split(" ");
        for (int i = 0; i < edges.length; i = i + 3) {
            Weight w = createWeight(edges[i + 2].split(":"));
            map.insertEdge(edges[i], edges[i + 1], w);
        }
    }

    /**
     * reads the input file, which contains information of
     * the origin and destination place and the weight of the delivery
     * @return
     * @throws FileNotFoundException if there's no file in the directory
     */
    public static String[] read() throws FileNotFoundException {
        Scanner sc = new Scanner(new File("input.txt"));
        String path = "";
        while (sc.hasNextLine()) {
            path = path + sc.nextLine() + "\n";
        }
        String[] res = path.split("\n");
        return res;
    }

    /**
     * writes answer to the output file
     * @param s
     */
    public static void write(String s) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream("output.txt"), "utf-8"))) { writer.write(s); }
        catch (IOException ex) { }
    }

    /**
     * for each delivery chooses the 'shortest' path by the time parameter
     * @param path array of the deliveries
     * @return answer, containing origin and destination place
     * with the overall cost of delivery
     * @throws ParseException if there's some mistake with turning string into double
     * @throws MyException if there's some mistake with the work of graph
     */
    public static String algorithm(String[] path) throws ParseException, MyException {
        DijkstraAlgorithm test = new DijkstraAlgorithm(minimumSpanningTree);
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        String res = "";
        for (int i = 0; i < path.length; i++) {
            String[] temp = path[i].split(" ");
            String origin = temp[0];
            String destination = temp[1];
            Double mass = format.parse(temp[2]).doubleValue();
            Weight w = test.findShortestPath(origin, destination);
            Double cost = w.cost * mass;
            res = res + origin + " " + destination + " " +
                    String.format("%.1f", mass) + " " + String.format("%.1f", w.hours)
                    + " " + String.format("%.1f", cost);
            if(i < path.length - 1)
                res = res + "\n";
        }
        return res;
    }

    /**
     * creates weight
     * @param w arrays of values for the weight
     * @return weight
     * @throws ParseException if there's some mistake with turning string into double
     */
    public static Weight createWeight(String[] w) throws ParseException {
        NumberFormat format = NumberFormat.getInstance(Locale.US);
        Double d = format.parse(w[0]).doubleValue();
        Double t = format.parse(w[1]).doubleValue();
        Double c = format.parse(w[2]).doubleValue();
        return new Weight(d,t,c);
    }

    /**
     * creates minimum spanning tree using Kruskal's Algorithm
     * @throws MyException if there's some mistakes with the work of the graph
     */
    public static void createMinimumSpanningTree() throws MyException {
        KruskalAlgorithm alg = new KruskalAlgorithm(map);
        minimumSpanningTree = alg.execute();
    }

    public static void main(String[] args) throws FileNotFoundException, ParseException, MyException {
        createMap();
        createMinimumSpanningTree();
        write(algorithm(read()));
    }
}
