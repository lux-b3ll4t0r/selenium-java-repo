package common.utils;

import java.util.Map;


public class MapTools {
	
	
public static Map<String, Object> swapMapValues(Map<String, Object> map1, Map<String, Object> map2){
		
		for(Map.Entry<String, Object> entry : map2.entrySet()) {
			if(map1.containsKey(entry.getKey())) {
				map1.put(entry.getKey(), entry.getValue());
			}
		}
		
		return map1;
	}
}
