package word_pattern_ii;

/*
 *291. Word Pattern II My Submissions Question
Total Accepted: 2820 Total Submissions: 8607 Difficulty: Hard
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.

Examples:
pattern = "abab", str = "redblueredblue" should return true.
pattern = "aaaa", str = "asdasdasdasd" should return true.
pattern = "aabb", str = "xyzabcxzyabc" should return false.
Notes:
You may assume both pattern and str contains only lowercase letters.
 */

public class Solution {
    public boolean wordPatternMatch(String pattern, String str) {
        HashMap map = new HashMap();
        return helper(pattern, 0, str, 0, map);
    }
    
    public boolean helper(String pattern, int i, String str, int j, HashMap map) {
        if (i >= pattern.length() && j >= str.length()) return true;
        if (i == pattern.length() || j == str.length()) return false;
        
        for (int k = j; k < str.length(); k++) {
            char c = pattern.charAt(i);
            if (map.get(c) == map.get(str.substring(j, k + 1))) {
                Integer val = (Integer) map.get(c);
                if (val == null) {
                    map.put(c, i);
                    map.put(str.substring(j, k + 1), i);
                }
                if (helper(pattern, i + 1, str, k + 1, map)) {
                    return true;
                }
                if (val == null) {
                    map.remove(c);
                    map.remove(str.substring(j, k + 1));
                }
            }
        }
        return false;
    }
}
