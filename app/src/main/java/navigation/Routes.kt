package navigation


sealed class Routes(val route: String) {
    object Launch : Routes("launch")
    object Menu : Routes("menu")
    object Game : Routes("game/{difficulty}") {
        fun createRoute(difficulty: String) = "game/$difficulty"
    }
}
