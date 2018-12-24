package com.company.Entity;

public class Store {
  private int id;
  private String name;
  private String address;

  public Store(){}

  private Store(Builder builder) {
    setId(builder.id);
    setName(builder.name);
    setAddress(builder.address);
  }

  public static Builder newBuilder() {
    return new Builder();
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public String toString() {
    return "Store{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", address='" + address + '\'' +
            '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    Store store = (Store) o;

    if (id != store.id) return false;
    if (name != null ? !name.equals(store.name) : store.name != null) return false;
    return address != null ? address.equals(store.address) : store.address == null;
  }

  @Override
  public int hashCode() {
    int result = id;
    result = 31 * result + (name != null ? name.hashCode() : 0);
    result = 31 * result + (address != null ? address.hashCode() : 0);
    return result;
  }


  public static final class Builder {
    private int id;
    private String name;
    private String address;

    private Builder() {
    }

    public Builder withId(int val) {
      id = val;
      return this;
    }

    public Builder withName(String val) {
      name = val;
      return this;
    }

    public Builder withAddress(String val) {
      address = val;
      return this;
    }

    public Store build() {
      return new Store(this);
    }
  }
}
