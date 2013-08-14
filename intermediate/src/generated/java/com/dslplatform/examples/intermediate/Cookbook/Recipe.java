package com.dslplatform.examples.intermediate.Cookbook;

import com.dslplatform.patterns.*;
import com.dslplatform.client.*;
import com.fasterxml.jackson.annotation.*;

public class Recipe implements java.io.Serializable, AggregateRoot {
    public Recipe() {
        _serviceLocator = Bootstrap.getLocator();
        _domainProxy = _serviceLocator.resolve(DomainProxy.class);
        _crudProxy = _serviceLocator.resolve(CrudProxy.class);
        this.name = "";
        this.ingredients = new com.dslplatform.examples.intermediate.Cookbook.Ingredient[0];
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
        final Recipe other = (Recipe) obj;

        return URI != null && URI.equals(other.URI);
    }

    @Override
    public String toString() {
        return URI != null ? "Recipe(" + URI + ')' : "new Recipe(" + super.hashCode() + ')';
    }

    private static final long serialVersionUID = 0x0097000a;

    public Recipe(
            final String name,
            final com.dslplatform.examples.intermediate.Cookbook.Ingredient[] ingredients) {
        _serviceLocator = Bootstrap.getLocator();
        _domainProxy = _serviceLocator.resolve(DomainProxy.class);
        _crudProxy = _serviceLocator.resolve(CrudProxy.class);
        setName(name);
        setIngredients(ingredients);
    }

    @JsonCreator private Recipe(
            @JacksonInject("_serviceLocator") final ServiceLocator _serviceLocator,
            @JsonProperty("URI") final String URI ,
            @JsonProperty("name") final String name,
            @JsonProperty("ingredients") final com.dslplatform.examples.intermediate.Cookbook.Ingredient[] ingredients) {
        this._serviceLocator = _serviceLocator;
        this._domainProxy = _serviceLocator.resolve(DomainProxy.class);
        this._crudProxy = _serviceLocator.resolve(CrudProxy.class);
        this.URI = URI;
        this.name = name == null ? "" : name;
        this.ingredients = ingredients == null ? new com.dslplatform.examples.intermediate.Cookbook.Ingredient[0] : ingredients;
    }

    public static Recipe find(final String uri) throws java.io.IOException {
        return find(uri, Bootstrap.getLocator());
    }
    public static Recipe find(final String uri, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(CrudProxy.class).read(Recipe.class, uri).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static java.util.List<Recipe> find(final Iterable<String> uris) throws java.io.IOException {
        return find(uris, Bootstrap.getLocator());
    }
    public static java.util.List<Recipe> find(final Iterable<String> uris, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).find(Recipe.class, uris).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static java.util.List<Recipe> findAll() throws java.io.IOException {
        return findAll(null, null, Bootstrap.getLocator());
    }
    public static java.util.List<Recipe> findAll(final ServiceLocator locator) throws java.io.IOException {
        return findAll(null, null, locator);
    }
    public static java.util.List<Recipe> findAll(final Integer limit, final Integer offset) throws java.io.IOException {
        return findAll(limit, offset, Bootstrap.getLocator());
    }
    public static java.util.List<Recipe> findAll(final Integer limit, final Integer offset, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).findAll(Recipe.class, limit, offset, null).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static java.util.List<Recipe> search(final Specification<Recipe> specification) throws java.io.IOException {
        return search(specification, null, null, Bootstrap.getLocator());
    }
    public static java.util.List<Recipe> search(final Specification<Recipe> specification, final ServiceLocator locator) throws java.io.IOException {
        return search(specification, null, null, locator);
    }
    public static java.util.List<Recipe> search(final Specification<Recipe> specification, final Integer limit, final Integer offset) throws java.io.IOException {
        return search(specification, limit, offset, Bootstrap.getLocator());
    }
    public static java.util.List<Recipe> search(final Specification<Recipe> specification, final Integer limit, final Integer offset, final ServiceLocator locator) throws java.io.IOException {
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
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).count(Recipe.class).get().longValue();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static long count(final Specification<Recipe> specification) throws java.io.IOException {
        return count(specification, Bootstrap.getLocator());
    }
    public static long count(final Specification<Recipe> specification, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).count(specification).get().longValue();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    private void updateWithAnother(final com.dslplatform.examples.intermediate.Cookbook.Recipe result) {
        this.URI = result.URI;

        this.name = result.name;
        this.ingredients = result.ingredients;
    }
    public Recipe persist() throws java.io.IOException {
        final Recipe result;
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
    public Recipe delete() throws java.io.IOException {
        try {
            return _crudProxy.delete(Recipe.class, URI).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }

    private String name;

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    public Recipe setName(final String value) {
        if(value == null) throw new IllegalArgumentException("Property \"name\" cannot be null!");
        this.name = value;

        return this;
    }

    private com.dslplatform.examples.intermediate.Cookbook.Ingredient[] ingredients;

    @JsonProperty("ingredients")
    public com.dslplatform.examples.intermediate.Cookbook.Ingredient[] getIngredients() {
        return ingredients;
    }

    public Recipe setIngredients(final com.dslplatform.examples.intermediate.Cookbook.Ingredient[] value) {
        if(value == null) throw new IllegalArgumentException("Property \"ingredients\" cannot be null!");
        com.dslplatform.examples.intermediate.Guards.checkNulls(value);
        this.ingredients = value;

        return this;
    }

public static class hasIngredient implements java.io.Serializable, Specification<Recipe> {
    public hasIngredient(
             final String what) {
        setWhat(what);
    }

    public hasIngredient() {
        this.what = "";
    }

    private static final long serialVersionUID = 0x0097000a;

    private String what;

    public String getWhat() {
        return what;
    }

    public hasIngredient setWhat(final String value) {
        if(value == null) throw new IllegalArgumentException("Property \"what\" cannot be null!");
        this.what = value;

        return this;
    }

        public java.util.List<Recipe> search() throws java.io.IOException {
            return search(null, null, Bootstrap.getLocator());
        }
        public java.util.List<Recipe> search(final ServiceLocator locator) throws java.io.IOException {
            return search(null, null, locator);
        }
        public java.util.List<Recipe> search(final Integer limit, final Integer offset) throws java.io.IOException {
            return search(limit, offset, Bootstrap.getLocator());
        }
        public java.util.List<Recipe> search(final Integer limit, final Integer offset, final ServiceLocator locator) throws java.io.IOException {
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
}
