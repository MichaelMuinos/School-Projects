import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

public class ProcessTest {
	public static void main(String[] args) throws IOException {
		ProcessBuilder pb = new ProcessBuilder("java");
        // INHERIT: Indicates that the subprocess source will be the same of current process
        pb.redirectOutput(Redirect.INHERIT);
        pb.redirectError(Redirect.INHERIT);
		Process process = pb.start();
	}
}
