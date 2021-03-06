package my.spring.project.model;

import java.sql.Date;

public class Product {
	
	private Long id;
	private String name;
	private String description;
	private Date creationDate;
	private int placeStorage;
	private boolean reversed;
	
	public Product() {}
	
	public Product(Long id, String name, String description, Date creationDate, int placeStorage, boolean reversed) {
		this(name, description, creationDate, placeStorage, reversed);
		this.id = id;
	}
	public Product(String name, String description, Date creationDate, int placeStorage, boolean reversed) {
		this.name = name;
		this.description = description;
		this.creationDate = creationDate;
		this.placeStorage = placeStorage;
		this.reversed = reversed;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public int getPlaceStorage() {
		return placeStorage;
	}

	public void setPlaceStorage(int placeStorage) {
		this.placeStorage = placeStorage;
	}

	public boolean isReversed() {
		return reversed;
	}

	public void setReversed(boolean reversed) {
		this.reversed = reversed;
	}
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", description=" + description + ", creationDate="
				+ creationDate + ", placeStorage=" + placeStorage + ", reversed=" + reversed + "]";
	}
	
}
