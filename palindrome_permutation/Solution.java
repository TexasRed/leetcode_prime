package palindrome_permutation;

/*
 *266. Palindrome Permutation My Submissions Question
Total Accepted: 6997 Total Submissions: 14151 Difficulty: Easy
Given a string, determine if a permutation of the string could form a palindrome.

For example,
"code" -> False, "aab" -> True, "carerac" -> True.

Hint:

Consider the palindromes of odd vs even length. What difference do you notice?
Count the frequency of each character.
If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?
 */

public class Solution {
    public boolean canPermutePalindrome(String s) {
        if (s == null) return false;
        
        int n = s.length();
        Map<Character, Integer> hash = new HashMap<>();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (!hash.containsKey(c)) {
                hash.put(c, 1);
            } else {
                hash.put(c, hash.get(c) + 1);
            }
        }
        
        if (n % 2 == 0) {
            for (Integer count : hash.values()) {
                if (count % 2 != 0)
                    return false;
            }
            return true;
        } else {
            int odd = 0;
            for (Integer count : hash.values()) {
                if (count % 2 != 0) {
                    odd++;
                }
            }
            if (odd == 1)
                return true;
            else
                return false;
        }
    }
    
}
