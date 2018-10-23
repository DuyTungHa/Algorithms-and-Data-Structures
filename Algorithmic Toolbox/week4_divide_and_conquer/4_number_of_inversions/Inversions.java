import java.util.*;

public class Inversions {

    private static long getNumberOfInversions(int[] a, int[] b, int left, int right) {
        long numberOfInversions = 0;
        if (right <= left +1) {
            return numberOfInversions;
        }
        int ave = (left + right) / 2;
        numberOfInversions += getNumberOfInversions(a, b, left, ave);
        numberOfInversions += getNumberOfInversions(a, b, ave, right);
        numberOfInversions += merge(a, b, left, ave, right);
        return numberOfInversions;
    }

    private static int merge(int[] a, int[] temp, int left, int mid, int right){
        int i,j,k;
        int inv_count = 0;

        i = left;
        j = mid;
        k = left;
        while((i < mid) && (j<right)){
            if (a[i] <= a[j]){
                temp[k++] = a[i++];
            } else{
                temp[k++] = a[j++];
                //Tricky - if a[i] > a[j] => the remaining form i -> mid is larger
                inv_count += mid -i;
            }
        }

        while(i< mid)
            temp[k++] = a[i++];
        while(j< right)
            temp[k++] = a[j++];

        for (i = left;i< right; i++){
            a[i] = temp[i];
        }
        return inv_count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
        }
        int[] b = new int[n];
        System.out.println(getNumberOfInversions(a, b, 0, a.length));
    }
}

