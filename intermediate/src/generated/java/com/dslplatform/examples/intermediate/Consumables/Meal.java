package com.dslplatform.examples.intermediate.Consumables;

import com.dslplatform.patterns.*;
import com.dslplatform.client.*;
import com.fasterxml.jackson.annotation.*;

public class Meal implements java.io.Serializable, AggregateRoot {
    public Meal() {
        _serviceLocator = Bootstrap.getLocator();
        _domainProxy = _serviceLocator.resolve(DomainProxy.class);
        _crudProxy = _serviceLocator.resolve(CrudProxy.class);
        this.name = "";
        this.available = false;
        this.cheffsMeal = false;
        this.price = 0;
    }

    private transient final ServiceLocator _serviceLocator;
    private transient final DomainProxy _domainProxy;
    private transient final CrudProxy _crudProxy;

    private String URI;

    @JsonProperty("URI")
    public String getURI() {
        return this.URI;
    }

    @Override
    public int hashCode() {
        return URI != null ? URI.hashCode() : super.hashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (getClass() != obj.getClass())
            return false;
        final Meal other = (Meal) obj;

        return URI != null && URI.equals(other.URI);
    }

    @Override
    public String toString() {
        return URI != null ? "Meal(" + URI + ')' : "new Meal(" + super.hashCode() + ')';
    }

    private static final long serialVersionUID = 0x0097000a;

    public Meal(
            final com.dslplatform.examples.intermediate.Cookbook.Recipe recipe,
            final boolean available,
            final boolean cheffsMeal,
            final int price) {
        _serviceLocator = Bootstrap.getLocator();
        _domainProxy = _serviceLocator.resolve(DomainProxy.class);
        _crudProxy = _serviceLocator.resolve(CrudProxy.class);
        setRecipe(recipe);
        setAvailable(available);
        setCheffsMeal(cheffsMeal);
        setPrice(price);
    }

    @JsonCreator private Meal(
            @JacksonInject("_serviceLocator") final ServiceLocator _serviceLocator,
            @JsonProperty("URI") final String URI ,
            @JsonProperty("recipeURI") final String recipeURI,
            @JsonProperty("name") final String name,
            @JsonProperty("available") final boolean available,
            @JsonProperty("cheffsMeal") final boolean cheffsMeal,
            @JsonProperty("price") final int price) {
        this._serviceLocator = _serviceLocator;
        this._domainProxy = _serviceLocator.resolve(DomainProxy.class);
        this._crudProxy = _serviceLocator.resolve(CrudProxy.class);
        this.URI = URI;
        this.recipeURI = recipeURI == null ? null : recipeURI;
        this.name = name == null ? "" : name;
        this.available = available;
        this.cheffsMeal = cheffsMeal;
        this.price = price;
    }

    public static Meal find(final String uri) throws java.io.IOException {
        return find(uri, Bootstrap.getLocator());
    }
    public static Meal find(final String uri, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(CrudProxy.class).read(Meal.class, uri).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static java.util.List<Meal> find(final Iterable<String> uris) throws java.io.IOException {
        return find(uris, Bootstrap.getLocator());
    }
    public static java.util.List<Meal> find(final Iterable<String> uris, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).find(Meal.class, uris).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static java.util.List<Meal> findAll() throws java.io.IOException {
        return findAll(null, null, Bootstrap.getLocator());
    }
    public static java.util.List<Meal> findAll(final ServiceLocator locator) throws java.io.IOException {
        return findAll(null, null, locator);
    }
    public static java.util.List<Meal> findAll(final Integer limit, final Integer offset) throws java.io.IOException {
        return findAll(limit, offset, Bootstrap.getLocator());
    }
    public static java.util.List<Meal> findAll(final Integer limit, final Integer offset, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).findAll(Meal.class, limit, offset, null).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static java.util.List<Meal> search(final Specification<Meal> specification) throws java.io.IOException {
        return search(specification, null, null, Bootstrap.getLocator());
    }
    public static java.util.List<Meal> search(final Specification<Meal> specification, final ServiceLocator locator) throws java.io.IOException {
        return search(specification, null, null, locator);
    }
    public static java.util.List<Meal> search(final Specification<Meal> specification, final Integer limit, final Integer offset) throws java.io.IOException {
        return search(specification, limit, offset, Bootstrap.getLocator());
    }
    public static java.util.List<Meal> search(final Specification<Meal> specification, final Integer limit, final Integer offset, final ServiceLocator locator) throws java.io.IOException {
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
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).count(Meal.class).get().longValue();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static long count(final Specification<Meal> specification) throws java.io.IOException {
        return count(specification, Bootstrap.getLocator());
    }
    public static long count(final Specification<Meal> specification, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).count(specification).get().longValue();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    private void updateWithAnother(final com.dslplatform.examples.intermediate.Consumables.Meal result) {
        this.URI = result.URI;

        this.recipe = result.recipe;
        this.recipeURI = result.recipeURI;
        this.name = result.name;
        this.available = result.available;
        this.cheffsMeal = result.cheffsMeal;
        this.price = result.price;
    }
    public Meal persist() throws java.io.IOException {
        if (this.getRecipeURI() == null) {
            throw new IllegalArgumentException("Cannot persist instance of 'com.dslplatform.examples.intermediate.Consumables.Meal' because reference 'recipe' was not assigned");
        }
        final Meal result;
        try {
            result = this.URI == null ? _crudProxy.create(this).get() : _crudProxy.update(this).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
        this.updateWithAnother(result);
        return this;
    }
    public Meal delete() throws java.io.IOException {
        try {
            return _crudProxy.delete(Meal.class, URI).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }

    private String recipeURI;

    @JsonProperty("recipeURI")
    public String getRecipeURI() {
        return this.recipeURI;
    }

    private com.dslplatform.examples.intermediate.Cookbook.Recipe recipe;

    @JsonIgnore
    public com.dslplatform.examples.intermediate.Cookbook.Recipe getRecipe() throws java.io.IOException {
        if (recipe != null && !recipe.getURI().equals(recipeURI) || recipe == null && recipeURI != null)
            try {
                recipe = _crudProxy.read(com.dslplatform.examples.intermediate.Cookbook.Recipe.class, recipeURI).get();
            } catch (final InterruptedException e) {
                throw new java.io.IOException(e);
            } catch (final java.util.concurrent.ExecutionException e) {
                throw new java.io.IOException(e);
            }
        return recipe;
    }

    public Meal setRecipe(final com.dslplatform.examples.intermediate.Cookbook.Recipe value) {
        if(value == null) throw new IllegalArgumentException("Property \"recipe\" cannot be null!");

        if(value != null && value.getURI() == null) throw new IllegalArgumentException("Reference \"Cookbook.Recipe\" for property \"recipe\" must be persisted before it's assigned");
        this.recipe = value;

        this.recipeURI = value.getURI();

        this.name = value.getName();
        return this;
    }

    private String name;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    private Meal setName(final String value) {
        if(value == null) throw new IllegalArgumentException("Property \"name\" cannot be null!");
        this.name = value;

        return this;
    }

    private boolean available;

    @JsonProperty("available")
    public boolean getAvailable() {
        return available;
    }

    public Meal setAvailable(final boolean value) {
        this.available = value;

        return this;
    }

    private boolean cheffsMeal;

    @JsonProperty("cheffsMeal")
    public boolean getCheffsMeal() {
        return cheffsMeal;
    }

    public Meal setCheffsMeal(final boolean value) {
        this.cheffsMeal = value;

        return this;
    }

    public static History<com.dslplatform.examples.intermediate.Consumables.Meal> getHistory(final String uri) throws java.io.IOException {
        final java.util.ArrayList<String> uris = new java.util.ArrayList<String>(1);
        uris.add(uri);
        try {
            return Bootstrap.getLocator().resolve(ReportingProxy.class).getHistory(com.dslplatform.examples.intermediate.Consumables.Meal.class, uris).get().get(0);
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static java.util.List<History<com.dslplatform.examples.intermediate.Consumables.Meal>> getHistory(final Iterable<String> uris) throws java.io.IOException {
        try {
            return Bootstrap.getLocator().resolve(ReportingProxy.class).getHistory(com.dslplatform.examples.intermediate.Consumables.Meal.class, uris).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }

    private int price;

    @JsonProperty("price")
    public int getPrice() {
        return price;
    }

    public Meal setPrice(final int value) {
        this.price = value;

        return this;
    }
}
