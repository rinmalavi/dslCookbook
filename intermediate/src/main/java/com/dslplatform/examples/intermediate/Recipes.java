package com.dslplatform.examples.intermediate;

import com.dslplatform.examples.intermediate.Cookbook.Ingredient;
import com.dslplatform.examples.intermediate.Cookbook.Recipe;

/** This class holds test data so the real logic is cleaner */
public class Recipes {

    public final static Ingredient[] ingChevaps = {i("grained meat", 500),
            i("clove garlic", 1), i("Mineral water", 250), i("salt", 1),
            i("papper", 1)};
    public final static Ingredient[] ingPancakes = {i("eggs", 1),
            i("flour", 1), i("milk", 1)};
    public final static Ingredient[] ingPeanutChicken = {i("peanut oil", 1),
            i("boneless chicken breast halves"), i("clove garlic, minced"),
            i("cayenne pepper"), i("1 fresh ginger root"),
            i("2 cups chopped dry roasted peanuts")};
    public final static Ingredient[] ingPepperedShrimp = {
            i("pound portobello mushrooms"), i("penne pasta", 12),
            i("cup butter", 0.25), i(" tablespoons extra-virgin olive oil"),
            i("1 onion, diced"), i("2 cloves garlic, minced"),
            i("1 pound medium shrimp, peeled and deveined"),
            i("1/2 cup cream"), i("salt"), i("papper")};
    public final static Ingredient[] ingOmelette = {i("eggs", 2),
            i("cooking cream"), i("butter", 3, "cl")};
    public final static Ingredient[] ingOmeletteAuFromage = {i("egg", 3),
            i("pinch of salt"), i("pinch of black papper"),
            i("parmesan cheese, grated", 3, "dg"),
            i("gruyere cheese, grated", 3, "dg"), i("butter", 3, "cl")};
    public final static Ingredient[] ingPancakesWithNuttela = {
            i("plain flour", 100, "g"), i("pinch of salt"), i("eggs"),
            i("milk mixed water"), i("butter", 50, "g"), i("nuttela")};
    public final static Ingredient[] ingMeatloaf = {
            i("ground beef", 1.5, "pounds"), i("egg", 1), i("onion", 1),
            i("salt"), i("paper"), i("dried bread crumbs", 1, "cup")};
    public final static Ingredient[] ingLasagna = {
            i("lean ground beef", 1, "pound"),
            i("spaghetti sauce", 32, "ounce"),
            i("cottage cheese", 32, "ounces"),
            i("shredded mozzarella cheese", 3, "cups"), i("eggs", 1),
            i("grated Parmesan cheese", 0.5, "cups"), i("salt"),
            i("ground black pepper"), i("lasagna noodles", 9)};

    public final static String nPepperedShrimp = "Peppered Shrimp";
    public final static String nPeanutChicken = "Peanut Chicken";
    public final static String nOmelette = "Omelette";
    public final static String nOmeletteAuFromage = "Omelette Au Fromage!";
    public final static String nPancakes = "Pancakes";
    public final static String nPancakesWithNuttela = "Pancakes with nuttela";
    public final static String nMeatloaf = "Meatloaf";
    public final static String nLasagna = "Lasagna";

    public final static Recipe rPepperedShrimp = new Recipe(nPepperedShrimp,
            ingPepperedShrimp);
    public final static Recipe rPeanutChicken = new Recipe(nPeanutChicken,
            ingPeanutChicken);
    public final static Recipe rOmelette = new Recipe(nOmelette, ingOmelette);
    public final static Recipe rOmeletteAuFromage = new Recipe(
            nOmeletteAuFromage, ingOmeletteAuFromage);
    public final static Recipe rPancakes = new Recipe(nPancakes, ingPancakes);
    public final static Recipe rMeatloaf = new Recipe(nMeatloaf, ingMeatloaf);
    public final static Recipe rPancakesWithNuttela = new Recipe(
            nPancakesWithNuttela, ingPancakesWithNuttela);
    public final static Recipe rLasagne= new Recipe(nLasagna, ingLasagna);

    public static final Recipe[] chefsChoice = {rPepperedShrimp,
            rPeanutChicken, rOmeletteAuFromage};
    public static final Recipe[] allRecipes = {rPepperedShrimp, rPeanutChicken,
            rOmeletteAuFromage, rOmelette, rPancakes, rPancakesWithNuttela,
            rMeatloaf, rLasagne};

    public static final Recipe[] yeOldeRecipes = {
            new Recipe("ye olde Chevaps", ingChevaps),
            new Recipe("ye olde Pancakes", ingPancakes),
            new Recipe("ye olde Peanut Chicken", ingPeanutChicken),};

    private static Ingredient i(final String name, final double amount,
            final String unit) {
        return new Ingredient(name, amount, unit);
    }

    private static Ingredient i(final String name, final double amount) {
        return i(name, amount, "any");
    }

    private static Ingredient i(final String name) {
        return i(name, 1);
    }

    // Beverages
    // ---------

    final public static String nWater = "Water";
    final public static String nBeer = "Beer";
    final public static String nJuice = "Juice";
    final public static String[] beverageNames = {nBeer, nJuice, nWater};
}
