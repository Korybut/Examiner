/**
 * Created by Korybut on 06.08.2017.
 */
public class Exam {

    private Question[] questionList;
    private char[] answerList;

    public Exam(Question[] arr) {
        questionList = arr;
        answerList = new char[questionList.length];
    }

}
