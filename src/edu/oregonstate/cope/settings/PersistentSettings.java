package edu.oregonstate.cope.settings;

import com.intellij.openapi.components.*;
import com.intellij.util.xmlb.XmlSerializerUtil;

/**
 * Change-Oriented Programming Environment (COPE) project
 * URL: http://cope.eecs.oregonstate.edu/
 * Created by nelsonni on 12/30/15.
 *
 * Persistent global settings_old object for Calypso plugin.
 */

@State(
        name = "calypso.settings", reloadable = false,
        storages = @Storage(id = "other", file = StoragePathMacros.APP_CONFIG + "/calypso.settings.xml")
)

public class PersistentSettings implements PersistentStateComponent<PersistentSettings> {

    public PluginStatus pluginStatus = PluginStatus.ACTIVE;
    public Integer loggingLevel = 2;
    public String url = "http://localhost:3000/loopback/testPost";
    public String userId;

    public static PersistentSettings getInstance() { return ServiceManager.getService(PersistentSettings.class); }

    public PluginStatus getPluginStatus() { return pluginStatus; }

    public void setPluginStatus(PluginStatus updatedState) {
        this.pluginStatus = updatedState;
    }

    public Integer getLoggingLevel() { return loggingLevel; }

    public void setLoggingLevel(Integer loggingLevel) {
        if (loggingLevel >= 0 && loggingLevel <= 2) {
            this.loggingLevel = loggingLevel;
        }
    }

    public String getUrl() { return url; }

    public void setUrl(String url) { this.url = url; }

    public String getUserId() { return userId; }

    public void setUserId(String userId) { this.userId = userId; }

    @Override
    public PersistentSettings getState() { return this; }

    @Override
    public void loadState(PersistentSettings state) { XmlSerializerUtil.copyBean(state, this); }
}
