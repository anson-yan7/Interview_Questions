import java.util.List;

/**
 * <h1>QUESTION 5: ODD SIZED FAMILY TREES</h1>
 * <p/>
 *
 * Class to implement the algorithm to determine the number of odd sized sub-trees in a given
 * family tree.
 * <p/>
 *
 * See description of problem and example in the write-up.
 *
 * @author bursztyn
 */
public class Question5 {

    /**
     * Returns the number of odd sized sub-trees in a given family tree.
     *
     * @param family an adjacency list of the family tree
     * @param root the favorite number of the root of the family tree
     * @return the number of odd sized sub-trees
     * 
     * @implSpec you may assume that input is valid and non-null. Do not throw any exceptions.
     */
    public static int getNumberOfOddSubtrees(List<List<Integer>> family, int root) {
        int[] numOfFam = new int[family.size()];
        count(family, root, numOfFam);
        int output = 0;
        for (int i = 0; i < family.size(); i++) {
            if (numOfFam[i] % 2 == 1) {
                output++;
            }
        }

        return output;
    }

    public static void count(List<List<Integer>> family, int root, int[] numOfFam) {
        for (Integer integer : family.get(root)) {
            count(family, integer, numOfFam);
        }
        numOfFam[root] = 1;
        for (Integer integer : family.get(root)) {
            numOfFam[root] = numOfFam[root] + numOfFam[integer];
        }
    }
}