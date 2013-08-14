package com.dslplatform.examples.intermediate.Consumables;

import com.dslplatform.patterns.*;
import com.dslplatform.client.*;
import com.fasterxml.jackson.annotation.*;

public final class MealInfo implements Identifiable, java.io.Serializable {
    @JsonCreator
    public MealInfo(
            @JsonProperty("URI")  final String URI,
            @JsonProperty("name")  final String name,
            @JsonProperty("available")  final boolean available,
            @JsonProperty("cheffsMeal")  final boolean cheffsMeal,
            @JsonProperty("price")  final int price,
            @JsonProperty("ingredients")  final com.dslplatform.examples.intermediate.Cookbook.Ingredient[] ingredients,
            @JsonProperty("ingredientNames")  final String[] ingredientNames) {
        this.URI = URI;
        this.name = name;
        if(name == null) throw new IllegalArgumentException("Property \"name\" cannot be null!");
        this.available = available;
        this.cheffsMeal = cheffsMeal;
        this.price = price;
        this.ingredients = ingredients;
        if(ingredients == null) throw new IllegalArgumentException("Property \"ingredients\" cannot be null!");
        com.dslplatform.examples.intermediate.Guards.checkNulls(ingredients);
        this.ingredientNames = ingredientNames;
    }

    private MealInfo() {
        this.URI = null;
        this.name = null;
        this.available = false;
        this.cheffsMeal = false;
        this.price = 0;
        this.ingredients = null;
        this.ingredientNames = null;
    }

    private final String URI;

    public String getURI() {
        return this.URI;
    }

    @Override
    public int hashCode() {
        return URI.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;
        final MealInfo other = (MealInfo) obj;

        return URI.equals(other.URI);
    }

    @Override
    public String toString() {
        return "MealInfo(" + URI + ')';
    }

    private static final long serialVersionUID = 0x0097000a;

    private final String name;

    public String getName() {
        return this.name;
    }

    private final boolean available;

    public boolean getAvailable() {
        return this.available;
    }

    private final boolean cheffsMeal;

    public boolean getCheffsMeal() {
        return this.cheffsMeal;
    }

    private final int price;

    public int getPrice() {
        return this.price;
    }

    private final com.dslplatform.examples.intermediate.Cookbook.Ingredient[] ingredients;

    public com.dslplatform.examples.intermediate.Cookbook.Ingredient[] getIngredients() {
        return this.ingredients;
    }

public static class budgetMeals implements java.io.Serializable, Specification<MealInfo> {
    public budgetMeals(
             final int budget) {
        setBudget(budget);
    }

    public budgetMeals() {
        this.budget = 0;
    }

    private static final long serialVersionUID = 0x0097000a;

    private int budget;

    public int getBudget() {
        return budget;
    }

    public budgetMeals setBudget(final int value) {
        this.budget = value;

        return this;
    }

        public java.util.List<MealInfo> search() throws java.io.IOException {
            return search(null, null, Bootstrap.getLocator());
        }
        public java.util.List<MealInfo> search(final ServiceLocator locator) throws java.io.IOException {
            return search(null, null, locator);
        }
        public java.util.List<MealInfo> search(final Integer limit, final Integer offset) throws java.io.IOException {
            return search(limit, offset, Bootstrap.getLocator());
        }
        public java.util.List<MealInfo> search(final Integer limit, final Integer offset, final ServiceLocator locator) throws java.io.IOException {
            try {
                return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).search(this, limit, offset, null).get();
            } catch (final InterruptedException e) {
                throw new java.io.IOException(e);
            } catch (final java.util.concurrent.ExecutionException e) {
                throw new java.io.IOException(e);
            }
        }
        public long count() throws java.io.IOException {
            return count(Bootstrap.getLocator());
        }
        public long count(final ServiceLocator locator) throws java.io.IOException {
            try {
                return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).count(this).get().longValue();
            } catch (final InterruptedException e) {
                throw new java.io.IOException(e);
            } catch (final java.util.concurrent.ExecutionException e) {
                throw new java.io.IOException(e);
            }
        }
}

public static class priceyMeals implements java.io.Serializable, Specification<MealInfo> {
    public priceyMeals() {}

    private static final long serialVersionUID = 0x0097000a;

        public java.util.List<MealInfo> search() throws java.io.IOException {
            return search(null, null, Bootstrap.getLocator());
        }
        public java.util.List<MealInfo> search(final ServiceLocator locator) throws java.io.IOException {
            return search(null, null, locator);
        }
        public java.util.List<MealInfo> search(final Integer limit, final Integer offset) throws java.io.IOException {
            return search(limit, offset, Bootstrap.getLocator());
        }
        public java.util.List<MealInfo> search(final Integer limit, final Integer offset, final ServiceLocator locator) throws java.io.IOException {
            try {
                return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).search(this, limit, offset, null).get();
            } catch (final InterruptedException e) {
                throw new java.io.IOException(e);
            } catch (final java.util.concurrent.ExecutionException e) {
                throw new java.io.IOException(e);
            }
        }
        public long count() throws java.io.IOException {
            return count(Bootstrap.getLocator());
        }
        public long count(final ServiceLocator locator) throws java.io.IOException {
            try {
                return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).count(this).get().longValue();
            } catch (final InterruptedException e) {
                throw new java.io.IOException(e);
            } catch (final java.util.concurrent.ExecutionException e) {
                throw new java.io.IOException(e);
            }
        }
}

    private final String[] ingredientNames;

    public String[] getIngredientNames() {
        return this.ingredientNames;
    }

    public static MealInfo find(final String uri) throws java.io.IOException {
        return find(uri, null);
    }
    public static MealInfo find(final String uri, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(CrudProxy.class).read(MealInfo.class, uri).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static java.util.List<MealInfo> find(final Iterable<String> uris) throws java.io.IOException {
        return find(uris, Bootstrap.getLocator());
    }
    public static java.util.List<MealInfo> find(final Iterable<String> uris, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).find(MealInfo.class, uris).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static java.util.List<MealInfo> findAll() throws java.io.IOException {
        return findAll(null, null, Bootstrap.getLocator());
    }
    public static java.util.List<MealInfo> findAll(final ServiceLocator locator) throws java.io.IOException {
        return findAll(null, null, locator);
    }
    public static java.util.List<MealInfo> findAll(final Integer limit, final Integer offset) throws java.io.IOException {
        return findAll(limit, offset, Bootstrap.getLocator());
    }
    public static java.util.List<MealInfo> findAll(final Integer limit, final Integer offset, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).findAll(MealInfo.class, limit, offset, null).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static java.util.List<MealInfo> search(final Specification<MealInfo> specification) throws java.io.IOException {
        return search(specification, null, null, Bootstrap.getLocator());
    }
    public static java.util.List<MealInfo> search(final Specification<MealInfo> specification, final ServiceLocator locator) throws java.io.IOException {
        return search(specification, null, null, locator);
    }
    public static java.util.List<MealInfo> search(final Specification<MealInfo> specification, final Integer limit, final Integer offset) throws java.io.IOException {
        return search(specification, limit, offset, Bootstrap.getLocator());
    }
    public static java.util.List<MealInfo> search(final Specification<MealInfo> specification, final Integer limit, final Integer offset, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).search(specification, limit, offset, null).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static long count() throws java.io.IOException {
        return count(Bootstrap.getLocator());
    }
    public static long count(final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).count(MealInfo.class).get().longValue();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static long count(final Specification<MealInfo> specification) throws java.io.IOException {
        return count(specification, Bootstrap.getLocator());
    }
    public static long count(final Specification<MealInfo> specification, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).count(specification).get().longValue();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
}
