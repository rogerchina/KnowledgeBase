package com.debuglife.codelabs.crazyit.chapter17;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

//import com.sun.corba.se.spi.orbutil.fsm.Guard.Result;

public class CrazyitMap<K,V> extends HashMap<K, V> {
	public void removeByValue(Object value){
		for(Object key : keySet()){
			if(get(key) == value){
				remove(key);
				break;
			}
		}
	}
	
	public Set<V> valueSet(){
		Set<V> result = new HashSet<V>();
		for(K key : keySet()){
			result.add(get(key));
		}
		return result;
	}
	
	public K getKeyByValue(V value){
		for(K key : keySet()){
			if(get(key).equals(value) && get(key) == value){
				return key;
			}
		}
		return null;
	}
	
	@Override
	public V put(K key, V value) {
		for(V val : valueSet()){
			if(val.equals(value) && (val.hashCode() == value.hashCode())){
				throw new RuntimeException("duplicate value is no allowed in map.");
			}
		}
		return super.put(key, value);
	}
	
	
}
