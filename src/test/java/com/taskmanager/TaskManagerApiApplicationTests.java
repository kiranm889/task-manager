package com.taskmanager;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TaskManagerApiApplicationTests {

	@Test
	@Disabled("Full context needs MySQL; web tests are in TaskControllerTest")
	void contextLoads() {
	}

}
