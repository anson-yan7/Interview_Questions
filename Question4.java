import java.util.*;

/**
 * <h1>QUESTION 4: AIRPORT CODE GAME -- OFF BY ONE!</h1>
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
public class Question4 {

    /**
     * Solves the "Airport Code Change" game. See complete problem description above.
     * <p/>
     * 
     * You may assume that all codes contain only UPPERCASE alphabetic (A-Z) characters.
     *
     * @param code1 the 3 letter airport code you want to start from
     * @param code2 the 3 letter airport code you want to end at
     * @param codes the set of 3 letter airport codes you can use to get from code1 -> code2
     * @return the smallest chain of airport codes to get from {@code code1 -> code2}. {@code code1}
     *         should be the first element, and {@code code2} should be the last. If no valid
     *         solution exists, return {@code null}. If {@code code1.equals(code2)}, return an empty
     *         list.
     *
     * @implSpec you may assume that all inputs are valid. Do not throw any exceptions.
     */
    public static List<String> getSmallestChain(String code1, String code2, Set<String> codes) {
        HashMap<String, LinkedList<String>> graph = new HashMap<>();
        HashMap<String, Boolean> discovered = new HashMap<>();
        HashMap<String, String> parent = new HashMap<>();
        for (String code : codes) {
            graph.put(code, new LinkedList<>());
            discovered.put(code, false);
            parent.put(code, null);
            for (String diff : codes) {
                int count = 0;
                for (int i = 0; i < 3; i++) {
                    if (code.charAt(i) != diff.charAt(i)) {
                        count++;
                    }
                }
                if (count == 1) {
                    graph.get(code).add(diff);
                }
            }
        }
        LinkedList<String> queue = new LinkedList<>();
        queue.addLast(code1);
        discovered.put(code1, true);
        while (!queue.isEmpty() && !discovered.get(code2)) {
            String curr = queue.pollFirst();
            LinkedList<String> list = graph.get(curr);
            for (String code : list) {
                if (!discovered.get(code)) {
                    discovered.put(code, true);
                    queue.addLast(code);
                    parent.put(code, curr);
                }
            }
        }
        LinkedList<String> output = new LinkedList<>();
        if (code1.equals(code2)) {
            return output;
        }
        if (parent.get(code2) != null) {
            output.addFirst(code2);
            String curr = code2;
            while (!curr.equals(code1)) {
                curr = parent.get(curr);
                output.addFirst(curr);
            }
            return output;
        } else {
            return null;
        }
    }
}