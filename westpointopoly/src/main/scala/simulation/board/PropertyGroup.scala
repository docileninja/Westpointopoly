package simulation.board

case class PropertyGroup(name: String, color: (Int, Int, Int)) {
    var properties = Set.empty[Property]
}