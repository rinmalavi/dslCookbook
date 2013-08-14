package com.dslplatform.examples.intermediate.Consumables.repositories;

import com.dslplatform.patterns.*;
import com.dslplatform.client.*;

public class MealRepository extends ClientPersistableRepository<com.dslplatform.examples.intermediate.Consumables.Meal> {
    public MealRepository(final ServiceLocator locator) {
        super(com.dslplatform.examples.intermediate.Consumables.Meal.class, locator);
    }
}
