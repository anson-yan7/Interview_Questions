import java.util.LinkedList;
import java.util.List;

/**
 * <h1>QUESTION 1: FLIGHT SCHEDULES</h1>
 * <p/>
 *
 * Class to implement the algorithm to determine a possible flight schedule given the conditions.
 * <p/>
 *
 * See description of problem and example in the write-up.
 *
 * @author bursztyn
 */
public class Question1 {

    /**
     * Given the number of flights to schedule and conditions for departure, returns one possible
     * departure schedule.
     *
     * @param numFlights the number of flights waiting to take off
     * @param conditions the list of condition pairs (no duplicate pairs may exist)
     * @return one possible valid flight departure ordering, or an empty list if none exists
     * 
     * @implSpec you may assume that input is valid (well formatted, mx2 2d-array) and non-null.
     * Do not throw any exceptions.
     */
    public static List<Integer> getFlightDepartureSchedule(int numFlights, int[][] conditions) {
        LinkedList<Integer>[] graph = new LinkedList[numFlights];
        int[] indegree = new int[numFlights];
        for (int i = 0; i < numFlights; i++) {
            graph[i] = new LinkedList<>();
        }
        for (int[] condition : conditions) {
            graph[condition[1]].add(condition[0]);
            indegree[condition[0]]++;
        }
        LinkedList<Integer> output = new LinkedList<>();
        LinkedList<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numFlights; i++) {
            if (indegree[i] == 0) {
                queue.addLast(i);
            }
        }
        while (queue.size() != 0) {
            int v = queue.pollFirst();
            output.addLast(v);
            for (int i = 0; i < graph[v].size(); i++) {
                int u = graph[v].get(i);
                indegree[u]--;
                if (indegree[u] == 0) {
                    queue.addLast(u);
                }
            }
        }
        if (output.size() != numFlights) {
            output.clear();
        }
        return output;
    }
}
