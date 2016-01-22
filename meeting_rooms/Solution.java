package meeting_rooms;

/*
 252. Meeting Rooms My Submissions Question
Total Accepted: 5949 Total Submissions: 14757 Difficulty: Easy
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), determine if a person could attend all meetings.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return false.
 */

/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */

class IntervalComparator implements Comparator<Interval> {
    @Override
    public int compare(Interval itv1, Interval itv2) {
        return itv1.start - itv2.start;
    }
}

public class Solution {
    public boolean canAttendMeetings(Interval[] intervals) {
        if (intervals == null || intervals.length < 2)
            return true;
            
        List<Interval> itvList = Arrays.asList(intervals);
        Collections.sort(itvList, new IntervalComparator());
        
        int n = itvList.size();
        Interval prev = itvList.get(0);
        for (int i = 1; i < n; i++) {
            Interval curr = itvList.get(i);
            if (curr.start < prev.end)
                return false;
            prev = curr;
        }
        
        return true;
    }
}
