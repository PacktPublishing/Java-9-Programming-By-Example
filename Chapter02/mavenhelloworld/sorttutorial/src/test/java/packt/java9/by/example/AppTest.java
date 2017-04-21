package packt.java9.by.example;


import org.junit.Test;
import packt.java9.by.example.stringsort.Sort;

import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest {
    @Test
    public void sortRunsFine(){
        Sort.sort(List.of("b","a","c").toArray(new String[3]));
    }

}
