package com.scheible.maven.extension.skipprofile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
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
		getOrCreateProfile(model, "skip-compilation-and-resources", profile -> {
			createSkipPluginExecution(profile, DEFAULT_GROUP_ID, "maven-resources-plugin",
					"default-resources", "default-testResources");
			createSkipPluginExecution(profile, DEFAULT_GROUP_ID, "maven-compiler-plugin",
					"default-compile", "default-testCompile");
		});

		getOrCreateProfile(model, "skip-unit-tests", profile -> {
			createSkipPluginExecution(profile, DEFAULT_GROUP_ID, "maven-surefire-plugin", "default-test");
		});

		getOrCreateProfile(model, "skip-flattening", profile -> {
			createSkipPluginExecution(profile, "org.codehaus.mojo", "flatten-maven-plugin", "default");
		});

		getOrCreateProfile(model, "skip-all-but-compilation-and-resources-and-unit-tests", profile -> {
			for (Map.Entry<SkipExecutionConfiguration.Plugin, Set<String>> pluginExecutionToSkip
					: skipExecutionConfiguration.getPluginExecutionsIds().entrySet()) {
				createSkipPluginExecution(profile, pluginExecutionToSkip.getKey().getGroupId(),
						pluginExecutionToSkip.getKey().getArtifactId(),
						pluginExecutionToSkip.getValue().toArray(STRING_ARRAY_PROTOTYPE));
			}
		});

		return model;
	}

	private void getOrCreateProfile(Model model, String name, Consumer<Profile> profileConsumer) {
		final Profile profile = model.getProfiles().stream().filter(p -> name.equals(p.getId())).findFirst().orElseGet(() -> {
			Profile p = new Profile();
			p.setId(name);
			model.addProfile(p);
			return p;
		});

		if (profile.getBuild() == null) {
			profile.setBuild(new Build());
		}

		profileConsumer.accept(profile);
	}

	private void createSkipPluginExecution(final Profile profile, final String groupId, final String artifactId,
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

		final int pluginIndex = profile.getBuild().getPlugins().indexOf(plugin);
		final boolean skipPluginExecutionAlreadyExists = pluginIndex >= 0;
		if(skipPluginExecutionAlreadyExists) {
			final Plugin existingPlugin = profile.getBuild().getPlugins().get(pluginIndex);
			
			final Set<String> pluginExecutionIds = existingPlugin.getExecutionsAsMap().keySet();
			pluginExecutionIds.removeAll(Arrays.asList(neverExecutionIds));
			final boolean neverExecutionIdsMatch = pluginExecutionIds.isEmpty();
			
			if(neverExecutionIdsMatch) {
				final boolean phaseIsAlwaysNever = existingPlugin.getExecutionsAsMap().values().stream()
						.map(PluginExecution::getPhase).filter(phase -> !"never".equals(phase)).count() == 0;
				if(phaseIsAlwaysNever) {
					// skip plugin execution already exists
					return;
				}
			}
		}

		if(!skipPluginExecutionAlreadyExists) {
			profile.getBuild().getPlugins().add(plugin);
		} else {
			logger.warn("Skip plugin execution for '" + plugin.getKey() + "' was already there but is different!");		
		}
	}
}
