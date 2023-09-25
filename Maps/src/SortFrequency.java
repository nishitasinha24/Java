package PartIII;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
public class SortFrequency {

    public static void sortByFrequency(ArrayList<Integer> ar) {
    	Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();
		for (int i = 0; i < ar.size(); i++) {
			int num = ar.get(i);
			if (frequencyMap.containsKey(num)) {
				frequencyMap.put(num, frequencyMap.get(num) + 1);
			} else {
				frequencyMap.put(num, 1);
			}
		}
		
		List<Map.Entry<Integer, Integer>> newList = new ArrayList<>(frequencyMap.entrySet());
		Collections.sort(newList, new Comparator<Map.Entry<Integer, Integer>>() {

			@Override
			public int compare(Entry<Integer, Integer> e1, Entry<Integer, Integer> e2) {
				return e1.getValue() - e2.getValue();
			}
		});
		
		ar.clear();
		for(Map.Entry<Integer, Integer> e:newList) {
			for(int i = 0; i < e.getValue(); i++) {
				ar.add(e.getKey());
			}
		}
            
    }
    
    public static void main(String[] args) {
            ArrayList<Integer> ar = new ArrayList<Integer>();
            for (int i=0;i<100;i++) {
                    ar.add((int)(Math.random()*10));                        
            }
            System.out.println(ar.toString());
            sortByFrequency(ar);
            System.out.println(ar.toString());
    }
}
