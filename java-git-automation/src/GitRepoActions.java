import java.io.*;
import java.nio.file.*;

public class GitRepoActions {

    public static void cloneRepo(String repoUrl, String cloneDir) throws IOException, InterruptedException {
        runCommand("git clone " + repoUrl + " " + cloneDir);
    }

    public static void addNewFile(String repoDir, String fileName, String content) throws IOException, InterruptedException {
        Path filePath = Paths.get(repoDir, fileName);
        Files.write(filePath, content.getBytes(), StandardOpenOption.CREATE);
        runCommand("git add " + fileName, repoDir);
        runCommand("git commit -m \"Add new file " + fileName + "\"", repoDir);
    }

    public static void appendToFile(String repoDir, String fileName, String content) throws IOException, InterruptedException {
        Path filePath = Paths.get(repoDir, fileName);
        Files.write(filePath, content.getBytes(), StandardOpenOption.APPEND);
        runCommand("git add " + fileName, repoDir);
        runCommand("git commit -m \"Update file " + fileName + "\"", repoDir);
    }

    private static void runCommand(String command, String dir) throws IOException, InterruptedException {
        ProcessBuilder pb = new ProcessBuilder("bash", "-c", command);
        pb.directory(new File(dir));
        pb.inheritIO();
        Process process = pb.start();
        int exitCode = process.waitFor();
        if (exitCode != 0) throw new RuntimeException("Command failed: " + command);
    }

    private static void runCommand(String command) throws IOException, InterruptedException {
        runCommand(command, ".");
    }

    private static void setupGitIdentity(String repoDir) throws IOException, InterruptedException {
        runCommand("git config user.name \"ssanadi\"", repoDir);
        runCommand("git config user.email \"sanadi.saifali.7@gmail.com\"", repoDir);
    }


    public static void main(String[] args) throws Exception {
        String repoUrl = "https://github.com/ssanadi/CloudBees-Assessment.git";
        String cloneDir = "cloned-repo";
        String newFile = "newfile.txt";
        String newContent = "This is a test file.\n";
        String appendContent = "Appending some test content.\n";

        File cloneDirFile = new File(cloneDir);
        if (!cloneDirFile.exists()) {
            cloneRepo(repoUrl, cloneDir);
            setupGitIdentity(cloneDir); // Only if it's a fresh clone
        } else {
            System.out.println("Repo already exists. Skipping clone.");
        }

        addNewFile(cloneDir, newFile, newContent);
        appendToFile(cloneDir, newFile, appendContent);
    }
}
