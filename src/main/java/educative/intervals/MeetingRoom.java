package educative.intervals;

import lc.MergeIntervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

class Interval{
    int start;
    int end;
    boolean closed;
    public Interval(int start, int end)
    {
        this.start = start;
        this.end = end;
        this.closed = true; // by default, the interval is closed
    }
    public int getStart()
    {
        return start;
    }

    public int getEnd(){
        return end;
    }
    public void setEnd(int end)
    {
        this.end = end;
    }

    // set the flag for closed/open
    public void setClosed(boolean closed)
    {
        this.closed = closed;
    }
}

public class MeetingRoom {


    public static int minMeetingRooms(List<Interval> meetingTimes) {
        List<Stack<Interval>> meetingRoomsRequired = new ArrayList<>();

        Collections.sort(meetingTimes, Comparator.comparingInt(Interval::getStart));
        for(Interval currInterval: meetingTimes){
            boolean foundEmptySlot = false;
            for(Stack<Interval> bookedSlots: meetingRoomsRequired){
                if(currInterval.getStart() >= bookedSlots.peek().getEnd()){
                    System.out.println("found");
                    bookedSlots.push(currInterval);
                    foundEmptySlot = true;
                    break;
                }
            }
            if(!foundEmptySlot){
                System.out.println("adding new meeting");
                meetingRoomsRequired.add(new Stack<>());
                meetingRoomsRequired.get(meetingRoomsRequired.size() - 1).push(currInterval);
            }
        }

        return meetingRoomsRequired.size();
    }

    public static final List<Integer[][]> INPUT_DATA =
            Collections.unmodifiableList(new ArrayList<>(){{
                add(
                        new Integer[][]{{2, 8}, {3, 4}, {3, 9}, {5, 11}, {8, 20}, {11, 15}}
                );  //Output: 3
                add(
                        new Integer[][]{{1, 2}, {4, 6}, {3, 4}, {7, 8}}
                );  //Output: 1

            }});

    public static void main(String[] args) {

        for (var rowData : INPUT_DATA.subList(1,2)) {
            List<Interval> inputArr = Arrays.stream(rowData)
                    .map(e -> new Interval(e[0], e[1]))
                    .collect(Collectors.toList());

            var result = minMeetingRooms(inputArr);

            System.out.println(String.format("result is: %s ",  result));
        }

    }

}
