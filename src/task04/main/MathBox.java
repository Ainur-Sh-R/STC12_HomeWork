package task04.main;

import java.util.*;

public class MathBox {
    private Set<Integer> setInt = new TreeSet<>();

    public MathBox(Integer[] arrayInt) {
        for (Integer n: arrayInt) {
            setInt.add(n);
        }
    }

    public int summator (){
        int sum = 0;
        if (!(setInt.isEmpty())){
            for(int n : setInt){
             sum += n;
            }
        }else {
            System.out.println("Был передан пустой массив");
        }
         return sum;
    }

    public TreeSet<Integer> splitter (int div){
       TreeSet<Integer> splitSet = new TreeSet<>();
        if (div == 0) {
            System.out.println("Деление на ноль запрещено");
            return splitSet;
        }
        if (!(setInt.isEmpty())){
            for(int n : setInt){
                splitSet.add(n/div);
            }
        }else{
            System.out.println("Был передан пустой массив");
        }

       return splitSet;
    }

    public void remove (Integer n){
        if (setInt.contains(n)){
            setInt.remove(n);
        }else{
            System.out.println("Переданное значение отсутствует в коллекции");
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
        return "MathBox{" +
                "setInt=" + setInt +
                '}';
    }
}
