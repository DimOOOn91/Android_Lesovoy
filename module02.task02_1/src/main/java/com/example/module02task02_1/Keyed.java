package com.example.module02task02_1;

public class Keyed {

    private String value;

    public Keyed(String value) {
        this.value = value;
    }

    public String getKey() {
        return value.substring(0, value.indexOf('='));
    }

    public String getValue() {
        return value.substring(value.indexOf('=')+1, value.length());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Keyed keyed = (Keyed) o;

        return value != null ? value.equals(keyed.value) : keyed.value == null;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Keyed{" +
                "key='" + getKey() + '\'' +
                ", value='" + getValue() + '\'' +
                '}';
    }
}
