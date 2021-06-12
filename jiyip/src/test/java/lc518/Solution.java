package lc518;

/**
 * @author JYP
 * @date 2021/6/10
 **/

class Solution {
    public int change(int amount, int[] coins) {
        int length = coins.length;
        int[][] dp = new int[length+1][amount+1];

        dp[0][0] = 1;

        for(int i=1;i<=length;++i){
            for(int j=0;j<=amount;++j) {
                dp[i][j] += dp[i - 1][j];
                    int p = 1;
                    while (j - coins[i-1] * p >= 0) {
                        dp[i][j] += dp[i-1][j - coins[i-1] * p];
                        p++;
                    }
            }
        }

        return dp[length][amount];
    }

    public static void main(String[] args) {
        int amount = 5;
        int[] coins = {1,2,5};
        System.out.println(new Solution().change(amount,coins));
    }
}
