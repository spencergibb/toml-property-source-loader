package org.springframework.cloud.toml;

import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.core.env.PropertySource;
import org.springframework.core.io.ClassPathResource;

import static org.assertj.core.api.Assertions.assertThat;

class TomlPropertySourceLoaderTests {

	private TomlPropertySourceLoader loader = new TomlPropertySourceLoader();

	@Test
	void getFileExtensions() {
		assertThat(this.loader.getFileExtensions()).isEqualTo(new String[] { "toml" });
	}

	@Test
	void loadProperties() throws Exception {
		List<PropertySource<?>> loaded = this.loader.load("test.toml",
				new ClassPathResource("test.toml", getClass()));
		PropertySource<?> source = loaded.get(0);
		assertThat(source.getProperty("servers.alpha.ip"))
				.isEqualTo("10.0.0.1");
	}
}
