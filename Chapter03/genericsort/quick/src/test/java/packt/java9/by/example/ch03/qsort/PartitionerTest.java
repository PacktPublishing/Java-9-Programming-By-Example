package packt.java9.by.example.ch03.qsort;

import org.junit.Assert;
import org.junit.Test;
import packt.java9.by.example.ch03.Swapper;
import packt.java9.by.example.ch03.support.ArraySwapper;
import packt.java9.by.example.ch03.support.ArrayWrapper;

public class PartitionerTest {

    @Test
    public void partitionsIntArray() {
        Integer[] partitionThis = new Integer[]{0, 7, 6, 2};
        Swapper swapper = new ArraySwapper<>(partitionThis);
        Partitioner<Integer> partitioner =
                new Partitioner<>((a, b) -> a < b ? -1 : a > b ? +1 : 0, swapper);
        final Integer pivot = 6;
        final int cutIndex = partitioner.partition(new ArrayWrapper<>(partitionThis), 0, partitionThis.length-1, pivot);
        Assert.assertEquals(2, cutIndex);
        final Integer[] expected = new Integer[]{0, 2, 6, 7};
        Assert.assertArrayEquals(expected,partitionThis);
    }
}
