package missing_ranges;

/*
 163. Missing Ranges My Submissions Question
Total Accepted: 7558 Total Submissions: 27350 Difficulty: Medium
Given a sorted integer array where the range of elements are [lower, upper] inclusive, return its missing ranges.

For example, given [0, 1, 3, 50, 75], lower = 0 and upper = 99, return ["2", "4->49", "51->74", "76->99"].
  
*/

public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        
        List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        int prev = lower - 1;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            addGap(result, sb, prev + 1, nums[i] - 1);
            prev = nums[i];
        }
        addGap(result, sb, prev + 1, upper);
        
        return result;
    }
    
    public void addGap(List<String> result, StringBuilder sb, int i, int j) {
        if (i > j) {
            return;
        } else if (i == j) {
            sb.append(i);
            result.add(sb.toString());
            sb.setLength(0);
        } else {
            sb.append(i + "->" + j);
            result.add(sb.toString());
            sb.setLength(0);
        }
    }
}


public class Solution {
    public List<String> findMissingRanges(int[] nums, int lower, int upper) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            addInterval(lower - 1, upper + 1, result);
            return result;
        }
        
        int n = nums.length; 
        
        addInterval(lower - 1, nums[0], result);
        for (int i = 1; i < n; i++) {
            addInterval(nums[i - 1], nums[i], result);
        }
        addInterval(nums[n - 1], upper + 1, result);
        
        return result;
    }
    
    public void addInterval(int start, int end, List<String> result) {
        if (start + 1 < end) {
            StringBuilder sb = new StringBuilder();
            if (start + 2 == end) {
                sb.append(start + 1);
                result.add(sb.toString());
            } else {
                sb.append(start + 1);
                sb.append("->");
                sb.append(end - 1);
                result.add(sb.toString());
            }
        }
    }
}
