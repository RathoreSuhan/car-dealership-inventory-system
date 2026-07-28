package com.incubyte.backend;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Basic JUnit test to verify
 * that the testing environment
 * is configured correctly.
 */
class BackendApplicationTests {

	@Test
	void shouldVerifyJUnitIsWorking() {

		// Arrange
		int expected = 4;

		// Act
		int actual = 2 + 2;

		// Assert
		assertEquals(expected, actual);

	}
}