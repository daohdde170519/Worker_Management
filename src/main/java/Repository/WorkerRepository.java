/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repository;

import DataAccess.WorkerManagementDao;

/**
 *
 * @author DAO
 */
public class WorkerRepository implements IWorkerRepository {
    @Override
    public void addWorker() {
    WorkerManagementDao.Instance().createWorker();
    }

    @Override
    public void changeSalary(String action) {
    WorkerManagementDao.Instance().changeSalary(action);
    }

    @Override
    public void getInfomationSalary() {
    WorkerManagementDao.Instance().getInformationSalary();
    }
    
}
