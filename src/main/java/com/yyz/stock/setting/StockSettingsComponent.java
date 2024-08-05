package com.yyz.stock.setting;

import javax.swing.*;

/**
 * 设置组件类
 *
 * @author yangyz
 * @version $ Id: StockSettingsComponent.java, v 1.0 2024年08月04日 15:20 yangyz Exp $
 * @since 1.0
 */
public class StockSettingsComponent {
    private JPanel panel;
    private JTextField stockSymbolTextField;

    public StockSettingsComponent() {
        panel = new JPanel();
        stockSymbolTextField = new JTextField(40);
        panel.add(new JLabel("请输入股票编号(用英文逗号分割):"));
        panel.add(stockSymbolTextField);
    }

    public JPanel getPanel() {
        return panel;
    }

    public String getStockSymbol() {
        return stockSymbolTextField.getText();
    }

    public void setStockSymbol(String stockSymbol) {
        stockSymbolTextField.setText(stockSymbol);
    }
}