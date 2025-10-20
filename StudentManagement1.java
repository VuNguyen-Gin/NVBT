
import java.util.Scanner;

public class StudentManagement1 {

    public static void main(String[] args) {


        Student1[] listStudents = new Student1[100];

        int studentCount = 0;

        Scanner scanner = new Scanner(System.in);

        String option;

        do {
            System.out.println("\n--- STUDENT MANAGEMENT MENU ---");
            System.out.println("1. Create a student");
            System.out.println("2. Display all");
            System.out.println("3. Find a student by id");
            System.out.println("4. Update a student by id");
            System.out.println("5. Quit");
            System.out.print("Select an option (1-5): ");

            option = scanner.nextLine();

            switch (option) {
                case "1":

                    if (studentCount >= 100) {
                        System.out.println("danh sach da full");
                        break;
                    }

                    Student1 newStudent = new Student1();

                    String id, name, address, gender, email;
                    int age;
                    boolean idExists;
                    do {
                        System.out.print("Enter ID: ");
                        id = scanner.nextLine();
                        idExists = false;
                        for (int i = 0; i < studentCount; i++) {

                            if (listStudents[i].id.equalsIgnoreCase(id)) {
                                System.out.println("Error: ID nay da ton tai");
                                idExists = true;
                                break;
                            }
                        }
                    } while (idExists);

                    do {
                        System.out.print("Enter Name: ");
                        name = scanner.nextLine();
                        if (name.trim().isEmpty()) {
                            System.out.println("Error: khong duoc de trong ten.");
                        }
                    } while (name.trim().isEmpty());

                    do {
                        System.out.print("Enter Age: ");
                        try {

                            age = Integer.parseInt(scanner.nextLine());
                            if (age < 18) {
                                System.out.println("Error: Yeu càu 18 tuoi tro len.");
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Nhạp vao tuoi khong hop le.");
                            age = 0;
                        }
                    } while (age < 18);

                    System.out.print("Enter Address: ");
                    address = scanner.nextLine();

                    do {
                        System.out.print("Enter Gender (Nam/Nu): ");
                        gender = scanner.nextLine();
                        if (!gender.equalsIgnoreCase("Nam") && !gender.equalsIgnoreCase("Nu")) {
                            System.out.println("Error: Hay nhap dung gioi tinh Nam or Nu ");
                        }
                    } while (!gender.equalsIgnoreCase("Nam") && !gender.equalsIgnoreCase("Nu"));

                    System.out.print("Enter Email: ");
                    email = scanner.nextLine();

                    newStudent.id = id;
                    newStudent.name = name;
                    newStudent.age = age;
                    newStudent.address = address;
                    newStudent.gender = gender;
                    newStudent.email = email;

                    listStudents[studentCount++] = newStudent;

                    System.out.println("Cap nhat thong tin sinh vien thanh cong!");
                    break;
                case "2":

                    System.out.println("\n------ ALL STUDENTS ------");
                    if (studentCount == 0) {
                        System.out.println("Khong co thong tin sinh vien nao.");
                    } else {

                        for (int i = 0; i < studentCount; i++) {
                            System.out.println("Student #" + (i + 1));

                            Student1 s = listStudents[i];

                            s.displayInfo();
                        }
                    }
                    break;

                case "3":

                    System.out.print("Enter ID to find: ");
                    String findId = scanner.nextLine();
                    boolean found = false;

                    for (int i = 0; i < studentCount; i++) {
                        Student1 s = listStudents[i];

                        if (s.id.equalsIgnoreCase(findId)) {
                            System.out.println("--- Student Found ---");

                            s.displayInfo();
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("khong tim thay sinh vien ID: " + findId);
                    }
                    break;

                case "4":

                    System.out.print("Enter ID of student to update: ");
                    String updateId = scanner.nextLine();
                    int foundIndex = -1;

                    for (int i = 0; i < studentCount; i++) {
                        if (listStudents[i].id.equalsIgnoreCase(updateId)) {
                            foundIndex = i;
                            break;
                        }
                    }

                    if (foundIndex != -1) {
                        System.out.println("--- Updating Student (ID: " + updateId + ") ---");
                        Student1 s = listStudents[foundIndex];

                        System.out.println("(Press Enter to keep current value)");


                        System.out.print("Enter new Name (current: " + s.name + "): ");
                        String newName = scanner.nextLine();
                        if (!newName.trim().isEmpty()) {
                            s.name = newName;
                        }


                        System.out.print("Enter new Age (current: " + s.age + "): ");
                        String ageString = scanner.nextLine();
                        if (!ageString.trim().isEmpty()) {
                            try {
                                int newAge = Integer.parseInt(ageString);
                                if (newAge >= 18) {
                                    s.age = newAge;
                                } else {
                                    System.out.println("Age not updated (must be >= 18).");
                                }
                            } catch (Exception e) {
                                System.out.println("Age not updated (invalid number).");
                            }
                        }

                        // --- CẬP NHẬT ĐỊA CHỈ ---
                        System.out.print("Enter new Address (current: " + s.address + "): ");
                        String newAddress = scanner.nextLine();
                        if (!newAddress.trim().isEmpty()) {
                            s.address = newAddress;
                        }


                        String newGender;
                        do {
                            System.out.print("Enter new Gender (Nam/Nu) (current: " + s.gender + "): ");
                            newGender = scanner.nextLine();
                            if (newGender.trim().isEmpty()) {
                                break;
                            }


                            if (!newGender.equalsIgnoreCase("Nam") && !newGender.equalsIgnoreCase("Nu")) {
                                System.out.println("Error: Please enter 'Nam' or 'Nu'.");
                            }


                        } while (!newGender.equalsIgnoreCase("Nam") && !newGender.equalsIgnoreCase("Nu"));

                        if (!newGender.trim().isEmpty()) {
                            s.gender = newGender;
                        }


                        System.out.print("Enter new Email (current: " + s.email + "): ");
                        String newEmail = scanner.nextLine();
                        if (!newEmail.trim().isEmpty()) {
                            s.email = newEmail;
                        }


                        System.out.println("Student information updated successfully!");

                    } else {

                        System.out.println("No student found with ID: " + updateId);
                    }
                    break;

                case "5":

                    System.out.println("Exiting program. Goodbye!");
                    break;

                default:

                    System.out.println("Invalid option. Please select from 1 to 5.");
                    break;
            }


        } while (!option.equals("5"));

        scanner.close();
    }
}
