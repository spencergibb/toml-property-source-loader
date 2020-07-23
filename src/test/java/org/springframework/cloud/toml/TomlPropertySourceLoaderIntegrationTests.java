package org.springframework.cloud.toml;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TomlPropertySourceLoaderIntegrationTests {

	@Autowired
	Environment env;

	@Test
	void loadProperties() throws Exception {
		assertThat(env.getProperty("servers.alpha.ip"))
				.isEqualTo("10.0.0.1");
	}

	@SpringBootConfiguration
	protected static class TestConfig {

	}
}
