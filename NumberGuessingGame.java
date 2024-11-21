import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            Random random = new Random();
            
            int totalScore = 0;
            int roundsPlayed = 0;
            boolean playAgain = true;
            
            System.out.println("Welcome to the Number Guessing Game!");
            
            while (playAgain) {
                roundsPlayed++;
                int numberToGuess = random.nextInt(100) + 1; // Step 1: Generate random number between 1 and 100
                int maxAttempts = 10; // Step 5: Limit attempts
                int attemptsUsed = 0;
                boolean guessedCorrectly = false;
                
                System.out.println("\nRound " + roundsPlayed + ":");
                System.out.println("I have chosen a number between 1 and 100. Try to guess it!");
                System.out.println("You have " + maxAttempts + " attempts.");
                
                // Step 2-4: Prompt user and check guesses
                while (attemptsUsed < maxAttempts) {
                    attemptsUsed++;
                    System.out.print("Attempt " + attemptsUsed + "/" + maxAttempts + " - Enter your guess: ");
                    
                    int userGuess;
                    try {
                        userGuess = Integer.parseInt(scanner.nextLine());
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input! Please enter a number.");
                        attemptsUsed--; // Don't count invalid input as an attempt
                        continue;
                    }
                    
                    if (userGuess < numberToGuess) {
                        System.out.println("Too low. Try again.");
                    } else if (userGuess > numberToGuess) {
                        System.out.println("Too high. Try again.");
                    } else {
                        System.out.println("Congratulations! You've guessed the number correctly in " + attemptsUsed + " attempts.");
                        guessedCorrectly = true;
                        break;
                    }
                }
                
                if (!guessedCorrectly) {
                    System.out.println("Sorry, you've used all your attempts. The correct number was: " + numberToGuess);
                }
                
                // Step 7: Update and display score
                int roundScore = guessedCorrectly ? Math.max(0, 10 - attemptsUsed) : 0;
                totalScore += roundScore;
                System.out.println("Your score for this round: " + roundScore);
                System.out.println("Total score after " + roundsPlayed + " rounds: " + totalScore);
                
                // Step 6: Ask if the user wants to play again
                System.out.print("Do you want to play again? (yes/no): ");
                String response = scanner.nextLine().trim().toLowerCase();
                if (!response.equals("yes")) {
                    playAgain = false;
                }
            }
            
            System.out.println("\nGame Over! You played " + roundsPlayed + " rounds.");
            System.out.println("Your total score is: " + totalScore);
            System.out.println("Thanks for playing!");
        }
    }
}
