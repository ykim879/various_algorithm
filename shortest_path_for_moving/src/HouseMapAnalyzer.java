import java.util.*;

public class HouseMapAnalyzer {
//    private HouseGraph initial;
//    private HouseGraph relocated;
//    Set<Integer> visited;
//    Set<Integer> full;
    private int vertex;
    private Map<Integer, Integer>[] result;

    private int[] reloacted;
    private int[] maxRelocated;

    private int totalDistance;
    private int totalMaxDistance;

    public HouseMapAnalyzer(HouseGraph graph) {
        vertex = graph.getVertex();
        result = new HashMap[graph.getVertex() + 1];

        reloacted = new int[graph.getVertex()];
        maxRelocated = new int[graph.getVertex()];
        for (int i = 1; i <= graph.getVertex(); i++) {
            result[i] = new HashMap<>();
            dijkstras(i, graph);
        }
    }
    private void dijkstras(Integer start, HouseGraph graph) {
        if (start == null || graph == null || graph.getVertex() < start) {
            throw new IllegalArgumentException("Invalid parameters.");
        }

        HashSet<Integer> visited = new HashSet<>();
        PriorityQueue<VertexDistance<Integer>> priorityQueue = new PriorityQueue<>();
        PriorityQueue<VertexDistance<Integer>> maxPriorityQueue = new PriorityQueue<>(Collections.reverseOrder());
        int visitedN = 0;
        for (int vd = 1; vd <= graph.getVertex(); vd++) {
            result[start].put(vd, Integer.MAX_VALUE);
        }
        priorityQueue.add(new VertexDistance<>(start, 0));
        while (!priorityQueue.isEmpty()) {
            VertexDistance<Integer> current = priorityQueue.remove();
            if (!visited.contains(current.getVertex())&& visitedN < graph.getVertex()) {
                visited.add(current.getVertex());
                result[start].replace(current.getVertex(), current.getDistance());
                visitedN++;
                for (VertexDistance<Integer> vd: graph.getAdjList().get(current.getVertex())) {
                    if (visitedN >= graph.getVertex()) {
                        break;
                    } else if (!visited.contains(vd.getVertex())) {
                        priorityQueue.add(new VertexDistance<>(vd.getVertex(),
                                current.getDistance() + vd.getDistance()));
                    }
                }
            }
        }
        result[start].remove(start);
    }
    public void outputResult() {
        HashSet<Integer> originated = new HashSet<>();
        HashSet<Integer> ended = new HashSet<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        PriorityQueue<Edge> mpq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 1; i <= vertex; i++) {
            for(Map<Integer, Integer> m : result) {
                for(int p = 1; p <= vertex; p++) {
                    if (p != i) {
                        pq.add(new Edge(i, p, m.get(p)));
                        mpq.add(new Edge(i, p, m.get(p)));
                    }
                }
            }
        }
        while (originated.size() < vertex) {
            Edge curr = pq.remove();
            if (!originated.contains(curr.getU()) && !ended.contains(curr.getV())) {
                reloacted[curr.getU()] = curr.getV();
                totalDistance += curr.getWeight();
                originated.add(curr.getU());
                ended.add(curr.getV());
            }
        }
        originated.clear();
        ended.clear();
        while (originated.size() < vertex) {
            Edge curr = mpq.remove();
            if (!originated.contains(curr.getU()) && !ended.contains(curr.getV())) {
                maxRelocated[curr.getU()] = curr.getV();
                totalMaxDistance += curr.getWeight();
                originated.add(curr.getU());
                ended.add(curr.getV());
            }
        }
        printResult();
    }
    private void printResult() {
        System.out.print("total minimum distance: " + totalDistance + " relocation: ");
        for(int i = 0; i < vertex; i++) {
            System.out.print(reloacted[i] + " ");
        }
        System.out.print("total maximum distance: " + totalMaxDistance + " relocation: ");
        for(int i = 0; i < vertex; i++) {
            System.out.print(maxRelocated[i] + " ");
        }
    }
    public static void main(String args[]) {
        HouseGraph graph = new HouseGraph();
        HouseMapAnalyzer result = new HouseMapAnalyzer(graph);
        result.outputResult();
    }
}
