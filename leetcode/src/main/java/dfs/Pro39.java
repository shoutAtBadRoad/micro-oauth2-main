package dfs;

import java.util.ArrayList;
import java.util.List;

public class Pro39 {

    List<List<Integer>> r = new ArrayList<>();
    List<Integer> t = new ArrayList<>();
    public List<List<Integer>> combinationSum(int[] candidates, int target) {

        search(candidates,target);

        return r;
    }

    public void search(int[] candidates,int target){
        if(target==0){
            List<Integer> l = new ArrayList<>(t);
            l.sort(Integer::compareTo);
            if(!r.contains(l)) {
                r.add(l);
            }
        }else if(target<0){
            return;
        }else {
            for (int candidate : candidates) {
                t.add(candidate);
                search(candidates, target - candidate);
                t.remove(t.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        int[] c = {2,3,5};
        Pro39 pro39 = new Pro39();
        List<List<Integer>> lists = pro39.combinationSum(c, 8);
        lists.forEach(System.out::println);
    }

}
