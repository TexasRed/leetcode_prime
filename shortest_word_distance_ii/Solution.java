package shortest_word_distance_ii;

/*
 * 
 244. Shortest Word Distance II My Submissions Question
Total Accepted: 4686 Total Submissions: 13323 Difficulty: Medium
This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?

Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = ¡°coding¡±, word2 = ¡°practice¡±, return 3.
Given word1 = "makes", word2 = "coding", return 1.

Note:
You may assume that word1 does not equal to word2, and word1 and word2 are both in the list.
 * 
 */

public class WordDistance {

    Map<String, List<Integer>> index;
    
    public WordDistance(String[] words) {
        index = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            List<Integer> list = null;
            if (!index.containsKey(word)) {
                list = new ArrayList<>();
            } else {
                list = index.get(word);
            }
            list.add(i);
            index.put(word, list);
        }
    }

    public int shortest(String word1, String word2) {
        int minDistance = Integer.MAX_VALUE;
        List<Integer> index1 = index.get(word1);
        List<Integer> index2 = index.get(word2);
        int m = index1.size();
        int n = index2.size();
        for (int i = 0, j = 0; i < m && j < n;) {
            int distance = Math.abs(index1.get(i) - index2.get(j));
            minDistance = Math.min(minDistance, distance);
            if (index1.get(i) < index2.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return minDistance;
    }
}

// Your WordDistance object will be instantiated and called as such:
// WordDistance wordDistance = new WordDistance(words);
// wordDistance.shortest("word1", "word2");
// wordDistance.shortest("anotherWord1", "anotherWord2");
