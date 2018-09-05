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


        Object result;

        if (clazz.getClass().getAnnotation(Logged.class) != null) {
            LoggList.logList.add("We call method: " + method.getName());
            result =  method.invoke(mathBox, args);
        } else {
            result = method.invoke(mathBox, args);
        }
        for (Method m : methods) {
            if (((m.getAnnotation(ClearData.class)) != null) && m.getName().equals(method.getName())) {
                LoggList.logList.clear();
            }
        }
        return result;
    }
}
