package edu.oregonstate.cope;

import com.intellij.openapi.components.ProjectComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.wm.StatusBar;
import com.intellij.openapi.wm.WindowManager;
import edu.oregonstate.cope.settings.PluginStatusBarWidget;
import org.jetbrains.annotations.NotNull;

/**
 * Change-Oriented Programming Environment (COPE) project
 * URL: http://cope.eecs.oregonstate.edu/
 * Created by nelsonni on 1/15/16.
 */
public class CalypsoProjectComponent implements ProjectComponent {

    private Project project;
    private StatusBar statusBar;
    private PluginStatusBarWidget pluginStatusBarWidget;

    public CalypsoProjectComponent(@NotNull Project project) {
        this.project = project;
    }

    public void refreshStatusBar() {
        statusBar.updateWidget(pluginStatusBarWidget.ID());
    }

    @Override
    public void projectOpened() {
        statusBar = WindowManager.getInstance().getStatusBar(project);
        pluginStatusBarWidget = new PluginStatusBarWidget();

        try {
            statusBar.addWidget(pluginStatusBarWidget);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ServiceManager.getService(CalypsoApplicationService.class).registerComponent(this);
    }

    @Override
    public void projectClosed() {
        statusBar.removeWidget(pluginStatusBarWidget.ID());
        ServiceManager.getService(CalypsoApplicationService.class).unregisterComponent(this);
    }

    @Override
    public void initComponent() {

    }

    @Override
    public void disposeComponent() {
        project = null;
        statusBar = null;
        pluginStatusBarWidget = null;
    }

    @NotNull
    @Override
    public String getComponentName() {
        return "Calypso.ProjectComponent";
    }
}
