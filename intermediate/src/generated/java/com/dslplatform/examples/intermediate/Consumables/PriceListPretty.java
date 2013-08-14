package com.dslplatform.examples.intermediate.Consumables;

import com.dslplatform.patterns.*;
import com.dslplatform.client.*;
import com.fasterxml.jackson.annotation.*;

public final class PriceListPretty implements java.io.Serializable {
    @JsonCreator
    public PriceListPretty(
            @JsonProperty("date")  final org.joda.time.DateTime date,
            @JsonProperty("beverages")  final com.dslplatform.examples.intermediate.Consumables.Beverage[] beverages,
            @JsonProperty("chefsMeals")  final com.dslplatform.examples.intermediate.Consumables.MealInfo[] chefsMeals,
            @JsonProperty("meals")  final com.dslplatform.examples.intermediate.Consumables.MealInfo[] meals) {
        this();
        if(date != null) setDate(date);
        setBeverages(beverages);
        setChefsMeals(chefsMeals);
        setMeals(meals);
    }

    private static final long serialVersionUID = 0x0097000a;

    public PriceListPretty() {
        this.date = new org.joda.time.DateTime();
    }

    private org.joda.time.DateTime date;

    public org.joda.time.DateTime getDate() {
        return date;
    }

    public PriceListPretty setDate(final org.joda.time.DateTime value) {
        if(value == null) throw new IllegalArgumentException("Property \"date\" cannot be null!");
        this.date = value;

        return this;
    }

    private com.dslplatform.examples.intermediate.Consumables.Beverage[] beverages;

    public com.dslplatform.examples.intermediate.Consumables.Beverage[] getBeverages() {
        return beverages;
    }

    public PriceListPretty setBeverages(final com.dslplatform.examples.intermediate.Consumables.Beverage[] value) {
        this.beverages = value;

        return this;
    }

    private com.dslplatform.examples.intermediate.Consumables.MealInfo[] chefsMeals;

    public com.dslplatform.examples.intermediate.Consumables.MealInfo[] getChefsMeals() {
        return chefsMeals;
    }

    public PriceListPretty setChefsMeals(final com.dslplatform.examples.intermediate.Consumables.MealInfo[] value) {
        this.chefsMeals = value;

        return this;
    }

    private com.dslplatform.examples.intermediate.Consumables.MealInfo[] meals;

    public com.dslplatform.examples.intermediate.Consumables.MealInfo[] getMeals() {
        return meals;
    }

    public PriceListPretty setMeals(final com.dslplatform.examples.intermediate.Consumables.MealInfo[] value) {
        this.meals = value;

        return this;
    }

    public byte[] createPriceList() throws java.io.IOException {
        return createPriceList(Bootstrap.getLocator());
    }
    public byte[] createPriceList(final ServiceLocator locator) throws java.io.IOException {
        final ReportingProxy proxy = (locator != null ? locator : Bootstrap.getLocator()).resolve(ReportingProxy.class);
        try {
            return proxy.createReport(this, "createPriceList").get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
    }

    public PriceListPretty populate() throws java.io.IOException {
        return populate(Bootstrap.getLocator());
    }
    public PriceListPretty populate(final ServiceLocator locator) throws java.io.IOException {
        final ReportingProxy proxy = (locator != null ? locator : Bootstrap.getLocator()).resolve(ReportingProxy.class);
        final PriceListPretty result;
        try {
            result = proxy.populate(this).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
        this.beverages = result.beverages;
        this.chefsMeals = result.chefsMeals;
        this.meals = result.meals;
        return this;
    }
}
