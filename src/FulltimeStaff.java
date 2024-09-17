public class FulltimeStaff extends Staff {
    private double bonus;
    private double fine;
    private double baseSalary;

    public FulltimeStaff(int staffCode, String name, String ICNum, String phone, String email, String password, double bonus, double fine, double baseSalary) {
        super(staffCode, name, ICNum, phone, email, password);
        this.bonus = bonus;
        this.fine = fine;
        this.baseSalary = baseSalary;
    }

    public double getBonus() {
        return this.bonus;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public double getFine() {
        return this.fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public double getBaseSalary() {
        return this.baseSalary;
    }

    public void setBaseSalary(double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public double getSalary() {
        return baseSalary + bonus - fine;
    }

    public String toString() {
        return super.toString() + String.format("|%.2f|%.2f|%.2f", this.bonus, this.fine, this.baseSalary);
    }
}