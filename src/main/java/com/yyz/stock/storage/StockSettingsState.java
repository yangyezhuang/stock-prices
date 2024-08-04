package com.yyz.stock.storage;

import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * 股票配置的持久化类
 *
 * @author yangyz
 * @version $ Id: StockSettingsState.java, v 1.0 2024年08月04日 15:16 yangyz Exp $
 * @since 1.0
 */
public class StockSettingsState implements PersistentStateComponent<StockSettingsState> {

    /**
     * 默认股票符号，取上证指数
     */
    public String stockSymbol = "sh000001";

    public static StockSettingsState getInstance() {
        return ServiceManager.getService(StockSettingsState.class);
    }

    @Override
    public @Nullable StockSettingsState getState() {
        return this;
    }

    @Override
    public void loadState(@NotNull StockSettingsState state) {
        this.stockSymbol = state.stockSymbol;
    }
}
