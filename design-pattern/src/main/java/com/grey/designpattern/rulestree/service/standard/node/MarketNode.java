package com.grey.designpattern.rulestree.service.standard.node;


import com.grey.designpattern.rulestree.entity.MarketProductEntity;
import com.grey.designpattern.rulestree.entity.TrialBalanceEntity;
import com.grey.designpattern.rulestree.service.standard.AbstractGroupBuyMarketSupport;
import com.grey.designpattern.rulestree.service.standard.factory.DefaultActivityStrategyFactory;
import com.grey.designpattern.rulestree.treebase.StrategyHandler;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 *
 * 营销优惠节点
 */
@Slf4j
@Service
public class MarketNode extends AbstractGroupBuyMarketSupport<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> {

    @Resource
    private EndNode endNode;

    @Override
    public TrialBalanceEntity apply(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        System.out.println("营销结点MarketNode正在计算营销后的折扣");
        return router(requestParameter, dynamicContext);
    }

    @Override
    public StrategyHandler<MarketProductEntity, DefaultActivityStrategyFactory.DynamicContext, TrialBalanceEntity> get(MarketProductEntity requestParameter, DefaultActivityStrategyFactory.DynamicContext dynamicContext) throws Exception {
        //营销折扣计算结点后给到结束结点EndRoot结束当前业务
        System.out.println("营销结点MarketNode执行完毕，下一个结点get方法被调用。");
        return endNode;
    }
}
