package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface MealDao {

    void add(LocalDateTime dateTime, String description, int calories);

    Meal getMealById(int id);

    List<Meal> getAll();

    void update(Meal meal);

    void delete(int id);
}
