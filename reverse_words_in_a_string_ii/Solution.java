package reverse_words_in_a_string_ii;

/*
 186. Reverse Words in a String II My Submissions Question
Total Accepted: 6557 Total Submissions: 21676 Difficulty: Medium
Given an input string, reverse the string word by word. A word is defined as a sequence of non-space characters.

The input string does not contain leading or trailing spaces and the words are always separated by a single space.

For example,
Given s = "the sky is blue",
return "blue is sky the".

Could you do it in-place without allocating extra space?

Related problem: Rotate Array 
 
 */

public class Solution {
    public void reverseWords(char[] s) {
        if (s == null || s.length == 0) return;
        int n = s.length;
        reverse(s, 0, n - 1);
        int i,j;
        for (i = 0, j = 0; j < n;) {
            if (s[j] != ' ') {
                j++;
            } else {
                reverse(s, i, j - 1);
                j++;
                i = j;
            }
        }
        reverse(s, i, j - 1);
    }
    
    public void reverse(char[] s, int start, int end) {
        for (int i = start, j = end; i < j; i++, j--) {
            swap(s, i, j);
        }
    }
    
    public void swap(char[] s, int i, int j) {
        char temp = s[i];
        s[i] = s[j];
        s[j] = temp;
    }
}
