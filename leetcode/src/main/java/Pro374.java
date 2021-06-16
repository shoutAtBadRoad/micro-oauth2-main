public class Pro374 {

    public int num;

    public int guessNumber(int n) {
        int low=1,high=n;
        while(low<=high){
            int m = middle(low,high);
            if(0==guess(m)){
                return m;
            }else if(-1==guess(m)){
                low = m+1;
            }else {
                high = m;
            }
        }
        return low;
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

    public int guess(int n){
        return Integer.compare(n, num);
    }


    public static void main(String[] args) {
        Pro374 pro374 = new Pro374();
        pro374.num = 6;
        System.out.println(pro374.guessNumber(10));
    }
}
