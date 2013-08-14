package com.dslplatform.examples.intermediate.Consumables.repositories;

import com.dslplatform.patterns.*;
import com.dslplatform.client.*;

public class MealInfoRepository extends ClientRepository<com.dslplatform.examples.intermediate.Consumables.MealInfo> {
    public MealInfoRepository(final ServiceLocator locator) {
        super(com.dslplatform.examples.intermediate.Consumables.MealInfo.class, locator);
    }
    public MealInfoRepository() {
        this(Bootstrap.getLocator());
    }
}
