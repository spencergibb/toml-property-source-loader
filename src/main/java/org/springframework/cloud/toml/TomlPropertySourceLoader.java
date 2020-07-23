package org.springframework.cloud.toml;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.tomlj.Toml;
import org.tomlj.TomlParseResult;

import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;

public class TomlPropertySourceLoader implements PropertySourceLoader {

	public String[] getFileExtensions() {
		return new String[] { "toml" };
	}

	public List<PropertySource<?>> load(String name, Resource resource) throws IOException {
		TomlParseResult result = Toml.parse(resource.getInputStream());
		result.errors().forEach(error -> System.err.println(error.toString()));

		TomlPropertySource propertySource = new TomlPropertySource("toml: " + resource
				.getFilename(), result);

		return Collections.singletonList(propertySource);
	}

	class TomlPropertySource extends PropertySource<TomlParseResult> {

		public TomlPropertySource(String name, TomlParseResult source) {
			super(name, source);
		}

		//TODO: EnumerablePropertySource

		@Override
		public Object getProperty(String key) {
			try {
				return getSource().get(key);
			}
			catch (IllegalArgumentException e) {
				// ignore, invalid toml key
			}
			return null;
		}
	}
}
