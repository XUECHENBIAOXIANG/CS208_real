package Lab1;

public class Recursion_2 {
    public static void main(String[] args) {
        int x=3;
        int []z=new int[4];
        System.out.println(tile(x,z));

    }
    static int tile(int n ,int zong[]) {
       if (zong[n]>0){
           return zong[n];
       }
       if (n==1){
           zong[1]=1;
           return 1;

       }
       if (n==2){
           zong[2]=2;
           return 2;
       }
       zong[n]=tile(n-1,zong)+2*tile(n-2,zong);
       return zong[n];

    }
}
