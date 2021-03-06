package shortest_word_distance_ii;

/*
 * 
 244. Shortest Word Distance II My Submissions Question
Total Accepted: 4686 Total Submissions: 13323 Difficulty: Medium
This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and your method will be called repeatedly many times with different parameters. How would you optimize it?

Design a class which receives a list of words in the constructor, and implements a method that takes two words word1 and word2 and return the shortest distance between these two words in the list.

For example,
Assume that words = ["practice", "makes", "perfect", "coding", "makes"].

Given word1 = ��coding��, word2 = ��practice��, return 3.
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


public class WordDistance {

    Map<String, List<Integer>> hash;
    public WordDistance(String[] words) {
        this.hash = new HashMap<>();
        if (words != null) {
            for (int i = 0; i < words.length; i++) {
                if (!hash.containsKey(words[i])) {
                    hash.put(words[i], new ArrayList<>());
                }
                hash.get(words[i]).add(i);
            }
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> indexes1 = hash.get(word1);
        List<Integer> indexes2 = hash.get(word2);
        int m = indexes1.size();
        int n = indexes2.size();
        int prevWordMode = -1; int prevIndex = -1;
        int minDistance = Integer.MAX_VALUE;
        int i, j;
        for (i = 0, j = 0; i < m && j < n;) {
            int indexA = indexes1.get(i);
            int indexB = indexes2.get(j);
            if (indexA < indexB) {
                if (prevWordMode == 1) {
                    minDistance = Math.min(minDistance, indexA - prevIndex);
                }
                prevIndex = indexA;
                prevWordMode = 0;
                i++;
            } else {
                if (prevWordMode == 0) {
                    minDistance = Math.min(minDistance, indexB - prevIndex);
                }
                prevIndex = indexB;
                prevWordMode = 1;
                j++;
            }
        }
        if (i < m && prevWordMode == 1) {
            minDistance = Math.min(minDistance, indexes1.get(i) - prevIndex);
        }
        if (j < n && prevWordMode == 0) {
            minDistance = Math.min(minDistance, indexes2.get(j) - prevIndex);
        }
        return minDistance;
    }
}

// Your WordDistance object will be instantiated and called as such:
// WordDistance wordDistance = new WordDistance(words);
// wordDistance.shortest("word1", "word2");
// wordDistance.shortest("anotherWord1", "anotherWord2");
