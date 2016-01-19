package edu.oregonstate.cope.settings;

import com.intellij.openapi.options.ShowSettingsUtil;
import com.intellij.openapi.util.IconLoader;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.StatusBarWidget;
import com.intellij.util.Consumer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.event.MouseEvent;

/**
 * Change-Oriented Programming Environment (COPE) project
 * URL: http://cope.eecs.oregonstate.edu/
 * Created by nelsonni on 12/22/15.
 */
public class PluginStatusBarWidget implements StatusBarWidget, StatusBarWidget.IconPresentation {

    public PluginStatusBarWidget() {
    }

    @Override
    public void dispose() { }

    @NotNull
    @Override
    public Icon getIcon() {
        PluginStatus status = PersistentSettings.getInstance().getPluginStatus();

        switch (status) {
            case FAULT:
                return IconLoader.getIcon("resources/logo_fault.png");
            case ACTIVE:
                return IconLoader.getIcon("resources/logo_active.png");
            case INACTIVE:
                // continue to default
            default:
                return IconLoader.getIcon("resources/logo_inactive.png");
        }
    }

    @NotNull
    @Override
    public String ID() {
        return "Calypso.StatusBarWidget";
    }

    @Nullable
    @Override
    public WidgetPresentation getPresentation(@NotNull PlatformType platformType) {
        return this;
    }

    @Override
    public void install(@NotNull StatusBar statusBar) {
    }

    @Nullable
    @Override
    public String getTooltipText() {
        return "Calypso Plugin Settings";
    }

    @Nullable
    @Override
    public Consumer<MouseEvent> getClickConsumer() {
        return mouseEvent -> ShowSettingsUtil.getInstance().showSettingsDialog(null, SettingsConfigurable.class);
    }
}
