package com.example.module02task01_3;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Good {
    private String product;
    private String manufacturer;
    private String manufacturerContacts;
    private int quantity;
    private final SimpleDateFormat mDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.UK);
    private Calendar productionDate;
    private Calendar expiryDate;
    private int shelfLife;
    private String provider;
    private String providerContacts;
    private int price;

    public Good(String product, String manufacturer, String manufacturerContacts,
                int quantity, String productionDate, int shelfLife,
                String provider, String providerContacts, int price) {
        this.product = product;
        this.manufacturer = manufacturer;
        this.manufacturerContacts = manufacturerContacts;
        this.quantity = quantity;

        try {
            this.productionDate = Calendar.getInstance();
            this.productionDate.setTime(mDateFormat.parse(productionDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.shelfLife = shelfLife;
        this.expiryDate = Calendar.getInstance();
        this.expiryDate.setTime(this.productionDate.getTime());
        this.expiryDate.add(Calendar.MONTH, shelfLife);
        this.provider = provider;
        this.providerContacts = providerContacts;
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Good good = (Good) o;

        if (quantity != good.quantity) return false;
        if (shelfLife != good.shelfLife) return false;
        if (price != good.price) return false;
        if (product != null ? !product.equals(good.product) : good.product != null) return false;
        if (manufacturer != null ? !manufacturer.equals(good.manufacturer) : good.manufacturer != null)
            return false;
        if (manufacturerContacts != null ? !manufacturerContacts.equals(good.manufacturerContacts) : good.manufacturerContacts != null)
            return false;
        if (productionDate != null ? !productionDate.equals(good.productionDate) : good.productionDate != null)
            return false;
        if (expiryDate != null ? !expiryDate.equals(good.expiryDate) : good.expiryDate != null)
            return false;
        if (provider != null ? !provider.equals(good.provider) : good.provider != null)
            return false;
        return providerContacts != null ? providerContacts.equals(good.providerContacts) : good.providerContacts == null;

    }

    @Override
    public int hashCode() {
        int result = product != null ? product.hashCode() : 0;
        result = 31 * result + (manufacturer != null ? manufacturer.hashCode() : 0);
        result = 31 * result + (manufacturerContacts != null ? manufacturerContacts.hashCode() : 0);
        result = 31 * result + quantity;
        result = 31 * result + (productionDate != null ? productionDate.hashCode() : 0);
        result = 31 * result + (expiryDate != null ? expiryDate.hashCode() : 0);
        result = 31 * result + shelfLife;
        result = 31 * result + (provider != null ? provider.hashCode() : 0);
        result = 31 * result + (providerContacts != null ? providerContacts.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }

    @Override
    public String toString() {
        return "Good{" +
                "product='" + product + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", manufacturerContacts='" + manufacturerContacts + '\'' +
                ", quantity=" + quantity +
                ", productionDate=" + mDateFormat.format(productionDate.getTime()) +
                ", expiryDate=" + mDateFormat.format(expiryDate.getTime()) +
                ", shelfLife=" + shelfLife +
                ", provider='" + provider + '\'' +
                ", providerContacts='" + providerContacts + '\'' +
                ", price=" + price +
                '}';
    }
}
