package edu.oregonstate.cope.settings;

import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.options.SearchableConfigurable;
import com.jgoodies.common.base.Objects;
import edu.oregonstate.cope.CalypsoApplicationService;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * Change-Oriented Programming Environment (COPE) project
 * URL: http://cope.eecs.oregonstate.edu/
 * Created by nelsonni on 12/30/15.
 *
 * Configuration interface for {@link PersistentSettings}.
 */
public class SettingsConfigurable implements SearchableConfigurable {

    protected PersistentSettings globalSettings;
    protected SettingsPanel settingsPanel = null;

    public SettingsConfigurable() {
        globalSettings = PersistentSettings.getInstance();
    }

    @NotNull
    @Override
    public String getId() {
        return "Calypso";
    }

    @Nullable
    @Override
    public Runnable enableSearch(String s) {
        return null;
    }

    @Nls
    @Override
    public String getDisplayName() {
        return getId();
    }

    @Nullable
    @Override
    public String getHelpTopic() {
        return null;
    }

    /**
     * createComponent: creates the UI visual form and returns its root element.
     * @return fully instantiated JPanel (derived from JComponent) representing the Calypso plugin settings panel
     */
    @Nullable
    @Override
    public JComponent createComponent() {
        if (settingsPanel == null) settingsPanel = new SettingsPanel();
        reset();
        return settingsPanel.panel;
    }

    /**
     * isModified: this method is regularly called to check the form for changes.
     * If the method returns false, the Apply button is disabled.
     * @return true if elements within the component form have been modified, false otherwise
     */
    @Override
    public boolean isModified() {
        return settingsPanel == null
                || !Objects.equals(globalSettings.getUrl(), settingsPanel.serverUrl.getText())
                || !Objects.equals(globalSettings.getUserId(), settingsPanel.userId.getText())
                || !Objects.equals(globalSettings.getLoggingLevel(), settingsPanel.loggingLevelSlider.getValue())
                || !Objects.equals(globalSettings.getPluginStatus(), settingsPanel.pluginStatus.getSelectedItem());
    }

    /**
     * apply: this method is called when the user clicks the OK or Apply button.
     * @throws ConfigurationException
     */
    @Override
    public void apply() throws ConfigurationException {
        if (settingsPanel != null) {
            globalSettings.setUrl(settingsPanel.serverUrl.getText());
            globalSettings.setUserId(settingsPanel.userId.getText());
            globalSettings.setLoggingLevel(settingsPanel.loggingLevelSlider.getValue());
            globalSettings.setPluginStatus((PluginStatus) settingsPanel.pluginStatus.getSelectedItem());
            ServiceManager.getService(CalypsoApplicationService.class).broadcastPluginStatusChange();
        }
    }

    /**
     * reset: this method is called when the user clicks the Cancel button.
     */
    @Override
    public void reset() {
        if (settingsPanel != null) {
            settingsPanel.serverUrl.setText(globalSettings.getUrl());
            settingsPanel.userId.setText(globalSettings.getUserId());
            settingsPanel.loggingLevelSlider.setValue(globalSettings.getLoggingLevel());
            settingsPanel.pluginStatus.setSelectedItem(globalSettings.getPluginStatus());
        }
    }

    /**
     * disposeUIResources: this method is called when the user closes the form.
     * In this method, resources used by the form are released.
     */
    @Override
    public void disposeUIResources() {
        settingsPanel = null;
    }
}
