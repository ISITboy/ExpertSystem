package uom

import model.Attribute

class PosteriorProbability() {
    fun getArrayPosteriorProbability(priori:Double, attributes : MutableList<Attribute>):MutableList<Array<Double>>{
        val result = mutableListOf<Array<Double>>().also {
            for (i in 0 until attributes.size){
                val table = Array((2)) { 0.0 }
                table[0] = formula1(p=priori, pLus = attributes[i].pPlus, pMinus = attributes[i].pMinus)
                table[1] = formula2(p=priori, pLus = attributes[i].pPlus, pMinus = attributes[i].pMinus)
                it.add(i,table)
            }
        }
        return result
    }
    private fun formula2(p:Double, pLus:Double,pMinus:Double) :Double = ((1-pLus)*p)/(1-((p*pLus)+(pMinus*(1-p))))
    private fun formula1(p:Double, pLus:Double,pMinus:Double) :Double = (p*pLus)/((p*pLus)+(pMinus*(1-p)))
}