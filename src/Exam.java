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

    public Question getQuestion(int n){
        return questionList[n];
    }

    public void setAnswer(int n, char ch){
        answerList[n] = ch;
    }

    // Gives char correct prompt.
    public char getCorrect(int n){
        return questionList[n].getCorrectAnswer();
    }

    // Return true if selected prompt is correct, or false if is not. Do not show correct answer.
    public boolean checkAnswer(int n){
        return questionList[n].getCorrectAnswer() == answerList[n];
    }

    // Gives sum of points earned.
    public int getSumPoints(){
        int points = 0;
        for(int i=0; i<answerList.length; i++) if (checkAnswer(i)) points++;
        return points;
    }

    // Gives true or false when exam was passed of failed. Pass when percent of sum points is higher than 49%.
    public boolean getPassed(){
        return (((double) getSumPoints()) / ((double) answerList.length * 0.01)) > 49;
    }
}
