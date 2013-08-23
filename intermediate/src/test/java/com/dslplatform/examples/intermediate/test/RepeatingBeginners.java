package com.dslplatform.examples.intermediate.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import org.junit.*;

import com.dslplatform.examples.intermediate.Cookbook.Recipe;
import com.dslplatform.examples.intermediate.Consumables.*;
import com.dslplatform.examples.intermediate.test.setup.OpenRestaurantInTest;

import static com.dslplatform.examples.intermediate.Recipes.*;

public class RepeatingBeginners extends OpenRestaurantInTest{

    /** How to insert, get all meals and access properties */
    @Test
    public void insetSomeMeals() throws Exception {
        restaurant
            .cleanDB()
            .insertCheffsRecipes()
            .insertCheffsMeal();
        final List<Meal> remoteMeals = mealRepository.findAll().get();

        // collect meal names
        final List<String> remoteNames = new ArrayList<String>();
        for (final Meal meal : remoteMeals) {
            String name = meal.getName();
            remoteNames.add(name);
            info(name);
        }

        final String [] expectedMealNames = { nPepperedShrimp, nPeanutChicken, nOmeletteAuFromage};
        for( final String mealname : expectedMealNames)    assertTrue(remoteNames.contains(mealname));
    }

    @Test
    public void insetSomeBeverages() throws Exception {
        restaurant
            .cleanDB()
            .insertSomeBeverages();
        final List<Beverage> remoteBeverage = beverageRepository.findAll().get();

        // collect meal names
        final List<String> remoteNames = new ArrayList<String>();
        for (final Beverage beverage : remoteBeverage) {
            String name = beverage.getName();
            remoteNames.add(name);
            info(name);
        }

        final String [] expectedBeverageNames = { "beer", "juice", "water"};
        for( final String beveragename : expectedBeverageNames)
            assertTrue(remoteNames.contains(beveragename));
    }

    /** How to use specification to query over an object. */
    @Test
    public void mealWithEggs() throws Exception {
        restaurant
            .cleanDB()
            .insertCheffsRecipes()
            .insertCheffsMeal()
            .insertSomeBeverages();
        final List<Meal> remoteMeals = mealRepository.findAll().get();
        final List<String> remoteNames = new ArrayList<String>();
        for (final Meal meal : remoteMeals) {
            String name = meal.getName();
            remoteNames.add(name);
        }

        final String [] expectedMealNames = { nPepperedShrimp, nPeanutChicken, nOmeletteAuFromage};
        for( final String mealname : expectedMealNames)
            assertTrue(remoteNames.contains(mealname));
        final List<Recipe> recipesWithEggs = new Recipe.hasIngredient("egg").search();
        assertEquals(nOmeletteAuFromage, recipesWithEggs.get(0).getName());
    }
}
