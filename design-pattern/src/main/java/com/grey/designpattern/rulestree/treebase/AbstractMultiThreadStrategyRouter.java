package com.grey.designpattern.rulestree.treebase;

import lombok.Getter;
import lombok.Setter;

/**
 * 多线程策略路由器，自定了了一个先执行查询，在执行子类策略的流程
 * 注意这个类是跟AbstractStrategyRouter是一层的，只不过是自定义的一套加了改装的东西。
 */
public abstract class AbstractMultiThreadStrategyRouter<T, D, R> implements StrategyHandler<T, D, R>,StrategyMapper<T, D, R>{

    @Getter
    @Setter
    protected StrategyHandler<T, D, R> defaultStrategyHandler = StrategyHandler.DEFAULT;

    /**
     * 获取下一个结点并执行
     */
    public R router(T requestParameter, D dynamicContext) throws Exception {
        StrategyHandler<T, D, R> strategyHandler = this.get(requestParameter, dynamicContext);
        if(null != strategyHandler) return strategyHandler.apply(requestParameter, dynamicContext);
        return defaultStrategyHandler.apply(requestParameter, dynamicContext);
    }


    /**
     * 当前抽象类实现该方法，定义了一套通用流程，先调用数据，再调用子类结点的逻辑。
     */
    @Override
    public R apply(T requestParameter, D dynamicContext) throws Exception {
        //调用多线程获取数据
        multiThread(requestParameter,dynamicContext);
        //调用子类实现的doApply
        return doApply(requestParameter, dynamicContext);
    }

    /**
     * 考虑到很多结点都需要先查询一些数据，再执行自己的逻辑，所以新增了这个多线程查询的方法给子类实现。
     * 异步加载数据(多线程),供子类结点可选实现，
     * 然后也可以在这个方法内进行一个接口调用、查询数据的统一处理
     * 可以方便的配置线程池之类的
     */
    public abstract void multiThread(T requestParameter, D dynamicContext) throws Exception;

    /**
     * 子类结点实现的业务执行逻辑
     */
    public abstract R doApply(T requestParameter, D dynamicContext) throws Exception;
}
