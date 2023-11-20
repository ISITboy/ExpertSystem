package informationBase.data

object DataHypothesis {
    const val NAME_HYPOTHESIS="Эктоморф,Мезоморф,Эндоморф"

    val P_FOR_EKTO = arrayOfNulls<DoubleArray>(10).also {
        it[0] = doubleArrayOf(0.8,0.01)
        it[1] = doubleArrayOf(0.9,0.2)
        it[2] = doubleArrayOf(0.9,0.2)
        it[3] = doubleArrayOf(0.6,0.099)
        it[4] = doubleArrayOf(0.7,0.3)
        it[5] = doubleArrayOf(0.6,0.3)
        it[6] = doubleArrayOf(0.5,0.001)
        it[7] = doubleArrayOf(0.01,0.01)
        it[8] = doubleArrayOf(0.001,0.01)
        it[9] = doubleArrayOf(0.05,0.7)
    }
    val P_FOR_MESO = arrayOfNulls<DoubleArray>(10).also {
        it[0] = doubleArrayOf(0.5,0.8)
        it[1] = doubleArrayOf(0.3,0.8)
        it[2] = doubleArrayOf(0.4,0.8)
        it[3] = doubleArrayOf(0.9,0.3)
        it[4] = doubleArrayOf(0.9,0.4)
        it[5] = doubleArrayOf(0.8,0.3)
        it[6] = doubleArrayOf(0.8,0.2)
        it[7] = doubleArrayOf(0.6,0.7)
        it[8] = doubleArrayOf(0.2,0.7)
        it[9] = doubleArrayOf(0.3,0.6)
    }
    val P_FOR_ENDO = arrayOfNulls<DoubleArray>(10).also {
        it[0] = doubleArrayOf(0.02,0.8)
        it[1] = doubleArrayOf(0.06,0.9)
        it[2] = doubleArrayOf(0.2,0.6)
        it[3] = doubleArrayOf(0.7,0.3)
        it[4] = doubleArrayOf(0.6,0.5)
        it[5] = doubleArrayOf(0.8,0.7)
        it[6] = doubleArrayOf(0.5,0.8)
        it[7] = doubleArrayOf(0.9,0.1)
        it[8] = doubleArrayOf(0.9,0.01)
        it[9] = doubleArrayOf(0.9,0.03)
    }
}