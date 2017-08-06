import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.List;

/**
 * Created by Korybut on 05.08.2017.
 */
public class Question implements Comparable {

    private int id;
    private String content;
    private ArrayList<String> prompt = new ArrayList<>(4);
    private char correct;

    public Question(int id, String content, String[] prompt, char correct) {
        this.id = id;
        this.content = content;
        Collections.addAll(this.prompt, prompt);
        this.correct = correct;
    }

    public Question(int id, String content, String[] prompt, char correct, String imgName) {
        this.id = id;
        this.content = content;
        Collections.addAll(this.prompt, prompt);
        this.correct = correct;
        ImageIcon picture = new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource(imgName));
    }

    @Override
    public int compareTo(Object otherObject) {
        Question other = (Question) otherObject;
        return Double.compare(this.id, other.id);
    }

    // Check that the objects are that same.
    private static boolean contains(Question[] array, Question q){
        for(Question a : array){
            if(a.compareTo(q)==0) return true;
        }
        return false;
    }

    // Gives array of questions (without repetition) of size (n).
    static Question[] getRandomArray(Question[] qb, int n){
        Random rand = new Random();
        Question[] arr = new Question[n];
        int a = rand.nextInt(qb.length);
        for(Question q : arr){
            while(Question.contains(arr, qb[a])) a = rand.nextInt(qb.length);
            q = qb[a];
        }
        return arr;
    }

    static Question[] readQuestionData(File file){
        List<Question> qb = null;
        try {
            Scanner scanner = new Scanner(file);
            int count = 0;
            while(scanner.hasNextLine()){
                String content = scanner.nextLine();
                String[] prompt = new String[4];
                for(String p : prompt) p = scanner.nextLine();
                char correct = scanner.nextLine().charAt(0);
                String imgName = scanner.nextLine();
                if(imgName==null) qb.add(new Question(count, content, prompt, correct));
                else qb.add(new Question(count, content, prompt, correct, imgName));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        if (qb != null) {
            return qb.toArray(new Question[qb.size()]);
        }
        return new Question[0];
    }

}
