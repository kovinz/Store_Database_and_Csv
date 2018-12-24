package com.company.DAO;

import com.company.Entity.Store;

import java.sql.SQLException;
import java.util.List;

/** Объект для управления персистентным состоянием объекта */
public interface StoreDao {

  /** Создает новую запись и соответствующий ей объект */
  public void insert(Store store);

  /** Возвращает объект соответствующий записи с первичным ключом key или null */
  public Store getStoreById(int key) throws SQLException;

  /** Возвращает объект соответствующий записи с именем name или null */
  public Store getStoreByNameAndAddress(String name, String address) throws SQLException;

  /** Возвращает список объектов соответствующих всем записям в базе данных */
  public List<Store> getAll() throws SQLException;
}