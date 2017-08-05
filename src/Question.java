import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Korybut on 05.08.2017.
 */
public class Question implements Comparable{

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
}
