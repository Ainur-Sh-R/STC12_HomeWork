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
        if (mathBox.getClass().getAnnotation(ClearData.class) != null){

        }
        if (mathBox.getClass().getAnnotation(Logged.class) != null){
            System.out.println("We call method: " + method.getName());
            return method.invoke(mathBox, args);
        } else {
            return method.invoke(mathBox, args);
        }
    }
}
