import java.util.HashMap;
import java.util.LinkedList;

/**
 * <h1>QUESTION 3: BUNNY HOP</h1>
 * <p/>
 *
 * Class to implement the algorithm to find the minimum number of hops a bunny rabbit
 * must make to navigate from the bottom right corner to the top left corner of the city.
 * <p/>
 *
 * See description of problem and example in the write-up.
 *
 * @author bursztyn
 */
public class Question3 {

    /**
     * Returns the minimum number of hops needed for the bunny rabbit to make its way from the
     * bottom right corner to the top left corner given the hopping constraints in the writeup,
     * or -1 if a path does not exist.
     *
     * @param city an n x n 2d-array representing the city where each entry is a positive integer
     *             which defines how many cells the bunny can hop up or to the left when that
     *             entry is reached.
     * @return the minimum number of hops the bunny needs to make, or -1 if there is no 
     *         possible path
     * 
     * @implSpec you may assume that input is valid and non-null. Do not throw any exceptions.
     */
    private static int[][] city;
    public static int getMinimumNumberOfHops(int[][] city) {
        HashMap<Integer, LinkedList<Integer>> graph = new HashMap<>();
        HashMap<Integer, Boolean> discovered = new HashMap<>();
        HashMap<Integer, Integer> parent = new HashMap<>();
        int n = city.length;
        for (int i = 0; i < n * n; i++) {
            graph.put(i, new LinkedList<>());
            discovered.put(i, false);
            parent.put(i, null);
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n;j++) {
                if (i - city[i][j] >=  0) {
                    graph.get(i*n+j).add((i - city[i][j])*n+j);
                }
                if (j - city[i][j] >=  0) {
                    graph.get(i*n+j).add(i*n+(j - city[i][j]));
                }
            }
        }
        LinkedList<Integer> queue = new LinkedList<>();
        queue.addLast(n*n-1);
        discovered.put(n*n-1, true);
        while (!queue.isEmpty() && !discovered.get(0)) {
            Integer curr = queue.pollFirst();
            LinkedList<Integer> list = graph.get(curr);
            for (Integer code : list) {
                if (!discovered.get(code)) {
                    discovered.put(code, true);
                    queue.addLast(code);
                    parent.put(code, curr);
                }
            }
        }
        int output = 0;
        if (parent.get(0) != null) {
            Integer curr = 0;
            while (!curr.equals(n*n-1)) {
                curr = parent.get(curr);
                output++;
            }
            return output;
        } else {
            return -1;
        }
    }

}