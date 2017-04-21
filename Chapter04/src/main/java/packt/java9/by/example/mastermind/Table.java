package packt.java9.by.example.mastermind;

import java.util.LinkedList;
import java.util.List;

/**
 * Represents the state of the table of the game.
 */
public class Table {
    final ColorManager manager;
    /**
     * The number of columns in the table.
     */
    final int nrColumns;
    /**
     * The rows of the table.
     */

    final List<Row> rows;

    public Table(int nrColumns, ColorManager manager) {
        this.nrColumns = nrColumns;
        this.rows = new LinkedList<>();
        this.manager = manager;
    }

    public void addRow(Row row) {
        rows.add(row);
    }
}
