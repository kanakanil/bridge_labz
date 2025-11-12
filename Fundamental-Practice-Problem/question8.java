import java.util.*;
public class question8 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int base=sc.nextInt();
        int exponent=sc.nextInt();
        int ans=1;;
        for(int i=1;i<=exponent;i++){
            ans=ans*base;
     
        }
        System.out.println(ans);
    }
}
