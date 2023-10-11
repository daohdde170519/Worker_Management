package controller;

import Library.Validate;
import model.History;
import model.Worker;
import view.Menu;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;

public class Worker_Management extends Menu<String> {
    
    private static final String[] MENU_CHOICES = {"Add Worker", "Increase Salary", "Decrease Salary", "Display Salary Information", "Exit"};
    private final ArrayList<Worker> workers;
    private final ArrayList<History> history;
    private final Validate validate;

    public Worker_Management() {
        super("======== Worker Management =========", MENU_CHOICES);
        workers = new ArrayList<>();
        history = new ArrayList<>();
        validate = new Validate();
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 -> addWorker();
            case 2 -> changeSalary("UP");
            case 3 -> changeSalary("DOWN");
            case 4 -> getInfomationSalary();
            case 5 -> System.exit(0);
            default -> System.err.println("Invalid choice. Please try again.");
        }
    }

    public void addWorker() {
        System.out.println("--------- Add Worker ----------");
        System.out.print("Enter code: ");
        String id = validate.validateInputString();
        System.out.print("Enter name: ");
        String name = validate.validateInputString();
        System.out.print("Enter age: ");
        int age = validate.validateInputIntRange(18, 50);
        System.out.print("Enter salary: ");
        int salary = validate.validateInputSalary();
        System.out.print("Enter work location: ");
        String workLocation = validate.validateInputString();

        if (!validate.checkWorkerExist(workers, id, name, age, salary, workLocation)) {
            System.err.println("Duplicate worker.");
        } else {
            workers.add(new Worker(id, name, age, salary, workLocation));
            System.err.println("Worker added successfully.");
        }
    }

    public void changeSalary(String action) {
        if (workers.isEmpty()) {
            System.err.println("Worker list is empty.");
            return;
        }
        System.out.println("------- Up/Down Salary --------");
        System.out.print("Enter code: ");
        String id = validate.validateInputString();
        Worker worker = getWorkerByCode(id);

        if (worker == null) {
            System.err.println("Worker does not exist.");
        } else {
            int currentSalary = worker.getSalary();
            System.out.print("Enter new salary: ");
            int newSalary = validate.validateInputSalary();

            if ((action.equals("UP") && newSalary <= currentSalary) || (action.equals("DOWN") && newSalary >= currentSalary)) {
                System.err.println("New salary must be " + (action.equals("UP") ? "greater" : "smaller") + " than current salary.");
            } else {
                history.add(new History(action.toUpperCase(), getCurrentDate(), worker.getId(), worker.getName(), worker.getAge(), newSalary, worker.getWorkLocation()));
                worker.setSalary(newSalary);
                System.err.println("Salary updated successfully.");
            }
        }
    }

    public void getInfomationSalary() {
        if (history.isEmpty()) {
            System.err.println("Salary history is empty.");
            return;
        }
        System.out.println("--------------------Display Information Salary-----------------------");
        System.out.printf("%-5s%-15s%-5s%-10s%-10s%-20s\n", "Code", "Name", "Age", "Salary", "Status", "Date");
        Collections.sort(history);
        
        for (History h : history) {
            printHistory(h);
        }
    }

    public Worker getWorkerByCode(String id) {
        for (Worker worker : workers) {
            if (id.equalsIgnoreCase(worker.getId())) {
                return worker;
            }
        }
        return null;
    }

    public String getCurrentDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        return dateFormat.format(calendar.getTime());
    }

    public void printHistory(History history) {
        System.out.printf("%-5s%-15s%-5d%-10d%-10s%-20s\n", history.getId(), history.getName(), history.getAge(), history.getSalary(), history.getStatus(), history.getDate());
    }
}
