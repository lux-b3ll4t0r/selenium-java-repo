package common.pojos;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Product {
		
	private String img, name, category, price, quantity, availability, condition, brand, total;
	private boolean addToCart, viewProduct;
	
	public static class Builder {
		private final Product product;
		
		public Builder() {
			product = new Product();			
		}
		
		public Builder img(String img) {
			this.product.setImg(img);
			return this;
		}
		
		public Builder name(String name) {
			this.product.setName(name);
			return this;
		}
		
		public Builder category(String category) {
			this.product.setCategory(category);
			return this;
		}
		
		public Builder price(String price) {
			this.product.setPrice(price);
			return this;
		}
		
		public Builder quantity(String quantity) {
			this.product.setQuantity(quantity);
			return this;
		}
		public Builder availability(String availability) {
			this.product.setAvailability(availability);
			return this;
		}
		public Builder condition(String condition) {
			this.product.setCondition(condition);
			return this;
		}
		public Builder brand(String brand) {
			this.product.setBrand(brand);
			return this;
		}
		
		public Builder total(String total) {
			this.product.setTotal(total);
			return this;
		}
		
		public Builder addToCart(boolean visible) {
			this.product.setAddToCart(visible);
			return this;
		}
		
		public Builder viewProduct(boolean visible) {
			this.product.setViewProduct(visible);
			return this;
		}
		
		public Product build() {
			return this.product;
		}
	}
	
	public String getImg() {return this.img;}
	public void setImg(String img) {this.img = img;}
	
	public String getName() {return this.name;}
	public void setName(String name) {this.name = name;}
	
	public String getCategory() {return this.category;}
	public void setCategory(String category) {this.category = category;}
	
	public String getPrice() {return price;}
	public void setPrice(String price) {this.price = price;}
	
	public String getQuantity() {return quantity;}
	public void setQuantity(String quantity) {this.quantity = quantity;}
	
	public String getAvailability() {return availability;}
	public void setAvailability(String availablity) {this.availability = availablity;}
	
	public String getCondition() {return condition;}
	public void setCondition(String condition) {this.condition = condition;}
	
	public String getBrand() {return brand;}
	public void setBrand(String brand) {this.brand = brand;}
	
	public String getTotal() {return total;}
	public void setTotal(String total) {this.total = total;}
	
	public boolean getAddToCart() {return addToCart;}
	public void setAddToCart(boolean visible) {this.addToCart = visible;}
	
	public boolean getViewProduct() {return viewProduct;}
	public void setViewProduct(boolean visible) {this.viewProduct = visible;}
	
	public String toString() {
		Map<String, Object> map = this.getAsMap();
		StringBuilder prettyString = new StringBuilder("(Product:\n");		
		
		for(Map.Entry<String, Object> entry : map.entrySet()) {
			if(entry.getValue() != null) {
				prettyString.append(entry.getKey()).append(":").append(entry.getValue()).append("\n");
			}
		}
		prettyString.append(")");
		
		return prettyString.toString();
	}
	
	public List<String> toList(){
		List<String> list = new ArrayList<>();
		list.add(this.getImg());
		list.add(this.getName());
		list.add(this.getCategory());
		list.add(this.getPrice());
		list.add(this.getQuantity());
		list.add(this.getAvailability());
		list.add(this.getCondition());
		list.add(this.getBrand());
		Collections.sort(list);
		
		return list;
	}
	
	public Map<String, Object> getAsMap(){
		Map<String, Object> productMap = new HashMap<>();
		productMap.put("img", this.getImg());
		productMap.put("name", this.getName());
		productMap.put("category", this.getCategory());
		productMap.put("price", this.getPrice());
		productMap.put("quantity", this.getQuantity());
		productMap.put("availability", this.getAvailability());
		productMap.put("condition", this.getCondition());
		productMap.put("brand", this.getBrand());
		productMap.put("total", this.getTotal());
		
		return productMap;
	}
}
