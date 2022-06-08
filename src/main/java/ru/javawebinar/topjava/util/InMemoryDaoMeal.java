package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class InMemoryDaoMeal implements DaoMeal {
    private AtomicInteger counter = new AtomicInteger();
    public List<Meal> meals;

    public InMemoryDaoMeal() {
        // hardcoded meals data until Users implementation
        meals = new ArrayList<Meal>();
        addMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500);
        addMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000);
        addMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500);
        addMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100);
        addMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000);
        addMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500);
        addMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410);
    }

    public void addMeal(LocalDateTime dateTime, String description, int calories) {
        meals.add(new Meal(generateId(), dateTime, description, calories));
    }

    private int generateId() {
        return counter.incrementAndGet();
    }

    public Meal getMealById(int id) {
        Meal result;
        for (Meal meal : meals) {
            if (meal.getId() == id) {
                result = meal;
                return result;
            }
        }
        return null;
    }

    public List<Meal> getAllMeals() {
        return meals;
    }

    public void updateMeal(Meal update) {
        for (Meal meal : meals) {
            if (meal.getId() == update.getId()) {
                meals.set(meals.indexOf(meal), update);
                return;
            }
        }
    }

    public void deleteMeal(int id) {
        for (Meal meal : meals) {
            if (meal.getId() == id) {
                meals.remove(meal);
                return;
            }
        }
    }
}
