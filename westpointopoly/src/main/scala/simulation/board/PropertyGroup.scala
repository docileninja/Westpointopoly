package simulation.board

class PropertyGroup(name: String, color: (Int, Int, Int)) {
    var properties = Set.empty[Property]
}