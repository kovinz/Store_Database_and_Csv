package com.company.DAO.FileDao;

import com.company.DAO.StoreDao;
import com.company.Entity.Store;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileStoreDao implements StoreDao {
  /** Создает новую запись и соответствующий ей объект */
  public void insert(Store store){
    try {
      List<Store> allProducts = this.getAll();

      Store lastStore = allProducts.get(allProducts.size() - 1);
      int newIndex = lastStore.getId() + 1;
      File file = new File("store.csv");
      FileWriter writer = new FileWriter(file, true);

      String newStore = newIndex + "," + store.getName() + "," + store.getAddress();
      writer.append("\n");
      writer.flush();
      writer.append(newStore);
      writer.flush();
      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  };

  /** Возвращает объект соответствующий записи с первичным ключом key или null */
  public Store getStoreById(int key) throws SQLException{
    Store store = null;
    List<Store> allStores = this.getAll();

    for (Store currentStore : allStores) {
      if (currentStore.getId() == key) {
        store = currentStore;
        break;
      }
    }
    return store;
  };

  /** Возвращает объект соответствующий записи с именем name или null */
  public Store getStoreByNameAndAddress(String name, String address) throws SQLException{
    Store store = null;
    List<Store> allStores = this.getAll();

    for (Store currentStore : allStores) {
      if (currentStore.getName().equals(name) && currentStore.getAddress().equals(address)) {
        store = currentStore;
        break;
      }
    }
    return store;
  };

  /** Возвращает список объектов соответствующих всем записям в csv */
  public List<Store> getAll() throws SQLException{
    List<Store> list = new ArrayList<>();
    try {
      File file = new File("store.csv");
      FileReader reader = new FileReader(file);
      Scanner sc = new Scanner(reader);
      while (sc.hasNextLine()) {
        List<String> storeCsv = Arrays.asList(sc.nextLine().split(","));
        Store store = new Store();
        store.setId(Integer.parseInt(storeCsv.get(0)));
        store.setName(storeCsv.get(1));
        store.setAddress(storeCsv.get(2));
        list.add(store);
      }
      return list;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  };
}
