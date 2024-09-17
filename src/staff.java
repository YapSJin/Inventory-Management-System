public class Staff {
    private int staffCode;
    private String name;
    private String ICNum;
    private String phone;
    private String email;
    private String password;

    public Staff(int staffCode, String name, String ICNum, String phone, String email, String password) {
        this.staffCode = staffCode;
        this.name = name;
        this.ICNum = ICNum;
        this.phone = phone;
        this.email = email;
        this.password = password;
    }

    public int getStaffCode() {
        return this.staffCode;
    }

    public void setStaffCode(int staffCode) {
        this.staffCode = staffCode;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getICNum() {
        return this.ICNum;
    }

    public void setICNum(String ICNum) {
        this.ICNum = ICNum;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.phone = email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getSalary() {
        return 0;
    }

    public String toString() {
        return String.format("%d|%s|%s|%s|%s|%s", staffCode, name, ICNum, phone, email, password);
    }
}