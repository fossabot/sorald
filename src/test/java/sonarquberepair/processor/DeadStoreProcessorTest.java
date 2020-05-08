package sonarquberepair.processor;

import org.junit.Test;
import org.sonar.java.checks.DeadStoreCheck;
import org.sonar.java.checks.verifier.JavaCheckVerifier;
import sonarquberepair.Constants;
import sonarquberepair.Main;

public class DeadStoreProcessorTest {

	@Test
	public void test() throws Exception {
		String fileName = "DeadStores.java";
		String pathToBuggyFile = Constants.PATH_TO_RESOURCES_FILE + fileName;
		String pathToRepairedFile = Constants.WORKSPACE + "/spooned/" + fileName;

		JavaCheckVerifier.verify(pathToBuggyFile, new DeadStoreCheck());
		Main.main(new String[]{
			"--originalFilesPath",pathToBuggyFile,
			"--ruleKeys","1854",
			"--workspace",Constants.WORKSPACE});
		JavaCheckVerifier.verifyNoIssue(pathToRepairedFile, new DeadStoreCheck());
	}

}
