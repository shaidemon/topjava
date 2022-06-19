package ru.javawebinar.topjava.repository.inmemory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class InMemoryMealRepository implements MealRepository {

    private static final Logger log = LoggerFactory.getLogger(InMemoryMealRepository.class);

    private final Map<Integer, Map<Integer, Meal>> repository = new ConcurrentHashMap<>();

    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal, 1));
    }

    private boolean isUserMealPresent(int userId, int id) {
        return repository.containsKey(userId) && repository.get(userId).containsKey(id);
    }

    @Override
    public Meal save(Meal meal, int userId) {
        if (!repository.containsKey(userId)) {
            repository.put(userId, new ConcurrentHashMap<>());
        }
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            repository.get(userId).put(meal.getId(), meal);
            return meal;
        }
        int mealId = meal.getId();
        return isUserMealPresent(userId, mealId) ? repository.get(userId).computeIfPresent(mealId, (id, oldMeal) -> meal) : null;
    }

    @Override
    public boolean delete(int userId, int id) {
        return isUserMealPresent(userId, id) && repository.get(userId).remove(id) != null;
    }

    @Override
    public Meal get(int userId, int id) {
        if (isUserMealPresent(userId, id)) {
            return repository.get(userId).get(id);
        }
        return null;
    }

    @Override
    public List<Meal> getAll(int userId) {
        log.debug("Get All Full repo: \n" + repository);
        return repository.containsKey(userId) ?
                repository.get(userId).values().stream()
                        .sorted(Comparator.comparing(Meal::getDateTime).reversed()).collect(Collectors.toList())
                : Collections.emptyList();
    }
}

