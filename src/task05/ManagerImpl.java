package task05;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManagerImpl implements Manager {
    private File file;

    /**
     * конструктор класса в атрибутах принимает файл
     * в потоке записи outStream в файл записывается пустой список сотрудников
     * @param file
     */
    public ManagerImpl(File file) {
        this.file = file;
        try (ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream(file, false))) {
            outStream.writeObject(new ArrayList<Employee>());
        } catch (IOException e) {
            log(Arrays.toString(e.getStackTrace()));
        }
    }

    /**
     * производится считываение списка сотрудников employees с файла, потоком inStream
     * поток записи outStream  дописывает сотрудника в конец файла, при наличии сотрудника в
     * списке запись не производится
     * @param employee - сотрудник
     * @return true, если сотрудник отсутсвует в списке
     *          false, при наличии сторудника в списке
     */
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


    /**
     * метод удаляет сотрудника из списка, при его налачия в списке
     * @param employee - сотрудник
     * @return true, если сотрудник есть в списке, производится уделение из спика
     *          false, сотрудник отсутвует в списке
     */
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

    /**
     * возвращает сотрудника по полному совпадению имени
     * при отсутствии сотрудника в списке, возвращается null
     * @param name - имя сотрудника
     */
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

    /**
     *
     * @param job - должность
     * @return список сотрудников с указанной должностью
     */
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

    /**
     * выполняет обновление, либо сохранение сотрудника
     * в зависимости от того, есть ли он уже в файле
     * @return true, если сотрудник есть в спике и происходит его обновление
     *      false, если сотрудника нет в списке, то происходит сохранение в список сотрудника
     */
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

    /**
     * замена должности для всех сотрудников на заданную
     * @return успешность замены должности
     */
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

    /**
     * метод производит запись в файл исключения возникшие при работе программы
     * @param message исключение с описанием возникшей ошибки в ходе работы программы
     */
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