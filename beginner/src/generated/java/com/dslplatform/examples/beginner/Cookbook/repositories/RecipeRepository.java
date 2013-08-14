package com.dslplatform.examples.beginner.Cookbook.repositories;

import com.dslplatform.patterns.*;
import com.dslplatform.client.*;

public class RecipeRepository extends ClientPersistableRepository<com.dslplatform.examples.beginner.Cookbook.Recipe> {
    public RecipeRepository(final ServiceLocator locator) {
        super(com.dslplatform.examples.beginner.Cookbook.Recipe.class, locator);
    }
}
