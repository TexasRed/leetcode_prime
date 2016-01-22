package flatten_2D_vector;

/*
 251. Flatten 2D Vector My Submissions Question
Total Accepted: 5676 Total Submissions: 17736 Difficulty: Medium
Implement an iterator to flatten a 2d vector.

For example,
Given 2d vector =

[
  [1,2],
  [3],
  [4,5,6]
]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,2,3,4,5,6].

Hint:

How many variables do you need to keep track?Show More Hint 
Follow up:
As an added challenge, try to code it using only iterators in C++ or iterators in Java.
 
 */

public class Vector2D {
    Iterator[] itrs;
    int currPos;
    int n;
    public Vector2D(List<List<Integer>> vec2d) {
        n = vec2d.size();
        currPos = 0;
        itrs = new Iterator[n];
        for (int i = 0; i < n; i++) {
            itrs[i] = vec2d.get(i).iterator();
        }
    }

    public int next() {
        int val = (Integer) itrs[currPos].next();
        if (!itrs[currPos].hasNext()) {
            currPos += 1;
        }
        return val;
    }

    public boolean hasNext() {
        while (currPos < n) {
            if (itrs[currPos].hasNext()) {
                return true;
            } else {
                currPos += 1;
            }
        }
        return false;
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
