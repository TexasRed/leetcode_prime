package one_edit_distance;

/*

161. One Edit Distance My Submissions Question
Total Accepted: 8644 Total Submissions: 31767 Difficulty: Medium
Given two strings S and T, determine if they are both one edit distance apart.

*/

public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        
        if (s == null || t == null) return false;
        
        int m = s.length(); int n = t.length();
        
        if (Math.abs(m - n) > 1) return false;
        
        if (m == n) {
            int count = 0;
            for (int i = 0, j = 0; i < m && j < n; i++, j++) {
                if (s.charAt(i) != t.charAt(j)) 
                    count++;
            }
            return count == 1;
        } else if (m == n + 1) {
             int i, j;
             for (i = 0, j = 0; i < m && j < n; i++, j++) {
                if (s.charAt(i) != t.charAt(j)) {
                    break;
                }
            }
            if (i == m - 1)
                return true;
            else 
                return s.substring(i + 1, m).equals(t.substring(j, n));
        } else {
            int i, j;
            for (i = 0, j = 0; i < m && j < n; i++, j++) {
                if (s.charAt(i) != t.charAt(j)) {
                    break;
                }
            }
            if (j == n - 1)
                return true;
            else 
                return s.substring(i, m).equals(t.substring(j + 1, n));
        }
        
    }
}


public class Solution {
    public boolean isOneEditDistance(String s, String t) {
        if (s == null || t == null || Math.abs(s.length() - t.length()) > 1) return false;
        
        int m = s.length(); int n = t.length();
        if (m > n) {
            return helper(s, t);
        } else if (m < n){
            return helper(t, s);
        } else {
            for (int i = 0, j = 0; i < m && j < n; i++, j++) {
                char c = s.charAt(i);
                char d = t.charAt(j);
                if (c == d) continue;
                else
                    return s.substring(i + 1).equals(t.substring(j + 1));
            }
            return false;
        }
    }
    
    public boolean helper(String s, String t) {
        int m = s.length(); int n = t.length();
        for (int i = 0, j = 0; i < m && j < n; i++, j++) {
            char c = s.charAt(i);
            char d = t.charAt(j);
            if (c == d) continue;
            else return s.substring(i + 1).equals(t.substring(j));
        }
        return true;
    }
}
