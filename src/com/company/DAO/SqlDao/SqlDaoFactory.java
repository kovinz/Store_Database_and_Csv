package com.company.DAO.SqlDao;

import com.company.DAO.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlDaoFactory implements DaoFactory {

  private String user = "root";
  private String password = "qwe123";
  private String url = "jdbc:mysql://localhost:3306/stores?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
  private String driver = "com.mysql.cj.jdbc.Driver";

  public Connection getConnection() throws SQLException {
    return DriverManager.getConnection(url, user, password);
  }

  @Override
  public PresenceDao getPresenceDao(Connection connection) {
    return new SqlPresenceDao(connection);
  }

  @Override
  public ProductDao getProductDao(Connection connection) {
    return  new SqlProductDao(connection);
  }

  @Override
  public StoreDao getStoreDao(Connection connection) {
    return new SqlStoreDao(connection);
  }

  public SqlDaoFactory() {
    try {
      Class.forName(driver);
    } catch (ClassNotFoundException e) {
      e.printStackTrace();
    }
  }
}