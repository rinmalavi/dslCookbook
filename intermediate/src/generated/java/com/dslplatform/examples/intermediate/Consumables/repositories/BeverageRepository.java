package com.dslplatform.examples.intermediate.Consumables.repositories;

import com.dslplatform.patterns.*;
import com.dslplatform.client.*;

public class BeverageRepository extends ClientPersistableRepository<com.dslplatform.examples.intermediate.Consumables.Beverage> {
    public BeverageRepository(final ServiceLocator locator) {
        super(com.dslplatform.examples.intermediate.Consumables.Beverage.class, locator);
    }
}
