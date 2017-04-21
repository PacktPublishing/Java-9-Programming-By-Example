package packt.java9.by.example.mastermind;

public class PrettyPrintRow {

    public static String pprint(Row row) {
        String string = "";
        PrintableRow pRow = new PrintableRow(row);
        for (int i = 0; i < pRow.nrOfColumns(); i++) {
            string += pRow.pos(i);
        }
        string += " ";
        string += pRow.full();
        string += "/";
        string += pRow.partial();
        return string;
    }
}
