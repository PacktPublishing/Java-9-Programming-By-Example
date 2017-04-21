package packt.java9.by.example.ch03.main;

import packt.java9.by.example.ch03.Sort;
//import packt.java9.by.example.ch03.qsort.Qsort;
import packt.java9.by.example.ch03.quick.QuickSort;
import packt.java9.by.example.ch03.support.ArraySwapper;
import packt.java9.by.example.ch03.support.ArrayWrapper;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String fileName = args[0];
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(new File(fileName))));
        List<String> lines = new LinkedList<>();
        String line;
        while ((line = br.readLine()) != null) {
            lines.add(line);
        }
        br.close();
        String[] lineArray = lines.toArray(new String[0]);
        Sort<String> sort = new QuickSort<>();
//        Qsort<String> qsort = new Qsort<>(String::compareTo,new ArraySwapper<>(lineArray));
        sort.setComparator(String::compareTo);
        sort.setSwapper(new ArraySwapper<>(lineArray));
        sort.sort(new ArrayWrapper<>(lineArray));
        for (final String outLine : lineArray) {
            System.out.println(outLine);
        }
    }
}
