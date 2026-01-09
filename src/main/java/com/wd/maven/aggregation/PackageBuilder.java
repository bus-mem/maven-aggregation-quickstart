package com.wd.maven.aggregation;

import com.intellij.openapi.application.ApplicationInfo;
import java.io.File;

/**
 * @author solom
 * @ClassName PackageBuilder
 * @Description
 * @Version 1.0.0
 * @create 2022-03-01 10:56
 */
public class PackageBuilder extends BaseBuilder {

	public void generation(PropertiesConfig config, String projectsRoot) throws Exception {
		PropertiesConfig tmpConfig = new PropertiesConfig();
		tmpConfig.setArtifactId(config.getArtifactId());
		tmpConfig.setParentArtifactId(config.getParentArtifactId());
		tmpConfig.setPackageName((config.getGroupId() + "." + config.getParentArtifactId() + "."
				+ config.getArtifactId()+".controller").replace("-", "_"));
		tmpConfig.setGroupId(config.getGroupId());
		String packagePath = config.getPackageName().replace(".", "/");
		File file = new File(projectsRoot + "/src/main/java/" + packagePath, "package-info.java");
		super.writeFile(file, "package-info.ftl", tmpConfig);
		tmpConfig.setPackageName((config.getGroupId() + "." + config.getParentArtifactId() + "."
				+ config.getArtifactId()+".exception").replace("-", "_"));
		packagePath = tmpConfig.getPackageName().replace(".", "/");
		file = new File(projectsRoot + "/src/main/java/" + packagePath, "package-info.java");
		super.writeFile(file, "package-info.ftl", tmpConfig);
	}
}
