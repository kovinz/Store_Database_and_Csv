package com.company.DAO;

import com.company.Entity.Presence;
import com.company.Entity.Product;
import com.company.Entity.Store;

import java.sql.SQLException;
import java.util.List;

/** Объект для управления персистентным состоянием объекта */
public interface PresenceDao {

  /** Создает новую запись и соответствующий ей объект */
  public void insert(Presence presence);

  public Presence getPresenceByStoreAndProduct(Store store, Product product) throws SQLException ;

  public void updateQuantity(Presence presence, int amountOfChange);

  public void updatePrice(Presence presence, double amountOfChange);

  /** Возвращает список объектов соответствующих всем записям в базе данных */
  public List<Presence> getAll() throws SQLException;
}