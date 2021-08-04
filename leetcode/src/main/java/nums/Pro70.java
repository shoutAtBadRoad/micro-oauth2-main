package nums;

/**
 * @author JYP
 * @date 2021/7/20
 **/

public class Pro70 {

    public int climbStairs(int n) {
        int[] f = new int[n+1];
        f[0] = 1;
        f[1] = 1;
        for(int i=2;i<n+1;i++){
            f[i] = f[i-1] + f[i-2];
        }
        return f[n];
    }

    public static void main(String[] args) {
        Pro70 pro70 = new Pro70();
        System.out.println(pro70.climbStairs(3));
    }
}
