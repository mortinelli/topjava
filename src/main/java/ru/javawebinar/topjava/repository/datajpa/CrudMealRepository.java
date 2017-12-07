package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;

import java.time.LocalDateTime;
import java.util.List;


@Transactional(readOnly = true)
public interface CrudMealRepository extends JpaRepository<Meal, Integer> {

    @Transactional
    @Modifying
//    @Query(name = User.DELETE)
    @Query("DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId")
    int delete(@Param("id") Integer id, @Param("userId") Integer userId);


    @Transactional
    Meal save(Meal meal);

    Meal getMealByIdAndUserId(Integer id, Integer userId);

    List<Meal> getMealsByUserIdOrderByDateTimeDesc(Integer userId);

    List<Meal> getMealsByUserIdAndDateTimeBetweenOrderByDateTimeDesc( Integer userId,LocalDateTime startDate, LocalDateTime endDate);


}
