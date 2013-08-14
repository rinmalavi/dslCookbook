package com.dslplatform.examples.intermediate.Consumables;

import com.dslplatform.patterns.*;
import com.dslplatform.client.*;
import com.fasterxml.jackson.annotation.*;

public class Beverage implements java.io.Serializable, AggregateRoot {
    public Beverage() {
        _serviceLocator = Bootstrap.getLocator();
        _domainProxy = _serviceLocator.resolve(DomainProxy.class);
        _crudProxy = _serviceLocator.resolve(CrudProxy.class);
        this.ID = 0;
        this.name = "";
        this.available = false;
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
        final Beverage other = (Beverage) obj;

        return URI != null && URI.equals(other.URI);
    }

    @Override
    public String toString() {
        return URI != null ? "Beverage(" + URI + ')' : "new Beverage(" + super.hashCode() + ')';
    }

    private static final long serialVersionUID = 0x0097000a;

    public Beverage(
            final String name,
            final boolean available,
            final int price) {
        _serviceLocator = Bootstrap.getLocator();
        _domainProxy = _serviceLocator.resolve(DomainProxy.class);
        _crudProxy = _serviceLocator.resolve(CrudProxy.class);
        setName(name);
        setAvailable(available);
        setPrice(price);
    }

    @JsonCreator private Beverage(
            @JacksonInject("_serviceLocator") final ServiceLocator _serviceLocator,
            @JsonProperty("URI") final String URI ,
            @JsonProperty("ID") final int ID,
            @JsonProperty("name") final String name,
            @JsonProperty("available") final boolean available,
            @JsonProperty("price") final int price) {
        this._serviceLocator = _serviceLocator;
        this._domainProxy = _serviceLocator.resolve(DomainProxy.class);
        this._crudProxy = _serviceLocator.resolve(CrudProxy.class);
        this.URI = URI;
        this.ID = ID;
        this.name = name == null ? "" : name;
        this.available = available;
        this.price = price;
    }

    private int ID;

    @JsonProperty("ID")
    public int getID() {
        return ID;
    }

    private Beverage setID(final int value) {
        this.ID = value;

        return this;
    }

    public static Beverage find(final String uri) throws java.io.IOException {
        return find(uri, Bootstrap.getLocator());
    }
    public static Beverage find(final String uri, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(CrudProxy.class).read(Beverage.class, uri).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static java.util.List<Beverage> find(final Iterable<String> uris) throws java.io.IOException {
        return find(uris, Bootstrap.getLocator());
    }
    public static java.util.List<Beverage> find(final Iterable<String> uris, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).find(Beverage.class, uris).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static java.util.List<Beverage> findAll() throws java.io.IOException {
        return findAll(null, null, Bootstrap.getLocator());
    }
    public static java.util.List<Beverage> findAll(final ServiceLocator locator) throws java.io.IOException {
        return findAll(null, null, locator);
    }
    public static java.util.List<Beverage> findAll(final Integer limit, final Integer offset) throws java.io.IOException {
        return findAll(limit, offset, Bootstrap.getLocator());
    }
    public static java.util.List<Beverage> findAll(final Integer limit, final Integer offset, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).findAll(Beverage.class, limit, offset, null).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static java.util.List<Beverage> search(final Specification<Beverage> specification) throws java.io.IOException {
        return search(specification, null, null, Bootstrap.getLocator());
    }
    public static java.util.List<Beverage> search(final Specification<Beverage> specification, final ServiceLocator locator) throws java.io.IOException {
        return search(specification, null, null, locator);
    }
    public static java.util.List<Beverage> search(final Specification<Beverage> specification, final Integer limit, final Integer offset) throws java.io.IOException {
        return search(specification, limit, offset, Bootstrap.getLocator());
    }
    public static java.util.List<Beverage> search(final Specification<Beverage> specification, final Integer limit, final Integer offset, final ServiceLocator locator) throws java.io.IOException {
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
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).count(Beverage.class).get().longValue();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    public static long count(final Specification<Beverage> specification) throws java.io.IOException {
        return count(specification, Bootstrap.getLocator());
    }
    public static long count(final Specification<Beverage> specification, final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).count(specification).get().longValue();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
    private void updateWithAnother(final com.dslplatform.examples.intermediate.Consumables.Beverage result) {
        this.URI = result.URI;

        this.name = result.name;
        this.available = result.available;
        this.price = result.price;
        this.ID = result.ID;
    }
    public Beverage persist() throws java.io.IOException {
        final Beverage result;
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
    public Beverage delete() throws java.io.IOException {
        try {
            return _crudProxy.delete(Beverage.class, URI).get();
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

    public Beverage setName(final String value) {
        if(value == null) throw new IllegalArgumentException("Property \"name\" cannot be null!");
        this.name = value;

        return this;
    }

    private boolean available;

    @JsonProperty("available")
    public boolean getAvailable() {
        return available;
    }

    public Beverage setAvailable(final boolean value) {
        this.available = value;

        return this;
    }

    private int price;

    @JsonProperty("price")
    public int getPrice() {
        return price;
    }

    public Beverage setPrice(final int value) {
        this.price = value;

        return this;
    }
}
