import java.util.*;

public class CoveringSegments {

    private static int[] optimalPoints(Segment[] segments) {
        Segment[] sortedSegment = sortSegment(segments);

        int count = 0;
        int[] points = new int[sortedSegment.length];
        points[count] = sortedSegment[0].end;
        count++;
        for (int i = 0; i<sortedSegment.length;i++){
            if (!(points[count-1]>=sortedSegment[i].start && points[count-1]<=sortedSegment[i].end)){
                points[count] = sortedSegment[i].end;
                count++;
            }
        }

        int[] result = new int[count];
        for (int i = 0; i<result.length; i++){
            result[i] = points[i];
        }

        return result;
    }

    private static Segment[] sortSegment(Segment[] segments){
        Segment[] sortedSegment = new Segment[segments.length];
        for (int i = 0; i<segments.length; i++){
            int minIndex = 0;
            for (int j = 0; j<segments.length; j++){
                if (segments[j].end < segments[minIndex].end)
                    minIndex = j;
            }
            sortedSegment[i] = segments[minIndex];
            segments[minIndex] = new Segment(1000000001, 1000000001);
        }
        return sortedSegment;
    }

    private static class Segment {
        int start, end;

        Segment(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Segment[] segments = new Segment[n];
        for (int i = 0; i < n; i++) {
            int start, end;
            start = scanner.nextInt();
            end = scanner.nextInt();
            segments[i] = new Segment(start, end);
        }
        int[] points = optimalPoints(segments);
        System.out.println(points.length);
        for (int point : points) {
            System.out.print(point + " ");
        }
    }
}
 
