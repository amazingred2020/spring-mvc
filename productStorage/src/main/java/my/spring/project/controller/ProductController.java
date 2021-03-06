package my.spring.project.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import my.spring.project.dao.ProductDao;
import my.spring.project.model.Product;

@RestController
public class ProductController {

	@Autowired
	private ProductDao productdao;
	
	@RequestMapping(value="/new", 
			consumes = "application/json",
			produces = "application/json", 
			method=RequestMethod.POST)
	public Product newProduct(@RequestBody Product p) throws ParseException {
		productdao.saveProduct(p);
		return productdao.getProduct(p.getName());
	}
	
	@RequestMapping(value="/edit", 
			consumes = "application/json",
			produces = "application/json;charset=UTF-8", 
			method=RequestMethod.POST)
	public Product editProduct(@RequestBody Product p) throws ParseException {
		productdao.updateProduct(p);
		return productdao.getProduct(p.getName());
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public void deleteProduct(@PathVariable Long id) throws ParseException {
		productdao.deleteProduct(id);
	}
	
	@RequestMapping(value="/")
	public ModelAndView getProductList(ModelAndView mv) {
		List<Product> products = productdao.getProducts();
		mv.setViewName("index");
		mv.addObject("productList", products);
		return mv;
	}
}
