import org.testng.annotations.*;
import java.io.*;
import java.nio.file.*;
import static org.testng.Assert.*;
import io.qameta.allure.Step;

public class GitHelperTest {
    private static final String REPO_URL = "https://github.com/ssanadi/CloudBees-Assessment.git";
    private String cloneDir = "cloned-repo";
    private String newFile = "newfile.txt";
    private String newContent = "This is a test file.\n";
    private String appendContent = "Appending some test content.\n";
    private File cloneDirFile;

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        cloneDirFile = new File(cloneDir);
        GitHelper.cloneRepo(REPO_URL, cloneDir);
        GitHelper.setupGitIdentity(cloneDir);
    }

    @AfterMethod
    public void tearDown() throws IOException {
        if (cloneDirFile != null) {
            FileUtility.deleteDirectory(cloneDirFile);
        }
    }

    @Test(description = "Test git workflow: add, append, assert")
    public void testGitWorkflow() throws Exception {
        addNewFileAndCommit();
        appendToFileAndCommit();
        assertFileContent();
    }

    @Step("Add new file and commit")
    private void addNewFileAndCommit() throws Exception {
        GitHelper.addNewFile(cloneDir, newFile, newContent);
    }

    @Step("Append to file and commit")
    private void appendToFileAndCommit() throws Exception {
        GitHelper.appendToFile(cloneDir, newFile, appendContent);
    }

    @Step("Assert file exists and content is as expected")
    private void assertFileContent() throws Exception {
        Path filePath = Paths.get(cloneDir, newFile);
        assertTrue(Files.exists(filePath), "File should exist after creation and append");
        String fileContent = Files.readString(filePath);
        assertEquals(fileContent, newContent + appendContent, "File content should match expected");
    }
}
