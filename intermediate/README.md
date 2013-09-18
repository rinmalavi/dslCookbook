##Java Intermediate Tutorial

In the beginners tutorial we saw how to declare a root
and perform some basic operations on it.

Now we got good at cooking we want to open a restaurant.
We could use an application that would track what are the meals we serve there,
what beverages and how much it all costs.
Lets describe it in the dsl:

    module Menu
    {
      root Meal(name)
      {
        String                name;
        boolean               available;

        int                   price;
      }

      root Beverage
      {
        String    name;
        boolean   available;

        int       price;
      }
    }

We will probably hire some new staff, and they will have to know how to cook
our meals. So dsl from when we were cooking just for ourselves is still useful.

    module Cookbook
    {
      root Recipe(name)
      {
        String    name;
        String[]  ingredients;

        specification hasIngredient 'it => it.ingredients.Any(
          ingredient => ingredient.ToLower().Contains(what))'
        {
          String what;
        }
      }
    }

We see here that ``Meal`` and ``Recipe`` have a strong connection between them.
Lets modify our dsl to describe it:

    root Meal(name)
    {
      Cookbook.Recipe(name)* recipe;
      String                 name;
      boolean                available;

      int                    price;
    }

This way we expressed that ``Meal`` holds a reference to ``Recipe`` and that field ``name``,
which is primary key in Recipe, is a field in Meal, which now we can use as a
primary field in Meal as well.

Using primary keys we can get our recipes from the remote server represented as
an instance of java class.

    Recipe recipeForPapperedShrimp = Recipe.find("Peppered Shrimp");
    Recipe recipePeanutChicken = Recipe.find("Peanut Chicken");
    Recipe recipeOmeletteAuFromage = Recipe.find("Omelette Au Fromage");

And add them to the Menu:

    final Meal [] meals = {
        new Meal(recipeForPapperedShrimp, true, 20),
        new Meal(recipePeanutChicken, true, 23),
        new Meal(recipeOmeletteAuFromage, true, 13)
    };
    mealRepository.insert(meals);

Some beverages to go with it:

    final Beverage [] beverages = {
        new Beverage("beer", true, 13),
        new Beverage("juice", true, 11),
        new Beverage("water", true, 12)
    };

    beverageRepository.insert(Arrays.asList(beverages));

    for (final Meal meal : mealRepository.findAll().get()) info(meal.getName());
    for (final Beverage beverage : beverageRepository.findAll().get()) info(beverage.getName());

    Omelette Au Fromage!
    Peanut Chicken
    Peppered Shrimp
    Beer
    Juice
    Water

Now we have something to offer.

##Snowflake
Lets make our data a bit presentable. Meal as it is now is an aggregate of more
than one data source lets make this data presentable

    snowflake MealInfo from Meal
    {
      name;
      available;
      price;
      recipe.ingreadients;
    }

<code>from</code> as a keyword here is optional.

Snowflake concept resolves as a java class when we commit the dsl.
It serves as a view into and trough some data structure.
It has a repository pattern implementation.

    mealInfoRepository = locator.resolve(MealInfoRepository.class);

Lets remind ourselves how to write a specification, which well use soon to build
a search:

    specification priceyMeals 'it => it.price >= 77';

    specification budgetMeals 'it => it.price < budget'
    {
      int budget;
    }

##Building a search

To get an instance of ``SearchBuilder`` simply call it from repository you wish to search over.

    SearchBuilder<MealInfo> searchBuilder = mealInfoRepository.builder();

which is shorthand form for

    SearchBuilder<MealInfo> searchBuilder = new SearchBuilder<MealInfo>(mealInfoRepository);

To define search parameters SearchBuilder has following methods:

    with(Specification<T> specification);

To define a search predicate. Alias ``filter``. T in this case is MealInfo.

    limit(int limit)

To define a maximum number of results. Alias ``take``.

    offset(in offset)

To define a number of results to be skipped. Alias ``skip``

    orderBy(String property, boolean ascending)

To define an ordering of search results.
Also:

    descending(String property)
    ascending(String property)

All of this methods return ``this`` which allows easy chaining
To call run this search, call ``search()`` method which returns ``Future<List<T>>``

example:

    List<MealInfo> budgetMeals =
        mealInfoRepository
          .builder()
          .with(new MealInfo.budgetMeals(22))
          .limit(20)
          .offset(0)
          .ascending("name")
          .search().get();

print their names:

    Lasagna
    Meatloaf
    Omelette
    Omelette Au Fromage!
    Pancakes
    Pancakes with nuttela

None of parameters are mandatory, they can be left out.
If all parameters are left out calling search over that builder is equivalent to ``findAll()``.


----------------------------

This is how we might want to present our data to the world, its all the information
on a single meal that we might want to, lets say, print on a price list.
So lets make a simple price list.

##Report

We want to pull all informations relevant to some presentation layer.
Namely this are all meals and beverages, their names and prices.
Later maybe some ingredients, date of reporting...
For this we will use ``report`` concept.

    module Menu
    {
      ...
      report BasicPriceList
      {
        MealInfo [] meals     'it => it.available';
        Beverage [] beverages 'it => it.available';
      }
    }

After committing our changes new files are added to the generated code.
Amongst them is the ``BasicPriceList`` that exposes methods ``getMeals`` and ``getBeverages``

Use it like this:

    BasicPriceList basicpricelist = new BasicPriceList();
    basicpricelist.populate();

    for(final MealInfo mi : basicpricelist.getMeals() ) System.out.println( mi.getName() + " "+ mi.getPrice());
    for(final Beverage b : basicpricelist.getBeverages()) System.out.println( b.getName()+ " "+ mi.getPrice());

    Peppered Shrimp 20
    Peanut Chicken 23
    Omelette Au Fromage! 13
    Beer 13
    Jucie 11
    Water 12

### Templater
We could output this data to ``txt`` file.
This file could look like this:

    Meal      Price
    ------------------------------------------------------------------
    [[meals.name]:padRight(20)] [[meals.price]:padLeft(7)]
    ------------------------------------------------------------------

    ==================================================================

    Beverage      Price
    ------------------------------------------------------------------
    [[beverages.name]:padRight(20)] [[beverages.price]:padLeft(7)]
    ------------------------------------------------------------------

Basic usage is to put field name in brackets(``[[]]``) and let Templater
worry about the rest.

More on how to write tempates on [templater](http://templater.info).

To include template in project go to your project [panel](https://dsl-platform.com/),
expand ``Uploads`` tab, click on ``Upload a file``,
then navigate to local copy of your template.

To use this file in a reports output add

    module Menu
    {
      ...
      report SimplePriceList
      {
        Beverage [] beverages 'it => it.available';
        MealInfo [] meals 'it => it.available';

        templater createPriceList 'SimplePriceList.txt';
      }
    }

This will, in effect, add the function createPriceList to generated ``class SimplePriceList`` that
tells platform to use ``SimplePriceList.txt`` to represent our data.

    SimplePriceList simplepricelist = new SimplePriceList();
    simplepricelist.populate();

    byte[] report = simplepricelist.CreatePriceList()

This ``byte[]`` holds our template filled with your SimplePriceList:

    Meal      Price
    ------------------------------------------------------------------
    Peppered Shrimp           20
    Peanut Chicken            23
    Omelette Au Fromage!      13
    ------------------------------------------------------------------

    ==================================================================

    Beverage      Price
    ------------------------------------------------------------------
    Beer                      13
    Jucie                     11
    Water                     12
    ------------------------------------------------------------------


To format recipe ingredients we'll change it a bit introducing a new concept.

#### value

    module Cookbook
    {
      root Recipe(name)
      {
        ...
        Ingredient[]  ingredients;
      }

      value Ingredient
      {
        String name;
        Double amount;
        String unit;
      }
    }

Value does not have a unique identification,
it exists only as a field in objects that contain it.

#### calculated
is a property that comes as a result of one or more operations on some or none of
objects fields. It can be persistable, but we will get to that later.

To have a report that only contains ingredient name we will add a calculation
that collects ingredients names returning ``String[]``. We will add this
function to the object that serves as a view to ``Meal`` which is ``MealInfo``

    calculated String[] ingredientNames from 'it =>
      it.ingredients.Select(ingredient => ingredient.name).ToArray()';

When we recompile with our addition to model,
ingredientNames will become a property of our MealInfo class.

We could now proclaim some meals chefs to give them more space in promotion.
Simple way is to add a field to ``Meals`` called ``chefsMeals``

    root Meal(name)
    {
      Cookbook.Recipe(name) *recipe;
      String                name;
      boolean               available;
      boolean               chefsMeal;

      int                   price;
    }

Make a report that recognizes this difference:

    report PriceListWithchefsMeals
    {
      Beverage [] beverages 'it => it.available';
      MealInfo [] chefsMeals 'it => it.chefsMeal & it.available';
      MealInfo [] meals 'it => it.available';
    }

Now, if we were to print chefsMeals

    final SimplePriceList simplePriceList = new SimplePriceList().populate();
    for (final String mealname : simplePriceList.getChefsMeals) info(mealname);

        Peppered Shrimp
        Peanut Chicken
        Omelette Au Fromage!



------
todo:
For the conclusion of this part we take a look at [dslCookbook @ github](https://github.com/ngs-doo/dslCookbook)
to see how could this price list look like if we made it with designer template with

    report PriceListPretty
    {
      Timestamp  date;
      Beverage [] beverages 'it => it.available';
      MealInfo [] chefsMeals 'it => it.cheffsMeal & it.available';
      MealInfo [] meals 'it => it.available';

      templater createPriceList 'PriceListTemplate.docx';
    }
