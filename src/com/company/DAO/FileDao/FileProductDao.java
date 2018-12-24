package com.company.DAO.FileDao;

import com.company.DAO.ProductDao;
import com.company.Entity.Product;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class FileProductDao implements ProductDao {
  /**
   * Создает новую запись и соответствующий ей объект
   */
  public void insert(Product product) {
    try {
      List<Product> allProducts = this.getAll();

      Product lastProduct = allProducts.get(allProducts.size() - 1);
      int newIndex = lastProduct.getId() + 1;
      File file = new File("product.csv");
      FileWriter writer = new FileWriter(file, true);

      String newProduct = newIndex + "," + product.getName();
      writer.append("\n");
      writer.append(newProduct);
      writer.flush();
      writer.close();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * Возвращает объект соответствующий записи с первичным ключом key или null
   */
  public Product getProductById(int key) throws SQLException {
    Product product = null;
    List<Product> allProducts = this.getAll();

    for (Product currentProduct : allProducts) {
      if (currentProduct.getId() == key) {
        product = currentProduct;
        break;
      }
    }
    return product;
  }

  /**
   * Возвращает объект соответствующий записи с именем name или null
   */
  public Product getProductByName(String name) throws SQLException {
    Product product = null;
    List<Product> allProducts = this.getAll();

    for (Product currentProduct : allProducts) {
      if (currentProduct.getName().equals(name)) {
        product = currentProduct;
        break;
      }
    }
    return product;
  }

  /**
   * Возвращает список объектов соответствующих всем записям в csv
   */
  public List<Product> getAll() throws SQLException {
    List<Product> list = new ArrayList<>();
    try {
      File file = new File("product.csv");
      FileReader reader = new FileReader(file);
      Scanner sc = new Scanner(reader);
      while (sc.hasNextLine()) {
        List<String> productCsv = Arrays.asList(sc.nextLine().split(","));
        Product product = new Product();
        product.setId(Integer.parseInt(productCsv.get(0)));
        product.setName(productCsv.get(1));
        list.add(product);
      }
      return list;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return list;
  }

}
