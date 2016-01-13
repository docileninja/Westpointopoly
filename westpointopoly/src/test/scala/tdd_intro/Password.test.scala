package tdd_intro

import org.scalatest.{FunSpec, Matchers}

class PasswordTest extends FunSpec with Matchers {

	describe("Password checker rejects passwords") {
		it("if they are less that 6 characters in length") {
			val short = "4dm1n"
			val justright = "qwerty"
			val longer = "137_m3_1n"

			Password.isValid(short) shouldBe false
			Password.isValid(justright) shouldBe true
			Password.isValid(longer) shouldBe true
		}
	}

}