package com.dslplatform.examples.intermediate;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.dslplatform.patterns.ServiceLocator;
import com.dslplatform.examples.intermediate.Cookbook.Recipe;
import com.dslplatform.examples.intermediate.Cookbook.repositories.RecipeRepository;
import com.dslplatform.examples.intermediate.Consumables.*;
import com.dslplatform.examples.intermediate.Consumables.repositories.*;

import static com.dslplatform.examples.intermediate.Recipes.*;

/**
 * This class holds helper functions for inserting test data to database. here
 * you can see how you would insert or delete data once you mapped it to DSL
 * objects.
 * */
public class Restaurant {

    final RecipeRepository recipeRepository;
    final MealRepository mealRepository;
    final MealInfoRepository mealInfoRepository;
    final BeverageRepository beverageRepository;

    public Restaurant(final ServiceLocator locator) throws IOException {
        recipeRepository   = locator.resolve(RecipeRepository.class);
        mealRepository     = locator.resolve(MealRepository.class);
        mealInfoRepository = locator.resolve(MealInfoRepository.class);
        beverageRepository = locator.resolve(BeverageRepository.class);
    }

    public Restaurant insertCheffsRecipes() throws InterruptedException,
            ExecutionException {
        recipeRepository.insert(chefsChoice).get();
        return this;
    }

    public Restaurant insertAllMealsAndRecipes() throws InterruptedException,
            ExecutionException, IOException {
        recipeRepository.insert(allRecipes).get();

        Recipe  recipePepperedShrimp      = Recipe.find(nPepperedShrimp);
        Recipe  recipePeanutChicken       = Recipe.find(nPeanutChicken);
        Recipe  recipeOmeletteAuFromage   = Recipe.find(nOmeletteAuFromage);
        Recipe  recipeOmelette            = Recipe.find(nOmelette);
        Recipe  recipePancakes            = Recipe.find(nPancakes);
        Recipe  recipeMeatloaf            = Recipe.find(nMeatloaf);
        Recipe  recipeLasagna             = Recipe.find(nLasagna);
        Recipe  recipePancakesWithNuttela = Recipe.find(nPancakesWithNuttela);

        final Meal[] meals = {
                new Meal(recipePepperedShrimp     , true, true, 32),
                new Meal(recipePeanutChicken      , true, true, 27),
                new Meal(recipeOmeletteAuFromage  , true, true, 12),
                new Meal(recipeOmelette           , true, false, 7),
                new Meal(recipePancakes           , true, false, 7),
                new Meal(recipeMeatloaf           , true, false, 21),
                new Meal(recipeLasagna            , true, false, 19),
                new Meal(recipePancakesWithNuttela, true, false, 9)
        };
        mealRepository.insert(meals).get();
        return this;
    }

    public Restaurant insertCheffsRecipesAndMeals() throws InterruptedException,
            ExecutionException, IOException {
        recipeRepository.insert(chefsChoice).get();
        insertCheffsMeal();
        return this;
    }

    public Restaurant insertCheffsMeal() throws InterruptedException,
            ExecutionException, IOException {
        Recipe recipeForPapperedShrimp = Recipe.find(nPepperedShrimp);
        Recipe recipePeanutChicken = Recipe.find(nPeanutChicken);
        Recipe recipeOmeletteAuFromage = Recipe.find(nOmeletteAuFromage);
        final Meal[] meals = {
                new Meal(recipeForPapperedShrimp, true, true, 20),
                new Meal(recipePeanutChicken, true, true, 23),
                new Meal(recipeOmeletteAuFromage, true, true, 13)};
        mealRepository.insert(meals).get();
        return this;
    }

    public Restaurant insertMealsFromBeginnerTutorial() throws IOException {
        Recipe  recipeOmelette            = Recipe.find(nOmelette);
        Recipe  recipePancakes            = Recipe.find(nPancakes);
        final Meal[] meals = {
                new Meal(recipeOmelette           , true, false, 12),
                new Meal(recipePancakes           , true, false, 12)
                };
        mealRepository.insert(meals);
        return this;
    }

    public Restaurant printMeals() throws InterruptedException,
            ExecutionException {
        for (final Meal meal : mealRepository.findAll().get())
            info(meal.getName());
        return this;
    }

    public Restaurant printBeverages() throws InterruptedException,
            ExecutionException {
        for (final Beverage beverage : beverageRepository.findAll().get())
            info(beverage.getName());
        return this;
    }

    public Restaurant print() throws InterruptedException, ExecutionException {
        printBeverages();
        printMeals();
        return this;
    }

    public Restaurant insertSomeBeverages() throws InterruptedException,
            ExecutionException {
        final Beverage[] beverages = {
                new Beverage(nBeer, true, 13),
                new Beverage(nJuice, true, 11),
                new Beverage(nWater, true, 12)};
        beverageRepository.insert(Arrays.asList(beverages)).get();
        return this;
    }

    public Restaurant cleanDB() throws Exception {
        mealRepository.delete(Meal.findAll()).get();
        recipeRepository.delete(Recipe.findAll()).get();
        beverageRepository.delete(Beverage.findAll()).get();
        return this;
    }

    public List<Meal> getMeals() throws Exception {
        return mealRepository.findAll().get();
    }

    public static void info(final String arg) {
        System.out.println(arg);
    }

    public static void info(final String[] args) {
        for (String arg : args) info("  " + arg);
    }
}
