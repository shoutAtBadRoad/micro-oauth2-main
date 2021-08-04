package nums;

import java.util.Arrays;

/**
 * @author JYP
 * @date 2021/7/19
 **/

public class Pro53 {

    public int maxSubArray(int[] nums) {
        int s = nums.length;
        int[] f = new int[s+1];
        Arrays.fill(f,-1);
        for(int i=0;i<s;i++){
            if(f[i]<0){
                f[i+1] = nums[i];
            }else {
                f[i+1] = f[i]+nums[i];
            }
        }
        Arrays.sort(f);
        return f[s];
    }

    public static void main(String[] args) {
        int[] p = new int[]{-2,1,-3,4,-1,2,1,-5,4};
        int[] f = new int[]{1};
        Pro53 pro53 = new Pro53();
        int i = pro53.maxSubArray(f);
        System.out.println(i);
    }
}
