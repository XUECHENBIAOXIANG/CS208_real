package Lab6;

import java.util.Arrays;
import java.util.Scanner;

public class tan {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[][] intervals = new int[n][2];
        for (int i = 0; i < n; i++) {
            intervals[i][0] = sc.nextInt();
            intervals[i][1] = sc.nextInt();
        }
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int count = 0;
        int last = -1;
        for (int i = 0; i < n; i++) {
            if (intervals[i][0] > last) {
                count++;
                last = intervals[i][1];
            }
        }
        System.out.println(count);
    }
}
