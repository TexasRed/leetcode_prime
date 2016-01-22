package zigzag_iterator;

/*
 *281. Zigzag Iterator My Submissions Question
Total Accepted: 5398 Total Submissions: 13436 Difficulty: Medium
Given two 1d vectors, implement an iterator to return their elements alternately.

For example, given two 1d vectors:

v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].

Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?

Clarification for the follow up question - Update (2015-09-18):
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:

[1,2,3]
[4,5,6,7]
[8,9]
It should return [1,4,8,2,5,9,3,6,7].
 */

public class ZigzagIterator {

    Iterator it1;
    Iterator it2;
    int mode;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        it1 = v1.iterator();
        it2 = v2.iterator();
        mode = 0;
    }

    public int next() {
        if (hasNext()) {
            if (mode == 0) {
                if (it1.hasNext()) {
                    mode = 1;
                    return (Integer)it1.next();
                } else {
                    mode = 2;
                    return (Integer)it2.next();
                }
            } else if (mode == 1) {
                if (it2.hasNext()) {
                    mode = 0;
                    return (Integer)it2.next();
                } else {
                    mode = 3;
                    return (Integer)it1.next();
                }
            } else if (mode == 3){
                return (Integer)it1.next();
            } else {
                return (Integer)it2.next();
            }
        } else {
            return -1;
        }
    }

    public boolean hasNext() {
        return it1.hasNext() || it2.hasNext();
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */