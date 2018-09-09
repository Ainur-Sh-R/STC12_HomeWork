package task05;

public class Main {
    public static void main(String[] args) {
        Manager manager = new Manager("E:\\test.dat");

        //создаем сотрудников с указанием имени, возраста, зарплаты, профессии
        Employee emp1 = new Employee("Ivan", 36, 35000, Job.BUILDER);
        Employee emp2 = new Employee("Anton", 42, 50000, Job.PROGRAMMER);
        Employee emp3 = new Employee("Jeka", 25, 41000, Job.BUILDER);
        Employee emp4 = new Employee("Konstantin", 69, 60000, Job.WORKER);
        Employee emp5 = new Employee("Tolik", 21, 25000, Job.PROGRAMMER);

        //добавляем сотрудников в файл
        boolean t1 = manager.save(emp1);
        boolean t2 = manager.save(emp2);
        boolean t3 = manager.save(emp3);
        boolean t4 = manager.save(emp4);
        boolean t5 = manager.save(emp5);
        boolean t6 = manager.save(emp2);

        System.out.println("Успешность записи в файл");
        System.out.println("t1: " + t1);
        System.out.println("t2: " + t2);
        System.out.println("t3: " + t3);
        System.out.println("t4: " + t4);
        System.out.println("t5: " + t5);
        System.out.println("t6: " + t6);
        System.out.println();

        //удаляем сотрудника из файла
        System.out.println("Удалили сотрудника из файла по полному совпадению имени");
        manager.delete(manager.getByName("Konstantin"));
        for (Employee employ: manager.readFromFile()) {
            System.out.println(employ);
        }
        System.out.println();

        //список сотрудников с указанной должностью
        System.out.println("Cписок сотрудников с указанной должностью");
        for (Employee employ: manager.getByJob(Job.PROGRAMMER)) {
            System.out.println(employ);
        }
        System.out.println();

        //обновление сотрудника
        System.out.println("Обновление сотрудника");
        Employee empUpdate = new Employee("Ivan", 37, 55000, Job.PROGRAMMER);
        manager.saveOrUpdate(empUpdate);
        for (Employee employ: manager.readFromFile()) {
            System.out.println(employ);
        }
        System.out.println();

        //замена должности для всех сотрудников на заданную
        System.out.println("Cписок сотрудников с указанной должностью");
        manager.changeAllWork(Job.BUILDER);
        for (Employee employ: manager.readFromFile()) {
            System.out.println(employ);
        }

    }
}
