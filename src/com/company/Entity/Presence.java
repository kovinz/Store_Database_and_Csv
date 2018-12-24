package com.company.Entity;

public class Presence {
  private Store store;
  private Product product;
  private double price;
  private int quantity;

  public Presence(){};

  private Presence(Builder builder) {
    setStore(builder.store);
    setProduct(builder.product);
    setPrice(builder.price);
    setQuantity(builder.quantity);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  @Override
  public String toString() {
    return "Presence{" +
            "store=" + store +
            ", product=" + product +
            ", price=" + price +
            ", quantity=" + quantity +
            '}';
  }

  public Store getStore() {
    return store;
  }

  public void setStore(Store store) {
    this.store = store;
  }

  public Product getProduct() {
    return product;
  }

  public void setProduct(Product product) {
    this.product = product;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public int getQuantity() {
    return quantity;
  }

  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Presence presence = (Presence) o;

    if (Double.compare(presence.price, price) != 0) return false;
    if (quantity != presence.quantity) return false;
    if (store != null ? !store.equals(presence.store) : presence.store != null) return false;
    return product != null ? product.equals(presence.product) : presence.product == null;
  }

  @Override
  public int hashCode() {
    int result;
    long temp;
    result = store != null ? store.hashCode() : 0;
    result = 31 * result + (product != null ? product.hashCode() : 0);
    temp = Double.doubleToLongBits(price);
    result = 31 * result + (int) (temp ^ (temp >>> 32));
    result = 31 * result + quantity;
    return result;
  }


  public static final class Builder {
    private Store store;
    private Product product;
    private double price;
    private int quantity;

    private Builder() {
    }

    public Builder withStore(Store val) {
      store = val;
      return this;
    }

    public Builder withProduct(Product val) {
      product = val;
      return this;
    }

    public Builder withPrice(double val) {
      price = val;
      return this;
    }

    public Builder withQuantity(int val) {
      quantity = val;
      return this;
    }

    public Presence build() {
      return new Presence(this);
    }
  }
}
