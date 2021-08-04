package nums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author JYP
 * @date 2021/7/20
 **/

public class Pro136 {

    public int singleNumber(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        int len = nums.length;

        for (int num : nums) {
            if (map.containsKey(num)) {
                Integer integer = map.get(num);
                map.put(num, integer + 1);
            } else {
                map.put(num, 1);
            }
        }
        for(Map.Entry<Integer,Integer> m : map.entrySet()){
            if(m.getValue()==1){
                return m.getKey();
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] p = new int[]{2,2,1};
        Pro136 pro136 = new Pro136();
        int i = pro136.singleNumber(p);
        System.out.println(i);
    }

}
