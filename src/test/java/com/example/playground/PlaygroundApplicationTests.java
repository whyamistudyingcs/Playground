package com.example.playground;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.*;

@SpringBootTest
class PlaygroundApplicationTests {

	@Autowired
	private PlaygroundApplication playgroundApplication;

	@Test
	void contextLoads() {
		assertThat(playgroundApplication).isNotNull();
	}

}
