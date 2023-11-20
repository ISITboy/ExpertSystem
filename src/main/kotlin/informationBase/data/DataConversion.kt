package informationBase.data

fun getListAttributeOrHypothesis(data: String) = data.split(",") as MutableList

var prioryH1 = 0.001
var prioryH2 = 0.03
var prioryH3 = 0.01