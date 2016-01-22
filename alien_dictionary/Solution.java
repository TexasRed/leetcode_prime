package alien_dictionary;

/*
 * 
 *269. Alien Dictionary My Submissions Question
Total Accepted: 4541 Total Submissions: 21329 Difficulty: Hard
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

For example,
Given the following words in dictionary,

[
  "wrt",
  "wrf",
  "er",
  "ett",
  "rftt"
]
The correct order is: "wertf".

Note:
You may assume all letters are in lowercase.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return any one of them is fine.
 * 
 */

public class Solution {
    public String alienOrder(String[] words) {
        if (words == null || words.length == 0) return "";
        int n = words.length;
        
        Map<Character, List<Character>> hash = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();
        for (int k = 0; k < n; k++) {
            String word = words[k];
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                if (!hash.containsKey(c)) {
                    hash.put(c, new ArrayList<>());
                    indegree.put(c, 0);
                }
            }
        }
        
        String prev = words[0];
        for (int k = 1; k < n; k++) {
            String curr = words[k];
            for (int i = 0, j = 0; i < prev.length() && j < curr.length(); i++, j++) {
                char c1 = prev.charAt(i);
                char c2 = curr.charAt(j);
                if (c1 != c2) {
                    hash.get(c1).add(c2);
                    indegree.put(c2, indegree.get(c2) + 1);
                    break;
                }
            }
            prev = curr;
        }
        
        Queue<Character> queue = new LinkedList<>();
        for (Map.Entry<Character, Integer> entry : indegree.entrySet()) {
            char c = entry.getKey();
            int count = entry.getValue();
            if (count == 0) queue.offer(c);
        }
        
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);
            for (Character next : hash.get(c)) {
                int indeg = indegree.get(next) - 1;
                indegree.put(next, indeg);
                if (indeg == 0) {
                    queue.offer(next);
                }
            }
            // hash.remove(c);
        }
        return (sb.length() == indegree.size()) ? sb.toString() : "";
    }
}
