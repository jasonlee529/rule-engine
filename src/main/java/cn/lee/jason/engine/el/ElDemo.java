package cn.lee.jason.engine.el;

import cn.lee.jason.engine.el.aviator.AviatorExecutor;
import cn.lee.jason.engine.el.nutz.NutzExecutor;
import cn.lee.jason.engine.el.qlexpres.QLExpressExecutor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ElDemo {

    public static void main(String[] args) throws Exception {
        String lamba = " n=10;\n" +
                " sum=0;\n" +
                "for(i=0;i<n;i++){\n" +
                "sum=sum+i;\n" +
                "}\n" +
                "return sum;";
        Map params = new HashMap();
        List<ELExecutor> executorList = new ArrayList<>();
        executorList.add(new QLExpressExecutor());
        executorList.add(new NutzExecutor());
        executorList.add(new AviatorExecutor());
        long pre = System.currentTimeMillis();
        int total = 10 * 1000;
        for (ELExecutor executor : executorList) {
            for (int i = 0; i < total; i++) {
                executor.execute(lamba, params);
            }
            long next = System.currentTimeMillis();
            System.out.println(executor.getClass().getName() + " execute : " + (next - pre) + "ms;");
            pre = System.currentTimeMillis();
        }
    }
}
