package group_shifted_strings;

/*
 249. Group Shifted Strings My Submissions Question
Total Accepted: 5252 Total Submissions: 17379 Difficulty: Easy
Given a string, we can "shift" each of its letter to its successive letter, for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence:

"abc" -> "bcd" -> ... -> "xyz"
Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.

For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"], 
Return:

[
  ["abc","bcd","xyz"],
  ["az","ba"],
  ["acef"],
  ["a","z"]
]
Note: For the return value, each inner list's elements must follow the lexicographic order.
 * 
 */

public class Solution {
    public List<List<String>> groupStrings(String[] strings) {
        
        List<List<String>> result = new ArrayList<>();
        Map<String, List<String>> hash = new HashMap<>();
        for (String word : strings) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                int diff = word.charAt(i) - word.charAt(0);
                int id = (diff > 0) ? diff % 26 : (diff + 26) % 26;
                sb.append(id);
                sb.append("#");
            }
            String groupID = sb.toString();
            List<String> stringGroup = null;
            if (!hash.containsKey(groupID)) {
                stringGroup = new ArrayList<>();
            } else {
                stringGroup = hash.get(groupID);
            }
            stringGroup.add(word);
            hash.put(groupID, stringGroup);
        }
        
        for (List<String> stringGroup : hash.values()) {
            Collections.sort(stringGroup);
            result.add(stringGroup);
        }
        
        return result;
    }
}
