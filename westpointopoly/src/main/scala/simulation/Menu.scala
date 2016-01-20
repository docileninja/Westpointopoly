package simulation

class Menu {
    private val players = PlayerOrder()

	def showGame = {
		var board = ""
		board += "+-------------+\n"
		board += "|_|_|_|_|_|_|_|\n"
		board += "|_|         |_|\n"
		board += "|_|         |_|\n"
		board += "|_|_ _ _ _ _|_|\n"
		board += "|_| | | | | |_|\n"
		board += "+-------------+"
		board
	}

	def listPlayers = "Players: " + players.toString

	def advanceTurn() = players.advance()
}

object Menu {
    def apply() = new Menu()
}