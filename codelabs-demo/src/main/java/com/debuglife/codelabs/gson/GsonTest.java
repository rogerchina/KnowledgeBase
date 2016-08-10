package com.debuglife.codelabs.gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * JSON --> Object 
 * Object --> JSON
 * 
 */
public class GsonTest {
    public static void main(String[] args) {
	testToJson();
	// testFromJson();
    }

    private static void testToJson() {
	Gson gson = new Gson();
	// 0. from list object
	List<Person> persons = new ArrayList<Person>();
	for (int i = 0; i < 4; i++) {
	    Person p = new Person();
	    p.setName("name" + i);
	    p.setAge(i * 5);
	    persons.add(p);
	}
	String str = gson.toJson(persons);
	System.out.println("from list object:\n " + str);

	// 1. from map object with array as value
	int i[] = { 1, 2, 3 };
	int j[] = { 4, 5, 6 };
	Map<String, int[]> map1 = new HashMap<>();
	map1.put("a", i);
	map1.put("b", j);
	String str1 = gson.toJson(map1);
	System.out.println("from map object:\n " + str1);

	// 2. from map object with Map as value
	Map<String, Map<String, int[]>> map2 = new HashMap<>();
	map2.put("map2", map1);

	String str2 = gson.toJson(map2);
	System.out.println("from map object:\n " + str2);

	// 3. from map object with List as value
	Map<String, List<Person>> map3 = new HashMap<>();
	map3.put("map3", persons);
	String str3 = gson.toJson(map3);
	System.out.println("from map object:\n" + str3);
    }

    private static void testFromJson() {
	// 1. to single object
	String jsonString = "{\"name\":\"name0\",\"age\":0}";
	Gson gson = new Gson();
	Person person = gson.fromJson(jsonString, Person.class);
	System.out.println(person);

	// 2. to list object
	String jsonString1 = "[{\"name\":\"name0\",\"age\":0}, {\"name\":\"name1\",\"age\":1}]";
	List<Person> ps = gson.fromJson(jsonString1, new TypeToken<List<Person>>() {}.getType());
	for (int i = 0; i < ps.size(); i++) {
	    Person p = ps.get(i);
	    System.out.println(p.toString());
	}
    }

}
