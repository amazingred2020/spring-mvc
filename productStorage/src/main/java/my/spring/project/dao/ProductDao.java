package my.spring.project.dao;

import java.util.List;

import my.spring.project.model.Product;

public interface ProductDao {
	public int saveProduct(Product p);
	public int updateProduct(Product p);
	public Product getProduct(String name);
	public int deleteProduct(Long id);
	public List<Product> getProducts();
}
