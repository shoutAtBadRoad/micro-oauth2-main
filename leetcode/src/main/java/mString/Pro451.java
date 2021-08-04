package mString;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @author JYP
 * @date 2021/7/3
 **/

public class Pro451 {

    class MapValueComparator implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> me1, Map.Entry<String, Integer> me2) {
            return me2.getValue().compareTo(me1.getValue());

        }

    }

    public String frequencySort(String s) {
        StringBuilder sb = new StringBuilder();
        Map<String,Integer> map = new TreeMap<>();

        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if (map.containsKey(String.valueOf(c))) {
                map.put(String.valueOf(c),map.get(String.valueOf(c))+1);
            }else {
                map.put(String.valueOf(c),1);
            }
        }
        List<Map.Entry<String, Integer>> entries = new ArrayList<>( map.entrySet());
        Collections.sort(entries,new MapValueComparator());
        entries.forEach((e)->{
            for(int i=0;i<e.getValue();i++)
                sb.append(e.getKey());
        });

        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "asdaashdncaaadd";
        Pro451 pro451 = new Pro451();
        System.out.println(pro451.frequencySort(s));
    }
}
