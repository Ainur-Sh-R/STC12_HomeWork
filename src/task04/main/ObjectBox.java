package task04.main;

import task04.main.exceptions.FileNotFoundException;

import java.util.*;

public class ObjectBox<T extends Number> {
    public static final String NOT_FOUND_ERROR_MESSAGE = "Переданное значение отсутствует в коллекции";
    public static final String WRONG_TYPE_MESSAGE = "Неверный тип объекта";
    private List<T> numbers = new ArrayList<>();

    public ObjectBox(T... args) {
        numbers.addAll(Arrays.asList(args));
    }

    /**
     * @return сумму всех элементов коллекции
     */
    public int summator() {
        int sum = 0;
        for (T num : numbers) {
            sum += (Integer) num;
        }
        return sum;
    }

    /**
     * @param divider общий делитель
     * @return коллекцию с результатами деления
     */
    public List<Integer> splitter(int divider) {
        List<Integer> result = new ArrayList<>();
        for (T number : numbers) {
            int x = (Integer) number / divider;
            result.add(x);
        }
        return result;
    }

    /**
     * @param number получает на вход и если такое значение есть в коллекции, удаляет его.
     * @throws FileNotFoundException
     */
    public void remove(T number) throws FileNotFoundException {
        if (numbers.contains(number)) {
            numbers.remove(number);
        } else{
            throw new FileNotFoundException(NOT_FOUND_ERROR_MESSAGE);
        }
    }

    /**
     * добавляет объект в коллекцию
     * @param object объект типа Number
     */
    public void addObject (Object object){
        if (object instanceof Number){
            numbers.add((T) object);
        }else {
            throw new FileNotFoundException(WRONG_TYPE_MESSAGE);
        }
    }

    /**
     * проверяет наличие объекта в коллекции
     * @param number проверяемый объект
     * @return true or false
     */
    public boolean deleteObject(T number){
        if (numbers.contains(number)){
            return true;
        }else {
            return  false;
        }
    }

    /**
     * @return содержимое коллекции в String
     */
    public String dump(){
        return numbers.toString();
    }

    @Override
    public String toString() {
        return "ObjectBox{" +
                "numbers=" + numbers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ObjectBox<?> objectBox = (ObjectBox<?>) o;
        return Objects.equals(numbers, objectBox.numbers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numbers);
    }
}
