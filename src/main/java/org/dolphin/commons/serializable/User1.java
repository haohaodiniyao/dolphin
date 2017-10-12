package org.dolphin.commons.serializable;

import java.io.Serializable;

/**
 * Created by hollis on 16/2/17.
 * 实现Serializable接口
 */
public class User1 implements Serializable {
 
    private String name;
    private int age;
    private int sex;
 
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }
 
    public int getAge() {
        return age;
    }
 
    public void setAge(int age) {
        this.age = age;
    }

	public int getSex() {
		return sex;
	}

	public void setSex(int sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		return "User1 [name=" + name + ", age=" + age + ", sex=" + sex + "]";
	}
 
    
}