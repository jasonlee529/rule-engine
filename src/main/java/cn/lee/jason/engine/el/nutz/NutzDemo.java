package cn.lee.jason.engine.el.nutz;

import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;
import org.nutz.el.El;
import org.nutz.lang.Lang;
import org.nutz.lang.util.Context;

public class NutzDemo {

    public static void main(String[] args) {
        El exp = new El("a*10");  // 预编译结果为一个 El 对象
        long pre = System.currentTimeMillis();
        Context context = Lang.context();
        context.set("a", 10);
        for (int i = 0; i < 100000; i++) {
            System.out.println(exp.eval(context));  // 将打印 100
        }
        long next = System.currentTimeMillis();
        System.out.println((next-pre));
    }
}
