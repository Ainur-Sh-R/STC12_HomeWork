package task05;

import java.io.Serializable;
import java.util.List;

public interface Manager extends Serializable {
    /**
     * дописывает сотрудника в конец файла, при наличии сотрудника в
     * списке запись не производится
     * @return успешность записи
     */
    boolean save (Employee employee);

    /**
     * удаляет сотрудника из файла
     * @return успешность удаления
     */
    boolean delete (Employee employee);

    /**
     * возвращает сотрудника по полному совпадению имени
     */
    Employee getByName(final String name);

    /**
     * @return список сотрудников по должности
     */
    List<Employee> getByJob(Job job);

    /**
     * выполняет обновление, либо сохранение сотрудника
     * в зависимости от того, есть ли он уже в файле
     * @return успешность обнавления
     */
    boolean saveOrUpdate (Employee employee);

    /**
     * замена должности для всех сотрудников на заданную
     * @return успешность замены должности
     */
    boolean changeAllWork(Job job);
}
