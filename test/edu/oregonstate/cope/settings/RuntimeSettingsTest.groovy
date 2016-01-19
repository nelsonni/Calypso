package edu.oregonstate.cope.settings

import com.intellij.openapi.project.Project
import com.intellij.testFramework.LightProjectDescriptor
import com.intellij.testFramework.fixtures.CodeInsightTestFixture
import com.intellij.testFramework.fixtures.IdeaProjectTestFixture
import com.intellij.testFramework.fixtures.IdeaTestFixtureFactory
import com.intellij.testFramework.fixtures.TestFixtureBuilder
import com.intellij.testFramework.fixtures.impl.LightTempDirTestFixtureImpl

/**
 * Change-Oriented Programming Environment (COPE) project
 * URL: http://cope.eecs.oregonstate.edu/
 * Created by nelsonni on 1/15/16.
 */
@Deprecated
class RuntimeSettingsTest extends GroovyTestCase {

    protected CodeInsightTestFixture myFixture
    protected PersistentSettings settings

    void setUp() {
        super.setUp()
        final IdeaTestFixtureFactory factory = IdeaTestFixtureFactory.getFixtureFactory()
        final LightProjectDescriptor projectDescriptor = LightProjectDescriptor.EMPTY_PROJECT_DESCRIPTOR
        final TestFixtureBuilder<IdeaProjectTestFixture> fixtureBuilder = factory.createLightFixtureBuilder(projectDescriptor)
        final IdeaProjectTestFixture fixture = fixtureBuilder.getFixture()
        myFixture = IdeaTestFixtureFactory.getFixtureFactory().createCodeInsightFixture(fixture,
                new LightTempDirTestFixtureImpl(true))
        myFixture.setUp()
        settings = new PersistentSettings()
    }

    void tearDown() {
        myFixture.tearDown()
        myFixture = null
        settings = null
        super.tearDown()
    }

    void testRegisterProject() {
        Project project = myFixture.getProject()
        assert(settings.projects.size() == 0)

        settings.registerProject(project)
        assert(settings.projects.size() == 1)
        assert(settings.projects.get(0).equals(project))

        settings.registerProject(project)
        assert(settings.projects.size() == 2)
    }

    void testUnregisterProject() {
        Project project = myFixture.getProject()

        settings.registerProject(project)
        assert(settings.projects.size() == 1)

        settings.unregisterProject(project)
        assert(settings.projects.size() == 0)

        // TODO: Create a second project that can be added in order to verify selective unregistering
    }
}
