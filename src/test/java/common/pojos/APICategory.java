package common.pojos;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class APICategory {
	
	@JsonIgnore
	public static final List<String> VALID_TYPES = Arrays.asList("Tops", "Tshirts", "Dress", "Tops & Shirts", "Jeans", "Saree");
	
	private APIUserType usertype;
	private String category;
	
	public APIUserType getUsertype() {return usertype;}
	public void setUsertype(APIUserType usertype) {this.usertype = usertype;}
	
	public String getCategory() {return category;}
	public void setCategory(String category) {this.category = category;}
}
