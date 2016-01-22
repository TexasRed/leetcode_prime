package factor_combinations;

/*
 254. Factor Combinations My Submissions Question
Total Accepted: 5196 Total Submissions: 15781 Difficulty: Medium
Numbers can be regarded as product of its factors. For example,

8 = 2 x 2 x 2;
  = 2 x 4.
Write a function that takes an integer n and return all possible combinations of its factors.

Note: 
Each combination's factors must be sorted ascending, for example: The factors of 2 and 6 is [2, 6], not [6, 2].
You may assume that n is always positive.
Factors should be greater than 1 and less than n.
Examples: 
input: 1
output: 
[]
input: 37
output: 
[]
input: 12
output:
[
  [2, 6],
  [2, 2, 3],
  [3, 4]
]
input: 32
output:
[
  [2, 16],
  [2, 2, 8],
  [2, 2, 2, 4],
  [2, 2, 2, 2, 2],
  [2, 4, 4],
  [4, 8]
]
 * 
 */

public class Solution {
    public List<List<Integer>> getFactors(int n) {
        Set<List<Integer>> result = new HashSet<>();
        List<Integer> row = new ArrayList<>();
        
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                row.add(i);
                row.add(n / i);
                Collections.sort(row);
                result.add(new ArrayList<>(row));
                
                List<List<Integer>> list1 = getFactors(i);
                List<List<Integer>> list2 = getFactors(n / i);
                List<Integer> row1 = new ArrayList<>();
                List<Integer> row2 = new ArrayList<>();
                row1.add(i);
                row2.add(n / i);
                list1.add(new ArrayList<>(row1));
                list2.add(new ArrayList<>(row2));
                for (List<Integer> left : list1) {
                    for (List<Integer> right : list2) {
                        row = new ArrayList<>(left);
                        row.addAll(new ArrayList<>(right));
                        Collections.sort(row);
                        result.add(new ArrayList<>(row));
                    }
                }
            }
            row.clear();
        }
        
        return new ArrayList<>(result);
    }
}
