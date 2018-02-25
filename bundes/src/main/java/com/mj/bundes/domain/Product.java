package com.mj.bundes.domain;

import java.io.Serializable;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

import com.mj.bundes.domain.Category;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Product implements Serializable, Comparable<Product>{
	
	  private static final long serialVersionUID = -6989243970039135205L;

	 @Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	 private Long id; // MySQL only accept Long or integer ID, can't be String type

	 @NotEmpty(message = "The name must not be null")
	 @Length(max = 255, message = "The field must be less than 255 characters")
	 private String name;
	 
	
	 @Length(max = 255, message = "The field must be less than 255 characters")
	 private String type;
	    
	 @Column(columnDefinition="DATETIME")
	    private Date productDate;
	 
	 @ManyToOne
	    @JoinColumn(name = "categoryId")
	    private Category productCategory;

	 @Column(name = "productViews", nullable = false, columnDefinition = "bigint(20) default 0")
	    private long productViews = 0;

	

	 @Min(value = 0, message = "Product price must no be less then zero.")
	    private double productPrice;

	private boolean active=true;
	
	@Column(columnDefinition="text")
	private String description;
	private int inStockNumber;
	
	@Transient
	private MultipartFile productImage;
	
	
	@OneToMany(mappedBy = "product")
	@JsonIgnore
	private List<ProductToCartItem> productToCartItemList;

	/******************* Comparator ************************/
    @Override
	public int compareTo(Product product){
		return new Double(this.getProductPrice()).compareTo(product.getProductPrice());
	}
    
    public static class Comparators {
    	public static Comparator<Product> PRICE = new Comparator<Product>(){
    		@Override
    		public int compare(Product p1, Product p2){
    			return new Double(p1.getProductPrice()).compareTo(p2.getProductPrice());
    		}
    	};
    	public static Comparator<Product> VIEWS = new Comparator<Product>(){
    		@Override
    		public int compare(Product p1, Product p2){
    			return new Long(p1.getProductViews()).compareTo(p2.getProductViews());
    		}
    	};
    	public static Comparator<Product> DATE = new Comparator<Product>(){
    		@Override
    		public int compare(Product p1, Product p2){
    			return (p1.getProductDate().after(p2.getProductDate())) ? 0:1;
    		}
    	};
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

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getProductDate() {
		return productDate;
	}

	public void setProductDate(Date productDate) {
		this.productDate = productDate;
	}

	public Category getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(Category productCategory) {
		this.productCategory = productCategory;
	}

	public long getProductViews() {
		return productViews;
	}

	public void setProductViews(long productViews) {
		this.productViews = productViews;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getInStockNumber() {
		return inStockNumber;
	}

	public void setInStockNumber(int inStockNumber) {
		this.inStockNumber = inStockNumber;
	}

	public MultipartFile getProductImage() {
		return productImage;
	}

	public void setProductImage(MultipartFile productImage) {
		this.productImage = productImage;
	}

	public List<ProductToCartItem> getProductToCartItemList() {
		return productToCartItemList;
	}

	public void setProductToCartItemList(List<ProductToCartItem> productToCartItemList) {
		this.productToCartItemList = productToCartItemList;
	}

	public Product(Long id, String name, String type, Date productDate, Category productCategory, long productViews,
			double productPrice, boolean active, String description, int inStockNumber, MultipartFile productImage,
			List<ProductToCartItem> productToCartItemList) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.productDate = productDate;
		this.productCategory = productCategory;
		this.productViews = productViews;
		this.productPrice = productPrice;
		this.active = active;
		this.description = description;
		this.inStockNumber = inStockNumber;
		this.productImage = productImage;
		this.productToCartItemList = productToCartItemList;
	}

	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}
    
	
	
}
