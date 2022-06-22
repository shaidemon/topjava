package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.bridge.SLF4JBridgeHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.assertThrows;
import static ru.javawebinar.topjava.MealTestData.*;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    static {
        // Only for postgres driver logging
        // It uses java.util.logging and logged via jul-to-slf4j bridge
        SLF4JBridgeHandler.install();
    }

    @Autowired
    private MealService service;

    @Test
    public void get() {
        Meal meal = service.get(MEAL_1_ID_USER, USER_ID);
        assertMatch(meal, meal_1_User);
    }

    @Test
    public void getNotFoundSomeonesMeal() {
        assertThrows(NotFoundException.class, () -> service.get(MEAL_1_ID_ADMIN, USER_ID));
    }

    @Test
    public void delete() {
        service.delete(MEAL_1_ID_ADMIN, ADMIN_ID);
        assertMatch(service.getAll(ADMIN_ID), meal_2_Admin);
    }

    @Test
    public void deletedNotFound() {
        assertThrows(NotFoundException.class, () -> service.delete(MEAL_1_ID_ADMIN, USER_ID));
    }

    @Test
    public void getBetweenInclusive() {
        LocalDate startDate = LocalDate.of(2022, Month.JUNE, 21);
        LocalDate endDate = LocalDate.of(2022, Month.JUNE, 22);
        assertMatch(service.getBetweenInclusive(startDate, endDate, USER_ID), meal_4_User, meal_3_User);
    }

    @Test
    public void getAll() {
        List<Meal> all = service.getAll(USER_ID);
        assertMatch(all, meal_4_User, meal_3_User, meal_2_User, meal_1_User);
    }

    @Test
    public void update() {
        Meal updated = getUpdated();
        service.update(updated, ADMIN_ID);
        assertMatch(service.get(MEAL_2_ID_ADMIN, ADMIN_ID), getUpdated());
    }

    @Test
    public void updateNotFoundSomeonesMeal() {
        assertThrows(NotFoundException.class, () -> service.update(getUpdated(), USER_ID));
    }

    @Test
    public void create() {
        Meal created = service.create(getNew(), USER_ID);
        Integer newId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        assertMatch(service.get(newId, USER_ID), newMeal);
    }

    @Test
    public void duplicateDateTimeCreate() {
        assertThrows(DuplicateKeyException.class, () -> service.create(getDouble(), USER_ID));
    }
}