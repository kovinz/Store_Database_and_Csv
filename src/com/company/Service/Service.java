package com.company.Service;

import com.company.Entity.Product;
import com.company.Entity.Store;
import javafx.util.Pair;

import java.util.List;

public interface Service {
  public void buyCheapest(String productName, int quantity);
  public void addProduct(String storeName, String storeAddress, String productName, double price, int quantity);
  public Store createStore(String storeName, String storeAddress);
  public Product createProduct(String productName);
  public List<Pair<Product, Integer>> whatCanBuyInStoreFor(String storeName, String storeAddress, double money);
  public void addConsignment(String storeName, String storeAddress, List<String> productNames,
                             List<Double> prices, List<Integer> quantities);
}
