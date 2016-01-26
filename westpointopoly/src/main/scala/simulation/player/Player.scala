package simulation.player

case class Player(name: String, var money: Int = 500) {
    var properties = Set.empty[Property]
    
    def toString = s"$name ($money)"
}