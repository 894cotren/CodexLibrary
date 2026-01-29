package com.grey.designpattern.rulestree.treebase;

/**
 *  策略映射器，负责找下一个策略结点
 * T 入参类型
 * D 上下文参数
 * R 返参类型
 */
public interface StrategyMapper<T, D, R> {

    /**
     * 获取下一个策略结点，具体逻辑子类实现类自定
     * @param requestParameter 入参
     * @param dynamicContext   上下文
     * @return 返参
     * @throws Exception 异常
     */
    StrategyHandler<T, D, R> get(T requestParameter, D dynamicContext) throws Exception;

}
