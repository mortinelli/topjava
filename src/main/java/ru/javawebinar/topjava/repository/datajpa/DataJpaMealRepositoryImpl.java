package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class DataJpaMealRepositoryImpl implements MealRepository {

    @Autowired
    private CrudMealRepository crudRepository;

    @Override
    public Meal save(Meal meal,int userId) throws NotFoundException {
        User ref = new User();
        ref.setId(userId);
        meal.setUser(ref);

        if (meal.isNew()){
            return crudRepository.save(meal);}

            else {
            if (!getAll(userId).contains(meal)) {
                throw new NotFoundException("Not found entity with id=" + meal.getId());
            }else return crudRepository.save(meal);
        }



    }

    @Override
    public boolean delete(int id, int userId) {
        return crudRepository.delete(id,userId) !=0;
    }

    @Override
    public Meal get(int id, int userId) {
        return crudRepository.getMealByIdAndUserId(id,userId);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudRepository.getMealsByUserIdOrderByDateTimeDesc(userId);
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {
        return crudRepository.getMealsByUserIdAndDateTimeBetweenOrderByDateTimeDesc(userId,startDate,endDate);
    }
}
