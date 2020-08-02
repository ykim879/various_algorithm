import java.util.PriorityQueue;
import java.util.ArrayList;

public class FullHouseGraph {
    private int vertex;
    private PriorityQueue<Edge> edgeList = new PriorityQueue<>();
    private ArrayList<Boolean> visited;

    public FullHouseGraph(HouseGraph initial) {
        this.vertex = initial.getVertex();
        this.edgeList = initial.getEdgeList();
        this.visited = new ArrayList<Boolean>(vertex);
    }
    private void completeGraph() {

    }
    private void completeVisited() {

    }
}
