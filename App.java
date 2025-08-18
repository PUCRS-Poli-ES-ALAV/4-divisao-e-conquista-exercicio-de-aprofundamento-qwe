import java.util.ArrayList;
import java.util.Scanner;

public class App 
{
    private static int recurs = 0;
    public static void main( String[] args )
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Base: ");
        int n = sc.nextInt();
        System.out.print("Potencia: ");
        int m = sc.nextInt();
        sc.close();
        // ArrayList<Integer> al = new ArrayList<>();
        // long[] arr = new long[n];
        // for (int i = 0; i < n; i++) arr[i] = ((int)Math.random());

        System.out.println("Trabalhando - powA");
        long start = System.currentTimeMillis();
        // mergeSort(al);
        // maxValC(arr, 0, n-1);
        powA(n, m);
        long end = System.currentTimeMillis();
        System.out.println("Elapsed: " + (end - start) + "ms");
        System.out.println("Recursoes: " + recurs);
        
        recurs = 0;
        System.out.println("Trabalhando - powB");
        start = System.currentTimeMillis();
        powB(n, m);
        end = System.currentTimeMillis();
        System.out.println("Elapsed: " + (end - start) + "ms");
        System.out.println("Recursoes: " + recurs);
    }

    private static int powA(int a, int n) {
        int p = 1;
        for (int i = 0; i < n; i++) {
            p *= a;
            recurs++;
        }
        return p;
    }

    private static int powB(int a, int n) {
        recurs++;
        if (n == 0) return 1;
        if (n % 2 == 0) return powB(a, n/2) * powB(a, n/2);
        return powB(a, (n-1)/2) * powB(a, (n-1)/2) * a; 
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

    private static long maxValC (long a[], int init, int end) {
        recurs++;
        if (end - init <= 1) return Math.max(a[init], a[end]);

        int m = (init + end) / 2;
        long v1 = maxValC(a, init, m);
        long v2 = maxValC(a, m+1, end);
        return Math.max(v1,v2);
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
