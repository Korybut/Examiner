import javax.swing.*;

/**
 * Created by Korybut on 06.08.2017.
 */
public class User {

    private String login;
    private char[] PASSWORD;
    private String name;
    private String surname;
    private int points;
    private int passExam;
    private int failExam;
    private int doneExam;
    private ImageIcon avatar;

    // When client create new profile with just necessary information.
    public User(String login, char[] PASSWORD, String name, String surname) {
        this.login = login;
        this.PASSWORD = PASSWORD;
        this.name = name;
        this.surname = name;
    }

    // When client profile is exist, or client expand his profile without image.
    public User(String login, char[] PASSWORD, String name, String surname, int points, int passExam, int failExam, int doneExam) {
        this.login = login;
        this.PASSWORD = PASSWORD;
        this.name = name;
        this.surname = surname;
        this.points = points;
        this.passExam = passExam;
        this.failExam = failExam;
        this.doneExam = doneExam;
    }

    // When profile is exist, or client expand his profile with profile image.
    public User(String login, char[] PASSWORD, String name, String surname, int points, int passExam, int failExam, int doneExam, ImageIcon avatar) {
        this.login = login;
        this.PASSWORD = PASSWORD;
        this.name = name;
        this.surname = surname;
        this.points = points;
        this.passExam = passExam;
        this.failExam = failExam;
        this.doneExam = doneExam;
        this.avatar = avatar;
    }

    //LOADING USER DATA AFTER AUTHENTICATION... to do
    //...
    //

    public String getLogin() {
        return login;
    }

    public char[] getPASSWORD() {
        return PASSWORD;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public int getPoints() {
        return points;
    }

    public int getPassExam() {
        return passExam;
    }

    public int getFailExam() {
        return failExam;
    }

    public int getDoneExam() {
        return doneExam;
    }

    public ImageIcon getAvatar() {
        return avatar;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public void setPassExam(int passExam) {
        this.passExam = passExam;
    }

    public void setFailExam(int failExam) {
        this.failExam = failExam;
    }

    public void setDoneExam(int doneExam) {
        this.doneExam = doneExam;
    }

    public void updateProfile(int points, boolean examStat){
        this.points += points;
        doneExam += 1;
        if(examStat) passExam += 1;
        else failExam += 1;
    }

    public void setAvatar(ImageIcon avatar) {
        this.avatar = avatar;
    }
}
