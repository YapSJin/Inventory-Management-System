public class ParttimeStaff extends Staff {
    private int workHours;

    public ParttimeStaff(int staffCode, String name, String ICNum, String phone, String email, String password, int workHours) {
        super(staffCode, name, ICNum, phone, email, password);
        this.workHours = workHours;
    }

    public int getWorkHours() {
        return workHours;
    }

    public void setWorkHours(int workHours) {
        this.workHours = workHours;
    }

    public double getSalary() {
        return workHours * 100;
    }

    public String toString() {
        return super.toString() + String.format("|%d", this.workHours);
    }
}
