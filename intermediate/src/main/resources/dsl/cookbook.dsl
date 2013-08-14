module Cookbook
{
  root Recipe(name)
  {
    String    name;
    Ingredient[]  ingredients;

    specification hasIngredient 'it => it.ingredients.Any(
      ingredient => ingredient.name.ToLower().Contains(what))'
    {
      String what;
    }
  }

  value Ingredient 
  {
    String name;
    Double amount;
    String unit;
  }
}
