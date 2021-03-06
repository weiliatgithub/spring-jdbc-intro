package org.wei.spring.jdbc.spock

import spock.lang.*

class FirstSpocTest extends Specification {

	def "maximum of two numbers"(int a, int b, int c) {
		expect:
			Math.max(a, b) == c

		where:
			a | b | c
			1 | 3 | 3
			7 | 4 | 7
			0 | 0 | 0
	}
}