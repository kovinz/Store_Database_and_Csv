package com.company.DAO;

import com.company.Entity.Product;

import java.sql.SQLException;
import java.util.List;

/** Объект для управления персистентным состоянием объекта */
public interface ProductDao {

  /** Создает новую запись и соответствующий ей объект */
  public void insert(Product product);

  /** Возвращает объект соответствующий записи с первичным ключом key или null */
  public Product getProductById(int key)  throws SQLException ;

  /** Возвращает объект соответствующий записи с именем name или null */
  public Product getProductByName(String name) throws SQLException;

  /** Возвращает список объектов соответствующих всем записям в базе данных */
  public List<Product> getAll() throws SQLException;
}