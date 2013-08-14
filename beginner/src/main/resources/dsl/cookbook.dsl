module Cookbook
{
  root Recipe(name)
  {
    String    name;
    String[]  ingredients;

    specification isTheOld 'it => it.name.ToLower().StartsWith("ye olde")';

    specification hasFlour 'it => it.ingredients.Any(
      ingredient => ingredient.ToLower().Contains("flour"))';

    specification hasNoPeanuts 'it => ! it.ingredients.Any(
      ingredient => ingredient.ToLower().Contains("peanut"))';

    specification hasIngredient 'it => it.ingredients.Any(
      ingredient => ingredient.ToLower().Contains(what))'
    {
      String what;
    }
  }
}
