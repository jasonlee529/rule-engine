package cn.lee.jason.engine.el;

import java.util.Map;

public interface ELExecutor {
    Object execute(String lamba, Map params) throws Exception;
}
