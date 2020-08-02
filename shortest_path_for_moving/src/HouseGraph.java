import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class HouseGraph {
    private int vertex;
    private PriorityQueue<Edge> edgeList = new PriorityQueue<>();
    public HouseGraph () {
        String fileName = RequestFileName();
        ReadFile(fileName);
    }
    public void setVertex(int vertex) {
        this.vertex = vertex;
    }
    public int getVertex() {
        return vertex;
    }

    public PriorityQueue<Edge> getEdgeList() {
        return edgeList;
    }

    public void addEdge(int u, int v) {
        edgeList.add(new Edge(u, v, 1));
    }
    public String RequestFileName() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Type the file name:");
        return scan.next();
    }
    public void ReadFile(String fileName) {
        boolean firstLine = true;
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
