package strobogrammatic_number_ii;

/*
 * 
 247. Strobogrammatic Number II My Submissions Question
Total Accepted: 5232 Total Submissions: 16280 Difficulty: Medium
A strobogrammatic number is a number that looks the same when rotated 180 degrees (looked at upside down).

Find all strobogrammatic numbers that are of length = n.

For example,
Given n = 2, return ["11","69","88","96"].

Hint:

Try to use recursion and notice that it should recurse with n - 2 instead of n - 1.

 * 
 */


public class Solution {
    public List<String> findStrobogrammatic(int n) {
        return helper(n, n);
    }
    
    public List<String> helper(int n, int totalLen) {
           if (n == 0) {
            String[] nums = {""};
            return new ArrayList<>(Arrays.asList(nums));
        } else if (n == 1) {
            String[] nums = {"0", "1", "8"};
            return new ArrayList<>(Arrays.asList(nums));
        } else {
            return process(helper(n - 2, totalLen), n, totalLen);
        }
    }
    
    public List<String> process(List<String> prev, int n, int totalLen) {
        List<String> result = new ArrayList<>();
        for (String num : prev) {
            if (n != totalLen) {
                result.add("0" + num + "0");
            }
            result.add("1" + num + "1");
            result.add("6" + num + "9");
            result.add("8" + num + "8");
            result.add("9" + num + "6");
        }
        return result;
    }
    
}
