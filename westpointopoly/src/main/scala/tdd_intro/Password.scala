package tdd_intro

object Password {
	def isValid(password: String): Boolean = password.length >= 6
}