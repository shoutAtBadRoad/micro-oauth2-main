package lc69;

/**
 * @author JYP
 * @date 2021/6/9
 * number 879
 **/

public class Solution {

    public int profitableSchemes(int n, int minProfit, int[] group, int[] profit) {

        int mod = (int) (1e9+7);

        int[][][] dp = new int[group.length+1][n+1][minProfit+1];
        for(int i=0;i<=n;++i){
            dp[0][i][0] = 1;
        }

        for(int i=1;i<group.length+1;++i){

            for(int j=0;j<n+1;++j){
                for(int k=0;k<minProfit+1;++k){
                    dp[i][j][k] = dp[i-1][j][k];
                    if(j >= group[i-1]){
                        dp[i][j][k] += dp[i-1][j-group[i-1]][Math.max(0,k-profit[i-1])];
                        if(dp[i][j][k] >= mod)
                            dp[i][j][k] -= mod;
                    }
                }
            }
        }


        return dp[group.length][n][minProfit];
    }

    public static void main(String[] args) {
        int n = 10;
        int minProfit = 5;
        int[] group = {2,3,5};
        int[] profit = {6,7,8};
        System.out.println(new Solution().profitableSchemes(n,minProfit,group,profit));
    }
}
