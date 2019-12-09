package cn.lee.jason.engine.el.aviator;

import com.googlecode.aviator.AviatorEvaluator;
import com.googlecode.aviator.Expression;
import com.googlecode.aviator.runtime.function.AbstractFunction;
import com.googlecode.aviator.runtime.function.FunctionUtils;
import com.googlecode.aviator.runtime.type.AviatorDouble;
import com.googlecode.aviator.runtime.type.AviatorObject;
import org.apache.commons.lang.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class AviatorDemo {
    public static void main(String[] args) throws NoSuchMethodException, IllegalAccessException {
        Long result = (Long) AviatorEvaluator.execute("1+2+3");
        System.out.println(result);
        System.out.println(AviatorEvaluator.execute("string.contains(\"test\", string.substring('hello', 1, 2))"));
        System.out.println(AviatorEvaluator.execute("string.substring('hello', 1, 2)"));
        System.out.println(execute(" 'hello ' + yourName + ' ' +age ", "dennis", "11"));
        System.out.println(lambda(" 'hello ' + yourName + ' ' +age ", "dennis", "11"));
        System.out.println(AviatorEvaluator.exec("99999999999999999999999999999999 + 99999999999999999999999999999999"));
        customeAddFunction();
        addStaticFunctions();
        compile();
    }

    public static Object execute(String command, Object... params) {
        return AviatorEvaluator.exec(command, params);
    }

    public static Object lambda(String command, Object... params) {
        return AviatorEvaluator.exec("(lambda (x,y) -> x + y end)(x,y)", 1, 2);
    }

    public static Object customeAddFunction() {
        AviatorEvaluator.addFunction(new AddFunction());
        System.out.println(AviatorEvaluator.execute("add(1, 2)"));           // 3.0
        System.out.println(AviatorEvaluator.execute("add(add(1, 2), 100)")); // 103.0
        return "";
    }

    public static void addStaticFunctions() throws NoSuchMethodException, IllegalAccessException {
        AviatorEvaluator.addStaticFunctions("str", StringUtils.class);
        System.out.println(AviatorEvaluator.execute("str.isBlank('1')"));           // 3.0
        System.out.println(AviatorEvaluator.execute("str.isBlank('1,2')"));           // 3.0
        System.out.println(AviatorEvaluator.execute("str.isBlank('')"));           // 3.0
    }

    public static void compile(){
        String expression = "a-(b-c)>100";
        // 编译表达式
        Expression compiledExp = AviatorEvaluator.compile(expression);
        Map<String, Object> env = new HashMap<String, Object>();
        env.put("a", 100.3);
        env.put("b", 45);
        env.put("c", -199.100);
        // 执行表达式
        Boolean result = (Boolean) compiledExp.execute(env);
        System.out.println(result);  // false
    }
}

class AddFunction extends AbstractFunction {
    @Override
    public AviatorObject call(Map<String, Object> env, AviatorObject arg1, AviatorObject arg2) {
        Number left = FunctionUtils.getNumberValue(arg1, env);
        Number right = FunctionUtils.getNumberValue(arg2, env);
        return new AviatorDouble(left.doubleValue() + right.doubleValue());
    }

    public String getName() {
        return "add";
    }
}


