package generalized_abbreviation;

/*
 *320. Generalized Abbreviation My Submissions Question
Total Accepted: 2204 Total Submissions: 5471 Difficulty: Medium
Write a function to generate the generalized abbreviations of a word.

Example:
Given word = "word", return the following list (order does not matter):
["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 */

public class Solution {
    public List<String> generateAbbreviations(String word) {
        List<String> result = new ArrayList<>();
        result.add(word);
        int n = word.length();
        for (int len = 1; len <= n; len++) {
            for (int i = 0; i <= n - len; i++) {
                int j = i + len - 1;
                String left = word.substring(0, i);
                String digit = Integer.toString(len);
                 String right = word.substring(j + 1, n);
                if (right.length() > 1) {
                    List<String> subList = generateAbbreviations(right.substring(1));
                    for (String candidate : subList) {
                        result.add(left + digit + right.substring(0, 1) + candidate);
                    }
                } else {
                    result.add(left + digit + right);
                }
            }
        }
        return result;
    }
    
}
