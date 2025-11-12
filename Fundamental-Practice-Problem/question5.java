import java.util.*;
public class question5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        
        int radius = sc.nextInt();
        
        int height=sc.nextInt();
    
        float volume  = 3.14f * radius * radius * height;
        
        
        System.out.println("The volume of the cylinder is " + volume);
    }
}
