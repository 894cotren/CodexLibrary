package com.grey.designpattern.rulestree.service.standard.factory;


import com.grey.designpattern.rulestree.entity.MarketProductEntity;
import com.grey.designpattern.rulestree.entity.TrialBalanceEntity;
import com.grey.designpattern.rulestree.service.standard.node.RootNode;
import com.grey.designpattern.rulestree.treebase.StrategyHandler;
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
     * 构造器注入，根节点开始。
     * @param rootNode 根节点
     */
    public DefaultActivityStrategyFactory(RootNode rootNode) {
        this.rootNode = rootNode;
    }

    public StrategyHandler<MarketProductEntity, DynamicContext, TrialBalanceEntity> strategyHandler() {
        return rootNode;
    }


    /**
     * 上下文类，用于节点间传递上下文。
     * 当前无字段；有字段后如需全参构造器可再加 @AllArgsConstructor（无字段时与 @NoArgsConstructor 会重复生成无参构造器）。
     */
    @Data
    @Builder
    @NoArgsConstructor
    public static class DynamicContext {

    }

}
