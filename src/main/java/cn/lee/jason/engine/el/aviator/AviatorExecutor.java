package cn.lee.jason.engine.el.aviator;

import cn.lee.jason.engine.el.ELExecutor;
import com.googlecode.aviator.AviatorEvaluator;

import java.util.Map;

public class AviatorExecutor implements ELExecutor {
    public Object execute(String lamba, Map params) throws Exception {
        return AviatorEvaluator.execute(lamba, params);
    }
}
