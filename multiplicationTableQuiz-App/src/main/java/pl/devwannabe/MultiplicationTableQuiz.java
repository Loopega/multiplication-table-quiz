package pl.devwannabe;

import java.text.SimpleDateFormat;
import java.util.*;
/**
 * Created by root on 22.11.17.
 */
public class MultiplicationTableQuiz {

    private static final String EXIT_CHARACTER = "q";
    private static final Random RANDOM = new Random();
    private static final Scanner SCANNER = new Scanner(System.in);
    private int correctAnswers;
    private int incorrectAnswers;
    public void start() {
        System.out.println("Hey, this is program which will ask you for a result of multiplication until 100.");
        System.out.println("If you wanna leave, press '" + EXIT_CHARACTER + "'. ");
        Stopwatch stopwatch = new Stopwatch("Summary time of answers");
        stopwatch.start();
        outerloop:
        while (true) {
            int factor1 = nextRandomFactor();
            int factor2 = nextRandomFactor();
            String answer = getUserAnswer(factor1, factor2);
            if (answer.equalsIgnoreCase(EXIT_CHARACTER)) {
                break;
            }
            if (isNumber(answer)) {
                compare(Integer.parseInt(answer), factor1 * factor2);
            } else {
                while (true) {
                    if (!isNumber(answer) && !answer.equalsIgnoreCase(EXIT_CHARACTER)) {
                        System.out.println("Bad format of answer, press 'integer' or 'q' if you wanna leave. ");
                        answer = getUserAnswer(factor1, factor2);
                    } else if (answer.equalsIgnoreCase(EXIT_CHARACTER)) {
                        break outerloop;
                    } else {
                        compare(Integer.parseInt(answer), factor1 * factor2);
                        break;
                    }
                }
            }
        }
        stopwatch.stop();
        summarize(stopwatch.toString());
    }
    private boolean isNumber(String answer) {
        return answer.matches("\\d+");
    }
    private int nextRandomFactor() {
        return RANDOM.nextInt(9) + 2;
    }
    private int getSumOfAnswers() {
        return correctAnswers + incorrectAnswers;
    }
    private String getUserAnswer(int n1, int n2) {
        System.out.println("How many it is? ");
        System.out.println(n1 + " * " + n2);
        return SCANNER.next();
    }
    private void compare(int userRes, int computerRes) {
        if (userRes == computerRes) {
            correctAnswers++;
            System.out.println("The correct Result");
        } else {
            incorrectAnswers++;
            System.out.println("Invalid result ");
            System.out.println("The correct result is: >> " + computerRes + " <<");
        }
    }
    private String sessionDate() {
        Date date = new Date();
        SimpleDateFormat dateFormatter = new SimpleDateFormat("EEEE d MMMM yyyy HH:mm:ss");
        return dateFormatter.format(date);
    }
    private void summarize(String stopwatch) {
        List<String> summaryList = new ArrayList<>();
        summaryList.add("\nSession summary of " + sessionDate());
        summaryList.add("----------------------------------\n");
        summaryList.add(stopwatch);
        summaryList.add("Sum of correct answers: >> " + correctAnswers + " <<");
        summaryList.add("Sum of no valid answers: >> " + incorrectAnswers + " <<");
        summaryList.add("Sum of answers: >> " + getSumOfAnswers() + " <<");
        summaryList.add("Ok, end of questions, cu next time.");
        for (String line : summaryList) {
            System.out.println(line);
        }
        SessionFile sessionFile = new SessionFile("src/session file.txt");
        sessionFile.append(summaryList);
    }
}
