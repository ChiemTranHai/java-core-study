package com.example.demo;

import java.util.Objects;

public class ValuePair implements Cloneable {
    private int a;
    private int b;

    public void copy(final ValuePair other) throws InterruptedException, CloneNotSupportedException {
        synchronized (this) {
            this.a = other.a;
            this.b = other.b;
        }
    }

    public void setValue(final int value) {
        synchronized (this){
            this.a = value;
            this.b = value;
        }
    }

    @Override
    protected ValuePair clone() throws CloneNotSupportedException {
        return (ValuePair) super.clone();
    }

    @Override
    public String toString() {
        return String.format("a: %d and b: %d", a, b);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValuePair valuePair = (ValuePair) o;
        return a == valuePair.a && b == valuePair.b;
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }
}
