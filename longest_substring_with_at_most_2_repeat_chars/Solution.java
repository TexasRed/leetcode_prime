package longest_substring_with_at_most_2_repeat_chars;

/*
159. Longest Substring with At Most Two Distinct Characters My Submissions Question
Total Accepted: 8575 Total Submissions: 25948 Difficulty: Hard
Given a string, find the length of the longest substring T that contains at most 2 distinct characters.

For example, Given s = ¡°eceba¡±,

T is "ece" which its length is 3.
*/

public class Solution {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> hash = new HashMap<>();
        
        int maxLen = 0;
        for (int i = 0, j = 0; j < s.length(); j++) {
            char c = s.charAt(j);
            if (!hash.containsKey(c)) {
                hash.put(c, 1);
                while (hash.size() > 2) {
                    char d = s.charAt(i);
                    if (hash.get(d) == 1) {
                        hash.remove(d);
                    } else {
                        hash.put(d, hash.get(d) - 1);
                    }
                    i++;
                }
            } else {
                hash.put(c, hash.get(c) + 1);
            }
            maxLen = Math.max(maxLen, j - i + 1);
        }
        
        return maxLen;
    }
}
