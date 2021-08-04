package nums;

import java.util.ArrayList;
import java.util.List;

/**
 * @author JYP
 * @date 2021/6/30
 **/

public class Pro78 {

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> r = new ArrayList<>();
        int len = nums.length;
        // r.add(new ArrayList<>());
        r.add(new ArrayList<>());

        for (int num : nums) {
            int s = r.size();
            for (int j = 0; j < s; j++) {
                List<Integer> t = new ArrayList<>(r.get(j));
                t.add(num);
                r.add(t);
            }
        }

        return r;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        Pro78 pro78 = new Pro78();
        List<List<Integer>> subsets = pro78.subsets(nums);
        subsets.forEach(System.out::println);
    }

}
