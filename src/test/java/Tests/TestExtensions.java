package Tests;

import java.io.File;

public class TestExtensions {

	public static File readFileFromResources(String path) {
		ClassLoader classLoader = TestExtensions.class.getClassLoader();
		return new File(classLoader.getResource(path).getFile());
	}

}
