package com.dslplatform.examples.intermediate.test;
//package com.dslplatform.examples.intermediate.test;
//
//import static org.junit.Assert.*;
//
//import java.util.List;
//
//import org.junit.Test;
//
//import com.dslplatform.examples.intermediate.Restaurant;
//import com.dslplatform.examples.intermediate.Consumables.Meal;
//import com.dslplatform.patterns.History;
//import com.dslplatform.patterns.Snapshot;
//
//public class HistoryExample {
//
//
//  @Test
//  public void changePrices() throws Exception{
//    final Restaurant testrest = new Restaurant("intermediate.ini");
//    final List<Meal> listOfMeals = testrest.getMeals();
//    for (final Meal meal : listOfMeals) {
//       meal.setPrice(meal.getPrice() + 1).persist();
//    }
//  }
//
//  @Test
//  public void test() throws Exception{
//    final Restaurant testrest = new Restaurant("intermediate.ini");
//    final List<Meal> listOfMeals = testrest.getMeals();
//
//    for (final Meal meal : listOfMeals) {
//
//      final History<Meal> history = Meal.getHistory(meal.getURI());
//      List<Snapshot<Meal>> arrSnapshot = history.getSnapshots();
//
//      System.out.println(meal.getName() + " history "  + arrSnapshot.size());
//
//      for( final Snapshot<Meal> snapshot : arrSnapshot){
//        System.out.println("  " + snapshot.Action);
//      }
//    }
//  }
//}
