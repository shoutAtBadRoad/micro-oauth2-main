import lombok.Data;

/**
 * @author JYP
 * @date 2021/6/13
 **/

@Data
public class Pro278 {

    private Integer bound;
    private Integer bad;

    public int firstBadVersion(int n) {
        int low = 1,high = n;

        while(true){
            int m = middle(low,high);
            boolean b = isBadVersion(m);
            if(!b){
                low = m+1;
            }else{
                high = m;
                if(high-low<=100){
                    while(true){
                        if(!isBadVersion(--high))
                            return high+1;
                    }
                }
            }
        }
    }

    public boolean isBadVersion(int n){
        if(n<bad)
            return false;
        return true;
    }

    public int middle(int num1,int num2){
        int f = num1&1,f2 = num2&1;
        int tmp = num1>>1;
        int tmp2 = num2>>1;
        tmp += tmp2;
        if((f+f2)==2){
            return tmp+1;
        }else{
            return tmp;
        }
    }

    public static void main(String[] args) {
        Pro278 pro278 = new Pro278();
        pro278.setBound(2126753390);
        pro278.setBad(1702766719);
        System.out.println(pro278.firstBadVersion(pro278.getBound()));
//        System.out.println(pro278.middle(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }
}
