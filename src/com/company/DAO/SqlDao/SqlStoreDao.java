package com.company.DAO.SqlDao;


import com.company.Entity.Store;
import com.company.DAO.StoreDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlStoreDao implements StoreDao {
  private final Connection connection;

  @Override
  public void insert(Store store) {
    try {
      String sql = "INSERT INTO Store(Name, Address) VALUES (\"" + store.getName() + "\", \"" +
              store.getAddress() + "\");";
      PreparedStatement stm = connection.prepareStatement(sql);
      stm.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e);
    }
  }

  @Override
  public Store getStoreByNameAndAddress(String name, String address) throws SQLException {
    String sql = "SELECT * FROM Store WHERE Name = ? and Address = ?;";
    PreparedStatement stm = connection.prepareStatement(sql);

    stm.setString(1, name);
    stm.setString(2, address);

    ResultSet rs = stm.executeQuery();
    if (rs.next()) {
      Store store = new Store();
      store.setId(rs.getInt("StoreID"));
      store.setName(rs.getString("Name"));
      store.setAddress(rs.getString("Address"));
      return store;
    } else {
      return null;
    }
  }

  @Override
  public Store getStoreById(int key) throws SQLException {
    String sql = "SELECT * FROM Store WHERE StoreID = ?;";
    PreparedStatement stm = connection.prepareStatement(sql);

    stm.setInt(1, key);

    ResultSet rs = stm.executeQuery();
    if (rs.next()) {
      Store store = new Store();
      store.setId(rs.getInt("StoreID"));
      store.setName(rs.getString("Name"));
      store.setAddress(rs.getString("Address"));
      return store;
    } else {
      return null;
    }
  }

  @Override
  public List<Store> getAll() throws SQLException {
    String sql = "SELECT * FROM Store;";
    PreparedStatement stm = connection.prepareStatement(sql);
    ResultSet rs = stm.executeQuery();
    List<Store> list = new ArrayList<Store>();
    while (rs.next()) {
      Store store = new Store();
      store.setId(rs.getInt("StoreID"));
      store.setName(rs.getString("Name"));
      store.setAddress(rs.getString("Address"));
      list.add(store);
    }
    return list;
  }

  public SqlStoreDao(Connection connection) {
    this.connection = connection;
  }
}