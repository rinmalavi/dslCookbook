package com.dslplatform.examples.intermediate.test;

import static org.junit.Assert.*;
import static com.dslplatform.examples.intermediate.Recipes.*;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.*;

import com.dslplatform.examples.intermediate.Consumables.*;
import com.dslplatform.examples.intermediate.Cookbook.Ingredient;
import com.dslplatform.examples.intermediate.test.setup.OpenRestaurantInTest;

public class Reporting extends OpenRestaurantInTest {
    private static final String [] expectedCheffMealNames = {nPepperedShrimp, 
            nPeanutChicken, nOmeletteAuFromage};

    private static final String [] expectedAllMealNames = {nPepperedShrimp,
            nPeanutChicken, nOmeletteAuFromage, nOmelette, nPancakes,
            nPancakesWithNuttela};

    /* Shows how to use report to query data 
     *   report BasicPriceList
     *   {
     *     Beverage [] beverages 'it => it.available';
     *     MealInfo [] meals 'it => it.available';
     *    }
     * */
    @Test
    public void reportBasicPriceList() throws Exception {
        restaurant.cleanDB().insertAllMealsAndRecipes().insertSomeBeverages();

        BasicPriceList bpl = new BasicPriceList();
        bpl.populate();

        final Set<String> setRemoteMealNames = new HashSet<String>();
        for (final MealInfo mi : bpl.getMeals())
            setRemoteMealNames.add(mi.getName());

        final Set<String> setExpectedMealsNames = new HashSet<String>(
                Arrays.asList(expectedAllMealNames));

        assertEquals(setRemoteMealNames, setExpectedMealsNames);

        final Set<String> setExpectedBeverages = toSet(beverageNames);
        final Set<String> setRemoteBeverages = new HashSet<String>();
        for (final Beverage beverage : bpl.getBeverages())
            setRemoteBeverages.add(beverage.getName());

        assertEquals(setRemoteBeverages, setExpectedBeverages);
    }

    /* Shows how to use report to query data 
     *   report CheffsPriceList
     *   {
     *     Beverage [] beverages 'it => it.available';
     *     MealInfo [] chefsMeals 'it => it.cheffsMeal & it.available';
     *     MealInfo [] meals 'it => it.available';
     *    }
     * */

    @Test
    public void reportCheffsPriceList() throws Exception {
        restaurant.cleanDB().insertAllMealsAndRecipes().insertSomeBeverages();

        PriceListWithCheffsMeals bpl = new PriceListWithCheffsMeals();
        bpl.populate();

        final Set<String> setRemoteMealNames = new HashSet<String>();
        for (final MealInfo mi : bpl.getChefsMeals())
            setRemoteMealNames.add(mi.getName());

        final Set<String> setExpectedMealNames = new HashSet<String>(
                Arrays.asList(expectedCheffMealNames));

        assertEquals(setRemoteMealNames, setExpectedMealNames);
    }

    /*
     * Shows how to use report concept to populate templates with data 
     * report SimplePriceList { 
     *     Beverage [] beverages 'it => it.available'; 
     *     MealInfo [] meals 'it => it.available';
     * 
     *     templater createPriceList 'SimplePriceList.txt'; 
     * }
     * 
     * template: SimplePriceList.txt
     * 
     * Meal Price
     * ------------------------------------------------------------------
     * [[meals.name]:padRight(43)] [[meals.price]:padLeft(7)]
     * ------------------------------------------------------------------
     * 
     * ==================================================================
     * 
     * Beverage Price
     * ------------------------------------------------------------------
     * [[beverages.name]:padRight(43)] [[beverages.price]:padLeft(7)]
     * ------------------------------------------------------------------
     */
    @Test
    public void simpleReport() throws Exception {
        restaurant.cleanDB().insertAllMealsAndRecipes().insertSomeBeverages();

        final SimplePriceList dpl = new SimplePriceList().populate();
        final String report = new String(dpl.createPriceList(), "UTF8");

        for (final String mealname : expectedAllMealNames) {
            assertTrue(report.contains(mealname));
        }

        for (final String b : beverageNames) {
            assertTrue(report.contains(b));
        }
    }

    /*
     * Shows how to place String [] inside template 
     * report PriceListWithIngredients { 
     *     Beverage [] beverages 'it => it.available';
     *     MealInfo [] meals 'it => it.available';
     * 
     *     templater createPriceList 'PriceListWithIngredients.txt'; 
     * }
     * 
     * template: PriceListWithIngredients.txt 
     * Meal Price
     * ------------------------------------------------------------------
     * [[meals.name]:padRight(33)] ([[meals.ingredientNames]:join(,)])
     * [[meals.price]:padLeft(7)] [[meals.name]:padRight(33)]
     * ([[meals.ingredientNames]:join(,)]) [[meals.price]:padLeft(7)]
     * ------------------------------------------------------------------ ...
     */
    @Test
    public void reportWithStringArrayProp() throws Exception {
        restaurant.cleanDB().insertCheffsRecipesAndMeals().insertSomeBeverages();
        final PriceListWithIngredients dpl = new PriceListWithIngredients()
                .populate();

        final String report = new String(dpl.createPriceList(), "UTF8");

        final String[] expectedMealNames = {nPepperedShrimp, nPeanutChicken,
                nOmeletteAuFromage};

        for (final String mealname : expectedMealNames) {
            assertTrue(report.contains(mealname));
        }

        final Ingredient[][] ings = {ingPepperedShrimp, ingPeanutChicken,
                ingOmeletteAuFromage};

        final Set<Ingredient> setOfIngredients = toSet(ings);

        for (final Ingredient ingredient : setOfIngredients) {
            assertTrue(report.contains(ingredient.getName()));
        }

        for (final String b : beverageNames) {
            assertTrue(report.contains(b));
        }
    }

    /*
     * Using calculation to get suitable report structure. 
     * report CheffsMeals {
     *     MealInfo [] cheffsMeals 'it => it.cheffsMeal & it.available'; calculated
     *     String [] cheffsMealsNames from 'it => it.cheffsMeals.Select(meal => meal.name).ToArray()'; 
     * }
     */
    /*
    @Test
    public void reportWithCalculaton() throws Exception {
        restaurant.cleanDB().insertCheffsRecipes().insertCheffsMeal()
                .insertSomeBeverages();
        final PriceListWithChefsMeals dpl = new PriceListWithChefsMeals()
                .populate();

        final String[] expectedMealNames = {nPepperedShrimp, nPeanutChicken,
                nOmeletteAuFromage};

        final Set<String> setExpectedMealsNames = new HashSet<String>(
                Arrays.asList(expectedMealNames));
        final Set<String> setRemoteMealsNames = toSet(dpl.getCheffsMealsNames());

        assertEquals(dpl.getCheffsMealsNames(), setExpectedMealsNames);
    }
*/
    @Test
    public void reportWithChefsMeals() throws Exception {
        restaurant.cleanDB().insertAllMealsAndRecipes().insertSomeBeverages();

        final PriceListWithCheffsMeals dpl = 
                new PriceListWithCheffsMeals().populate();

        final String report = new String(dpl.createPriceList(), "UTF8");
        
        final String firstPart = report.split(":)")[0];
        
        final Set<String> setCheffsMealsNames = new HashSet<String>(
                Arrays.asList(expectedCheffMealNames));

        /* check if cheffs meals are in first part of report seperated with ':)'
         * also check that other meals are not.
         */
        for (final String mealname : expectedAllMealNames) {
            assertTrue(
                    firstPart.contains(mealname) 
                        ? setCheffsMealsNames.contains(mealname) 
                        : ! setCheffsMealsNames.contains(mealname)
                    );
        }

        for (final String b : beverageNames) {
            assertTrue(report.contains(b));
        }
    }
}
