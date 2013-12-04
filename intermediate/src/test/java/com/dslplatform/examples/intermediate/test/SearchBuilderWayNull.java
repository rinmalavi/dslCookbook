package com.dslplatform.examples.intermediate.test;

import static org.junit.Assert.*;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.junit.*;
import com.dslplatform.examples.intermediate.Consumables.*;
import com.dslplatform.examples.intermediate.test.setup.OpenRestaurantInTest;
import com.dslplatform.patterns.SearchBuilder;

public class SearchBuilderWayNull extends OpenRestaurantInTest {

    /** How to insert, get all meals and access properties */
    @Test
    public void searchRepository() throws Exception {
        restaurant
        .cleanDB()
        .insertSomeBeverages()
        .insertAllMealsAndRecipes();


        SearchBuilder<MealInfo> sb = mealInfoRepository.builder().with(null).limit(20).offset(0).ascending("name");

        for (final MealInfo mealInfos : sb.search().get()) {
            System.out.println(mealInfos.getName());
        }
    }
}
