package edu.oregonstate.cope
import com.intellij.ide.highlighter.XmlFileType
import com.intellij.openapi.module.Module
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.search.FileTypeIndex
import com.intellij.testFramework.builders.JavaModuleFixtureBuilder
import com.intellij.testFramework.fixtures.*
import com.intellij.util.indexing.FileBasedIndex
import org.jetbrains.jps.model.java.LanguageLevel
/**
 * Change-Oriented Programming Environment (COPE) project
 * URL: http://cope.eecs.oregonstate.edu/
 * Created by nelsonni on 1/18/16.
 */
class CalypsoApplicationServiceTest extends GroovyTestCase {

    protected JavaCodeInsightTestFixture myFixture;

    protected Module myFirstModule;
    protected Module mySecondModule;

    @Override
    public void setUp() throws Exception {
        super.setUp();

        final TestFixtureBuilder<IdeaProjectTestFixture> projectBuilder = JavaTestFixtureFactory.createFixtureBuilder(getName());
        myFixture = JavaTestFixtureFactory.getFixtureFactory().createCodeInsightFixture(projectBuilder.getFixture());

        final ModuleFixture firstModuleFixture = newModule(projectBuilder, "0");
        final ModuleFixture secondModuleFixture = newModule(projectBuilder, "1");

        // Call setup on our project fixture, which now allows us to access the ModuleFixture's modules without exception
        myFixture.setUp();

        myFirstModule = firstModuleFixture.getModule();
        mySecondModule = secondModuleFixture.getModule();
    }

    private ModuleFixture newModule(TestFixtureBuilder<IdeaProjectTestFixture> projectBuilder, String contentRoot) throws Exception {
        final JavaModuleFixtureBuilder firstProjectBuilder = projectBuilder.addModule(JavaModuleFixtureBuilder.class);
        firstProjectBuilder.languageLevel = LanguageLevel.JDK_1_8 as com.intellij.pom.java.LanguageLevel;
        String tempDirPath = myFixture.getTempDirPath();

        // Create a new content root for each module, and create a directory for it manually
        String contentRootPath = tempDirPath + "/" + contentRoot;
        new File(contentRootPath).mkdir();

        // Call the builder
        ModuleFixture moduleFixture = firstProjectBuilder
                .addContentRoot(contentRootPath)
                .addSourceRoot("src")
                .getFixture();

        return moduleFixture;
    }

    @Override
    public void tearDown() throws Exception {
        super.tearDown();
        myFixture.tearDown();
    }

    public String getTestDataPath() {
        return TestHelper.getTestDataPath();
    }

    public void testFileCopiesSuccessfullyIntoTwoDifferentModules() {
        // Copy two different files into both modules separately
        myFixture.copyFileToProject("blueprint/dom/inspection/OneTwoThreeFourBeans.xml", myFirstModule.getName() + "/src/OneTwoThreeFourBeans.xml");
        myFixture.copyFileToProject("blueprint/dom/inspection/TwoThreeFourFiveBeans.xml", mySecondModule.getName() + "/src/TwoThreeFourFiveBeans.xml");

        Collection<VirtualFile> virtualFilesTwo = FileBasedIndex.getInstance().getContainingFiles(
                FileTypeIndex.NAME, XmlFileType.INSTANCE, mySecondModule.getModuleContentScope());
        assertEquals("The second module should only have one xml file available within its content scope", 1, virtualFilesTwo.size());
    }

    void testBaseState() {
        assert(true)
    }

    void testRegisterComponent() {
        assert(true)
    }

    void testUnregisterComponent() {
        assert(true)
    }

    void testBroadcastPluginStatus() {
        assert(true)
    }
}
