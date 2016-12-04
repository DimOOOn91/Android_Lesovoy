package com.example.module02task02_1;

public class Immediate {

    private String value;

    public Immediate(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Immediate immediate = (Immediate) o;

        return value != null ? value.equals(immediate.value) : immediate.value == null;

    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Immediate{" +
                "value='" + value + '\'' +
                '}';
    }
}
