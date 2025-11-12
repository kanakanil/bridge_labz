import java.util.Scanner;

public class question6 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        float Principal=sc.nextFloat();
        float Rate=sc.nextFloat();
        float Time=sc.nextFloat();
        float Simple_Interest=(Principal * Rate * Time) / 100f;
        System.out.println("Simple Interest :"+Simple_Interest);
    }
}
