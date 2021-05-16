package subVmsys;

public class subSysTest {

    int test(int a,int b)
    {
        return a*b;
    }

//    double test(int a,int b){
//        return 0.0+a+b;
//    }

    public static void main(String[] args) {
        double a = new subSysTest().test(1,2);
        System.out.println(a);
    }
}
