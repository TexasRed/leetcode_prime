package palindrome_permutation_ii;

/*
 267. Palindrome Permutation II My Submissions Question
Total Accepted: 3371 Total Submissions: 12903 Difficulty: Medium
Given a string s, return all the palindromic permutations (without duplicates) of it. Return an empty list if no palindromic permutation could be form.

For example:

Given s = "aabb", return ["abba", "baab"].

Given s = "abc", return [].

Hint:

If a palindromic permutation exists, we just need to generate the first half of the string.
To generate all distinct permutations of a (half of) string, use a similar approach from: Permutations II or Next Permutation.
 */

public class Solution {
    public List<String> generatePalindromes(String s) {
        List<String> result = new ArrayList<>();
        if (s== null || s.length() == 0) return result;
         
        Map<Character, Integer> hash = new HashMap<>();
        if (hasPalindromePermutation(s, hash)) {
            int n = s.length();
            int idx = 0;
            char[] arr = new char[n / 2];
            String mid = null;
            for (Character c : hash.keySet()) {
                int count = hash.get(c);
                if ((count & 1) != 0) {
                    mid = Character.toString(c);
                }
                for (int i = 0; i < count / 2; i++) {
                    arr[idx++] = c;
                }
            }
            List<String> halves = generateHalf(arr);
            for (String half : halves) {
                String rHalf = reverse(half);
                if ((n & 1) == 0) {
                    result.add(half + rHalf);
                } else {
                    result.add(half + mid + rHalf);
                }
            }
        }
        return result;
    }
    
    public boolean hasPalindromePermutation(String s, Map<Character, Integer> hash) {
        if (s == null) return false;
        if (s.length() == 0) return true;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (!hash.containsKey(c)) {
                hash.put(c, 1);
            } else {
                hash.put(c, hash.get(c) + 1);
            }
        }
        int even = 0, odd = 0;
        for (Character c : hash.keySet()) {
            if ((hash.get(c) & 1) == 0)
                even++;
            else
                odd++;
        }
        if ((n & 1) == 0)
            return odd == 0;
        else
            return odd == 1;
    }
    
    public List<String> generateHalf(char[] arr) {
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int n = arr.length;
        boolean[] visited = new boolean[n];
        // Arrays.sort(arr);
        helper(arr, result, sb, visited);
        return result;
    }
    public String reverse(String s) {
    	if (s == null || s.length() == 0) return s;
    	char[] arr = s.toCharArray();
    	for (int i = 0, j = s.length() - 1; i < j; i++, j--) {
    		char temp = arr[i];
    		arr[i] = arr[j];
    		arr[j] = temp;
    	}
    	return new String(arr);
    }
    
    public void helper(char[] A, List<String> result, StringBuilder sb, boolean[] visited) {
        int n = A.length;
        if (sb.length() == n) {
            result.add(sb.toString());
            return;
        }
        for (int i = 0; i < n; i++) {
            if (visited[i] || i > 0 && !visited[i - 1] && A[i] == A[i - 1]) continue;
            visited[i] = true;
            sb.append(A[i]);
            helper(A, result, sb, visited);
            sb.setLength(sb.length() - 1);
            visited[i] = false;
        }
    }
}
