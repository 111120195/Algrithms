package probability;

import java.util.Collection;
import java.util.TreeMap;

public class Probability {
    private boolean[] used;
    private int[] arr;
    private int count;

    public Probability(int[] arr) {
        this.arr = arr;
        used = new boolean[arr.length];
    }

    public static int factor(int n) {
        int res = 1;
        for (int i = 1; i <= n; i++) {
            res *= i;
        }
        return res;
    }

    private static int biasedRandom(double p) {
        assert p > 0 && p < 1;
        if (Math.random() < p) {
            return 1;
        }
        return 0;
    }

    /**
     * generate 0 or 1 with equal probability
     * the probability of a==1 and b==0
     * is equal to a==0 and b==1
     *
     * @param p double
     * @return int 1 or 0
     */
    public static int rand0to1(double p) {
        int a, b;
        do {
            a = biasedRandom(p);
            b = biasedRandom(p);
        } while (a == b); //both a and b is 1 or 0

        if (a == 1) //a == 1 && b == 0
            return 1;
        return 0; //a == 0 && b == 1
    }

    public int hatHeck() {
        hatHeck(0);
        return count;
    }

    private void hatHeck(int n) {
        if (n == arr.length) {
            for (int i = 0; i < arr.length; i++) {
                if (arr[i] == i) count++;
            }
            return;
        }

        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                arr[n] = i;
                used[i] = true;
                hatHeck(n + 1);
                used[i] = false;
            }
        }
    }

    public void shuffler() {
        TreeMap<Integer, Integer> treeMap = new TreeMap<>();
        int N = arr.length * arr.length * arr.length;
        for (int val : arr) {
            int key = ((int) (Math.random() * N));
            treeMap.put(key, val);
        }
        Collection<Integer> vacs = treeMap.values();
        int i = 0;
        for (Integer vac : vacs) {
            arr[i++] = vac;
        }
        for (int j = 0; j < arr.length; j++) {
            System.out.println(arr[j]);
        }
    }

    private int random(int l, int h){
        return l+(int)(Math.random()*(h-l));
    }

    private void swap(int[] arr, int i, int k){
        int temp = arr[i];
        arr[i] = arr[k];
        arr[k] = temp;
    }

    /**
     * 对 arr 数组进行随机排列
     */
    public void randomPermutation(){
        for (int i = 0; i < arr.length; i++) {
            int randIndex = random(i, arr.length);
            swap(arr, i, randIndex);
        }
    }

    @Override
    public String toString() {
        StringBuffer str = new StringBuffer();
        for (int i = 0; i < arr.length; i++) {
            str.append(arr[i]).append(",");
        }
        return str.toString();
    }

    /**
     * driver method
     */
    public static void main(String[] args) {
        int[] arr = new int[10];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i;
        }
        Probability p = new Probability(arr);
        p.randomPermutation();
        System.out.println(p);
    }
}

