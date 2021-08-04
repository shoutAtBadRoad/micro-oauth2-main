/**
 * @author JYP
 * @date 2021/6/13
 **/

public class Pro121 {

    public int maxProfit(int[] prices) {
        int length = prices.length;
        int[][] f = new int[length+1][2];
        f[0][0] = 0;
        f[0][1] = prices[0];

        for(int i=1;i<=length;i++){
            f[i][0] = Math.max(f[i-1][0],prices[i-1]-f[i-1][1]);
            f[i][1] = Math.min(prices[i-1], f[i - 1][1]);
        }

        return f[length][0];

    }

    public static void main(String[] args) {
        Pro121 pro121 = new Pro121();
        int[] profits = {7,6,4,3,1};
        System.out.println(pro121.maxProfit(profits));
    }
}
