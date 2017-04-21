package packt.java9.by.example.ch03.quick;

import org.junit.Assert;
import org.junit.Test;
import packt.java9.by.example.ch03.Sort;
import packt.java9.by.example.ch03.support.ArraySwapper;
import packt.java9.by.example.ch03.support.ArrayWrapper;

public class QuickSortTest {

    @Test
    public void canSortStrings() {
        final String[] actualNames = new String[]{
                "Johnson", "Wilson",
                "Wilkinson", "Abraham", "Dagobert"
        };
        final String[] expected = new String[]{"Abraham", "Dagobert", "Johnson", "Wilkinson", "Wilson"};
        Sort<String> sort = new QuickSort<>();
        sort.setComparator(String::compareTo);
        sort.setSwapper(new ArraySwapper<String>(actualNames));
        sort.sort(new ArrayWrapper<>(actualNames));
        Assert.assertArrayEquals(expected, actualNames);
    }

    @Test
    public void canSortOne() {
        final String[] actualNames = new String[]{"Abraham"};
        final String[] expected = new String[]{"Abraham"};
        Sort<String> sort = new QuickSort<>();
        sort.setComparator(String::compareTo);
        sort.setSwapper(new ArraySwapper<String>(actualNames));
        sort.sort(new ArrayWrapper<>(actualNames));
        Assert.assertArrayEquals(expected, actualNames);
    }

    @Test
    public void canSortAlreadySorted() {
        final String[] actualNames = new String[]{"Abraham", "Dagobert"};
        final String[] expected = new String[]{"Abraham", "Dagobert"};
        Sort<String> sort = new QuickSort<>();
        sort.setComparator(String::compareTo);
        sort.setSwapper(new ArraySwapper<String>(actualNames));
        sort.sort(new ArrayWrapper<>(actualNames));
        Assert.assertArrayEquals(expected, actualNames);
    }
}
