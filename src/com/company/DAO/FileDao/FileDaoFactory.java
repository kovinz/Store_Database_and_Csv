package com.company.DAO.FileDao;

import com.company.DAO.*;

import java.sql.Connection;
import java.sql.SQLException;

public class FileDaoFactory implements DaoFactory {

  @Override
  public Connection getConnection() throws SQLException {
    return null;
  }

  @Override
  public PresenceDao getPresenceDao(Connection connection) {
    return new FilePresenceDao();
  }

  @Override
  public ProductDao getProductDao(Connection connection) {
    return new FileProductDao();
  }

  @Override
  public StoreDao getStoreDao(Connection connection) {
    return new FileStoreDao();
  }

}

