package com.company.Service;

import com.company.DAO.*;
import com.company.Entity.Presence;
import com.company.Entity.Product;
import com.company.Entity.Store;
import javafx.util.Pair;

import java.sql.Connection;
import java.util.List;

public class ServiceStore implements Service{
  private DaoFactory daoFactory;

  public ServiceStore(DaoFactory daoFactory) {
    this.daoFactory = daoFactory;
  }

  public void buyCheapest(String productName, int quantity) {
    try {
      Connection con = daoFactory.getConnection();

      ProductDao productDao = daoFactory.getProductDao(con);
      Product product = productDao.getProductByName(productName);
      int productId = product.getId();

      PresenceDao presenceDao = daoFactory.getPresenceDao(con);
      List<Presence> presences = presenceDao.getAll();

      double minPrice = 999999999;
      Presence minPresence = null;
      for (Presence presence : presences) {
        if ((presence.getProduct().getId() == productId) && (presence.getQuantity() >= quantity)
                && (presence.getPrice() < minPrice)) {
          minPresence = presence;
          minPrice = presence.getPrice();
        }
      }
      presenceDao.updateQuantity(minPresence, -quantity);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void addProduct(String storeName, String storeAddress, String productName, double price, int quantity) {
    try {
      Connection con = daoFactory.getConnection();

      ProductDao productDao = daoFactory.getProductDao(con);
      Product product = productDao.getProductByName(productName);
      if (product == null) {
        product = Product.newBuilder()
                .withName(productName)
                .build();
        productDao.insert(product);
        product = productDao.getProductByName(productName);
      }

      StoreDao storeDao = daoFactory.getStoreDao(con);
      Store store = storeDao.getStoreByNameAndAddress(storeName, storeAddress);
      if (store == null) {
        store = Store.newBuilder()
                .withName(storeName)
                .withAddress(storeAddress)
                .build();
        storeDao.insert(store);
        store = storeDao.getStoreByNameAndAddress(storeName, storeAddress);
      }

      PresenceDao presenceDao = daoFactory.getPresenceDao(con);
      Presence presence = presenceDao.getPresenceByStoreAndProduct(store, product);
      if (presence == null) {
        presence = Presence.newBuilder()
                .withStore(store)
                .withProduct(product)
                .withPrice(price)
                .withQuantity(quantity)
                .build();
        presenceDao.insert(presence);
      } else {
        presenceDao.updatePrice(presence, price);
        presenceDao.updateQuantity(presence, quantity);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public Store createStore(String storeName, String storeAddress) {
    Store store = null;
    try {
      Connection con = daoFactory.getConnection();

      StoreDao storeDao = daoFactory.getStoreDao(con);
      store = storeDao.getStoreByNameAndAddress(storeName, storeAddress);
      if (store == null) {
        store = Store.newBuilder()
                .withName(storeName)
                .withAddress(storeAddress)
                .build();
        storeDao.insert(store);
        store = storeDao.getStoreByNameAndAddress(storeName, storeAddress);
      }
      return store;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return store;
  }

  public Product createProduct(String productName) {
    Product product = null;
    try {
      Connection con = daoFactory.getConnection();

      ProductDao productDao = daoFactory.getProductDao(con);
      product = productDao.getProductByName(productName);
      if (product == null) {
        product = Product.newBuilder()
                .withName(productName)
                .build();
        productDao.insert(product);
        product = productDao.getProductByName(productName);
      }
      return product;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return product;
  }

  public List<Pair<Product, Integer>> whatCanBuyInStoreFor(String storeName, String storeAddress, double money) {
    List<Pair<Product, Integer>> products = null;
    try {
      Connection con = daoFactory.getConnection();
      Store store = this.createStore(storeName, storeAddress);
      PresenceDao presenceDao = daoFactory.getPresenceDao(con);
      List<Presence> presences = presenceDao.getAll();
      for (Presence presence : presences){
        if (presence.getStore().equals(store) && (presence.getPrice() <= money)){
          int howManyCanBuy = 0;
          int quantityOfProductInStore = presence.getQuantity();
          while((money >= presence.getPrice()) && (quantityOfProductInStore > 0)){
            howManyCanBuy++;
            money -= presence.getPrice();
            quantityOfProductInStore--;
          }
          products.add(new Pair<>(presence.getProduct(), howManyCanBuy));
        }
      }
      return products;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return products;
  }

  public void addConsignment(String storeName, String storeAddress, List<String> productNames,
                               List<Double> prices, List<Integer> quantities){
    for (int i = 0; i < productNames.size(); i++){
      this.addProduct(storeName, storeAddress, productNames.get(i), prices.get(i), quantities.get(i));
    }
  }
}
