package strobogramatic_number;

/*

246. Strobogrammatic Number My Submissions Question
Total Accepted: 5847 Total Submissions: 16745 Difficulty: Easy
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Write a function to determine if a number is strobogrammatic. The number is represented as a string.

For example, the numbers "69", "88", and "818" are all strobogrammatic.
 * 
 * 
 */

public class Solution {
    public boolean isStrobogrammatic(String num) {
        StringBuilder sb = new StringBuilder();
        int n = num.length();
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            char rc = flip(c);
            if (rc == '*')
                return false;
            else
                sb.append(rc);
        }
        
        String rnum = sb.toString();
        for (int i = 0; i < n; i++) {
            char c = num.charAt(i);
            char rc = rnum.charAt(n - 1 - i);
            if (c != rc)
                return false;
        }
        return true;
    }
    
    public char flip(char c) {
        char res;
        switch(c) {
            case '0':
                res = '0'; break;
            case '1':
                res = '1'; break;
            case '6':
                res = '9'; break;
            case '8':
                res = '8'; break;
            case '9':
                res = '6'; break;
            default: 
                res = '*';
                break;
        }
        return res;
    }
    
    public boolean isStrobogrammatic(String num) {
        if (num == null || num.length() == 0) return false;
        Map<Character, Character> hash = new HashMap<>();
        hash.put('0', '0');
        hash.put('1', '1');
        hash.put('6', '9');
        hash.put('8', '8');
        hash.put('9', '6');
        int i, j;
        for (i = 0, j = num.length() - 1; i < j; i++, j--) {
            char c = num.charAt(i);
            char d = num.charAt(j);
            if (!hash.containsKey(c) || !hash.containsKey(d))
                return false;
            if (hash.get(c) != d)
                return false;
        }
        if (i != j)
            return true;
        else {
            char middle = num.charAt(i);
            return (middle == '0' || middle == '1' || middle == '8');
        }
    }
}