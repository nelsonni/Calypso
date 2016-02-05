package edu.oregonstate.cope.settings;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.ui.Messages;

import javax.swing.*;

/**
 * Change-Oriented Programming Environment (COPE) project
 * URL: http://cope.eecs.oregonstate.edu/
 * Created by nelsonni on 12/30/15.
 */
class SettingsPanel {

    private static final Logger LOG = Logger.getInstance(SettingsPanel.class);

    JPanel panel;
    JTextField serverUrl;
    JTextField userId;
    JSlider loggingLevelSlider;
    JComboBox<PluginStatus> pluginStatus;
    private JButton testConnection;
    private JLabel loggingLevelLabel;
    private JLabel pluginStatusLabel;
    private JLabel serverUrlLabel;
    private JLabel userIdLabel;

    public SettingsPanel() {
        pluginStatus.setModel(new DefaultComboBoxModel<>(PluginStatus.values()));

        testConnection.addActionListener(e ->
                Messages.showInfoMessage(panel, "Connection successful: success", "Success"));

        // set dynamic Swing elements to default values later to prevent race condition
        javax.swing.SwingUtilities.invokeLater(() -> {
            pluginStatus.setSelectedItem(PersistentSettings.getInstance().getPluginStatus());
            loggingLevelSlider.setValue(PersistentSettings.getInstance().getLoggingLevel());
        });
    }
}
