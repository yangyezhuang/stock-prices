package com.yyz.stock.dialog;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.ui.tabs.TabInfo;
import com.intellij.ui.tabs.impl.JBTabsImpl;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

/**
 * 打赏弹窗
 *
 * @author yangyz
 * @version $ Id: PayDialog.java, v 1.0 2024年08月04日 18:17 yangyz Exp $
 * @since 1.0
 */
public class PayDialog extends DialogWrapper {
    private final JPanel root = new JPanel(new BorderLayout());
    private Project project;

    public PayDialog(@Nullable Project project) {
        super(project);
        this.project = project;
        setTitle("喜欢这个软件吗? 支持一下吧!");
        setSize(250, 300);
        init();
    }

    private static class BaseQrCodeJPanel extends JPanel {
        public BaseQrCodeJPanel(String path) {
            super(new BorderLayout());
            ImageIcon imageIcon = new ImageIcon(getClass().getResource(path));
            setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
            add(new JLabel(new ImageIcon(imageIcon.getImage().getScaledInstance(150, 150, Image.SCALE_SMOOTH))));
        }
    }


    @Override
    protected @Nullable JComponent createCenterPanel() {
        JBTabsImpl jbTabs = new JBTabsImpl(project);
        jbTabs.addTab(new TabInfo(new BaseQrCodeJPanel("/img/csdn.png")).setText("CSDN"));
        jbTabs.addTab(new TabInfo(new BaseQrCodeJPanel("/img/github.png")).setText("github"));
        root.add(jbTabs.getComponent(), BorderLayout.CENTER);
        return jbTabs;
    }

}
