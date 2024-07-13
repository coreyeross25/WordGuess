package com.github.zipcodewilmington;


import java.util.Random;
import java.util.Scanner;

public class Wordguess {
    public static String[] words = {"house", "apartment", "villa", "condo"};
    public static Random random = new Random();
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("Let's Play Wordguess version 1.0!");
        boolean playAgain = true;
        while (playAgain) {

            String secretWord = words[random.nextInt(words.length)];
            char[] playerGuesses = new char[secretWord.length()];


            for (int i = 0; i < playerGuesses.length; i++) {
                playerGuesses[i] = '_';
            }

            int maxTries = secretWord.length();
            int remainingTries = maxTries;


            while (remainingTries > 0 && !isWordGuessed(playerGuesses)) {
                displayCurrentState(playerGuesses, remainingTries);


                char guess = getNextGuess();


                boolean foundLetter = false;
                for (int i = 0; i < secretWord.length(); i++) {
                    if (secretWord.charAt(i) == guess) {
                        playerGuesses[i] = guess;
                        foundLetter = true;
                    }
                }

                if (!foundLetter) {
                    remainingTries--;
                    System.out.println("Letter '" + guess + "' is not in the word.");
                }
            }


            if (isWordGuessed(playerGuesses)) {
                System.out.println("Congratulations, You Won! The word was: " + secretWord);
            } else {
                System.out.println("You Lost! The word was: " + secretWord);
            }


            System.out.print("Would you like to play again? (yes/no): ");
            String response = scanner.next();
            playAgain = response.equals("yes");
        }


        System.out.println("Game Over.");
        scanner.close();
    }


    private static void displayCurrentState(char[] playerGuesses, int remainingTries) {
        System.out.print("Current Guesses: ");
        for (char guess : playerGuesses) {
            System.out.print(guess + " ");
        }
        System.out.println("\nYou have " + remainingTries + " tries left.");
    }


    private static char getNextGuess() {
        System.out.print("Enter a single character: ");
        return scanner.next().charAt(0);
    }


    private static boolean isWordGuessed(char[] playerGuesses) {
        for (char guess : playerGuesses) {
            if (guess == '_') {
                return false;
            }
        }
        return true;
    }
}



