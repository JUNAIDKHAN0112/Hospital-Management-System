import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Doctor class
class Doctor {
    private int doctorId;
    private String name;
    private String specialization;

    public Doctor(int doctorId, String name, String specialization) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Doctor ID: " + doctorId + ", Name: " + name + ", Specialization: " + specialization;
    }
}

// Patient class
class Patient {
    private int patientId;
    private String name;
    private int age;

    public Patient(int patientId, String name, int age) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Patient ID: " + patientId + ", Name: " + name + ", Age: " + age;
    }
}

// Appointment class
class Appointment {
    private int appointmentId;
    private Doctor doctor;
    private Patient patient;
    private String date;
    private String time;

    public Appointment(int appointmentId, Doctor doctor, Patient patient, String date, String time) {
        this.appointmentId = appointmentId;
        this.doctor = doctor;
        this.patient = patient;
        this.date = date;
        this.time = time;
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentId + ", Doctor: " + doctor.getName() +
                ", Patient: " + patient.getName() + ", Date: " + date + ", Time: " + time;
    }
}

// DoctorAppointmentSystem class
class DoctorAppointmentSystem {
    private List<Doctor> doctors = new ArrayList<>();
    private List<Patient> patients = new ArrayList<>();
    private List<Appointment> appointments = new ArrayList<>();

    public void addDoctor(int doctorId, String name, String specialization) {
        Doctor doctor = new Doctor(doctorId, name, specialization);
        doctors.add(doctor);
    }

    public void addPatient(int patientId, String name, int age) {
        Patient patient = new Patient(patientId, name, age);
        patients.add(patient);
    }

    public void bookAppointment(int appointmentId, int doctorId, int patientId, String date, String time) {
        Doctor doctor = doctors.stream().filter(d -> d.getDoctorId() == doctorId).findFirst().orElse(null);
        Patient patient = patients.stream().filter(p -> p.getPatientId() == patientId).findFirst().orElse(null);

        if (doctor != null && patient != null) {
            Appointment appointment = new Appointment(appointmentId, doctor, patient, date, time);
            appointments.add(appointment);
            System.out.println("Appointment booked: " + appointment);
        } else {
            System.out.println("Doctor or Patient not found");
        }
    }

    public void viewAppointments() {
        for (Appointment appointment : appointments) {
            System.out.println(appointment);
        }
    }
}

// Main class to run the application
public class Main {
    public static void main(String[] args) {
        DoctorAppointmentSystem system = new DoctorAppointmentSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nDoctor Appointment System");
            System.out.println("1. Add Doctor");
            System.out.println("2. Add Patient");
            System.out.println("3. Book Appointment");
            System.out.println("4. View Appointments");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Doctor ID: ");
                    int doctorId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Doctor Name: ");
                    String doctorName = scanner.nextLine();
                    System.out.print("Enter Specialization: ");
                    String specialization = scanner.nextLine();
                    system.addDoctor(doctorId, doctorName, specialization);
                    break;

                case 2:
                    System.out.print("Enter Patient ID: ");
                    int patientId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Patient Name: ");
                    String patientName = scanner.nextLine();
                    System.out.print("Enter Patient Age: ");
                    int age = scanner.nextInt();
                    system.addPatient(patientId, patientName, age);
                    break;

                case 3:
                    System.out.print("Enter Appointment ID: ");
                    int appointmentId = scanner.nextInt();
                    System.out.print("Enter Doctor ID: ");
                    doctorId = scanner.nextInt();
                    System.out.print("Enter Patient ID: ");
                    patientId = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Date (YYYY-MM-DD): ");
                    String date = scanner.nextLine();
                    System.out.print("Enter Time (HH:MM AM/PM): ");
                    String time = scanner.nextLine();
                    system.bookAppointment(appointmentId, doctorId, patientId, date, time);
                    break;

                case 4:
                    system.viewAppointments();
                    break;

                case 5:
                    System.out.println("Exiting the system.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
