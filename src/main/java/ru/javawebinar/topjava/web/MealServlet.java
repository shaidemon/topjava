package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MealDao;
import ru.javawebinar.topjava.repository.InMemoryMealDao;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);
    private static final String MEALS_LIST = "meals.jsp";
    private static final String MEAL_ADD_OR_UPDATE = "mealAddOrUpdate.jsp";
    private static final int CALORIES_LIMIT = 2000;
    private MealDao dao;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
    List<MealTo> mealsTo;

    @Override
    public void init() {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
        dao = new InMemoryMealDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meals");
//        response.sendRedirect("meals.jsp");
//        request.setCharacterEncoding("UTF-8");
        String action = request.getParameter("action");
        String forward = "";
        mealsTo = MealsUtil.filteredList(dao.getAll(), LocalTime.of(0, 0), LocalTime.of(23, 59), CALORIES_LIMIT);
        if (action == null) {
            forward = MEALS_LIST;
            request.setAttribute("meals", mealsTo);
        } else if (action.equalsIgnoreCase("update")) {
            forward = MEAL_ADD_OR_UPDATE;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            Meal meal = dao.getMealById(mealId);
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("delete")) {
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            dao.delete(mealId);
            forward = MEALS_LIST;
            response.sendRedirect("meals");
            return;
//            request.setAttribute("meals", mealsTo);
        } else {
            forward = MEAL_ADD_OR_UPDATE;
        }


        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        log.debug("id :" + request.getParameter("id")+";");
        String id = request.getParameter("id");
        LocalDateTime mealDateTime = LocalDateTime.parse(request.getParameter("dateTime"), formatter);
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        if (id == null || id.isEmpty()) {
            dao.add(mealDateTime, description, calories);
        } else {
            dao.update(new Meal(Integer.parseInt(id), mealDateTime, description, calories));
        }
        response.sendRedirect("meals");

    }

}
