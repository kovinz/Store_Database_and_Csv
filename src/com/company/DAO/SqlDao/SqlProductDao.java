package com.company.DAO.SqlDao;

import com.company.DAO.ProductDao;
import com.company.Entity.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlProductDao implements ProductDao {
  private final Connection connection;

  @Override
  public void insert(Product product) {
    try {
      String sql = "INSERT INTO Product(Name) VALUES (\"" + product.getName() + "\");";
      PreparedStatement stm = connection.prepareStatement(sql);
      stm.executeUpdate();
    } catch (SQLException e) {
      System.out.println(e);
    }
  }

  @Override
  public Product getProductByName(String name) throws SQLException {
    String sql = "SELECT * FROM Product WHERE name = ?;";
    PreparedStatement stm = connection.prepareStatement(sql);

    stm.setString(1, name);

    ResultSet rs = stm.executeQuery();
    if (rs.next()) {
      Product product = new Product();
      product.setId(rs.getInt("ProductID"));
      product.setName(rs.getString("Name"));
      return product;
    } else {
      return null;
    }
  }

  @Override
  public Product getProductById(int key) throws SQLException {
    String sql = "SELECT * FROM Product WHERE ProductID = ?;";
    PreparedStatement stm = connection.prepareStatement(sql);

    stm.setInt(1, key);

    ResultSet rs = stm.executeQuery();
    if (rs.next()) {
      Product product = new Product();
      product.setId(rs.getInt("ProductID"));
      product.setName(rs.getString("Name"));
      return product;
    } else {
      return null;
    }
  }

  @Override
  public List<Product> getAll() throws SQLException {
    String sql = "SELECT * FROM Product;";
    PreparedStatement stm = connection.prepareStatement(sql);
    ResultSet rs = stm.executeQuery();
    List<Product> list = new ArrayList<Product>();
    while (rs.next()) {
      Product product = new Product();
      product.setId(rs.getInt("ProductID"));
      product.setName(rs.getString("Name"));
      list.add(product);
    }
    return list;
  }

  public SqlProductDao(Connection connection) {
    this.connection = connection;
  }
}
