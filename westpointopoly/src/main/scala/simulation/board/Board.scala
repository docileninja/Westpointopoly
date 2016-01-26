package simulation.board

class Board(playerNames: Seq[String]) {
    private val players = PlayerOrder(playerNames:_*)
    
    def showGame = {
		var board = ""
		board += "+-------------+\n"
		board += "|_|_|_|_|_|_|_|\n"
		board += "|_|         |_|\n"
		board += "|_|         |_|\n"
		board += "|_|_ _ _ _ _|_|\n"
		board += "|_| | | | | |_|\n"
		board += "+-------------+"
		board += listProperties
		board
	}
	
	def listPlayers = "Players: " + players.toString

	def listProperties = players.map { p: Player => p + "\n" + p.properties.mkString("\n") + "\n"}.mkString("\n")

	def advanceTurn() = players.advance()
	
}