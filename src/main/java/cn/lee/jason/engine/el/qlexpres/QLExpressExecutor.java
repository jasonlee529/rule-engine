package cn.lee.jason.engine.el.qlexpres;

import cn.lee.jason.engine.el.ELExecutor;
import com.ql.util.express.DefaultContext;
import com.ql.util.express.ExpressRunner;

import java.util.Map;

public class QLExpressExecutor implements ELExecutor {

    ExpressRunner runner;

    public QLExpressExecutor() {
        runner = new ExpressRunner();
    }

    public Object execute(String lamba, Map params) throws Exception {
        DefaultContext<String, Object> context = new DefaultContext<String, Object>();
        context.putAll(params);
        return runner.execute(lamba, context, null, true, false);
    }
}
