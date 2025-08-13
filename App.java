package div_conq;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class App 
{
    private static int recurs = 0;
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Input: ");
        int n = sc.nextInt();
        sc.close();
        ArrayList<Integer> al = new ArrayList<>();
        for (int i = 0; i < n; i++) al.add((int)Math.random());

        System.out.println("Trabalhando");
        long start = System.currentTimeMillis();
        mergeSort(al);
        long end = System.currentTimeMillis();
        System.out.println("Elapsed: " + (end - start) + "ms");
        System.out.println("Recursoes: " + recurs);
    }

    private static long multiply(long x, long y, long n) {
        recurs++;
        if (n == 1) return x * y;

        long m = n / 2;
        long pow = (long)(Math.pow(2,m));
        long a = x / pow;
        long b = x % pow;
        long c = y / pow;
        long d = y % pow;

        long e = multiply(a, c, m);
        long f = multiply(b, d, m);
        long g = multiply(b, c, m);
        long h = multiply(a, d, m);

        // what the fuck is this
        return ((long)Math.pow(2, 2*m)) * e + (pow) * (g + h) + f;
    }

    private static long maxValA (long a[], int n) {
        long max = a[0];
        for (int i = 1; i < n; i++) {
            recurs++;
            if (a[i] > max) max = a[i];
        }
        return max;
    }

    private static long maxValB (long a[], int init, int end) {
        recurs++;
        if (end <= init) return Math.max(a[init], a[end]);

        int m = (init + end)/2;
        long v1 = maxValB(a, init, m);
        long v2 = maxValB(a, m + 1, end - 1);

        return Math.max(v1, v2);
    }

    private static ArrayList<Integer> mergeSort (ArrayList<Integer> al) {
        recurs++;
        if (al.size() <= 1) return al;

        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();
        for (int i = 0; i < al.size(); i++) {
            if (i < al.size()/2) left.add(al.get(i));
            else right.add(al.get(i));
        }

        left = mergeSort(left);
        right = mergeSort(right);

        return merge(left, right);
    }

    private static ArrayList<Integer> merge (ArrayList<Integer> left, ArrayList<Integer> right) {
        ArrayList<Integer> r = new ArrayList<>();

        while (!left.isEmpty() && !right.isEmpty()) {
            if (left.get(0) <= right.get(0)) r.add(left.remove(0));
            else r.add(right.remove(0));
        }

        while (!left.isEmpty()) r.add(left.remove(0));

        while (!right.isEmpty()) r.add(right.remove(0));

        return r;
    }
}
