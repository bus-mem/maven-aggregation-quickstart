package com.wd.maven.aggregation;

import com.intellij.ui.components.JBCheckBox;
import com.intellij.ui.components.JBLabel;
import com.intellij.ui.components.JBTextField;
import com.intellij.util.ui.UIUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MavenAggregationSettingsPanel {
    private JPanel mainPanel;
    private JBTextField groupIdField;
    private JBTextField artifactIdField;

  private final MavenAggregationSettings settings;

  public MavenAggregationSettingsPanel(MavenAggregationSettings settings) {
        this.settings = settings;
        createUI();
        initValues();
    }

  private void createUI() {
        mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(5, 5, 5, 5);

        // Group ID
        c.gridx = 0;
        c.gridy = 0;
        mainPanel.add(new JBLabel("Group ID:"), c);

        c.gridx = 1;
        c.weightx = 1.0;
        groupIdField = new JBTextField();
        mainPanel.add(groupIdField, c);

        // Artifact ID
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 0.0;
        mainPanel.add(new JBLabel("Artifact ID:"), c);

        c.gridx = 1;
        c.weightx = 1.0;
        artifactIdField = new JBTextField();
        mainPanel.add(artifactIdField, c);
        // 添加监听器
        groupIdField.getDocument().addDocumentListener(new DebounceDocumentListener(() -> {
            settings.setGroupId(groupIdField.getText().trim());
        }, 300));

        artifactIdField.getDocument().addDocumentListener(new DebounceDocumentListener(() -> {
            settings.setArtifactId(artifactIdField.getText().trim());
        }, 300));

    }

  private void initValues() {
        groupIdField.setText(settings.getGroupId());
        artifactIdField.setText(settings.getArtifactId());
    }

  public JPanel getPanel() {
        return mainPanel;
    }
}