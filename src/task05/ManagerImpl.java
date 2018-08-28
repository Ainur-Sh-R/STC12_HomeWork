package task05;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManagerImpl implements Manager {
    private File file;

    public ManagerImpl(File file) {
        this.file = file;
        try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(file, false))) {
            outStream.writeObject(new ArrayList<Employee>());
        } catch (IOException e) {
            log(Arrays.toString(e.getStackTrace()));
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
            log(Arrays.toString(e.getStackTrace()));
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
            log(Arrays.toString(e.getStackTrace()));
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
            log(Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

    @Override
    public List<Employee> getByJob(Job job) {
        try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(file))) {
            List<Employee> resultList = (List<Employee>) inStream.readObject();
            for (Employee empl : resultList) {
                if (!(empl.getJob().equals(job))) {
                    resultList.remove(empl);
                }
                return resultList;
            }
        } catch (IOException | ClassNotFoundException e) {
            log(Arrays.toString(e.getStackTrace()));
        }
        return null;
    }

    @Override
    public boolean saveOrUpdate(Employee employee) {
        try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(file));
             ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(file, false))) {
            List<Employee> employees = (List<Employee>) inStream.readObject();
            boolean result = employees.contains(employee);
            if (result) {
                employees.remove(employee);
            }
            employees.add(employee);
            outStream.writeObject(employees);
            return result;
        } catch (IOException |
                ClassNotFoundException e) {
            log(Arrays.toString(e.getStackTrace()));
        }
        return false;
    }

    @Override
    public boolean changeAllWork(Job job) {
        try (ObjectInputStream inStream = new ObjectInputStream(new FileInputStream(file));
             ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(file, false))) {
            List<Employee> employees = (List<Employee>) inStream.readObject();
            for (Employee employee : employees){
                employee.setJob(job);
            }
            outStream.writeObject(employees);
        } catch (IOException | ClassNotFoundException e) {
            log(Arrays.toString(e.getStackTrace()));
        }

        return false;
    }

    private void log(String message) {
        System.out.println(message);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File("log.txt"), true))) {
            writer.write(message);
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}