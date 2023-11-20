package uom

import model.Choose
import model.Question

class Questions {

    fun getQuestion(question: List<String>, number:Int):Question {
        var questions: Question? = null
        question.forEachIndexed { index, value ->
            if (index == number)
                questions = Question(value, index)
        }
        if(question==null){
            return Question(null,null)
        }
        else return questions!!
    }

    fun choose(question: Question): Choose {
        println("Question: ${question.name}\n" +
                "Choose: y/n"); var choose = readln()
        val result = choose=="y"
        return Choose(result,question.number)
    }

}