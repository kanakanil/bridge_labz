import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        
        long sum = 0;
        while (n > 0) {
            sum += n %10;
        }
        System.out.println(sum);
    }
}
