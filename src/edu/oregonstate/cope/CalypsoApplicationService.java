package edu.oregonstate.cope;

import java.util.ArrayList;
import java.util.List;

/**
 * Change-Oriented Programming Environment (COPE) project
 * URL: http://cope.eecs.oregonstate.edu/
 * Created by nelsonni on 1/18/16.
 *
 * Application service for all active CalypsoProjectComponents; provides guaranteed plugin status change communications
 */
public class CalypsoApplicationService {

    private List<CalypsoProjectComponent> projectComponents = new ArrayList<>();

    public void registerComponent(CalypsoProjectComponent component) {
        projectComponents.add(component);
    }

    public void unregisterComponent(CalypsoProjectComponent component) {
        projectComponents.removeIf(e -> e.equals(component));
    }

    public void broadcastPluginStatusChange() {
        projectComponents.forEach(CalypsoProjectComponent::refreshComponentStatus);
    }

}
