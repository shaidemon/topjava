package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.DaoMeal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final String MEALS_JSP = "meals.jsp";
    private DaoMeal dao;
    public static List<Meal> meals;

    public MealServlet() {
        super();
        dao = new DaoMeal();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
//        request.getRequestDispatcher("/meals.jsp").forward(request, response);
//        response.sendRedirect("meals.jsp");
//        request.getRequestDispatcher("/meals.jsp").forward(request,response);
        String forward = MEALS_JSP;
        request.setAttribute("meals", dao.getAllMeals());
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }


}
