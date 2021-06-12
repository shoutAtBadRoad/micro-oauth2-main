public class Pro1449 {

    public String largestNumber(int[] cost, int target) {
        int length = cost.length;

        int[][][] dp = new int[length+1][target+1][2];

        for(int i=0;i<=length;i++)
            for(int j=0;j<=target;j++)
                dp[i][j][1] = 0;


        for(int j=0;j<=target;j++)
            dp[0][j][0] = -1;
        for(int i=0;i<=length;i++)
            dp[i][0][0] = 0;
//        dp[0][0][0] = 0;



        for(int i=1;i<=length;i++){

            for(int j=1;j<=target;j++){
                dp[i][j][0] = dp[i-1][j][0];
                for(int k=1;j-k*cost[i-1]>=0;k++){
                    if(dp[i-1][j-k*cost[i-1]][0]+k >= dp[i][j][0] && dp[i-1][j-k*cost[i-1]][0]>=0) {
                        dp[i][j][0] = dp[i - 1][j - k * cost[i - 1]][0] + k;
                        dp[i][j][1] = k;
                    }
                }
            }
        }

        StringBuilder s = new StringBuilder();
        for(int i=length,k=target;i>=0;i--){
            int c = dp[i][k][1];
            if(c >0){
                k = k-c*cost[i-1];
            }else {
                continue;
            }
            while(c>0){
                s.append(i);
                c--;
            }
        }
        String str = s.toString();
        if(str.equals(""))
            return "0";
        return str;
    }

    public static void main(String[] args) {
        int[] cost = {7,6,5,5,5,6,8,7,8};
        int target = 12;
        Pro1449 pro1449 = new Pro1449();
        System.out.println(pro1449.largestNumber(cost,target));
    }
}
