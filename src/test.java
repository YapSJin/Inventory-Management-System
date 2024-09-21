import java.util.Scanner;

public class test {

    public static void main(String[] args) {
        Scanner x = new Scanner(System.in);
        System.out.print("Are You manager (Y/N) :");
        String ID = x.next();
        if ("Y".equalsIgnoreCase(ID)) {
            staff b = new manager_detail("", "", "");

        } else if ("N".equalsIgnoreCase(ID)) {
            staff a = new staff_detail("", "", "");
        } else {
            System.out.println("please enter again");
        }
    }

}