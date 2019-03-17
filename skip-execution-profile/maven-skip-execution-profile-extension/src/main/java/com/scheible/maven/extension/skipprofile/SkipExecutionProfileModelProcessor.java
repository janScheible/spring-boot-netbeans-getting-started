package com.scheible.maven.extension.skipprofile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.apache.maven.model.Build;
import org.apache.maven.model.Model;
import org.apache.maven.model.Plugin;
import org.apache.maven.model.PluginExecution;
import org.apache.maven.model.Profile;
import org.apache.maven.model.building.ModelProcessor;
import org.apache.maven.model.io.ModelParseException;
import org.apache.maven.model.io.ModelReader;
import org.apache.maven.model.locator.ModelLocator;
import org.codehaus.plexus.component.annotations.Component;
import org.codehaus.plexus.component.annotations.Requirement;
import org.codehaus.plexus.logging.Logger;

/**
 *
 * @author sj
 */
@Component(role = ModelProcessor.class)
public class SkipExecutionProfileModelProcessor implements ModelProcessor {

	private static final String DEFAULT_GROUP_ID = "org.apache.maven.plugins";
	private static final String[] STRING_ARRAY_PROTOTYPE = new String[0];

	@Requirement
	private ModelLocator modelLocator;

	@Requirement
	private ModelReader modelReader;

	@Requirement
	private Logger logger;

	private SkipExecutionConfiguration skipExecutionConfiguration = null;

	@Override
	public File locatePom(File projectDirectory) {
		if (skipExecutionConfiguration == null) {
			skipExecutionConfiguration = findDotMvnDirectory(projectDirectory)
					.map(d -> new File(d, "skip-executions.conf"))
					.filter(File::exists)
					.map(f -> SkipExecutionConfiguration.load(f, logger))
					.orElse(new SkipExecutionConfiguration());
		}

		return modelLocator.locatePom(projectDirectory);
	}

	private static Optional<File> findDotMvnDirectory(final File projectDirectory) {
		File currentDirectory = projectDirectory;
		do {
			final File potentialDirectory = new File(currentDirectory, ".mvn");
			if (potentialDirectory.exists()) {
				final File extensionsXml = new File(potentialDirectory, "extensions.xml");
				if (extensionsXml.exists()) {
					return Optional.of(potentialDirectory);
				}
			}
			currentDirectory = currentDirectory.getParentFile();
		} while (currentDirectory != null);
		return Optional.empty();
	}

	@Override
	public Model read(Reader reader, Map<String, ?> options) throws IOException, ModelParseException {
		return addProfile(modelReader.read(reader, options));
	}

	@Override
	public Model read(File file, Map<String, ?> options) throws IOException, ModelParseException {
		return addProfile(modelReader.read(file, options));
	}

	@Override
	public Model read(InputStream in, Map<String, ?> options) throws IOException, ModelParseException {
		return addProfile(modelReader.read(in, options));
	}

	private Model addProfile(final Model model) {
		createProfile(model, "skip-compilation-and-resources").ifPresent(profile -> {
			createSkipExecutionPlugin(profile, DEFAULT_GROUP_ID, "maven-resources-plugin",
					"default-resources", "default-testResources");
			createSkipExecutionPlugin(profile, DEFAULT_GROUP_ID, "maven-compiler-plugin",
					"default-compile", "default-testCompile");
		});

		createProfile(model, "skip-unit-tests").ifPresent(profile -> {
			createSkipExecutionPlugin(profile, DEFAULT_GROUP_ID, "maven-surefire-plugin", "default-test");
		});

		createProfile(model, "skip-all-but-compilation-and-resources-and-unit-tests").ifPresent(profile -> {
			for (Map.Entry<SkipExecutionConfiguration.Plugin, Set<String>> pluginExecutionToSkip
					: skipExecutionConfiguration.getPluginExecutionsIds().entrySet()) {
				createSkipExecutionPlugin(profile, pluginExecutionToSkip.getKey().getGroupId(),
						pluginExecutionToSkip.getKey().getArtifactId(),
						pluginExecutionToSkip.getValue().toArray(STRING_ARRAY_PROTOTYPE));
			}
		});

		return model;
	}

	private Optional<Profile> createProfile(Model model, String name) {
		final Profile profile = model.getProfiles().stream().filter(p -> name.equals(p.getId())).findFirst().orElseGet(() -> {
			Profile p = new Profile();
			p.setId(name);
			model.addProfile(p);
			return p;
		});

		if (profile.getBuild() == null) {
			profile.setBuild(new Build());
		}

		if (!profile.getBuild().getPlugins().isEmpty()) {
			logger.warn("Profile '" + name + "' has already plugins defined! Skipping...");
			return Optional.empty();
		}

		return Optional.of(profile);
	}

	private void createSkipExecutionPlugin(final Profile profile, final String groupId, final String artifactId,
			final String... neverExecutionIds) {
		final Plugin plugin = new Plugin();
		plugin.setGroupId(groupId);
		plugin.setArtifactId(artifactId);

		for (final String neverExecutionId : neverExecutionIds) {
			final PluginExecution pluginExecution = new PluginExecution();
			plugin.addExecution(pluginExecution);
			pluginExecution.setId(neverExecutionId);
			pluginExecution.setPhase("never");
		}

		profile.getBuild().getPlugins().add(plugin);
	}
}
