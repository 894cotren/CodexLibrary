package com.grey.designpattern.rulestree.treebase;

/**
 *  受理策略处理，就是结点执行该结点的逻辑。
 * T 入参类型
 * D 上下文参数
 * R 返参类型
 */
public interface StrategyHandler<T, D, R> {

    /**
     * 定义一个默认为空的策略结点
     */
    StrategyHandler DEFAULT = (T, D) -> null;

    /**
     * 执行策略方法，子类实现类需要实现自己的一个执行策略。
     * @param requestParameter 入参
     * @param dynamicContext 上下文参数
     * @return R 返参
     */
    R apply(T requestParameter, D dynamicContext) throws Exception;

}