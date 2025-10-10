package api.pojos;

import java.util.Objects;

public class APIProduct {
	
	private int id;
	private String name;
	private String price;
	private String brand;
	private APICategory category;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getName() {return name;}
	public void setName(String name) {this.name = name;}
	
	public String getPrice() {return price;}
	public void setPrice(String price) {this.price = price;}
	
	public String getBrand() {return brand;}
	public void setBrand(String brand) {this.brand = brand;}
	
	public APICategory getCategory() {return category;}
	public void setCategory(APICategory category) {this.category = category;}
	
	@Override
	public boolean equals(Object obj) {
		APIProduct product = (APIProduct) obj;
		
		 return id == product.id && Objects.equals(name, product.name) &&
		     Objects.equals(category.getCategory(), product.category.getCategory()) &&
		     Objects.equals(category.getUsertype().getUsertype(), product.category.getUsertype().getUsertype());
	}
	
	@Override
	public String toString() {
		return "product:[" + "id=" + id + ", name=" + name + ", brand=" + brand + 
				", category=" + category.getCategory() + ", usertype=" + category.getUsertype().getUsertype() + "]";
			
	}
}
