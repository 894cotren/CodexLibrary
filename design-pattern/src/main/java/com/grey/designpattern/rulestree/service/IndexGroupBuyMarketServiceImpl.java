package com.grey.designpattern.rulestree.service;


import com.grey.designpattern.rulestree.entity.MarketProductEntity;
import com.grey.designpattern.rulestree.entity.TrialBalanceEntity;
import com.grey.designpattern.rulestree.service.trial.factory.DefaultActivityStrategyFactory;
import com.grey.designpattern.rulestree.treebase.StrategyHandler;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 *  首页营销服务
 */
@Service
public class IndexGroupBuyMarketServiceImpl implements IIndexGroupBuyMarketService {

    @Resource
    private DefaultActivityStrategyFactory defaultActivityStrategyFactory;

    @Override
    public TrialBalanceEntity indexMarketTrial(MarketProductEntity marketProductEntity) throws Exception {

        StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> strategyHandler = defaultActivityStrategyFactory.strategyHandler();

        TrialBalanceEntity trialBalanceEntity = strategyHandler.apply(marketProductEntity, new DefaultActivityStrategyFactory.DynamicContext());

        return trialBalanceEntity;
    }

}
