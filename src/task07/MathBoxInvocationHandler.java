package task07;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MathBoxInvocationHandler implements InvocationHandler {
    private MathBoxInterface mathBox;

    public MathBoxInvocationHandler(MathBoxInterface mathBox) {
        this.mathBox = mathBox;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Class clazz = mathBox.getClass();
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            if (((m.getAnnotation(ClearData.class)) != null) && m.equals(method)) {
                System.out.println("++++++++++++++++++++++++++" + clazz.getName());
            }
        }

        if (clazz.getClass().getAnnotation(Logged.class) != null) {
            LoggList.logList.add("We call method: " + method.getName());
            return method.invoke(mathBox, args);
        } else {
            return method.invoke(mathBox, args);
        }
    }
}
