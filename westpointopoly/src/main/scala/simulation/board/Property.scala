package simulation.board

case class Property(name: String, cost: Int, group: PropertyGroup) {
    def rent = cost / 2
    def toString = s"$name - $$$cost"
}