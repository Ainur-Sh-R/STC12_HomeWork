package task07;

import java.lang.reflect.Proxy;

public class Main {

    public static void main(String[] args) {
        Integer[] integers = {20, 15, 30, 10, 5, 30, 81, 3, 2, 31};
        MathBoxInterface mathBox = new MathBox(integers);
        MathBoxInvocationHandler mathBoxInvoce = new MathBoxInvocationHandler(mathBox);
        MathBoxInterface mathBoxProxy = (MathBoxInterface)
                Proxy.newProxyInstance(MathBoxInvocationHandler.class.getClassLoader(),
                        new Class[]{MathBoxInterface.class}, mathBoxInvoce);

        int div = 3;
        System.out.println("1. Отсортированный массив: " + mathBoxProxy);

        System.out.println("2. Cумма всех элементов коллекции: " + mathBoxProxy.summator());

        System.out.println("3. Mетод splitter, возвращающий результат деления элементов коллекции на "
                + div + " :" + mathBoxProxy.splitter(div));

        mathBoxProxy.remove(3);
        System.out.println("4. Удаление элемента коллекции '3': " + mathBoxProxy);
    }
}
