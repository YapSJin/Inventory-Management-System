import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class report_incidents {

    private String ID, reason, dateAbsent, approved = "waiting";
    Scanner c = new Scanner(System.in);
    String line;
    ArrayList<String> al = new ArrayList<String>();

    public String getReason() {
        return reason;
    }

    public String getDateAbsent() {
        return dateAbsent;
    }

    public String getApproved() {
        return approved;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setdateAbsent(String dateAbsent) {
        this.dateAbsent = dateAbsent;
    }

    public void setApproved(String approved) {
        this.approved = approved;
    }

    public void report(String name, String ID) {

        String function = "report_incident";
        Date date = new Date();
        String i = String.valueOf(date);
        new history().saving(name, ID, function, i);

        String id;
        do {
            System.out.print("\nEnter Your Staff ID : ");
            id = c.next();
        } while (id.equalsIgnoreCase(this.ID));
        this.ID = id;

        System.out.print("Enter Your Reason for Absent (must use underscore replace space) : ");
        this.reason = c.nextLine();
        this.reason = c.nextLine();
        // underscore

        System.out.print("Enter The date Absent (XX/XX/XXXX) : ");
        this.dateAbsent = c.nextLine();

        try {
            FileWriter fw = new FileWriter("report.txt", true);
            String a = word();
            fw.append(a);
            fw.append("\n");
            fw.close();
            System.out.println("\n------------------------------------------------------");
            System.out.println("The Incident Record Is Added Succesfully Inside System");
            System.out.println("------------------------------------------------------");
        } catch (IOException e) {
            System.out.println(e);
        }

        System.out.print("Are You Manager (Y/N): ");
        String comfirm = c.next();
        char choose = comfirm.charAt(0);

        if (choose == 'Y' || choose == 'y') {

            System.out.print("\nEnter Your Manager ID (Mxxx) : ");
            String MID = c.nextLine();
            MID = c.nextLine();

            System.out.print("Enter Your Password : ");
            String Password = c.nextLine();

            try {
                BufferedReader br = new BufferedReader(new FileReader("staffdetail.txt"));
                int count = 0;

                while ((line = br.readLine()) != null) {
                    if (line.contains(MID) && line.contains(Password)) {
                        Approvement();
                        count++;
                    }

                }
                if (count == 0) {
                    System.out.println("you are not manager ");

                }
            } catch (Exception e) {
                System.out.println(e);
            }

        } else if (choose == 'N' || choose == 'n') {
            System.out.println("\nThank You for Your Visit Here ");
        } else {
            System.out.println("\nYou Enter The Wrong Word Please Enter Again ");
        }

    }

    public void Approvement() {

        int count = 0;
        String f;
        try {
            c = new Scanner(new File("report.txt"));
            Scanner x = new Scanner(System.in);

            int num = 0;
            while (c.hasNext()) {
                this.ID = c.next();
                this.reason = c.next();
                this.dateAbsent = c.next();
                this.approved = c.next();
                System.out.println("");

                System.out.printf(toString());
                System.out.print("\nDo You Approve The Reason ? Approve (A) or Reject(R) : ");
                setApproved(x.next());

                try {
                    FileWriter fw = new FileWriter("incident.txt", true);
                    fw.append(toString());
                    fw.append("\n");
                    fw.close();
                } catch (IOException e) {
                    System.out.println(e);
                }

            }
        } catch (Exception e) {
            System.out.print(e);
        }
        System.out.println("----------------------------------");
        System.out.println("All Record Is Added Succesfully");
        System.out.println("----------------------------------");

        try {
            BufferedReader br = new BufferedReader(new FileReader("incident.txt"));
            System.out.println("\nThis is all the records that find inside file ");
            System.out.print(
                    "----------------------------------------------------------------------------------------\n");
            while ((line = br.readLine()) != null) {

                System.out.println(line);

            }

        } catch (Exception e) {
            System.out.println(e);
        } finally {
            System.out.println(
                    "----------------------------------------------------------------------------------------");

        }

    }

    public String word() {
        return "" + this.ID + " " + this.reason + " " + this.dateAbsent + " " + this.approved;
    }

    @Override
    public String toString() {
        return "Staff ID : " + this.ID + " the Absent Reason : " + this.reason + " the Absent date : " + this.dateAbsent
                + " the approvement :" + this.approved;
    }
}