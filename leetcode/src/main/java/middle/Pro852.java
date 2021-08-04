package middle;

/**
 * @author JYP
 * @date 2021/6/15
 **/

public class Pro852 {
    public int peakIndexInMountainArray(int[] arr) {
        int length = arr.length;
        int low = 0,high = length-1;
        while(low <= high){
            int m = (low+high)/2;
            if(arr[m] > arr[m-1] && arr[m] > arr[m+1]){
                return m;
            }else if(arr[m] < arr[m+1]){
                low = m+1;
            }else{
                high = m;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        int[] arr = {24,69,100,99,79,78,67,36,26,19};
        System.out.println(new Pro852().peakIndexInMountainArray(arr));
    }
}
