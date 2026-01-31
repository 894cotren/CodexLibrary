package com.grey.designpattern.rulestree.service.standard.node;


import com.grey.designpattern.rulestree.entity.MarketProductEntity;
import com.grey.designpattern.rulestree.entity.TrialBalanceEntity;
import com.grey.designpattern.rulestree.service.standard.AbstractGroupBuyMarketSupport;
import com.grey.designpattern.rulestree.service.standard.factory.DefaultActivityStrategyFactory;
import com.grey.designpattern.rulestree.treebase.StrategyHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *  结束节点
 */
@Slf4j
@Service
public class EndNode extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    @Override
    public TrialBalanceEntity apply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        System.out.println("结束结点EndRoot正在执行");
        //注意哦，最后一个结点了直接返回结果了，不调用router。
        return new TrialBalanceEntity();
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        //最后一个结点
        return defaultStrategyHandler;
    }

}
