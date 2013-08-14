package com.dslplatform.examples.beginner;

import com.dslplatform.examples.beginner.Cookbook.Recipe;

public class Recipes {

    final static String [] ingChevaps = {"500 g grained meat", "1/2 clove garlic", "1/4 dl Mineral water", "salt", "papper" };
    final static String [] ingPancakes = {"2 eggs", "flour", "milk" };
    final static String [] ingPeanutChicken = {"peanut oil", "boneless chicken breast halves", "1 clove garlic, minced", "cayenne pepper", "1 fresh ginger root", "2 cups chopped dry roasted peanuts" };
    final static String [] ingPepperedShrimp = {"1/2 pound portobello mushrooms, diced", "12 ounces penne pasta", "1/4 cup butter", "2 tablespoons extra-virgin olive oil", "1 onion, diced", "2 cloves garlic, minced", "1 pound medium shrimp, peeled and deveined", "1/2 cup cream", "salt", "papper" };
    final static String [] ingOmelette = {"2 eggs", "cooking cream", "3 cl butter" };
    final static String [] ingOmeletteAuFromage = {"2 eggs", "pinch of salt", "pinch of black papper", "3 dg parmesan cheese, grated", "3 dg gruyere cheese, grated", "3 cl butter" };
    final static String [] ingPancakesWithNuttela = {"110g plain flour", "pinch of salt", "2 eggs", "200ml oz milk mixed with 75ml oz water", "50g/2oz butter", "nuttela"};

    public final static String nPepperedShrimp = "Peppered Shrimp";
    public final static String nPeanutChicken = "Peanut Chicken";
    public final static String nOmelette = "Omelette";
    public final static String nOmeletteAuFromage = "Omelette Au Fromage!";
    public final static String nPancakes = "Pancakes";
    public final static String nPancakesWithNuttela = "Pancakes with nuttela";

    public final static Recipe rPepperedShrimp = new Recipe(nPepperedShrimp, ingPepperedShrimp);
    public final static Recipe rPeanutChicken = new Recipe(nPeanutChicken, ingPeanutChicken);
    public final static Recipe rOmelette = new Recipe(nOmelette, ingOmelette);
    public final static Recipe rOmeletteAuFromage = new Recipe(nOmeletteAuFromage, ingOmeletteAuFromage);
    public final static Recipe rPancakes = new Recipe(nPancakes, ingPancakes);
    public final static Recipe rPancakesWithNuttela = new Recipe(nPancakesWithNuttela, ingPancakesWithNuttela);

    public static final Recipe [] chefsChoice = { rPepperedShrimp, rPeanutChicken, rOmeletteAuFromage };

    public static final Recipe [] yeOldeRecipes = {
        new Recipe("ye olde Chevaps", ingChevaps),
        new Recipe("ye olde Pancakes", ingPancakes),
        new Recipe("ye olde Peanut Chicken", ingPeanutChicken),
    };
}
