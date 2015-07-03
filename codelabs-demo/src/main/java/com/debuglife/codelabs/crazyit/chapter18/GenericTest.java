package com.debuglife.codelabs.crazyit.chapter18;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Map;

public class GenericTest {
	private Map<String, Integer> score;
	
	public static void main(String[] args) throws Exception{
		Class<GenericTest> clazz = GenericTest.class;
		Field field = clazz.getDeclaredField("score");
		
		Class<?> a = field.getType();
		System.out.println("score's type is: " + a);
		
		Type type = field.getGenericType();
		if(type instanceof ParameterizedType){
			ParameterizedType pType = (ParameterizedType)type;
			Type rType = pType.getRawType();
			System.out.println("the original type is " + rType);
			
			Type[] tArgs = pType.getActualTypeArguments();
			for(int i=0; i<tArgs.length; i++){
				System.out.println(i + "th generic type is: " + tArgs[i]);
			}
		}else{
			System.out.println("failed to obtain the generic type.");
		}
	}
}
