package com.yyz.stock.config;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.util.NlsContexts;
import com.yyz.stock.StockSettingsComponent;
import com.yyz.stock.storage.StockSettingsState;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * TODO(这里用一句话描述这个类的作用)
 *
 * @author yangyz
 * @version $ Id: StockConfigurable.java, v 1.0 2024年08月04日 15:21 yangyz Exp $
 * @since 1.0
 */
public class StockConfig implements Configurable {

    private StockSettingsComponent settingsComponent;

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "Stock Plugin";
    }

    @Override
    public @Nullable JComponent createComponent() {
        settingsComponent = new StockSettingsComponent();
        return settingsComponent.getPanel();
    }

    @Override
    public boolean isModified() {
        StockSettingsState settings = StockSettingsState.getInstance();
        return !settingsComponent.getStockSymbol().equals(settings.stockSymbol);
    }

    @Override
    public void apply() {
        StockSettingsState settings = StockSettingsState.getInstance();
        settings.stockSymbol = settingsComponent.getStockSymbol();
    }

    @Override
    public void reset() {
        StockSettingsState settings = StockSettingsState.getInstance();
        settingsComponent.setStockSymbol(settings.stockSymbol);
    }
}
