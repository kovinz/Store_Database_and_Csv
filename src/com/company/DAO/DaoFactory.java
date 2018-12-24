package com.company.DAO;

import java.sql.Connection;
import java.sql.SQLException;

/** Фабрика объектов для работы с базой данных */
public interface DaoFactory {

  /** Возвращает объект для управления персистентным состоянием объекта Product */
  public ProductDao getProductDao(Connection connection);

  /** Возвращает объект для управления персистентным состоянием объекта Store */
  public StoreDao getStoreDao(Connection connection);

  /** Возвращает объект для управления персистентным состоянием объекта Presence */
  public PresenceDao getPresenceDao(Connection connection);

  public Connection getConnection()  throws SQLException;
}
