package packt.java9.by.example.ch03.main.bubble;

import org.junit.Assert;
import org.junit.Test;
import packt.java9.by.example.ch03.Sort;
import packt.java9.by.example.ch03.SortableCollection;
import packt.java9.by.example.ch03.Swapper;

import java.util.ArrayList;
import java.util.Arrays;

public class BubbleSortTest {
    @Test
    public void canSortStrings() {
        ArrayList<String> actualNames = new ArrayList<>(Arrays.asList(
                "Johnson", "Wilson",
                "Wilkinson", "Abraham", "Dagobert"
        ));
        ArrayList<String> expectedResult = new ArrayList<>(Arrays.asList(
                "Abraham", "Dagobert",
                "Johnson", "Wilkinson", "Wilson"
        ));
        SortableCollection<String> names =
                new ArrayListSortableCollection<>(actualNames);
        Sort<String> sort = new BubbleSort<>();
        sort.setComparator(String::compareTo);
        sort.setSwapper(new ArrayListSwapper<>(actualNames));
        sort.sort(names);
        Assert.assertEquals(expectedResult, actualNames);
    }

    @Test(expected = RuntimeException.class)
    public void throwsWhateverComparatorDoes() {
        ArrayList<String> actualNames = new ArrayList<>(Arrays.asList(
                "", "Wilson",
                "Wilkinson", "Abraham", "Dagobert"
        ));
        ((ArrayList) actualNames).set(0, 42);
        SortableCollection<String> names =
                new ArrayListSortableCollection<>(actualNames);
        Sort<String> sort = new BubbleSort<>();
        sort.setComparator((a, b) -> {
            throw new RuntimeException();
        });
        final Swapper neverInvoked = null;
        sort.setSwapper(neverInvoked);
        sort.sort(names);
    }
}
