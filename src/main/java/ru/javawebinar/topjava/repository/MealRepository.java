package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collection;

public interface MealRepository {
    Meal save(Meal meal, int userId);

    boolean delete(int userId, int id );

    Meal get(int userId, int id);

    Collection<Meal> getAll(int userId);
}
