package com.grey.designpattern.rulestree.service.multi;

import com.grey.designpattern.rulestree.service.standard.factory.DefaultActivityStrategyFactory;
import com.grey.designpattern.rulestree.treebase.AbstractMultiThreadStrategyRouter;

/**
 * 抽象的、支持多线程查询的、拼团营销支撑类；
 * 总之就是通过类似的方式，想要就自定义功能或者抽取通用逻辑，
 * 就去类似的实现上层顶级抽象父类AbstractMultiThreadStrategyRouter这样子
 * 然后通过该支撑类进行一个泛型的具体化，功能的兜底实现，这样子给子类结点去使用
 */

public abstract class AbstractGroupBuyMarketMultiSupport<MarketProductEntity, DynamicContext, TrialBalanceEntity> extends AbstractMultiThreadStrategyRouter<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext,TrialBalanceEntity>  {

    @Override
    public void multiThread(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        //缺省实现，子类想要具体查询逻辑就重写
    }
}
