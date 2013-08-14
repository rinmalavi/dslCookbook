package com.dslplatform.examples.beginner;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import java.util.concurrent.Future;

import com.dslplatform.client.Bootstrap;
import com.dslplatform.examples.beginner.Cookbook.Recipe;
import com.dslplatform.examples.beginner.Cookbook.repositories.RecipeRepository;
import com.dslplatform.patterns.ServiceLocator;
import com.dslplatform.patterns.Specification;
import static com.dslplatform.examples.beginner.Recipes.*;

public class CookingSession {

    public static void main(final String [] args) throws Exception {
        final String pathnameRaw = System.getProperty("user.home") + "/.config/dslcookbook/beginner.ini";
        final String pathname = pathnameRaw;
        final ServiceLocator locator = Bootstrap.init(pathname);
        final CookingSession cookingSession = new CookingSession(locator);
        cookingSession.cook();
    }

    private final RecipeRepository recipeRepository;

    public CookingSession(final ServiceLocator locator) throws IOException{
        recipeRepository = locator.resolve(RecipeRepository.class);
    }

    public void cook() throws Exception {

        recipeRepository.delete(Recipe.findAll()).get();

        final List<String> chfsUris = recipeRepository.insert(chefsChoice).get();
        recipeRepository.insert(yeOldeRecipes).get();

        final List<Recipe> listOfRecipes = recipeRepository.findAll().get();

        //update with sugar
        for( Recipe recipe: recipeRepository.findAll().get())
            recipe.setIngredients(
                addIfNotThere(recipe.getIngredients(), "sugar"));
        recipeRepository.update(listOfRecipes).get();

        info("Ye Olde");
        for( final Recipe recipeIsYeOld: new Recipe.isTheOld().search())
          info("    " + recipeIsYeOld.getURI());

        info("Flour");
        for( final Recipe recipeWithFlour: new Recipe.hasFlour().search())
            info("    " + recipeWithFlour.getURI());

        info("No peanuts:");
        for( final Recipe recipeWithNoPeanuts: new Recipe.hasNoPeanuts().search())
            info("    " + recipeWithNoPeanuts.getURI());

        final Specification<Recipe> hi = new Recipe.hasIngredient("egg");
        final Future<List<Recipe>> futureResult = recipeRepository.search(hi, 2, 2);

        recipeRepository.find(chfsUris.get(0));
        for( Recipe recipeWithWhat: futureResult.get()) {
            info("    " + recipeWithWhat.getName());
            //info(recipeWithWhat.getIngredients());
        }
    }

    private static String[] addIfNotThere(final String [] ingredients, final String ... args) {
        if (args.length == 1){
            final String arg = args[0];
            for( final String ingredient: ingredients)
              if( ingredient.contains(arg)) return ingredients;
            final String[] newArray = (String [] )Arrays.copyOf(ingredients, ingredients.length + 1);
            newArray[ingredients.length] = arg;
            return newArray;
        }
        else
            return addIfNotThere(
                        addIfNotThere( ingredients, args[0]),
                        (String[]) Arrays.copyOfRange(args, 1, args.length)
                    );
    }

    public static void info(final String arg) { System.out.println(arg); }

    public static void info(final String [] args) {
        for(String arg : args)
            info("  " + arg);
    }
}
