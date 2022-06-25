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
    public static final int MEAL_1_ID_ADMIN = START_SEQ + 3;
    public static final int MEAL_2_ID_ADMIN = START_SEQ + 4;
    public static final int MEAL_1_ID_USER = START_SEQ + 5;
    public static final int MEAL_2_ID_USER = START_SEQ + 6;
    public static final int MEAL_3_ID_USER = START_SEQ + 7;
    public static final int MEAL_4_ID_USER = START_SEQ + 8;

    public static final Meal meal_2_Admin = new Meal(MEAL_2_ID_ADMIN, LocalDateTime.of(2022, Month.JUNE, 20, 19, 0), "Admin dinner", 1500);
    public static final Meal meal_1_User = new Meal(MEAL_1_ID_USER, LocalDateTime.of(2022, Month.JUNE, 20, 13, 0), "User launch", 500);
    public static final Meal meal_2_User = new Meal(MEAL_2_ID_USER, LocalDateTime.of(2022, Month.JUNE, 20, 20, 0), "User dinner", 1500);
    public static final Meal meal_3_User = new Meal(MEAL_3_ID_USER, LocalDateTime.of(2022, Month.JUNE, 21, 14, 0), "User launch", 600);
    public static final Meal meal_4_User = new Meal(MEAL_4_ID_USER, LocalDateTime.of(2022, Month.JUNE, 21, 20, 0), "User dinner", 1500);

    public static Meal getNew() {
        return new Meal(null, LocalDateTime.of(2022, Month.JUNE, 01, 01, 0), "new meal", 1000);
    }

    public static Meal getDouble() {
        return new Meal(null, meal_2_User.getDateTime(), "duplicated time meal", 888);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal_2_Admin);
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
        assertThat(actual).usingRecursiveComparison().isEqualTo(expected);
    }
}
