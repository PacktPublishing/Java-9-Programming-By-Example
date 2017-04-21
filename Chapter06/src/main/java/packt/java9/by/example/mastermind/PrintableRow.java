package packt.java9.by.example.mastermind;

public class PrintableRow extends Row {
    public PrintableRow(Row row) {
        super(row);
    }

    public Color pos(int i) {
        return guess.getColor(i);
    }

    public int full() {
        return full;
    }

    public int partial() {
        return partial;
    }
}
