package com.company.DAO.SqlDao;

import com.company.DAO.*;
import com.company.Entity.Presence;
import com.company.Entity.Product;
import com.company.Entity.Store;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlPresenceDao implements PresenceDao {
  private final Connection connection;

  @Override
  public void insert(Presence presence) {
    try {
      String sql = "INSERT INTO Presence(StoreID, ProductID, Price, Quantity) VALUES (" + presence.getStore().getId()
              + ", " + presence.getProduct().getId() + ", " + presence.getPrice() + ", " + presence.getQuantity() + ");";
      PreparedStatement stm = connection.prepareStatement(sql);
      stm.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e);
    }
  }

  @Override
  public void updateQuantity(Presence presence, int amountOfChange){
    try {
      String sql = "UPDATE Presence SET Quantity = " + presence.getQuantity()+amountOfChange + " WHERE StoreID = " +
              presence.getStore().getId() + " and ProductID = " + presence.getProduct().getId() + ";";
      PreparedStatement stm = connection.prepareStatement(sql);
      stm.executeUpdate();
      presence.setQuantity(presence.getQuantity()+amountOfChange);
    } catch (SQLException e) {
      System.out.println(e);
    }
  }

  @Override
  public void updatePrice(Presence presence, double newPrice){
    try {
      String sql = "UPDATE Presence SET Price = " + newPrice + " WHERE StoreID = " +
              presence.getStore().getId() + " and ProductID = " + presence.getProduct().getId() + ";";
      PreparedStatement stm = connection.prepareStatement(sql);
      stm.executeUpdate();
      presence.setPrice(newPrice);
    } catch (SQLException e) {
      System.out.println(e);
    }
  }

  @Override
  public Presence getPresenceByStoreAndProduct(Store store, Product product) throws SQLException {
    String sql = "SELECT * FROM Presence WHERE StoreID = ? and ProductID = ?;";
    PreparedStatement stm = connection.prepareStatement(sql);

    stm.setInt(1, store.getId());
    stm.setInt(2, product.getId());

    ResultSet rs = stm.executeQuery();
    rs.next();
    if (rs.next()) {
      Presence presence = new Presence();
      presence.setStore(store);
      presence.setProduct(product);
      presence.setPrice(rs.getDouble("Price"));
      presence.setQuantity(rs.getInt("Quantity"));
      return presence;
    } else {
      return null;
    }
  }

  @Override
  public List<Presence> getAll() throws SQLException {
    String sql = "SELECT * FROM Presence;";
    PreparedStatement stm = connection.prepareStatement(sql);
    ResultSet rs = stm.executeQuery();
    List<Presence> list = new ArrayList<Presence>();
    while (rs.next()) {
      Presence presence = new Presence();
      StoreDao storeDao = new SqlStoreDao(connection);
      ProductDao productDao = new SqlProductDao(connection);
      presence.setStore(storeDao.getStoreById(rs.getInt("StoreID")));
      presence.setProduct(productDao.getProductById(rs.getInt("ProductID")));
      presence.setPrice(rs.getDouble("Price"));
      presence.setQuantity(rs.getInt("Quantity"));
      list.add(presence);
    }
    return list;
  }

  public SqlPresenceDao(Connection connection) {
    this.connection = connection;
  }

}
