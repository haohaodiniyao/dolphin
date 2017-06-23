package org.dolphin.commons.jackson;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Fasterxml_jackson_test {
    /**
     * http://wiki.fasterxml.com/JacksonInFiveMinutes#Full_Data_Binding_.28POJO.29_Example
     * @param args
     */
	public static void main(String[] args) {
		try {
			List<User> list = new ArrayList<User>();
			for(int i=0;i<3;i++){
				User user = new User();
				user.setName("小石头"+i);
				user.setAge(18+i);
//				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				user.setBirthday(new Date());
				list.add(user);
			}
			ObjectMapper objectMapper = new ObjectMapper();
			String json = objectMapper.writeValueAsString(list);
			System.out.println(json);
//			System.out.println(json);
//			List<Map<String,Object>> users = objectMapper.readValue(json, objectMapper.getTypeFactory().constructParametricType(List.class, Map.class));
//			System.out.println(users);
			
			List<User> users = objectMapper.readValue(json, new TypeReference<List<User>>() { });
			System.out.println(users);
			
			//读取json文件
//			User user = objectMapper.readValue(new File("user.json"), User.class);
//			System.out.println(user);
			
			
//			User user = new User();
//			user.setName("小石头123");
//			user.setAge(18);
//			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
//			user.setBirthday(format.parse("1989-11-11"));
//			objectMapper.writeValue(new File("user.json"), user);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
