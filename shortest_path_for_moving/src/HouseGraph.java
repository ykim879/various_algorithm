import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class HouseGraph {
    private int vertex;
    private Set<Edge> edges;
    private Map<Integer, List<VertexDistance<Integer>>> adjList;
    //constructor
    public HouseGraph () {
        String fileName = RequestFileName();
        ReadFile(fileName);
    }
    //setter
    public void setVertex(int vertex) {
        this.vertex = vertex;
    }


    //getter
    public int getVertex() {
        return vertex;
    }
    public Set<Edge> getEdges() {
        return edges;
    }
    public Map<Integer, List<VertexDistance<Integer>>> getAdjList() {
        return adjList;
    }

    // methods for constructor
    private void addEdge(int u, int v) {
        Edge edge = new Edge(u, v, 1);
        Edge rEdge = new Edge(u, v, 1);
        this.edges.add(edge);
        edges.add(rEdge);
        if (!adjList.containsKey(u)) {
            adjList.put(u, new ArrayList<>(vertex));
        }
        if (!adjList.containsKey(v)) {
            adjList.put(v, new ArrayList<>());
        }
        adjList.get(u).add(new VertexDistance<>(v, 1));
        adjList.get(v).add(new VertexDistance<>(u, 1));
    }
    private String RequestFileName() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Type the file name:");
        return scan.next();
    }
    private void ReadFile(String fileName) {
        boolean firstLine = true;
        adjList = new HashMap<>();
        this.edges = new HashSet<>();
        try {
            File file = new File(fileName);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                if (firstLine) {
                    setVertex(Integer.parseInt(data));
                } else {
                    String[] edgeInfo = data.split(" ");
                    addEdge(Integer.parseInt(edgeInfo[0]), Integer.parseInt(edgeInfo[1]));
                }
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
