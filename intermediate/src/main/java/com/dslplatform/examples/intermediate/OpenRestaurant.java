package com.dslplatform.examples.intermediate;

import java.util.*;

import com.dslplatform.client.Bootstrap;
import com.dslplatform.examples.intermediate.Consumables.*;
import com.dslplatform.patterns.SearchBuilder;
import com.dslplatform.patterns.ServiceLocator;

public class OpenRestaurant {

    public final static void main( final String [] args) throws Exception {

        // ---------------------------------------------------------------------
        final String pathnameRaw = System.getProperty("user.home") + "/.config/dslcookbook/intermediate.ini";
        final String pathname = pathnameRaw.replace("/", System.getProperty("file.separator"));

        final ServiceLocator locator = Bootstrap.init(pathname);

        final Restaurant restaurant = new Restaurant(locator);
        // ---------------------------------------------------------------------

        restaurant
            .cleanDB()
            .insertSomeBeverages()
            .insertAllMealsAndRecipes();


    }
}
