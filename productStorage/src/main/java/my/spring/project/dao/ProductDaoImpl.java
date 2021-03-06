package my.spring.project.dao;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import my.spring.project.model.Product;

@Repository
public class ProductDaoImpl implements ProductDao {

	private JdbcTemplate template;
	
	public ProductDaoImpl(DataSource ds) {
		this.template = new JdbcTemplate(ds);
	}
	
	@Override
	public int saveProduct(Product p) {
		String query = "INSERT INTO products (name, description, create_date, place_storage, reversed)"
				+ " VALUES(?, ?, ?, ?, ?)";
		return template.update(query, p.getName(), p.getDescription(), p.getCreationDate(), p.getPlaceStorage(), p.isReversed());
	}

	@Override
	public int updateProduct(Product p) {
		String query = "UPDATE products SET name=?, description=?, create_date=?, place_storage=?, reversed=? WHERE id=?";
		return template.update(query, p.getName(), p.getDescription(), p.getCreationDate(), p.getPlaceStorage(), p.isReversed(), p.getId());
	}

	@Override
	public Product getProduct(String name) {
		String query = "SELECT * FROM products WHERE name='" + name + "'";
		ResultSetExtractor<Product> extractor = new ResultSetExtractor<Product>() {
			@Override
			public Product extractData(ResultSet rs) throws SQLException {
				if(rs.next()) {
					Long id = rs.getLong("id");
					String namep = rs.getString("name");
					String description = rs.getString("description");
					Date creationDate = rs.getDate("create_date");
					int placeStorage = rs.getInt("place_storage");
					boolean reversed = rs.getBoolean("reversed");
					return new Product(id, namep, description, creationDate, placeStorage, reversed);
				}
				return null;
			}
		};
		return template.query(query, extractor);
	}

	@Override
	public int deleteProduct(Long id) {
		String query = "DELETE FROM products WHERE id=" + id; 
		return template.update(query);
	}

	@Override
	public List<Product> getProducts() {
		String query = "SELECT * FROM products"; 
		RowMapper<Product> rm = new RowMapper<Product>() {
			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Long id = rs.getLong("id");
				String name = rs.getString("name");
				String description = rs.getString("description");
				Date creationDate = rs.getDate("create_date");
				int placeStorage = rs.getInt("place_storage");
				boolean reversed = rs.getBoolean("reversed");
				return new Product(id, name, description, creationDate, placeStorage, reversed);
			}
			
		};
		return template.query(query, rm);
	}

	
}
