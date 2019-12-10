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
        String lamba1 = " n=10;\n" +
                " sum=0;\n" +
                "for(i=0;i<n;i++){\n" +
                "sum=sum+i;\n" +
                "}\n" +
                "return sum;";
        List<String> lambas = new ArrayList<String>() {
            {
                this.add("a+b+c");
                this.add("n=10;return a+n;");
                this.add(lamba1);
            }
        };
        Map params = new HashMap();
        params.put("a", 10);
        params.put("b", 20);
        params.put("c", 30);
        List<ELExecutor> executorList = new ArrayList<>();
        executorList.add(new QLExpressExecutor());
        executorList.add(new NutzExecutor());
        executorList.add(new AviatorExecutor());
        int total = 10 * 1000;
        for (String lamba : lambas) {
            System.out.println(lamba +": ");
            for (ELExecutor executor : executorList) {
                try {
                    long pre = System.currentTimeMillis();
                    for (int i = 0; i < total; i++) {
                        executor.execute(lamba, params);
                    }
                    long next = System.currentTimeMillis();
                    System.out.println(executor.getClass().getName() + " execute : " + (next - pre) + "ms;");
                } catch (Exception e) {
                    System.out.println(executor.getClass().getName() + " execute faild ! " + e.getMessage());
                } finally {

                }
            }
        }
    }
}
