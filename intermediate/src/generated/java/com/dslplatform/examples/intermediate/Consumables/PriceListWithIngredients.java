package com.dslplatform.examples.intermediate.Consumables;

import com.dslplatform.patterns.*;
import com.dslplatform.client.*;
import com.fasterxml.jackson.annotation.*;

public final class PriceListWithIngredients implements java.io.Serializable {
    @JsonCreator
    public PriceListWithIngredients(
            @JsonProperty("beverages")  final com.dslplatform.examples.intermediate.Consumables.Beverage[] beverages,
            @JsonProperty("meals")  final com.dslplatform.examples.intermediate.Consumables.MealInfo[] meals) {
        this();
        setBeverages(beverages);
        setMeals(meals);
    }

    private static final long serialVersionUID = 0x0097000a;

    public PriceListWithIngredients() {}

    private com.dslplatform.examples.intermediate.Consumables.Beverage[] beverages;

    public com.dslplatform.examples.intermediate.Consumables.Beverage[] getBeverages() {
        return beverages;
    }

    public PriceListWithIngredients setBeverages(final com.dslplatform.examples.intermediate.Consumables.Beverage[] value) {
        this.beverages = value;

        return this;
    }

    private com.dslplatform.examples.intermediate.Consumables.MealInfo[] meals;

    public com.dslplatform.examples.intermediate.Consumables.MealInfo[] getMeals() {
        return meals;
    }

    public PriceListWithIngredients setMeals(final com.dslplatform.examples.intermediate.Consumables.MealInfo[] value) {
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

    public PriceListWithIngredients populate() throws java.io.IOException {
        return populate(Bootstrap.getLocator());
    }
    public PriceListWithIngredients populate(final ServiceLocator locator) throws java.io.IOException {
        final ReportingProxy proxy = (locator != null ? locator : Bootstrap.getLocator()).resolve(ReportingProxy.class);
        final PriceListWithIngredients result;
        try {
            result = proxy.populate(this).get();
        } catch (final InterruptedException e) {
            throw new java.io.IOException(e);
        } catch (final java.util.concurrent.ExecutionException e) {
            throw new java.io.IOException(e);
        }
        this.beverages = result.beverages;
        this.meals = result.meals;
        return this;
    }
}
