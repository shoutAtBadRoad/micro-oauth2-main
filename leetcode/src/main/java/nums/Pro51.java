package nums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author JYP
 * @date 2021/6/28
 **/

public class Pro51 {

    List<int[]> p = new ArrayList<>();
    List<String> r1 = new ArrayList<>();
    List<List<String>> r = new ArrayList<>();
    public List<List<String>> solveNQueens(int n) {
        search(n,0);
        return r;
    }

    public void search(int n,int k){
        if(k==n){
            r.add(new ArrayList<>(r1));
            return;
        }
        for(int i=0;i<n;i++){
            int flag = 0;
            for(int[] t : p){
                if (t[1] == i || Math.abs(t[0]-k)==Math.abs(t[1]-i)) {
                    flag = 1;
                    break;
                }
            }
            if(flag==1)
                continue;
            p.add(new int[]{k, i});
            StringBuilder sb = new StringBuilder();
            for(int j=0;j<i;j++)
                sb.append(".");
            sb.append("Q");
            for(int j=0;j<(n-i-1);j++)
                sb.append(".");
            r1.add(sb.toString());
            search(n,k+1);
            r1.remove(r1.size()-1);
            p.remove(p.size()-1);
        }
    }

    public static void main(String[] args) {
        Pro51 pro51 = new Pro51();
        List<List<String>> lists = pro51.solveNQueens(8);
        lists.forEach(System.out::println);
        System.out.println(lists.size());
    }
}
