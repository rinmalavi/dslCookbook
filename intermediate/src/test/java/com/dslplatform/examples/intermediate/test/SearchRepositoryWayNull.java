package com.dslplatform.examples.intermediate.test;

import static org.junit.Assert.*;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.*;
import com.dslplatform.examples.intermediate.Consumables.*;
import com.dslplatform.examples.intermediate.test.setup.OpenRestaurantInTest;

public class SearchRepositoryWayNull extends OpenRestaurantInTest {

    /** How to insert, get all meals and access properties */
    @Test
    public void searchRepository() throws Exception {
        restaurant
        .cleanDB()
        .insertSomeBeverages()
        .insertAllMealsAndRecipes();

    List<Map.Entry<String, Boolean>> ordering = new LinkedList<Map.Entry<String, Boolean>>();
    ordering.add(new AbstractMap.SimpleEntry<String, Boolean>("name", false));

    final List<MealInfo> orderedRecipes =
            mealInfoRepository.search(null).get();//findAll(0, 1000, ordering).get();

    for( MealInfo r : orderedRecipes )
        System.out.println(r.getName());
    }

}
