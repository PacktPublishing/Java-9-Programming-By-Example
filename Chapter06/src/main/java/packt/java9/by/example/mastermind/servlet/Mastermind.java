package packt.java9.by.example.mastermind.servlet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Mastermind extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(Mastermind.class);

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {
        GameSessionSaver sessionSaver = new GameSessionSaver();
        sessionSaver.reset(request.getSession());
        doPost(request, response);
    }


    public void doPost(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        Injector injector = Guice.createInjector(new MastermindModule());

        MastermindHandler handler = injector.getInstance(MastermindHandler.class);
        handler.handle(request, response);
    }


}