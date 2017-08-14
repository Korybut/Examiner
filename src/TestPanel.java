import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Korybut on 14.08.2017.
 */
public class TestPanel extends JPanel {

    private JPanel[] questPanel;
    private JTextArea content = new JTextArea();
    private JRadioButton[][] radioButtons;
    private ButtonGroup[] buttonGroup;
    private JButton next;
    private JButton prev;
    private JButton finish;
    private int count = 0; // Counting currently question number


    public TestPanel(Exam exam, User user) {
        setBounds(0,40,960,600);
        setVisible(true);
        setLayout(null);

        //button ending the test
        finish = new JButton("Sprawdź wynik");
        finish.setBounds(380,460,200,40);
        finish.setVisible(false);
        finish.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                for(int i=0; i<exam.getSize(); i++){
                    System.out.println();
                    exam.setAnswer(i, getSelectedAnswer(i));
                }
                System.out.println(exam.getSumPoints());
                // set user points
                // set passed or failed exams
            }
        });
        add(finish);

        // Components non-editable during exam action
        questPanel = new JPanel[exam.getSize()];
        radioButtons = new JRadioButton[exam.getSize()][4];
        buttonGroup = new ButtonGroup[exam.getSize()];
        // Create RadioButtons for all question
        for(int i=0; i<exam.getSize(); i++){
            questPanel[i] = new JPanel();
            questionSet(i, exam.getQuestion(i));
        }

        // set TextArea with content of question
        content.setBounds(30,30,540,200);
        content.setLineWrap(true);
        content.setWrapStyleWord(true);
        content.setFocusable(false);
        content.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        content.setBackground(null);
        content.setBorder(null);
        content.setFont(new Font("Corbel", Font.PLAIN, 30));
        content.setVisible(true);

        // Starting set components
        questPanel[0].setVisible(true);
        content.setText("Pytanie 1:\n" + exam.getQuestion(count).getContent());
        add(content);

        // Next Button
        next = new JButton("Dalej");
        next.setBounds(840,490,80,40);
        next.setVisible(true);
        next.addActionListener(e -> nextQuestion(exam.getQuestion(++count), exam.getSize()));
        add(next);

        // Preview Button
        prev = new JButton("Wstecz");
        prev.setBounds(40,490,80,40);
        prev.setVisible(true);
        prev.setEnabled(false);
        prev.addActionListener(e -> prevQuestion(exam.getQuestion(--count)));
        add(prev);
    }

    // Next button action
    private void nextQuestion(Question quest, int size){
        content.setText("Pytanie " + (count+1) + ":\n" + quest.getContent());
        questPanel[count-1].setVisible(false);
        questPanel[count].setVisible(true);
        if(count==size-1){
            next.setEnabled(false);
        }
        if(!prev.isEnabled()) prev.setEnabled(true);
    }

    // Preview button action
    private void prevQuestion(Question quest){
        content.setText("Pytanie " + (count+1) + ":\n" + quest.getContent());
        questPanel[count+1].setVisible(false);
        questPanel[count].setVisible(true);
        if(count==0) prev.setEnabled(false);
        if(!next.isEnabled()) next.setEnabled(true);
    }

    // set RadioButtons and adds them to buttonGroup
    private void questionSet(int n, Question quest){
        buttonGroup[n] = new ButtonGroup();
        questPanel[n].setLayout(null);
        questPanel[n].setBounds(0,250,960,200);
        add(questPanel[n]);
        for(int i=0, p=0; i<4; i++, p+=50){
            radioButtons[n][i] = new JRadioButton(quest.getPrompt(i));
            radioButtons[n][i].setFont(new Font("Corbel", Font.PLAIN, 24));
            radioButtons[n][i].setBounds(30,p,700,30);
            radioButtons[n][i].setFocusable(false);
            radioButtons[n][i].addActionListener(e -> allQuestionSelected());
            questPanel[n].add(radioButtons[n][i]);
            buttonGroup[n].add(radioButtons[n][i]);
        }

    }

    // Check that all the answer have been selected
    private void allQuestionSelected(){
        boolean check = true;
        for(ButtonGroup bg : buttonGroup) {
            if(bg.getSelection()==null) check = false;
        }
        finish.setVisible(check);
    }

    // Given selected answer (RadioButton index)
    private int getSelectedAnswer(int n){
        if(radioButtons[n][0].isSelected()) return 0;
        else if(radioButtons[n][1].isSelected()) return 1;
        else if(radioButtons[n][2].isSelected()) return 2;
        else return 3;
    }

}
