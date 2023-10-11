/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Library;

import java.util.ArrayList;
import java.util.Scanner;
import model.Worker;

public class Validate {

    private final Scanner in = new Scanner(System.in);

    // Check if an integer input is within a specified range.
    public int validateInputIntRange(int min, int max) {
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine().trim());
                if (result < min || result > max) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Please input a number in the range [" + min + ", " + max + "]");
                System.out.print("Enter again: ");
            }
        }
    }

    // Check if a string input is not empty.
    public String validateInputString() {
        while (true) {
            String result = in.nextLine().trim();
            if (result.isEmpty()) {
                System.err.println("Input cannot be empty");
                System.out.print("Enter again: ");
            } else {
                return result;
            }
        }
    }

    // Check if a worker with the given id exists in the list.
    public boolean checkIdExist(ArrayList<Worker> lw, String id) {
        for (Worker worker : lw) {
            if (id.equalsIgnoreCase(worker.getId())) {
                return true;
            }
        }
        return false;
    }

    // Check if a salary input is greater than 0.
    public int validateInputSalary() {
        while (true) {
            try {
                int result = Integer.parseInt(in.nextLine().trim());
                if (result <= 0) {
                    throw new NumberFormatException();
                }
                return result;
            } catch (NumberFormatException e) {
                System.err.println("Salary must be greater than 0");
                System.out.print("Enter again: ");
            }
        }
    }

    // Check if a worker is not a duplicate.
    public boolean checkWorkerExist(ArrayList<Worker> lw, String id, String name, int age, int salary, String workLocation) {
        for (Worker worker : lw) {
            if (id.equalsIgnoreCase(worker.getId())
                    && name.equalsIgnoreCase(worker.getName())
                    && age == worker.getAge()
                    && salary == worker.getSalary()) {
                return false;
            }
        }
        return true;
    }

    // Close the scanner when it's no longer needed.
    public void closeScanner() {
        in.close();
    }
}
