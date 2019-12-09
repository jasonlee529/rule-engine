package cn.lee.jason.engine.el.nutz;

import cn.lee.jason.engine.el.ELExecutor;
import org.nutz.el.El;
import org.nutz.lang.util.Context;
import org.nutz.lang.util.SimpleContext;

import java.util.Map;

public class NutzExecutor implements ELExecutor {
    public Object execute(String lamba, Map params) throws Exception {
        El exp = new El(lamba);  // 预编译结果为一个 El 对象
        Context context = new SimpleContext(params);
        return exp.eval(context);
    }
}
