package dynamic;

public class Pro877 {

    public boolean stoneGame(int[] piles) {
        int length = piles.length;
        int[][] f = new int[length+2][length+2];

        for(int i=1;i<=length;i++)
            for(int l=1;l+i-1<=length;l++){
                int r = l+i-1;
                int a = piles[l-1] - f[l+1][r];
                int b = piles[r-1] - f[l][r-1];
                f[l][r] = Math.max(a,b);
            }
        return f[1][length] > 0;
    }

    public static void main(String[] args) {
        int[] piles = {5,3,4,5,7};
        Pro877 pro877 = new Pro877();
        System.out.println(pro877.stoneGame(piles));
    }
}
