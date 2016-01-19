package edu.oregonstate.cope

import com.intellij.openapi.wm.StatusBarWidget
import edu.oregonstate.cope.settings.PluginStatus
import edu.oregonstate.cope.settings.PluginStatusBarWidget

import java.util.regex.Matcher

/**
 * Change-Oriented Programming Environment (COPE) project
 * URL: http://cope.eecs.oregonstate.edu/
 * Created by nelsonni on 12/23/15.
 */
class PluginStatusBarWidgetTest extends GroovyTestCase {

    PluginStatusBarWidget widget;

    void setUp() {
        super.setUp();
        widget = new PluginStatusBarWidget(PluginStatus.ACTIVE);
    }

    void tearDown() {
        widget.dispose();
    }

    void testDispose() {
        PluginStatusBarWidget activeWidget = new PluginStatusBarWidget(PluginStatus.ACTIVE);
        assertNotNull("PluginStatusBarWidget:PluginStatus is null before dispose()", activeWidget.state);
        activeWidget.dispose();
        assertNull("PluginStatusBarWidget:PluginStatus is not null after dispose()", activeWidget.state);
    }

    void testGetIcon() {
        assertNotNull("PluginStateBarWidget:getIcon() returns null", widget.getIcon());
    }

    void testGetIcon_Active() {
        PluginStatusBarWidget active = new PluginStatusBarWidget(PluginStatus.ACTIVE);

        // using regex to test for both lite and dark themed icons
        def m = active.getIcon().toString() =~ /logo_active_(.*)\.png/
        assert m instanceof Matcher
        if (!m) {
            throw new RuntimeException("PluginStateBarWidget:getIcon() not returning 'active' icon")
        }
    }

    void testGetIcon_Inactive() {
        PluginStatusBarWidget inactive = new PluginStatusBarWidget(PluginStatus.INACTIVE);

        // using regex to test for both lite and dark themed icons
        def m = inactive.getIcon().toString() =~ /logo_inactive_(.*)\.png/
        assert m instanceof Matcher
        if (!m) {
            throw new RuntimeException("PluginStateBarWidget:getIcon() not returning 'inactive' icon")
        }
    }

    void testGetIcon_Fault() {
        PluginStatusBarWidget fault = new PluginStatusBarWidget(PluginStatus.FAULT);

        // using regex to test for both lite and dark themed icons
        def m = fault.getIcon().toString() =~ /logo_fault_(.*)\.png/
        assert m instanceof Matcher
        if (!m) {
            throw new RuntimeException("PluginStateBarWidget:getIcon() not returning 'fault' icon")
        }
    }

    void testID() {
        assertEquals("Calypso-StatusBar", widget.ID());
    }

    void testGetPresentation() {
        assert widget.getPresentation(StatusBarWidget.PlatformType.DEFAULT) instanceof PluginStatusBarWidget
    }

    void testInstall() {
        assertTrue(true);
    }

    void testGetTooltipText() {
        assertEquals("PluginStateBarWidget:getTooltipText() not returning message text", "Calypso Plugin Settings", widget.getTooltipText());
    }

    void testGetClickConsumer() {

    }
}
