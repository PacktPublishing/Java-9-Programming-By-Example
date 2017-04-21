package packt.java9.by.example.mastermind.servlet;

import packt.java9.by.example.mastermind.ColorManager;
import packt.java9.by.example.mastermind.Table;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class GameSessionSaver {
    private static final String STATE_NAME = "GAME_STATE";
    @Inject
    private HtmlTools html;
    @Inject
    Table table;
    @Inject
    ColorManager manager;

    public void save(HttpSession session) {
        Map<String,String> params = convertTableToMap();
        session.setAttribute(STATE_NAME,params);
    }

    public void reset(HttpSession session) {
        session.removeAttribute(STATE_NAME);
    }

    public Map<String,String> restore(HttpSession session){
        Map<String,String> map= (Map<String,String>)session.getAttribute(STATE_NAME);
        if( map == null ){
            map = new HashMap<>();
        }
        return map;
    }

    private Map<String,String> convertTableToMap() {
        Map<String, String> params = new HashMap<>();
        for (int row = 0; row < table.nrOfRows(); row++) {
            for (int column = 0; column < table.nrOfColumns(); column++) {
                params.put(html.paramNameGuess(row,column),table.getColor(row,column).toString());
            }
            params.put(html.paramNameFull(row),""+table.getFull(row));
            params.put(html.paramNamePartial(row),""+table.getPartial(row));
        }
        return params;
    }

}
