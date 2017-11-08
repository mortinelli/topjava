package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.dao.HardDB;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by smatveev on 08.11.2017.
 */
public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.debug("redirect to meal");

        List<MealWithExceed> mealWithExceeds = MealsUtil.getWithExceeded(HardDB.MEAL_LIST, 2000);

        request.setAttribute("meal",mealWithExceeds);
        request.getRequestDispatcher("meal.jsp").forward(request,response);
    }
}
