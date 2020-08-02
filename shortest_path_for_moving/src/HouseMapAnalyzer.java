import java.util.Set;

public class HouseMapAnalyzer {
    private HouseGraph initial;
    private HouseGraph relocated;
    Set<Integer> visited;
    Set<Integer> full;
    public HouseMapAnalyzer(HouseGraph initial) {
        this.initial = initial;
    }
}
