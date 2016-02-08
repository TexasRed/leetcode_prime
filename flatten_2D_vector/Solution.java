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

public class Vector2D {
    int curr = 0;
    List<Iterator<Integer>> iterators = new ArrayList<>();
    public Vector2D(List<List<Integer>> vec2d) {
        if (vec2d == null || vec2d.size() == 0) return;
        for (List<Integer> vec : vec2d) {
            if (vec != null) {
                Iterator<Integer> itr = vec.iterator();
                if (itr.hasNext())
                    iterators.add(itr);
            }
        }
    }

    public int next() {
        Iterator<Integer> itr = iterators.get(curr);
        int retval = itr.next();
        if (!itr.hasNext()) curr++;
        return retval;
    }

    public boolean hasNext() {
        return (curr < iterators.size() && iterators.get(curr).hasNext());
    }
    
    //     Queue<Iterator<Integer>> queue = new LinkedList<>();
    // Iterator<Integer> top = null;
    // public Vector2D(List<List<Integer>> vec2d) {
    //     for (List<Integer> vec : vec2d) {
    //         Iterator<Integer> itr = vec.iterator();
    //         if (itr != null && itr.hasNext()) {
    //             if (top == null) top = itr;
    //             queue.offer(itr);
    //         }
    //     }
    // }

    // public int next() {
    //     return top.next();
    // }

    // public boolean hasNext() {
    //     if (top == null)
    //         return false;
    //     else {
    //         boolean retval = top.hasNext();
    //         if (retval) return true;
    //         while (!queue.isEmpty() && !queue.peek().hasNext()) {
    //             queue.poll();
    //         }
    //         if (queue.isEmpty()) top = null;
    //         else top = queue.peek();
    //         return top != null;
    //     }
    // }
}
