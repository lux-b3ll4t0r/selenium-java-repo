package api.pojos;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class APIBrand {
	
	@JsonIgnore
	public static final List<String> VALID_TYPES = Arrays.asList("Polo", "H&M", "Madame", "Mast & Harbour", 
			"Babyhug", "Allen Solly Junior", "Kookie Kids", "Biba");
	
	private int id;
	private String brand;
	
	public int getId() {return id;}
	public void setId(int id) {this.id = id;}
	
	public String getBrand() {return brand;}
	public void setBrand(String brand) {this.brand = brand;}
	
	public String toString() {
		return "brand:[\n" + "id:" + id + "\nbrand: " + brand + "\n}";
	}
}
