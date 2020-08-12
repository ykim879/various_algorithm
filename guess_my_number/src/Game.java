import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private int userRange;
    private int minValue;
    private int maxValue;
    private boolean gotAnswer;
    private final int Max_Trial = 50;

    public Game() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.print("Select the range of the integer N((1 ≤ N ≤ 10E9): ");
                userRange = scanner.nextInt();
                if (userRange < 1 || userRange > 10E9) {
                    throw new InputMismatchException("Out of range");
                }
                break;
            }catch (InputMismatchException e) {
                System.out.println("The input has to be the integer N((1 ≤ N ≤ 10E9). Type again: ");
            }
        }
        maxValue = userRange;
        minValue = 1;
        gotAnswer = false;
    }
    private int guessNumber() {
        Random random = new Random();
        int guess = random.nextInt(maxValue -1) + minValue;
        System.out.println("? " + guess);
        return guess;
    }
    private void response(int guess) {
        Scanner scanner = new Scanner(System.in);
        int response;
        while (true) {
            try {
                System.out.print("input -1 when it's bigger than your number, 1 when it's smaller, or 0 when it's correct: ");
                response = scanner.nextInt();
                if (userRange != -1 && userRange != 0 && userRange != 1) {
                    throw new InputMismatchException("Out of range");
                }
                break;
            }catch (InputMismatchException e) {
                System.out.println("The input must be 1, 0, or -1.");
            }
        }
        if (response == 1) {
            minValue = guess;
        } else if(response == -1) {
            maxValue = guess;
        } else {
            guessRightNumber(guess);
        }

    }
    private void guessRightNumber(int answer) {
        System.out.print("= " + answer);
        gotAnswer = true;
    }
    public void startGame() {
        int trial = 0;
        while (trial < Max_Trial && !gotAnswer) {
            int guess = guessNumber();
            response(guess);
            trial++;
        }
        if(!gotAnswer) {
            System.out.println("The Program is unable to guess your number! You win!");
        }
    }
}
