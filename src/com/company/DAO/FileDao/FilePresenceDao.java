package com.company.DAO.FileDao;

import com.company.DAO.*;
import com.company.Entity.Presence;
import com.company.Entity.Product;
import com.company.Entity.Store;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FilePresenceDao implements PresenceDao {
  /** Создает новую запись и соответствующий ей объект */
  @Override
  public void insert(Presence presence){
    try {
      File file = new File("presence.csv");
      FileWriter writer = new FileWriter(file, true);

      String newPresence = presence.getStore().getId() + "," + presence.getProduct().getId() + ","
              + presence.getPrice() + "," + presence.getQuantity();
      writer.append("\n");
      writer.flush();
      writer.append(newPresence);
      writer.flush();
      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  };

  @Override
  public Presence getPresenceByStoreAndProduct(Store store, Product product) throws SQLException{
    Presence presence = null;
    List<Presence> allPresences = this.getAll();

    for (Presence currentPresence : allPresences) {
      if ((currentPresence.getStore().getId() == store.getId())
              && (currentPresence.getProduct().getId() == product.getId())) {
        presence = currentPresence;
        break;
      }
    }
    return presence;
  };

  @Override
  public void updateQuantity(Presence presence, int amountOfChange){
    try {
      List<Presence> allPresences = this.getAll();
      for (Presence currentPresence : allPresences){
        if (presence.equals(currentPresence)){
          currentPresence.setQuantity(currentPresence.getQuantity()+amountOfChange);
          presence.setQuantity(presence.getQuantity()+amountOfChange);
          break;
        }
      }

      File file = new File("presence.csv");
      FileWriter writer = new FileWriter(file);
      StringBuilder newPresenceFile = new StringBuilder();
      int size = allPresences.size();
      for (Presence currentPresence : allPresences) {
//        newPresenceFile.append(currentPresence.getStore().getId() + "," + currentPresence.getProduct().getId() + ","
//                + currentPresence.getPrice() + "," + currentPresence.getQuantity());
//        if (size != 0){
//          size--;
//          newPresenceFile.append('\n');
//        }
        writer.append(Integer.toString(currentPresence.getStore().getId()));
        writer.append(",");
        writer.append(Integer.toString(currentPresence.getProduct().getId()));
        writer.append(",");
        writer.append(Double.toString(currentPresence.getPrice()));
        writer.append(",");
        writer.append(Integer.toString(currentPresence.getQuantity()));
        if (size != 1){
          size--;
          writer.append("\n");
        }
        writer.flush();
      }
      writer.write(newPresenceFile.toString());
      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  };

  @Override
  public void updatePrice(Presence presence, double newPrice){
    try {
      List<Presence> allPresences = this.getAll();
      for (Presence currentPresence : allPresences){
        if (presence.equals(currentPresence)){
          currentPresence.setPrice(newPrice);
          presence.setPrice(newPrice);
          break;
        }
      }

      File file = new File("presence.csv");
      FileWriter writer = new FileWriter(file);
      int size = allPresences.size();
      for (Presence currentPresence : allPresences) {
        writer.append(Integer.toString(currentPresence.getStore().getId()));
        writer.append(",");
        writer.append(Integer.toString(currentPresence.getProduct().getId()));
        writer.append(",");
        writer.append(Double.toString(currentPresence.getPrice()));
        writer.append(",");
        writer.append(Integer.toString(currentPresence.getQuantity()));
        if (size != 0){
          size--;
          writer.append("\n");
        }
        writer.flush();
      }
      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  };

  /** Возвращает список объектов соответствующих всем записям в csv */
  @Override
  public List<Presence> getAll() throws SQLException{
    List<Presence> list = new ArrayList<>();
    try {
      File file = new File("presence.csv");
      FileReader reader = new FileReader(file);
      Scanner sc = new Scanner(reader);

      while (sc.hasNextLine()) {
        List<String> presenceCsv = Arrays.asList(sc.nextLine().split(","));

        StoreDao storeDao = new FileStoreDao();
        ProductDao productDao = new FileProductDao();

        Presence presence = new Presence();
        presence.setStore(storeDao.getStoreById(Integer.parseInt(presenceCsv.get(0))));
        presence.setProduct(productDao.getProductById(Integer.parseInt(presenceCsv.get(1))));
        presence.setPrice(Double.parseDouble(presenceCsv.get(2)));
        presence.setQuantity(Integer.parseInt(presenceCsv.get(3)));
        list.add(presence);
      }
      return list;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  };
}
