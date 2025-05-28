import java.io.*;
import java.nio.file.*;

public class GitHelper {
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

    public static void setupGitIdentity(String repoDir) throws IOException, InterruptedException {
        runCommand("git config user.name \"ssanadi\"", repoDir);
        runCommand("git config user.email \"sanadi.saifali.7@gmail.com\"", repoDir);
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
} 