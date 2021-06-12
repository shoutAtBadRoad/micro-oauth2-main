package tree;

import java.util.Arrays;

public class pro96 {

    public int[] dp;

    public int numTrees(int n) {
        dp = new int[n+1];
        Arrays.fill(dp,-1);
        return generate(1,n);

    }

    public int generate(int start,int end){
        if(start>end){
            return 1;
        }
        int result=0;
        for (int i = start; i <= end; i++) {
            int left,right;
            if(dp[i-start]!=-1) {
                left = dp[i - 1 - start + 1];
            }
            else {
                left = generate(start, i - 1);
                dp[i-start] = left;
            }
            if(dp[end-i]!=-1){
                right = dp[end-i];
            }else {
                right = generate(i + 1, end);
                dp[end-i] = right;
            }
            result += left*right;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(new pro96().numTrees(19));
    }
}
