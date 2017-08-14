import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by Korybut on 05.08.2017.
 */
public class Question implements Comparable {

    private int id;
    private String content;
    private ArrayList<String> prompt = new ArrayList<>(4);
    private char correct;
    private String imageURL;

    public Question(int id, String content, String[] prompt, char correct) {
        this.id = id;
        this.content = content;
        Collections.addAll(this.prompt, prompt);
        this.correct = correct;
    }

    public Question(int id, String content, String[] prompt, char correct, String imageURL) {
        this.id = id;
        this.content = content;
        Collections.addAll(this.prompt, prompt);
        this.correct = correct;
        this.imageURL = imageURL;
    }

    @Override
    public int compareTo(Object otherObject) {
        Question other = (Question) otherObject;
        return Double.compare(this.id, other.id);
    }

    // Check that the objects are that same.
    private static boolean contains(Question[] array, Question q){
        for(Question a : array){
            if(a != null) if (a.compareTo(q)==0) return true;
        }
        return false;
    }

    // Gives array of questions (without repetition) of size (n).
    static Question[] getRandomArray(Question[] qb, int n){
        Random rand = new Random();
        Question[] arr = new Question[n];
        arr[0] = qb[rand.nextInt(qb.length)];
        int count = 1;
        while(count<n){
            int a = rand.nextInt(qb.length);
            if(!Question.contains(arr,qb[a])){
                arr[count] = qb[a];
                count++;
            }
        }
        return arr;
    }

    // Reads files with questions data
    static Question[] readQuestionData(File file){
        List<Question> qb = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            int count = 0;
            while(scanner.hasNextLine()){
                String content = scanner.nextLine();
                String[] prompt = new String[4];
                for(int p=0; p<4; p++){
                    prompt[p] = scanner.nextLine();
                }
                char correct = scanner.nextLine().charAt(0);
                String imageURL = scanner.nextLine();
                if(imageURL.equals("null")) qb.add(new Question(count, content, prompt, correct));
                else qb.add(new Question(count, content, prompt, correct, imageURL));
                count++;
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return qb.toArray(new Question[qb.size()]);
    }

    public String getContent(){
        return content;
    }

    public String[] getPrompt(){
        String[] ar = new String[prompt.size()];
        return prompt.toArray(ar);
    }

    public String getPrompt(int index){
        return prompt.get(index);
    }

    public char getCorrectAnswer(){
        return correct;
    }

    public String getImageURL() { return imageURL; }
}
