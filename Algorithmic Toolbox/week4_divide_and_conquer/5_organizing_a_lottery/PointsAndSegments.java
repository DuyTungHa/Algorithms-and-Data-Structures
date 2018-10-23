import java.util.Scanner;
import java.io.*;
import java.util.*;

public class PointsAndSegments {
    private static Random random = new Random();
    private static final int LEFT = 1;
    private static final int RIGHT = 2;
    private static final int POINT = 3;

    private static int[] fastCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        
        int[] numbers = new int[starts.length + ends.length + points.length];
        int[] letters = new int[numbers.length];
        for (int i = 0; i<starts.length; i++){
            numbers[i] = starts[i];
            numbers[i + starts.length] = ends[i];
            letters[i] = LEFT;
            letters[i + starts.length] = RIGHT;
        }
        for (int i = 0; i<points.length; i++){
            numbers[i + starts.length*2] = points[i];
            letters[i + starts.length*2] = POINT;
        }

        randomizedQuickSort(numbers, letters, 0, numbers.length-1);

        int[] pointCounts = new int[numbers.length];
        int leftCount = 0;
        for(int i = 0; i< numbers.length ; i++){
            if (letters[i] == LEFT){
                leftCount++;
            } else if (letters[i] == RIGHT){
                leftCount--;
            } else if (letters[i] == POINT){
                pointCounts[i] = leftCount;
            }
        }

        for(int i = 0; i<points.length; i++){
            for(int j =0; j<numbers.length; j++){
                if(points[i] == numbers[j])
                    cnt[i] = pointCounts[j];
            }
        }

        return cnt;
    }

    private static int[] partition3(int[] a, int[] b, int l, int r) {
        int x = a[l];
        int m1 = l;
        int m2 = r+1;

        for (int i = l+1;i<= r; i++){
            if (a[i] < x){
                m1++;
                int t = a[i];
                a[i] = a[m1];
                a[m1] = t;
                t = b[i];
                b[i] = b[m1];
                b[m1] = t;
            }        
        }

        for (int i = r; i >m1; i--){
            if (a[i] > x){
                m2--;
                int t = a[i];
                a[i] = a[m2];
                a[m2] = t;
                t = b[i];
                b[i] = b[m2];
                b[m2] = t;
            }
        }

        int t = a[l];
        a[l] = a[m1];
        a[m1] = t;
        t = b[l];
        b[l] = b[m1];
        b[m1] = t;
        int[] m = {m1, m2};
        return m;
    }

    private static void randomizedQuickSort(int[] a, int[] b, int l, int r) {
        if (l >= r) {
            return;
        }
        int k = random.nextInt(r - l + 1) + l;
        int t = a[l];
        a[l] = a[k];
        a[k] = t;
        t = b[l];
        b[l] = b[k];
        b[k] = t;

        
        int[] m = partition3(a,b, l, r);
        if (m[0] -1 >=0)
            randomizedQuickSort(a,b, l, m[0] - 1);
        if (m[1] <= r)
            randomizedQuickSort(a,b, m[1], r);
    }

    private static int[] naiveCountSegments(int[] starts, int[] ends, int[] points) {
        int[] cnt = new int[points.length];
        for (int i = 0; i < points.length; i++) {
            for (int j = 0; j < starts.length; j++) {
                if (starts[j] <= points[i] && points[i] <= ends[j]) {
                    cnt[i]++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, m;
        n = scanner.nextInt();
        m = scanner.nextInt();
        int[] starts = new int[n];
        int[] ends = new int[n];
        int[] points = new int[m];
        for (int i = 0; i < n; i++) {
            starts[i] = scanner.nextInt();
            ends[i] = scanner.nextInt();
        }
        for (int i = 0; i < m; i++) {
            points[i] = scanner.nextInt();
        }
        //use fastCountSegments
        int[] cnt = fastCountSegments(starts, ends, points);
        for (int x : cnt) {
            System.out.print(x + " ");
        }
    }
}

