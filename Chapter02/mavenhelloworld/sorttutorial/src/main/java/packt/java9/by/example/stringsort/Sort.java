package packt.java9.by.example.stringsort;

public class Sort {

    public static void sort(String[] names) {
        int n = names.length;
        while (n > 1) {
            for (int j = 0; j < n - 1; j++) {
                if (names[j].compareTo(names[j + 1]) > 0) {
                    final String tmp = names[j + 1];
                    names[j + 1] = names[j];
                    names[j] = tmp;
                }
            }
            n--;
        }
    }
}
