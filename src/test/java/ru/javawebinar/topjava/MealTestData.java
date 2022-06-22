package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int USER_ID = START_SEQ;
    public static final int ADMIN_ID = START_SEQ + 1;
    public static final int MEAL_0_ID = START_SEQ + 3;
    public static final int MEAL_00_ID = START_SEQ + 4;
    public static final int MEAL_1_ID = START_SEQ + 5;
    public static final int MEAL_2_ID = START_SEQ + 6;
    public static final int MEAL_3_ID = START_SEQ + 7;
    public static final int MEAL_4_ID = START_SEQ + 8;
    public static final int NOT_FOUND = 10;

    public static final Meal meal00 = new Meal(MEAL_00_ID, LocalDateTime.of(2022, Month.JUNE, 20, 19, 0), "Admin dinner", 1500);
    public static final Meal meal1 = new Meal(MEAL_1_ID, LocalDateTime.of(2022, Month.JUNE, 20, 13, 0), "User launch", 500);
    public static final Meal meal2 = new Meal(MEAL_2_ID, LocalDateTime.of(2022, Month.JUNE, 20, 20, 0), "User dinner", 1500);
    public static final Meal meal3 = new Meal(MEAL_3_ID, LocalDateTime.of(2022, Month.JUNE, 21, 14, 0), "User launch", 600);
    public static final Meal meal4 = new Meal(MEAL_4_ID, LocalDateTime.of(2022, Month.JUNE, 21, 20, 0), "User dinner", 1500);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2022, Month.JUNE, 01, 01, 0), "new meal", 1000);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal00);
        updated.setDateTime(LocalDateTime.of(2022, Month.JUNE, 20, 14, 30));
        updated.setDescription("Updated Meal");
        updated.setCalories(777);
        return updated;
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }
}
