import java.util.Scanner;

public class StaffDetail extends Staff {
    public StaffDetail(int staffCode, String name, String ICNum, String phone, String email, String password) {
        super(staffCode, name, ICNum, phone, email, password);
    }

    @Override
    public void viewSalary() {
        // Implementation for viewing salary (if applicable)
    }

    @Override
    public void updateDetails(Scanner scanner) {
        // Implementation for updating staff details
    }
}
