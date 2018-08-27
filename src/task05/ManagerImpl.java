package task05;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManagerImpl implements Manager {
    private File file;

    public ManagerImpl(File file) {
        this.file = file;
        try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(file, false))) {
            outStream.writeObject(new ArrayList<Employee>());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean save(Employee employee) {
        try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(file));
             ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(file, false))) {
            List<Employee> employees = (List<Employee>) inStream.readObject();
            if (employees.contains(employee)) {
                return false;
            } else {
                employees.add(employee);
                outStream.writeObject(employees);
                return true;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(Employee employee) {
        try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(file));
             ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(file, false))) {
            List<Employee> employees = (List<Employee>) inStream.readObject();
            if (employees.contains(employee)) {
                boolean result = employees.remove(employee);
                outStream.writeObject(employees);
                return result;
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public Employee getByName(String name) {
        try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(file))) {
            List<Employee> employees = (List<Employee>) inStream.readObject();
            for (Employee employee : employees) {
                if (name.equals(employee.getName())) {
                    return employee;
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> getByJob(Job job) {
        return null;
    }

    @Override
    public boolean saveOrUpdate(Employee employee) {
        return false;
    }

    @Override
    public boolean changeAllWork(Job job) {
        return false;
    }
}
