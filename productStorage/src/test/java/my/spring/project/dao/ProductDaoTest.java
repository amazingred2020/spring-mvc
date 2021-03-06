package my.spring.project.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import my.spring.project.config.SpringMVCConfiguration;
import my.spring.project.model.Product;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = SpringMVCConfiguration.class)
public class ProductDaoTest {
	
	@Autowired
	private ProductDao service;

	@Test
	public void testSaveProduct() throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date sqlDate = new Date(df.parse("2020-02-20").getTime());
		Product p = new Product("Яблоко", "особый сорт красных яблок", sqlDate, 5, true);
		int res = service.saveProduct(p);
		assertTrue(res > 0);
	}

	@Test
	public void testUpdateProduct() throws ParseException {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date sqlDate = new Date(df.parse("2015-02-20").getTime());
		Product p = new Product(5L,"Яблоко", "красные яблоки", sqlDate, 15, false);
		int res = service.updateProduct(p);
		assertTrue(res > 0);
	}

	@Test
	public void testGetProduct() {
		String name = "Яблоко";
		Product p = service.getProduct(name);
		assertNotNull(p);
		if(p != null) System.out.println(p.toString());
	}

	@Test
	public void testDeleteProduct() {
		Long id = 1L;
		int res = service.deleteProduct(id);
		assertTrue(res > 0);
	}

	@Test
	public void testGetProducts() {
		List<Product> products = service.getProducts(); 
		assertTrue(!products.isEmpty());
		for(Product p : products) {
			System.out.println(p.toString());
		}
	}


}
