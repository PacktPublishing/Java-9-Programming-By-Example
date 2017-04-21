package packt.java9.by.example.mastermind.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import packt.java9.by.example.mastermind.*;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.stream.Collectors;

public class MastermindHandler {

    @Inject
    @Named("nrColors")
    private int NR_COLORS;
    @Inject
    @Named("nrColumns")
    private int NR_COLUMNS;
    @Inject
    private HtmlTools html;
    @Inject
    Table table;
    @Inject
    ColorManager manager;
    @Inject
    Guesser guesser;
    @Inject
    GameSessionSaver sessionSaver;

    private static Logger log = LoggerFactory.getLogger(MastermindHandler.class);

    public void handle(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        Game game = buildGameFromSessionAndRequest(request);
        Guess newGuess = guesser.guess();
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        if (game.isFinished() || newGuess == Guess.none) {
            displayGameOver(out);
        } else {
            log.debug("Adding new guess {} to the game", newGuess);
            game.addGuess(newGuess, 0, 0);
            sessionSaver.save(request.getSession());
            displayGame(out);
        }
        bodyEnd(out);
    }

    private void displayGameOver(PrintWriter out) throws IOException {
        out.println(html.tableToHtml());
        out.println("</form>");
        out.println("Game finished, no more guesses");
    }

    private void displayGame(PrintWriter out) throws IOException {
        out.println(html.tableToHtml());
        out.println(html.tag("input", "type", "submit", "value", "submit"));
        out.println("</form>");

    }

    private void bodyEnd(PrintWriter out) {
        out.println("</body></head></html>");
    }

    private Game buildGameFromSessionAndRequest(HttpServletRequest request) {
        Game game = buildGameFromMap(sessionSaver.restore(request.getSession()));
        Map<String, String> params = toMap(request);
        int row = getLastRowIndex(params);
        log.debug("last row is {}", row);
        if (row >= 0) {
            final int full = Integer.parseInt(params.get(html.paramNameFull(row)));
            final int partial = Integer.parseInt(params.get(html.paramNamePartial(row)));
            log.debug("setting full {} and partial {} for row {}", full, partial, row);
            table.setPartial(row, partial);
            table.setFull(row, full);
            if (full == table.nrOfColumns()) {
                game.setFinished();
            }
        }
        return game;
    }

    private static final int MAX_ROWS = 10;

    private int getLastRowIndex(Map<String, String> params) {
        int row = -1;
        for (int i = 0; i < MAX_ROWS; i++) {
            if (params.containsKey(html.paramNameFull(i))) {
                row = i;
            }
        }
        log.debug("last row is {}", row);
        return row;
    }

    private Map<String, String> toMap(HttpServletRequest request) {
        log.debug("converting request to map");
        return request.getParameterMap().entrySet().
                stream().collect(
                Collectors.toMap(
                        Map.Entry::getKey,
                        e -> e.getValue()[0]));
    }

    private Game buildGameFromMap(Map<String, String> params) {
        final Guess secret = new Guess(new Color[NR_COLUMNS]);
        final Game game = new Game(table, secret);
        for (int row = 0;
             params.containsKey(html.paramNameGuess(row, 0));
             row++) {
            Color[] colors = getRowColors(params, row);
            Guess guess = new Guess(colors);
            final int full = Integer.parseInt(params.get(html.paramNameFull(row)));
            final int partial = Integer.parseInt(params.get(html.paramNamePartial(row)));
            log.debug("Adding guess to game");
            game.addGuess(guess, full, partial);
        }
        return game;
    }

    private Color[] getRowColors(Map<String, String> params, int row) {
        Color[] colors = new Color[NR_COLUMNS];
        for (int column = 0; column < NR_COLUMNS; column++) {
            String letter = params.get(html.paramNameGuess(row, column));
            colors[column] = colorFrom(letter);
            log.debug("Processing guess{}{} = {}", row, column, colors[column]);
        }
        return colors;
    }

    private Color colorFrom(String letter) {
        Color color = manager.firstColor();
        while (!color.toString().equals(letter)) {
            if (manager.thereIsNextColor(color)) {
                color = manager.nextColor(color);
            } else {
                return null;
            }
        }
        return color;
    }

}
