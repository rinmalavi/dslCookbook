package com.dslplatform.examples.intermediate.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.*;

import com.dslplatform.examples.intermediate.Cookbook.Recipe;
import com.dslplatform.examples.intermediate.Consumables.*;
import com.dslplatform.examples.intermediate.test.setup.OpenRestaurantInTest;

import static com.dslplatform.examples.intermediate.Recipes.*;

public class FindingInRepository extends OpenRestaurantInTest{

    /** How to use specification to query over an object. */
    @Test
    public void mealWithEggs() throws Exception {
        restaurant
            .cleanDB()
            .insertCheffsRecipes()
            .insertCheffsMeal()
            .insertSomeBeverages();
        final List<Meal> remoteMeals = mealRepository.findAll().get();
        final List<String> someUris = new ArrayList<String>();
        for (final Meal meal : remoteMeals) {
            String name = meal.getURI();
            someUris.add(name);
        }
        mealRepository.update(mealRepository.find(someUris).get()).get();
        }
}
