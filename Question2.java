import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * <h1>QUESTION 2: ROOMMATE MATCHING</h1>
 * <p/>
 *
 * Class to implement the algorithm to select roommates from a 2d-array of pairs.
 * <p/>
 *
 * See description of problem and example in the write-up.
 *
 * @author bursztyn
 */
public class Question2 {

    /**
     * Returns the set of roommates who mutually requested each other.
     *
     * @param requests the 2d-array of pairs of requests of the form (student, requested roommate).
     *                 Note: a student cannot request themself. 
     * @return a set of pairs of roommates who mutually requested each other
     * 
     * @implSpec you may assume that input is valid and non-null. Do not throw any exceptions.
     */
    public static Set<Pair<String, String>> findMatches(String[][] requests) {
        int size = requests.length;
        Arrays.sort(requests, java.util.Comparator.comparing(a -> a[0]));
        Set<Pair<String, String>> output = new HashSet<>();
        for (int i = 0; i < size; i++) {
            String search = requests[i][1];
            int split = 2;
            int curr = (int) Math.ceil(size / split);
            while (!requests[curr][0].equals(search)) {
                split = 2 * split;
                if (requests[curr][0].compareTo(search) < 0) {
                    if (curr == curr + Math.ceil((double) size / (double) split) || curr +
                            Math.ceil((double) size / (double) split) >= size) {
                        curr = size - 1;
                        break;
                    }
                    curr = (int) (curr + Math.ceil((double) size / (double) split));
                } else if (requests[curr][0].compareTo(search) > 0) {
                    if (curr == curr - Math.ceil((double) size / (double) split) || curr -
                            Math.ceil((double) size / (double) split) < 0) {
                        curr = 0;
                        break;
                    }
                    curr = (int) (curr - Math.ceil((double) size / (double) split));
                }
            }
            int first;
            if (i < curr) {
                first = i;
            } else {
                first = curr;
            }
            if (requests[curr][1].equals(requests[i][0])) {
                output.add(new Pair<>(requests[first][0], requests[first][1]));
            }
        }
        return output;
    }
}
