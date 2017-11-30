package ru.javawebinar.topjava.repository.jpa;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional (readOnly = true, propagation = Propagation.SUPPORTS)
public class JpaMealRepositoryImpl implements MealRepository {


    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        User ref = em.getReference(User.class, userId);
        meal.setUser(ref);
            if (meal.isNew()) {
                em.persist(meal);
                return meal;
            } else {
                return em.merge(meal);
            }

    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE)
                .setParameter("id",id)
                .setParameter("userId",userId)
                .executeUpdate() !=0;
    }

    @Override
    @Transactional
    public Meal get(int id, int userId) throws NoResultException {
                return em.createNamedQuery(Meal.GET,Meal.class)
                .setParameter("id", id)
                .setParameter("userId",userId)
                .getSingleResult();
    }

  //@NamedQuery(name = Meal.ALL_SORTED, query = "SELECT m FROM Meal m left join FETCH m.user WHERE m.user.id =:userID ")

    @Override
    @Transactional
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED,Meal.class)
                .setParameter("userId",userId)
                .getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId) {

        return em.createNamedQuery(Meal.GET_BETWEEN,Meal.class)
                .setParameter("userId",userId)
                .setParameter("startDate",startDate)
                .setParameter("endDate",endDate)
                .getResultList();
    }
}