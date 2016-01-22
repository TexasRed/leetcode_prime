package two_sum_iii;

/*
 170. Two Sum III - Data structure design My Submissions Question
Total Accepted: 8019 Total Submissions: 33152 Difficulty: Easy
Design and implement a TwoSum class. It should support the following operations: add and find.

add - Add the number to an internal data structure.
find - Find if there exists any pair of numbers which sum is equal to the value.

For example,
add(1); add(3); add(5);
find(4) -> true
find(7) -> false
 
 */

public class TwoSum {
    Set<Integer> set;
    List<Integer> numbers;
    
    public TwoSum() {
        set = new HashSet<>();
        numbers = new ArrayList<>();
    }
    // Add the number to an internal data structure.
	public void add(int number) {
	    numbers.add(number);
	}

    // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    set.clear();
	    for (int i = 0; i < numbers.size(); i++) {
	        if (!set.contains(numbers.get(i))) {
	            set.add(value - numbers.get(i));
	        } else {
	            return true;
	        }
	    }
	    return false;
	}
}


// Your TwoSum object will be instantiated and called as such:
// TwoSum twoSum = new TwoSum();
// twoSum.add(number);
// twoSum.find(value);
