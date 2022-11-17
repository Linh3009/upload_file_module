package service;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService {
    public List<Product> products = new ArrayList<>();

    public ProductService(){
        products.add(new Product(1,"Phong","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcReF-kUWCJV7sVD4Zu9bkXzZUYsiC8aebL56w&usqp=CAU",100,true));
        products.add(new Product(2,"Định","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQfuYSwdxsFHeOFFEDklZ1ZS9vkAOZT538Uj_2eKIOPKtcnpaNlEob-JY5qut3E8a3JEJc&usqp=CAU",200,true));

    }

    public void add(Product product) {
        products.add(product);
    }

    public int findIndexById(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                return i;
            }
        }
        return -1;
    }
    public void edit(int id, Product product){
        int index = findIndexById(id);
        if (index != -1){
            products.set(index,product);
        }
    }
    public void delete(int id){
        int index = findIndexById(id);
        if (index != -1){
            products.remove(index);
        }
    }
}
