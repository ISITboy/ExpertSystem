import informationBase.data.*
import model.Attribute
import model.Choose
import model.Hypothesis
import model.Question
import uom.CertificatePrice
import uom.PosteriorProbability
import uom.Questions

fun main(args: Array<String>) {
    val namesHypothesis = getListAttributeOrHypothesis(DataHypothesis.NAME_HYPOTHESIS)
    val attributesForQuestions  = getListAttributeOrHypothesis(DataAttribute.NAME_ATTRIBUTE)
    val posteriorProbability = PosteriorProbability()
    val certificatePrice = CertificatePrice()
    val questions = Questions()

    var resultPosteriorProbability1 : MutableList<Array<Double>>
    var resultPosteriorProbability2 : MutableList<Array<Double>>
    var resultPosteriorProbability3 : MutableList<Array<Double>>
    var resultCertificatePrice1 : MutableList<Double>
    var resultCertificatePrice2 : MutableList<Double>
    var resultCertificatePrice3 : MutableList<Double>
    var currentQuestion : Question
    var choose:Choose

    val hypothesis1 = createHypothesis(
        namesHypothesis[0],
        createAttributes(DataAttribute.NAME_ATTRIBUTE, DataHypothesis.P_FOR_EKTO),
        prioryH1
    )
    val hypothesis2 = createHypothesis(
        namesHypothesis[1],
        createAttributes(DataAttribute.NAME_ATTRIBUTE, DataHypothesis.P_FOR_MESO),
        prioryH2
    )
    val hypothesis3 = createHypothesis(
        namesHypothesis[2],
        createAttributes(DataAttribute.NAME_ATTRIBUTE, DataHypothesis.P_FOR_ENDO),
        prioryH3
    )


    while (attributesForQuestions.isNotEmpty()){
        resultPosteriorProbability1 = posteriorProbability.getArrayPosteriorProbability(hypothesis1.prior,hypothesis1.attributes)
        resultPosteriorProbability2 = posteriorProbability.getArrayPosteriorProbability(hypothesis2.prior,hypothesis2.attributes)
        resultPosteriorProbability3 = posteriorProbability.getArrayPosteriorProbability(hypothesis3.prior,hypothesis3.attributes)

        resultCertificatePrice1 = certificatePrice.getABSPosteriorProbability(resultPosteriorProbability1)
        resultCertificatePrice2 = certificatePrice.getABSPosteriorProbability(resultPosteriorProbability2)
        resultCertificatePrice3 = certificatePrice.getABSPosteriorProbability(resultPosteriorProbability3)


        currentQuestion = questions.getQuestion(attributesForQuestions,certificatePrice.getMaxCertificatePrice(
            resultCertificatePrice1, resultCertificatePrice2, resultCertificatePrice3
        ))

        choose = questions.choose(currentQuestion)

        if (choose.result){
            changePrioriPlus(
                choose.number!!,
                listOf(resultPosteriorProbability1,resultPosteriorProbability2,resultPosteriorProbability3),
                hypothesis1,hypothesis2,hypothesis3)
        }else
            changePrioriMinus(
                choose.number!!,
                listOf(resultPosteriorProbability1,resultPosteriorProbability2,resultPosteriorProbability3),
                hypothesis1,hypothesis2,hypothesis3)

        attributesForQuestions.removeAt(choose.number!!)
        resultPosteriorProbability1.removeAt(choose.number!!)
        resultPosteriorProbability2.removeAt(choose.number!!)
        resultPosteriorProbability3.removeAt(choose.number!!)
        resultCertificatePrice1.removeAt(choose.number!!)
        resultCertificatePrice2.removeAt(choose.number!!)
        resultCertificatePrice3.removeAt(choose.number!!)

    }

    val resultIndex = findMaxPriori(hypothesis1,hypothesis2,hypothesis3)

    when(resultIndex){
        0 -> println(hypothesis1.name)
        1 -> println(hypothesis2.name)
        2 -> println(hypothesis3.name)
    }
}



fun createAttributes(namesAttributes: String, listP: Array<DoubleArray?>):MutableList<Attribute>{
    var indexForNameAttribute = 0
    val listNameAttribute = getListAttributeOrHypothesis(namesAttributes)
    val listAttributes = mutableListOf<Attribute>()
    for (i in listP.indices){
        for(j in 0 until 2 step 2){
            listAttributes.add(
                Attribute(listNameAttribute[indexForNameAttribute++], listP[i]!![j],listP[i]!![j+1])
            )
            continue
        }
    }
    return listAttributes
}

fun createHypothesis(nameHypothesis: String,attributes: MutableList<Attribute>, proir:Double) =
    Hypothesis(nameHypothesis,attributes,proir)

fun changePrioriPlus(number:Int,list : List<MutableList<Array<Double>>>,vararg hypothesises: Hypothesis){
    for (i in 0 until hypothesises.size){
        val listPosteriorProbability = list[i]
        val arrayPosteriorProbability = listPosteriorProbability[number]
        hypothesises[i].prior = arrayPosteriorProbability[0]
        hypothesises[i].attributes.removeAt(number)
    }
}
fun changePrioriMinus(number:Int,list : List<MutableList<Array<Double>>>,vararg hypothesises: Hypothesis){
    for (i in 0 until hypothesises.size){
        val listPosteriorProbability = list[i]
        val arrayPosteriorProbability = listPosteriorProbability[number]
        hypothesises[i].prior = arrayPosteriorProbability[1]
        hypothesises[i].attributes.removeAt(number)
    }
}

fun findMaxPriori(vararg hypothesises: Hypothesis):Int{
    var result :Int = 0
    val arr = listOf<Double>(hypothesises[0].prior,hypothesises[1].prior,hypothesises[2].prior)
    hypothesises.forEachIndexed(){index, hypothesis ->
        if (hypothesis.prior==arr.max()){
            result = index
        }
    }
    return result
}