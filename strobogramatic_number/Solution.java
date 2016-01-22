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
}