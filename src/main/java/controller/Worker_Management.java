package controller;

import Repository.IWorkerRepository;
import Repository.WorkerRepository;
import view.Menu;

public class Worker_Management extends Menu<String> {
    private IWorkerRepository wokerRepository;
    private static final String[] MENU_CHOICES = {"Add Worker", "Increase Salary", "Decrease Salary", "Display Salary Information", "Exit"};

    public Worker_Management() {
        super("======== Worker Management =========", MENU_CHOICES);
        wokerRepository = new WorkerRepository();
    }

    @Override
    public void execute(int choice) {
        switch (choice) {
            case 1 -> wokerRepository.addWorker();
            case 2 -> wokerRepository.changeSalary("UP");
            case 3 -> wokerRepository.changeSalary("DOWN");
            case 4 -> wokerRepository.getInfomationSalary();
            case 5 -> System.exit(0);
            default -> System.err.println("Invalid choice. Please try again.");
        }
    }
}
