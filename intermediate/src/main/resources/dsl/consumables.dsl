module Consumables
{
  root Meal(name)
  {
    Cookbook.Recipe(name) *recipe;
    String                name;
    boolean               available;
    boolean               cheffsMeal;

    history;
    int                   price;
  }

  snowflake MealInfo Meal
  {
    name;
    available;
    cheffsMeal;
    price;

    recipe.ingredients;
    
    specification budgetMeals 'it => it.price < budget'
    {
      int budget;
    }

    specification priceyMeals 'it => it.price >= 77';

    calculated String[] ingredientNames from 'it =>
        it.ingredients.Select(ingredient => ingredient.name).ToArray()';
  }

  root Beverage
  {
    String    name;
    boolean   available;

    int       price;
  }

  event Order 
  {
    String serviceName;
  }

  // reports
  // -------

  report BasicPriceList
  {
    Beverage [] beverages 'it => it.available';
    MealInfo [] meals 'it => it.available';
  }

  report SimplePriceList
  {
    Beverage [] beverages 'it => it.available';
    MealInfo [] meals 'it => it.available';
    
    templater createPriceList 'SimplePriceList.txt';
  }

  report PriceListWithIngredients
  {
    Beverage [] beverages 'it => it.available';
    MealInfo [] meals 'it => it.available';
    
    templater createPriceList 'PriceListWithIngredients.txt';
  }
  
/* TODO
  report CheffsMeals
  {
    MealInfo [] cheffsMeals 'it => it.cheffsMeal & it.available';
    calculated String [] cheffsMealsNames from 'it => 
      it.cheffsMeals.Select(meal => meal.name).ToArray()';
  }
*/

  report PriceListWithCheffsMeals
  {
    Beverage [] beverages 'it => it.available';
    MealInfo [] chefsMeals 'it => it.cheffsMeal & it.available';
    MealInfo [] meals 'it => it.available';
    
    templater createPriceList 'PriceListWithChefsMeals.txt';
  }

  report PriceListPretty
  {
    Timestamp  date;
    Beverage [] beverages 'it => it.available';
    MealInfo [] chefsMeals 'it => it.cheffsMeal & it.available';
    MealInfo [] meals 'it => it.available';

    templater createPriceList 'PriceListTemplate.docx';
  }
}
