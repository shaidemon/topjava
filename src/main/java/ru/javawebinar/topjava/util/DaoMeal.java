package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.List;

public interface DaoMeal {

    void addMeal(LocalDateTime dateTime, String description, int calories);

    Meal getMealById(int id);

    List<Meal> getAllMeals();

    void updateMeal(Meal meal);

    void deleteMeal(int id);
}
