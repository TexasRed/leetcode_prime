package shortest_word_distance;

/*
 
 243. Shortest Word Distance My Submissions Question
Total Accepted: 6587 Total Submissions: 14418 Difficulty: Easy
Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = ¡°coding¡±, word2 = ¡°practice¡±, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 
 * 
 */

public class Solution {
    public int shortestDistance(String[] words, String word1, String word2) {
        if (words == null || words.length < 2) return 0;
        
        int n = words.length;
        int minDist = Integer.MAX_VALUE;
        String prev = null;
        int j = 0;
        
        for (int i = 0; i < n; i++) {
            if (words[i].equals(word1)) {
                if (word2.equals(prev)) {
                    minDist = Math.min(minDist, i - j);
                }
                prev = word1;
                j = i;
            } else if (words[i].equals(word2)) {
                if (word1.equals(prev)) {
                    minDist = Math.min(minDist, i - j);
                }
                prev = word2;
                j = i;
            } else {
                continue;
            }
        }
        
        return minDist;
    }
}