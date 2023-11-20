package model

data class Hypothesis(
    val name: String,
    val attributes: MutableList<Attribute>,
    var prior: Double,
)