package task05;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Manager implements Serializable {
    String fileName = null;

    /**
     * конструктор класса в атрибутах принимает имя файла
     * в потоке записи outStream в файл записывается пустой список сотрудников
     *
     * @param fileName
     */
    public Manager(String fileName) {
        this.fileName = fileName;
        writeToFile(new ArrayList<Employee>());
    }

    /**
     * производится считываение списка сотрудников employees с файла, методом readFromFile
     * метод writeToFile дописывает сотрудника в конец файла, при наличии сотрудника в
     * списке запись не производится
     *
     * @param employee - сотрудник
     * @return true, если сотрудник отсутсвует в списке
     * false, при наличии сторудника в списке
     */
    public boolean save(Employee employee) {
        ArrayList<Employee> employees = readFromFile();
        if (employees.contains(employee)) {
            return false;
        } else {
            employees.add(employee);
            writeToFile(employees);
            return true;
        }
    }

    /**
     * метод удаляет сотрудника из списка, при его налачия в списке
     *
     * @param employee - сотрудник
     * @return true, если сотрудник есть в списке, производится уделение из спика
     * false, сотрудник отсутвует в списке
     */
    public boolean delete(Employee employee) {
        ArrayList<Employee> employees = readFromFile();
        if (employees.contains(employee)) {
            boolean result = employees.remove(employee);
            writeToFile(employees);
            return result;
        } else return false;
    }

    /**
     * возвращает сотрудника по полному совпадению имени
     * при отсутствии сотрудника в списке, возвращается null
     *
     * @param name - имя сотрудника
     */
    public Employee getByName(String name) {
        ArrayList<Employee> employees = readFromFile();
        for (Employee employee : employees) {
            if (name.equals(employee.getName())) {
                return employee;
            }
        }
        return null;
    }

    /**
     * @param job - должность
     * @return список сотрудников с указанной должностью
     */

    public ArrayList<Employee> getByJob(Job job) {
        ArrayList<Employee> employees = readFromFile();
        ArrayList<Employee> resultList = new ArrayList<>();
        for (Employee empl : employees) {
            if ((empl.getJob().equals(job))) {
                resultList.add(empl);
            }
        }
        return resultList;
    }

    /**
     * замена должности для всех сотрудников на заданную
     *
     * @return успешность замены должности
     */
    public boolean changeAllWork(Job job) {
        ArrayList<Employee> employees = readFromFile();
        for (Employee employee : employees) {
            employee.setJob(job);
        }
        writeToFile(employees);
        return true;
    }

    /**
     * выполняет обновление, либо сохранение сотрудника
     * в зависимости от того, есть ли он уже в файле
     * сравнение сотрудников производится по имени
     *
     * @return true, если сотрудник есть в спике и происходит его обновление
     * false, если сотрудника нет в списке, то происходит сохранение в список сотрудника
     */
    public boolean saveOrUpdate(Employee employee) {
        ArrayList<Employee> employees = readFromFile();
        boolean result = employees.contains(employee);
        if (result) {
            employees.remove(employee);
        }
        employees.add(employee);
        writeToFile(employees);
        return result;
    }


    /**
     * считывание с файла списка сотрудников
     *
     * @return коллекцию со списоком сотрудников
     */
    public ArrayList<Employee> readFromFile() {
        ArrayList<Employee> list = null;
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            list = (ArrayList<Employee>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            log(Arrays.toString(e.getStackTrace()));
        }
        return list;
    }

    /**
     * производит запись колекции со списком сотрудников в файл
     *
     * @param list коллекция со списком сотрудников
     */
    public void writeToFile(ArrayList<Employee> list) {
        try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(fileName, false))) {
            outStream.writeObject(list);
        } catch (IOException e) {
            log(Arrays.toString(e.getStackTrace()));
        }
    }

    private void log(String message) {
        System.out.println(message);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(new File("log.txt"), true))) {
            writer.write(message + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}