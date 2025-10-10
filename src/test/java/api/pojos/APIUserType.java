package api.pojos;

import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class APIUserType {

	@JsonIgnore
	public static final List<String> VALID_TYPES = Arrays.asList("Women", "Men", "Kids");
	
	private String usertype;
	
	public String getUsertype() {return usertype;}
	public void setUsertype(String usertype) {this.usertype = usertype;}
}
