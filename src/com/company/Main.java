package com.company;

import com.company.DAO.*;
import com.company.DAO.SqlDao.SqlDaoFactory;
import com.company.Service.Service;
import com.company.Service.ServiceStore;

import java.sql.Connection;

public class Main {

  public static void main(String[] args) {
    DaoFactory daoFactory = new SqlDaoFactory();
    try {
      Connection con = daoFactory.getConnection();
      ProductDao productDao = daoFactory.getProductDao(con);
      PresenceDao presenceDao = daoFactory.getPresenceDao(con);
      StoreDao storeDao = daoFactory.getStoreDao(con);
      Service serviceStore = new ServiceStore(daoFactory);
      System.out.println(presenceDao.getAll().toString());
      serviceStore.addProduct("Pyaterochka", "Novosibirsk", "Potato", 30, 500);
      System.out.println(presenceDao.getAll().toString());
      serviceStore.buyCheapest("Milk", 2);
      System.out.println(presenceDao.getAll().toString());
      System.out.println("Products: ");
      System.out.println(productDao.getAll().toString());
      serviceStore.createProduct("Orange");
      System.out.println(productDao.getAll().toString());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
