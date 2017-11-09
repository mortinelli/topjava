package ru.javawebinar.topjava.web;

import org.slf4j.Logger;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.repository.InMemoryMealRepo;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by smatveev on 08.11.2017.
 */
public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private MealRepository mealRepository;

    public void init(ServletConfig config) throws ServletException{
        super.init(config);
        mealRepository = new InMemoryMealRepo();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");
        Meal meal = new Meal(id.isEmpty() ? null : Integer.valueOf(id),
                LocalDateTime.parse(req.getParameter("datetime")),
                req.getParameter("description"),
                Integer.valueOf(req.getParameter("calories")));
                log.info(meal.isNew() ? "Create {}" : "Uptade {}", meal);
                mealRepository.save(meal);
                resp.sendRedirect("meal");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if(action == null){
            log.info("getAll");
            request.setAttribute("meal",
                        MealsUtil.getWithExceeded(mealRepository.getAll(), 2000));
            request.getRequestDispatcher("meal.jsp").forward(request,response);
        } else if (action .equals("delete")){
            int id = getId(request);
            log.info("Delete {}", id);
            mealRepository.delete(id);
            response.sendRedirect("meal");
        } else {
            final Meal meal = action.equals("create") ? new Meal(LocalDateTime.now(), "", 1000) :
                    mealRepository.get(getId(request));
            request.setAttribute("meal", meal);
            request.getRequestDispatcher("mealEdit.jsp").forward(request,response);
        }


    }

    private int getId(HttpServletRequest request){
        String paramId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(paramId);
    }
}
