public class ProfitProb {

    public int solution(int n, int minProfit, int[] group, int[] profit){

        int len = group.length;
        int[][][] dp = new int[len+1][n+1][minProfit+1];
        for(int i=0;i<=n;i++){
            dp[0][i][0] = 1;
        }

        for(int i=1;i<len;i++){

            for(int j=0;j<=n;j++){

            }

        }

        return 1;
    }

    public static void main(String[] args) {
        int n=5;
        int minProfit = 3;
        int[] group = {2,2};
        int[] profit = {2,3};
        System.out.println(new ProfitProb().solution(n,minProfit,group,profit));
    }
}
