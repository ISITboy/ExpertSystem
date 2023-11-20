package uom

import kotlin.math.abs

class CertificatePrice() {

    fun getMaxCertificatePrice(vararg posteriorProbabilityList: MutableList<Double>):Int{
        val table = Array(posteriorProbabilityList[0].size) { 0.0 }
        for (i in 0 until posteriorProbabilityList[0].size){
            for (list in posteriorProbabilityList){
                table[i] += list.get(i)
            }
        }
        return getMaxNumber(table)
    }

    private fun getMaxNumber(table: Array<Double>):Int {
        var maxIndex = 0
        table.forEachIndexed{index, value ->
            if (value==table.max()) maxIndex = index
        }
        return maxIndex
    }


    fun getABSPosteriorProbability(posteriorProbability:MutableList<Array<Double>>):MutableList<Double>{
        val result = mutableListOf<Double>().also {
            for (i in 0 until posteriorProbability.size){
                val itemArray = posteriorProbability[i]
                it.add(i,differenceABS(itemArray[0],itemArray[1]))
            }
        }
        return result
    }
    private fun differenceABS(n1:Double,n2:Double ) = abs(n1-n2)
}