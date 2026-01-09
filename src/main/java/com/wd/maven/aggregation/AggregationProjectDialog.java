package com.wd.maven.aggregation;

import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AggregationProjectDialog extends DialogWrapper {

    private JBTextField groupIdField;
    private JBTextField artifactIdField;

    public AggregationProjectDialog(Project project) {
        super(project);
      setTitle("创建多模块Maven项目");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);

        // 领域名称（用于单模块DDD架构）
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.0;
        panel.add(new JLabel("领域名称:"), c);

        // Group ID
        c.gridx = 0;
        c.gridy = 2;
        c.weightx = 0.0;
        panel.add(new JBLabel("Group ID:"), c);

        c.gridx = 1;
        c.weightx = 1.0;
        groupIdField = new JBTextField("com.example");
        panel.add(groupIdField, c);

        // Artifact ID
        c.gridx = 0;
        c.gridy = 3;
        c.weightx = 0.0;
        panel.add(new JBLabel("Artifact ID:"), c);

        c.gridx = 1;
        c.weightx = 1.0;
        artifactIdField = new JBTextField("demo");
        panel.add(artifactIdField, c);
        // 初始更新UI组件可见性
        updateUIComponentsVisibility();

        return panel;
    }

    /**
     * 根据选择的项目类型更新UI组件的可见性
     */
    private void updateUIComponentsVisibility() {
      boolean isMultiModule = true;
    }

    @Override
    protected ValidationInfo doValidate() {
        if (groupIdField.getText().trim().isEmpty()) {
            return new ValidationInfo("Group ID不能为空", groupIdField);
        }
        if (artifactIdField.getText().trim().isEmpty()) {
            return new ValidationInfo("Artifact ID不能为空", artifactIdField);
        }
        return null;
    }

    public String getGroupId() {
        return groupIdField.getText().trim();
    }

    public String getArtifactId() {
        return artifactIdField.getText().trim();
    }

    /**
     * 是否为单模块模式
     */
    public boolean isSingleModule() {
      return false;
    }
    /**
     * 是否添加常用依赖
     */
    public boolean isAddDependencies() {
      return true;
    }

  // 重复方法已在原始代码中存在，保留原始实现


}