package ru.javawebinar.topjava.repository.datajpa;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public class DataJpaMealRepository implements MealRepository {

    private static final Sort SORT_DATETIME = Sort.by(Sort.Direction.DESC, "dateTime");

    private final CrudMealRepository crudMealRepository;
    private final CrudUserRepository crudUserRepository;

    public DataJpaMealRepository(CrudMealRepository crudRepository, CrudUserRepository crudUserRepository) {
        this.crudMealRepository = crudRepository;
        this.crudUserRepository = crudUserRepository;
    }

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        if (meal.isNew()) {
            meal.setUser(crudUserRepository.getReferenceById(userId));
            return crudMealRepository.save(meal);
        } else {
            if (get(meal.getId(), userId) == null) return null;
        }
        return crudMealRepository.save(meal);
    }

    @Override
    public boolean delete(int id, int userId) {

        return crudMealRepository.delete(id, userId) !=0;
    }

    @Override
    public Meal get(int id, int userId) {
          return crudMealRepository.findById(id).stream()
                 .filter(meal -> meal.getUser().getId() == userId).findAny().orElse(null);
    }

    @Override
    public List<Meal> getAll(int userId) {
        return crudMealRepository.findAll(SORT_DATETIME);
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return crudMealRepository.getBetweenHalfOpen(startDateTime, endDateTime, userId);
    }
}
