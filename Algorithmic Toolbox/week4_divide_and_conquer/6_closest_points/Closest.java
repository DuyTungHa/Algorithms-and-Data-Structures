import java.io.*;
import java.util.*;

import static java.lang.Math.*;

public class Closest {

    static class Point implements Comparable<Point> {
        long x, y;
        static int COMPARE_BY_X = 1;

        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Point o) {
            if (COMPARE_BY_X ==1)
                return o.x == x ? Long.signum(y - o.y) : Long.signum(x - o.x);
            return o.y == y ? Long.signum(x - o.x) : Long.signum(y - o.y);
        }
    }

    static double minimalDistance(int[] x, int y[]) {
        double ans = Double.POSITIVE_INFINITY;
        //Create the array of points
        List<Point> points = new ArrayList<Point>();
        for(int i = 0; i<x.length; i++){
            points.add( new Point(x[i], y[i]));       
        }
        Collections.sort(points);
        ans = findMinimalDistance(points);

        return ans;
    }

    static double findMinimalDistance(List<Point> points){
        double min = Double.POSITIVE_INFINITY;
        if (points.size() == 1)
            return min;
        if(points.size() == 2){
            return computeDistance(points.get(0), points.get(1));
        }
        List<Point> firstHalf = points.subList(0, points.size()/2);
        List<Point> secondHalf = points.subList(points.size()/2, points.size());
        double firstMin = findMinimalDistance(firstHalf);
        double secondMin = findMinimalDistance(secondHalf);
        min = Math.min(firstMin, secondMin);

        points = filterList(points, (secondHalf.get(0).x+firstHalf.get(firstHalf.size()-1).x)/2, min);

        Point.COMPARE_BY_X = 0;
        Collections.sort(points);
        double thirdmin = minimalDistanceMerge(points);

        return Math.min(min,thirdmin);
    }

    static double minimalDistanceMerge(List<Point> points){
        double min = Double.POSITIVE_INFINITY;
        for (Point p : points) {
            if (points.indexOf(p) == points.size()-1)  break;
            int lastIndex = (points.indexOf(p)+7 < points.size()) ? points.indexOf(p)+7 : points.size() -1;
            for(int i = points.indexOf(p) +1; i<= lastIndex; i++){
                double m = computeDistance(points.get(points.indexOf(p)), points.get(i));
                if (m <= min)
                    min = m;
            }
        }
        return min;
    }

    static List<Point> filterList(List<Point> points, double middle, double d){
        List<Point> result = new ArrayList<Point>(points);

        for(int i = 0; i< result.size(); i++){
            if(Math.abs(result.get(i).x - middle) > d){
                result.remove(i);
                i--;
            }
        }

        return result;
    }

    static double computeDistance(Point firstPoint, Point secondPoint){
        return Math.pow( Math.pow((firstPoint.x-secondPoint.x), 2) + Math.pow((firstPoint.y - secondPoint.y), 2), 0.5 );
    }

    public static void main(String[] args) throws Exception {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new PrintWriter(System.out);
        int n = nextInt();
        int[] x = new int[n];
        int[] y = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = nextInt();
            y[i] = nextInt();
        }

        System.out.println(minimalDistance(x, y));
        writer.close();
    }

    static BufferedReader reader;
    static PrintWriter writer;
    static StringTokenizer tok = new StringTokenizer("");


    static String next() {
        while (!tok.hasMoreTokens()) {
            String w = null;
            try {
                w = reader.readLine();
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (w == null)
                return null;
            tok = new StringTokenizer(w);
        }
        return tok.nextToken();
    }

    static int nextInt() {
        return Integer.parseInt(next());
    }
}
