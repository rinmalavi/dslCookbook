package com.dslplatform.examples.intermediate.Consumables;

import com.dslplatform.patterns.*;
import com.dslplatform.client.*;

public final class Order implements DomainEvent, java.io.Serializable {
    public Order(
             final String serviceName) {
        setServiceName(serviceName);
    }

    public Order() {
        this.serviceName = "";
    }

    private String URI;

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
        final Order other = (Order) obj;

        return URI != null && URI.equals(other.URI);
    }

    @Override
    public String toString() {
        return URI != null ? "Order(" + URI + ')' : "new Order(" + super.hashCode() + ')';
    }

    private static final long serialVersionUID = 0x0097000a;

    private String serviceName;

    public String getServiceName() {
        return serviceName;
    }

    public Order setServiceName(final String value) {
        if(value == null) throw new IllegalArgumentException("Property \"serviceName\" cannot be null!");
        this.serviceName = value;

        return this;
    }

    public String submit() throws java.io.IOException {
        return submit(Bootstrap.getLocator());
    }
    public String submit(final ServiceLocator locator) throws java.io.IOException {
        try {
            return (locator != null ? locator : Bootstrap.getLocator()).resolve(DomainProxy.class).submit(this).get();
        } catch (InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }
}
