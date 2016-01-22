package flip_game;

/*
 *293. Flip Game My Submissions Question
Total Accepted: 4835 Total Submissions: 9958 Difficulty: Easy
You are playing the following Flip Game with your friend: Given a string that contains only these two characters: + and -, you and your friend take turns to flip two consecutive "++" into "--". The game ends when a person can no longer make a move and therefore the other person will be the winner.

Write a function to compute all possible states of the string after one valid move.

For example, given s = "++++", after one move, it may become one of the following states:

[
  "--++",
  "+--+",
  "++--"
]
If there is no valid move, return an empty list [].
 */

public class Solution {
    public List<String> generatePossibleNextMoves(String s) {
        List<String> result = new ArrayList<>();
        if (s == null || s.length() < 2) return result;
        
        int n = s.length();
        for (int i = 0; i < n - 1; i++) {
            if (s.substring(i, i + 2).equals("++")) {
                flip(result, s, i);
            }
        }
        
        return result;
    }
    
    
    public void flip(List<String> result, String s, int i) {
        char[] arr = s.toCharArray();
        arr[i] = arr[i + 1] = '-';
        result.add(new String(arr));
    }
}
