package com.grey.designpattern.rulestree.service.trial.node;


import com.grey.designpattern.rulestree.entity.MarketProductEntity;
import com.grey.designpattern.rulestree.entity.TrialBalanceEntity;
import com.grey.designpattern.rulestree.service.trial.AbstractGroupBuyMarketSupport;
import com.grey.designpattern.rulestree.service.trial.factory.DefaultActivityStrategyFactory;
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
        return null;
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        return null;
    }

}
