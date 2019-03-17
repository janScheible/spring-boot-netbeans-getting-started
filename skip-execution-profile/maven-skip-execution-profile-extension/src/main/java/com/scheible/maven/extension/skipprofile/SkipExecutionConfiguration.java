package com.scheible.maven.extension.skipprofile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.UncheckedIOException;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import static java.util.Objects.requireNonNull;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;
import org.codehaus.plexus.logging.Logger;

/**
 *
 * @author sj
 */
public class SkipExecutionConfiguration {

	public static class Plugin {

		private final String groupId;
		private final String artifactId;

		Plugin(String groupId, String artifactId) {
			this.groupId = requireNonNull(groupId);
			this.artifactId = requireNonNull(artifactId);
		}

		public String getGroupId() {
			return groupId;
		}
		
		public String getArtifactId() {
			return artifactId;
		}

		@Override
		public int hashCode() {
			return Objects.hash(groupId, artifactId);
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj) {
				return true;
			} else if (obj instanceof Plugin) {
				Plugin other = (Plugin) obj;
				return Objects.equals(this.groupId, other.groupId) && Objects.equals(this.artifactId, other.artifactId);
			} else {
				return false;
			}
		}
	}

	private final Map<Plugin, Set<String>> pluginExecutionsIds;

	public SkipExecutionConfiguration() {
		this.pluginExecutionsIds = Collections.unmodifiableMap(new HashMap<>());
	}
	
	SkipExecutionConfiguration(final Map<Plugin, Set<String>> pluginExecutionsIds) {
		this.pluginExecutionsIds = Collections.unmodifiableMap(pluginExecutionsIds);
	}

	public static SkipExecutionConfiguration load(File file, Logger logger) {
		logger.debug("loading executions to skip from '" + file.getAbsolutePath() + "'.");
		final Map<Plugin, Set<String>> pluginExecutionsIds = new HashMap<>();
		
		try {
			final Scanner scanner = new Scanner(file, "UTF8");
			while(scanner.hasNextLine()) {
				final String line = scanner.nextLine().trim();
				if(!(line.isEmpty() || line.startsWith("#"))) {
					final Map.Entry<Plugin, String> parsed = parse(line);
					logger.debug("  " + parsed.getKey().getGroupId() + ":" + parsed.getKey().getArtifactId() + "@" + parsed.getValue());
					pluginExecutionsIds.computeIfAbsent( parsed.getKey(), (k) -> new HashSet<>()).add(parsed.getValue());
				}				
			}
			return new SkipExecutionConfiguration(pluginExecutionsIds);
		} catch (FileNotFoundException ex) {
			throw new UncheckedIOException(ex);
		}
	}
	
	static Map.Entry<Plugin, String> parse(final String line) {
		final String[] pluginAndExecutionId = line.split(Pattern.quote("@"));
		if(pluginAndExecutionId.length != 2) {
			throw new IllegalArgumentException("'" + line + "' does not contain an executionId preceded by an '@'! Required format: 'groupId:artifactId@executionId'.");
		}
		final String[] groupIdAndArtifactId = pluginAndExecutionId[0].split(Pattern.quote(":"));
		if(groupIdAndArtifactId.length != 2) {
			throw new IllegalArgumentException("'" + line + "' does not contain an groupId and artifactId separated by an ':'! Required format: 'groupId:artifactId@executionId'.");
		}
		
		final Plugin plugin = new Plugin(groupIdAndArtifactId[0], groupIdAndArtifactId[1]);
		
		return new AbstractMap.SimpleImmutableEntry<>(plugin, pluginAndExecutionId[1]);
	}

	public Map<Plugin, Set<String>> getPluginExecutionsIds() {
		return pluginExecutionsIds;
	}
}
