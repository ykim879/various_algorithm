public final class VertexDistance<T>
    implements Comparable<VertexDistance<? super T>> {

    private final Integer vertex;
    private final int distance;

    /**
     * Creates a pairing of vertex and distance to that vertex.
     *
     * @param vertex   the unused.Vertex to be stored.
     * @param distance the integer representing the distance to this unused.Vertex
     *                 from the previous unused.Vertex.
     */
    public VertexDistance(int vertex, int distance) {
        this.vertex = vertex;
        this.distance = distance;
    }

    /**
     * Gets the vertex.
     *
     * @return the vertex
     */
    public int getVertex() {
        return vertex;
    }

    /**
     * Gets the distance
     *
     * @return the distance
     */
    public int getDistance() {
        return distance;
    }

    @Override
    public boolean equals(Object o) {
        if (o != null && o instanceof VertexDistance<?>) {
            VertexDistance<?> e = (VertexDistance<?>) o;
            return distance == e.distance && vertex == e.vertex;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return vertex.hashCode() ^ distance;
    }

    @Override
    public int compareTo(VertexDistance<? super T> pair) {
        return this.getDistance() - pair.getDistance();
    }

    @Override
    public String toString() {
        return "Pair with vertex " + vertex + " and distance " + distance;
    }
}