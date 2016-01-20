package simulation

class Player(name: String) {
    var money: Int = 500
}

object Player {
    def apply(name: String) = new Player(name)
}