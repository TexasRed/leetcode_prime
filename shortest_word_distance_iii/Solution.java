package shortest_word_distance_iii;

/*
 
 245. Shortest Word Distance III My Submissions Question
Total Accepted: 4672 Total Submissions: 10272 Difficulty: Medium
This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2.

Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

word1 and word2 may be the same and they represent two individual words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = ¡°makes¡±, word2 = ¡°coding¡±, return 1.
Given word1 = "makes", word2 = "makes", return 3.

Note:
You may assume word1 and word2 are both in the list.
 
 * 
 */

public class Solution {
    public int shortestWordDistance(String[] words, String word1, String word2) {
        int n = words.length;
        int minDist = Integer.MAX_VALUE;
        String prev = null;
        for (int i = 0, j = 0; j < n; j++) {
            String word = words[j];
            if (word.equals(word1) || word.equals(word2)) {
                if (prev != null) {
                    if (word.equals(word1) && prev.equals(word2) || word.equals(word2) && prev.equals(word1)) {
                        minDist = Math.min(minDist, j - i);
                    }
                }
                prev = word;
                i = j;
            }
        }
        return minDist;
    }
    
    
    public int shortestWordDistance(String[] words, String word1, String word2) {
        if (words == null || words.length < 2) return Integer.MAX_VALUE;
        int n = words.length;
        int prev = -1;
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (words[i].equals(word1) || words[i].equals(word2)) {
                if (prev != - 1 && (words[i].equals(word1) && words[prev].equals(word2) || words[i].equals(word2) && words[prev].equals(word1))) {
                    minDistance = Math.min(minDistance, i - prev);
                }
                prev = i;
            }
        }
        return minDistance;
    }
}
