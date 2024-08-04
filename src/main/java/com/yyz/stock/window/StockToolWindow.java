package com.yyz.stock.window;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.openapi.wm.ToolWindowFactory;
import com.intellij.ui.content.Content;
import com.intellij.ui.content.ContentFactory;
import com.intellij.ui.table.JBTable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

import com.yyz.stock.GetStockPriceResponse;
import com.yyz.stock.StockPriceHandler;
import com.yyz.stock.dialog.PayDialog;
import com.yyz.stock.storage.StockSettingsState;
import com.yyz.stock.utils.DateUtil;
import org.jetbrains.annotations.NotNull;

/**
 * 股票展示页面
 *
 * @author yangyz
 * @version $ Id: StockToolWindow.java, v 1.0 2024年08月04日 15:26 yangyz Exp $
 * @since 1.0
 */
public class StockToolWindow implements ToolWindowFactory {
    private JPanel mainPanel;
    private JButton refreshButton;
    private JButton payButton;
    private JBTable stockTable;
    private DefaultTableModel tableModel;
    private JLabel lastUpdatedLabel;
    private final String[] columnNames = {"股票名称", "股票编号", "当前价格", "涨跌%", "昨收", "今开"};

    @Override
    public void createToolWindowContent(@NotNull Project project, @NotNull ToolWindow toolWindow) {
        mainPanel = new JPanel(new BorderLayout());

        // 创建按钮
        JPanel buttonPanel = new JPanel();
        refreshButton = new JButton("刷新");
        payButton = new JButton("支持");
        lastUpdatedLabel = new JLabel("上次更新: " + DateUtil.getDateTime());

        buttonPanel.add(lastUpdatedLabel);
        buttonPanel.add(refreshButton);
        buttonPanel.add(payButton);
        // 创建表格
        Object[][] data = getDataArray();
        tableModel = new DefaultTableModel(data, columnNames);
        stockTable = new JBTable(tableModel);

        // 添加组件到主面板
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(new JScrollPane(stockTable), BorderLayout.CENTER);

        ContentFactory contentFactory = ContentFactory.getInstance();
        Content content = contentFactory.createContent(mainPanel, "", false);
        toolWindow.getContentManager().addContent(content);

        // 添加按钮操作逻辑
        refreshButton.addActionListener(e -> refreshFetchingStockPrice());
        payButton.addActionListener(e -> new PayDialog(project).show());
    }

    /**
     * 刷新股票的价格数据
     */
    private void refreshFetchingStockPrice() {
        Object[][] data = getDataArray();
        tableModel = new DefaultTableModel(data, columnNames);
        stockTable.setModel(tableModel);
        lastUpdatedLabel.setText("上次更新: " + DateUtil.getDateTime());
    }

    /**
     * 获取表格的数据列
     *
     * @return
     */
    private Object[][] getDataArray() {
        String stockSymbol = StockSettingsState.getInstance().stockSymbol;
        StockPriceHandler handler = new StockPriceHandler();
        List<GetStockPriceResponse> getStockPriceResponseList = handler.fetchStockPrice(stockSymbol);
        Object[][] data = new Object[getStockPriceResponseList.size()][];
        for (int i = 0; i < getStockPriceResponseList.size(); i++) {
            GetStockPriceResponse stockPriceResponse = getStockPriceResponseList.get(i);
            Object[] rowData = {stockPriceResponse.getStockName(), stockPriceResponse.getStockNo()
                    , stockPriceResponse.getLastTrade(), stockPriceResponse.getChange()
                    , stockPriceResponse.getPrevClose(), stockPriceResponse.getOpen()};
            data[i] = rowData;
        }
        return data;
    }
}
