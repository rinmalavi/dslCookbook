package com.dslplatform.examples.intermediate.Cookbook.repositories;

import com.dslplatform.patterns.*;
import com.dslplatform.client.*;

public class RecipeRepository extends ClientPersistableRepository<com.dslplatform.examples.intermediate.Cookbook.Recipe> {
    public RecipeRepository(final ServiceLocator locator) {
        super(com.dslplatform.examples.intermediate.Cookbook.Recipe.class, locator);
    }
}
