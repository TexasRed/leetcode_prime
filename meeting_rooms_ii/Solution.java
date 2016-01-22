package meeting_rooms_ii;

/*
 * 
 *253. Meeting Rooms II My Submissions Question
Total Accepted: 6456 Total Submissions: 19719 Difficulty: Medium
Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

For example,
Given [[0, 30],[5, 10],[15, 20]],
return 2.
 * 
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

class Timestamp implements Comparable<Timestamp> {
    int time;
    boolean isEnd;
    public Timestamp(int time, boolean isEnd) {
        this.time = time;
        this.isEnd = isEnd;
    }
    @Override 
    public int compareTo(Timestamp t) {
        int diff = this.time - t.time;
        if (diff < 0) 
            return -1;
        else if (diff > 0) 
            return 1;
        else {
            if (this.isEnd && !t.isEnd) {
                return -1;
            } else if (!this.isEnd && t.isEnd) {
                return 1;
            } else {
                return 0;
            }
        }
    }
}

public class Solution {
    public int minMeetingRooms(Interval[] intervals) {
          if (intervals == null || intervals.length == 0)
            return 0;
        
        List<Timestamp> times = new ArrayList<>();
        for (Interval itv : intervals){
            times.add(new Timestamp(itv.start, false));
            times.add(new Timestamp(itv.end, true));
        }
        Collections.sort(times);
        
        int roomNum = 1;
        int n = times.size();
        int currNum = 0;
        for (int i = 0; i < n; i++) {
            if (!times.get(i).isEnd) {
                currNum++;
            } else {
                currNum--;
            }
            roomNum = Math.max(roomNum, currNum);
        }
        return roomNum;
    }
}
