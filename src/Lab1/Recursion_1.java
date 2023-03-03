package Lab1;

public class Recursion_1 {
    public static void main(String[] args) {

        tower(3,'a','b','c');

    }

    static void tower(int n , char from ,char to ,char help){
        if (n==1){
            System.out.println("Move disk 1 from rod " +from + " to rod "+to);
            return;
        }
        tower(n-1,from,help,to);
        System.out.println("Move disk "+n +" from rod "+from + " to rod "+to);
        tower(n-1,help,to,from);
    }



}
