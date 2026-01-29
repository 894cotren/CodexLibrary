package com.grey.designpattern.rulestree.service.trial.factory;


import com.grey.designpattern.rulestree.entity.MarketProductEntity;
import com.grey.designpattern.rulestree.entity.TrialBalanceEntity;
import com.grey.designpattern.rulestree.service.trial.node.RootNode;
import com.grey.designpattern.rulestree.treebase.StrategyHandler;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *  活动策略工厂
 */
@Service
public class DefaultActivityStrategyFactory {

    private final RootNode rootNode;

    /**
     * 构造器注入
     * @param rootNode 根节点
     */
    public DefaultActivityStrategyFactory(RootNode rootNode) {
        this.rootNode = rootNode;
    }

    public StrategyHandler<MarketProductEntity, DynamicContext, TrialBalanceEntity> strategyHandler() {
        return rootNode;
    }


    /**
     * 上下文类，用于节点间传递上下文
     */
    @Data
    @Builder
    @AllArgsConstructor
    @NoArgsConstructor
    public static class DynamicContext {

    }

}
