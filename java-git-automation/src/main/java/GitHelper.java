import java.io.*;
import java.nio.file.*;
import java.util.Properties;

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
        Properties props = loadGitConfig();
        String userName = props.getProperty("user.name");
        String userEmail = props.getProperty("user.email");
        runCommand("git config user.name \"" + userName + "\"", repoDir);
        runCommand("git config user.email \"" + userEmail + "\"", repoDir);
    }

    private static Properties loadGitConfig() {
        Properties props = new Properties();
        Path configPath = Paths.get("config.properties");
        if (Files.exists(configPath)) {
            try (InputStream in = Files.newInputStream(configPath)) {
                props.load(in);
            } catch (IOException ignored) {}
        }
        return props;
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