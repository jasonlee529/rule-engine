package cn.lee.jason.engine.el.qlexpres;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

public class QLExpressDemo {

    public static void main(String[] args) throws Exception {


        ExpressRunner runner = new ExpressRunner();
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.put("a", 1);
        context.put("b", 2);
        context.put("c", 3);
        System.out.println(runner.execute(" mins = [5,30]", context, null, true, false));
        System.out.println(runner.execute(" a>b?a:b", context, null, true, false));
        String line = " n=10;\n" +
                " sum=0;\n" +
                "for(i=0;i<n;i++){\n" +
                "sum=sum+i;\n" +
                "}\n" +
                "return sum;";
        System.out.println(runner.execute(line, context, null, true, false));
        String e2 = "function add(int a,int b){\n" +
                "  return a+b;\n" +
                "};\n" +
                "\n" +
                "function sub(int a,int b){\n" +
                "  return a - b;\n" +
                "};\n" +
                "\n" +
                "a=10;\n" +
                "return add(a,4) + sub(a,9);";
        System.out.println(runner.execute(e2, context, null, true, false));
        long pre = System.currentTimeMillis();
        for (int i = 0; i < 100000; i++) {
            String express = "a*10";
            Object r = runner.execute(express, context, null, true, false);
            System.out.println(r);
        }
        long next = System.currentTimeMillis();
        System.out.println((next-pre));
    }
}
