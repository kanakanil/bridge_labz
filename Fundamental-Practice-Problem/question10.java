import java.util.Scanner;
public class question10 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        int Kilometers=sc.nextInt();
       float  Miles = (Kilometers * 0.621371f);
       System.out.println("Miles into Kilometer "+Miles);
    }
}
