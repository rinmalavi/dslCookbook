##Java basic tutorial

In this tutorial we will show how to write simple model definition and use code generated from it.
We will show some basic concepts, how to use them, and how to run operations on them.

Lets say we want to make a cookbook that will hold some information about cooking recipes.

    module Cookbook
    {
        root Recipe(name)
        {
          String    name;
          String[]  ingredients;
        }
    }

This way we defined a schema holding a single entity defined by its unique name and a list of ingredients.
Writing name like this, in braces, next to root name, tells the compiler we want that property to be a primary key.
We could have wrote:

    module Cookbook
    {
        root Recipe
        {
            String    name { unique;}
            String[]  ingredients;
        }
    }

This way Recipe would get surrogate sequential ID and "unique" keyword would
ensure that it is unique to this root.
Lets create a recipe for "Pancakes":

    Recipe pancake = new Recipe()
    // and give it a name:
        .setName("Pancakes")
    // then set what we need to make pancakes:
        .setIngredients({
            "110g plain flour",
            "pinch of salt",
            "2 eggs",
            "200ml  milk mixed with 75ml oz water",
            "50g butter"
        });

Having set how we define it, we could persist it:

    pancake.persist();

Lets add another:

    Recipe omlette = new Recipe()
        .setName("Omelette Au Fromage!")
        .setIngredients({
            "2 eggs",
            "pinch of salt",
            "pinch of black papper",
            "3 dg parmesan cheese, grated",
            "3 dg gruyere cheese, grated",
            "3 cl butter"
        })
        .persist();

Lets say, for simplicity, info is:

    public static void info(final String arg) {
        System.out.println(arg);
    }
    public static void info(final String [] args) {
        for(String arg: args) info("  " + arg);
    }

Now we can see our persisted recipes by
iterating over result of findAll() helper function:

    for(Recipe recipe: Recipe.findAll())
        info(recipe.getName());

Would produce output:

    Pancakes
    Omelette Au Fromage!


##Locator

To start communicating with model you need to initialize a locator:

    ServiceLocator locator = Bootstrap.init(<path to your project.ini>);

We'll use locator to initialize dependencies, so we can resolve repositories, each root has one of its own.

project.ini may look something like this:

    username=user@domain.org
    project-id=653ed6fe-5875-41f7-a871-05b50d04b9b6
    api-url=https://node0.dsl-platform.com/alpha_faa884fe404531be2aeh73/
    package-name=com.dslplatform.examples.beginner

##Repositories

Using persist function blocks your program till API-server communicating with
your database responds with its uri. To avoid blocking calls use repositories.
To get an instance Recipes repository:

    RecipeRepository recipeRepository = locator.resolve(RecipeRepository.class);

Lets define:

    public List<Recipe> aListOfRecipes() {
      List<Recipe> lr = new ArrayList<Recipe>();
      lr.add(new Recipe()
        .setName("Peppered Shrimp")
        .setIngredients(new String[]{
            "1/2 pound portobello mushrooms, diced",
            "12 ounces penne pasta",
            "1/4 cup butter",
            "2 tablespoons extra-virgin olive oil",
            "1 onion, diced",
            "2 cloves garlic, minced",
            "1 pound medium shrimp, peeled and deveined",
            "1/2 cup cream",
            "salt",
            "papper"
        }));
      lr.add(new Recipe()
      .setName("Peanut Chicken!")
      .setIngredients(new String[]{
          "peanut oil",
          "boneless chicken breast halves",
          "1 clove garlic, minced",
          "cayenne pepper",
          "1 fresh ginger root",
          "2 cups chopped dry roasted peanuts"
      }));
      lr.add(new Recipe()
      .setName("Omelette!")
      .setIngredients(new String[]{
          "2 eggs",
          "cooking cream",
          "3 cl butter"
      }));
      return lr;
    }

To insert this list call insert function:

    Future<List<String>> urisInFuture = recipeRepository.insert(kitchen.aListOfSomeRecipes());

This function returns a Future&lt;String&gt; or a Future&lt;List&lt;String&gt;&gt; depending on what you are inserting.

If you have never worked with futures here is a brief explanation:
Future is a container around some object that might exist in future.
Calculation of a future object is done in a separate thread,
so your program doesn't block and carries on doing other tasks till
the product of this calculation is called upon.
If you wish to access information Future might have or will have, just get it:

    List<String> uris = urisInFuture.get();

This will assign uri values to variable uris, here defined this way, if they exist,
or halt program execution till this values do exist,
or throw an exception if there was a problem with this api call.

This list now holds URIs uniquely representing Recipes:
If you print them:

    info(uris);

Result is:

    Omelette!
    Peanut Chicken!
    Peppered Shrimp
    The old Chevaps
    The old Pancake!

Usually its some random strings, but since our primary key is the name of recipe
they are the same.

###update

Lets say we are sugar fanatic and add sugar to everything, so lets add them to all recipes we got so far.
For simplicity lets define:

      public static String[] addIfNotThere(
          final String [] ingredients,
          final String ... args) {
        if (args.length == 1){
          final String arg = args[0];
          for (final String ingredient: ingredients)
            if( ingredient.contains(arg)) return ingredients;
          final String[] newArray = (String [])Arrays.copyOf(ingredients, ingredients.length + 1);
          newArray[ingredients.length] = arg;
          return newArray;
        }
        else
          return addIfNotThere(
              addIfNotThere(
                  ingredients,
                  args[0]
                  ),
              (String[]) Arrays.copyOfRange(ingredients, 1, ingredients.length)
            );
      }

Add sugar to every recipe:

    for (Recipe recipe : recipeRepository.findAll().get()) {
      recipe.setIngredients(addIfNotThere(recipe.getIngredients(), "sugar"));
    }

    recipeRepository.update(listOfRecipes);


##Specifications

Lets add functionality telling us which recipe is "ye olde":

    specification isTheOld 'it => it.name.ToLower().StartsWith("ye olde")';

Now we can use it this way:

    for (Recipe recipe: new isTheOld().search()){
        info( recipe.getName());

Which will naturally produce nothing.
Lets insert more recipes:

    public Recipe [] anArrayOfYeOldeis() {
      final String [] chevapsIng = {
          "500 g grained meat",
          "1/2 clove garlic",
          "1/4 dl Mineral water",
          "salt",
          "papper"
      };

      final String [] pancakeIng = {"2 eggs", "flour", "milk" };
      final String [] peanutChickenIng = {
        "peanut oil",
        "boneless chicken breast halves",
        "1 clove garlic, minced",
        "cayenne pepper",
        "1 fresh ginger root",
        "2 cups chopped dry roasted peanuts"
      };

      final Recipe [] lr = { new Recipe()
      .setName("ye olde Chevaps")
      .setIngredients(chevapsIng),
      new Recipe()
      .setName("ye olde Pancakes")
      .setIngredients(chevapsIng),
      new Recipe()
      .setName("ye olde Peanut Chicken")
      .setIngredients(chevapsIng),
      };

      return lr;
    }

Insert it:

    recipeRepository.insert(anArrayOfYeOldies).get(); // get to wait till it finished!
    for (Recipe recipe : new isTheOld().search()){
        info(recipe.getName());

Output:

    ye olde Chevaps
    ye olde Pancakes
    ye olde Peanut Chicken

Lets say we want to be able to search all recipes containing flour.
Add following to the recipe scope:

In dsl:

    specification hasFlour 'it => it.ingredients.Any(
        ingredient => ingredient.ToLower().Contains("flour"))';

In Java:

    for (Recipe recipe : new hasFlour().search()){
        info(recipe.getName());

Would produce:

    Pancakes with nuttela
    ye olde Pancakes

Having people allergic to peanuts you might want to search meals not containing those:

In dsl:

    specification hasNoPeanuts 'it => ! it.ingredients.Any(
      ingredient => ingredient.ToLower().Contains("peanut"))';

In Java:

    for (Recipe recipe : new hasNoPeanuts().search()){
        info(recipe.getName());

Recipes containing no mention of word peanut are:

    Omelette Au Fromage!
    Omelette!
    Pancakes with nuttela
    Peppered Shrimp
    ye olde Chevaps
    ye olde Pancakes


This might be too much results for one page, so you will set the offset, and limit:

    for (Recipe recipe : new hasNoPeanuts().search(2, 0)){
        info(recipe.getName());

    Omelette Au Fromage!
    Omelette!

    for (Recipe recipe : new hasNoPeanuts().search(2, 2)){
        info(recipe.getName());

    Pancakes with nuttela
    Peppered Shrimp

To be able to search over ingredients searching for custom ingredient add following to our root:

    specification hasIngredient
        'it => it.ingredients.Any(ingredient => ingredient.ToLower().Contains(what))'
        {
            String what;
        }

This means we now have a class where we can set "what" property as our query and call search with it.

    final Specification hi = new Recipe.hasIngredient().setWhat("egg");

So far we searched blocking our program lets do this right.
That is by passing a specification to a repository instance as an argument of search method.

    final Future<List<Recipe>> futureResult = recipeRepository.search(hi);

It will instantly return Future&lt;List&lt;Recipe&gt;&gt;. So later we can:

    for (final Recipe recipeWithWhat : futureResult.get()) {
        info("    " + recipeWithWhat.getName());
      }

    Omelette Au Fromage!
    Omelette!
    Pancakes with nuttela
    ye olde Pancakes

Like with the blocking search you can set the limit and the offset.

    final Future<List<Recipe>> futureResult = recipeRepository.search(hi, 2, 2);

It will instantly return Future&lt;List&lt;Recipe&gt;&gt;. So later we can:

    for (final Recipe recipeWithWhat : futureResult.get()) {
        info("    " + recipeWithWhat.getName());
      }

    Omelette Au Fromage!
    Omelette!
    Pancakes with nuttela
    ye olde Pancakes
