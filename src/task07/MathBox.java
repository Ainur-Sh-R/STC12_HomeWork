package task07;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;


@Logged
public class MathBox implements MathBoxInterface {
    public Set<Integer> setInt = new TreeSet<>();

    /**
     * Конструктор на вход получает arrayInt
     *
     * @param arrayInt массив Integer
     */
    public MathBox(Integer[] arrayInt) {
        for (Integer n : arrayInt) {
            setInt.add(n);
        }
    }

    /**
     * @return сумму всех элементов коллекции
     */
    @Override
    public int summator() {
        int sum = 0;
        if (!(setInt.isEmpty())) {
            for (int n : setInt) {
                sum += n;
            }
        }
        return sum;
    }

    /**
     * Выполняет поочередное деление всех хранящихся
     * в Set<Integer> setInt элементов на div
     * @param div делитель
     * @return коллекцию TreeSet<Integer> с результатами деления
     */
    @Override
    public TreeSet<Integer> splitter(int div) {
        TreeSet<Integer> splitSet = new TreeSet<>();
        if (div == 0) {
            return splitSet;
        }
        if (!(setInt.isEmpty())) {
            for (int n : setInt) {
                splitSet.add(n / div);
            }
        }
        return splitSet;
    }

    /**
     * получает на вход  n, если такое значение есть
     * в коллекции, удаляет его
     * @param n значение возможного элемента коллеции
     */
    @ClearData
    @Override
    public void remove(Integer n) {
        if (setInt.contains(n)) {
            setInt.remove(n);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MathBox mathBox = (MathBox) o;
        return Objects.equals(setInt, mathBox.setInt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(setInt);
    }

    @Override
    public String toString() {
        return "MathBox: " + setInt ;
    }
}
