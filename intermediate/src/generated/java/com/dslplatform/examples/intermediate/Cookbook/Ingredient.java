package com.dslplatform.examples.intermediate.Cookbook;

public final class Ingredient implements java.io.Serializable {
    public Ingredient(
            final String name,
            final double amount,
            final String unit) {
        setName(name);
        setAmount(amount);
        setUnit(unit);
    }

    public Ingredient() {
        this.name = "";
        this.amount = 0.0;
        this.unit = "";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + 162305339;
        result = prime * result + (this.name != null ? this.name.hashCode() : 0);
        result = prime * result + (Double.valueOf(this.amount).hashCode());
        result = prime * result + (this.unit != null ? this.unit.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;

        if (!(obj instanceof Ingredient))
            return false;
        final Ingredient other = (Ingredient) obj;

        if(!(this.name.equals(other.name)))
            return false;
        if(!(Double.doubleToLongBits(this.amount) == Double.doubleToLongBits(other.amount)))
            return false;
        if(!(this.unit.equals(other.unit)))
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "Ingredient(" + name + ',' + amount + ',' + unit + ')';
    }

    private static final long serialVersionUID = 0x0097000a;

    private String name;

    public String getName() {
        return name;
    }

    public Ingredient setName(final String value) {
        if(value == null) throw new IllegalArgumentException("Property \"name\" cannot be null!");
        this.name = value;

        return this;
    }

    private double amount;

    public double getAmount() {
        return amount;
    }

    public Ingredient setAmount(final double value) {
        this.amount = value;

        return this;
    }

    private String unit;

    public String getUnit() {
        return unit;
    }

    public Ingredient setUnit(final String value) {
        if(value == null) throw new IllegalArgumentException("Property \"unit\" cannot be null!");
        this.unit = value;

        return this;
    }
}
