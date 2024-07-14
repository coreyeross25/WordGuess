package com.github.zipcodewilmington;


import java.util.Random;
import java.util.Scanner;

public class Wordguess {
    public String[] words = {"red", "blue", "green", "yellow", "black"};
    public char[] secretWord;
    public char[] playerGuesses;
    public int maxTries;
    public int remainingTries;
    public Scanner scanner;
    public Random random;

    public Wordguess() {
        scanner = new Scanner(System.in);
        random = new Random();
    }

    public void initializeGameState() {
        for (int i = 0; i < playerGuesses.length; i++) {
            playerGuesses[i] = '_';
        }
        remainingTries = maxTries;
    }

    private void process(char guess) {
        boolean correctGuess = false;
        for (int i = 0; i < secretWord.length; i++) {
            if (secretWord[i] == guess) {
                playerGuesses[i] = guess;
                correctGuess = true;
            }
        }
        if (!correctGuess) {
            remainingTries--;
        }
    }

    public void printCurrentState() {
        System.out.print("Current Guesses: ");
        printArray(playerGuesses);
        System.out.println();
    }

    public char getNextGuess(Scanner scanner) {
        System.out.print("Enter a single character: ");
        return scanner.next().charAt(0);
    }

    public boolean isWordGuessed () {
        for (char guess : playerGuesses) {
            if (guess == '_') {
                return false;
            }
        }
        return true;
    }

    public boolean askToPlayAgain(Scanner scanner) {
        System.out.print("Would you like to play again? (yes/no): ");
        String response = scanner.next();
        return response.equals("yes");
    }

    public void gameOver() {
        System.out.println("Game Over.");
    }

    public void playerWon() {
        printCurrentState();
        System.out.println(" Congratulations, You Won!");
    }

    public void playerLost () {
        printCurrentState();
        System.out.println(":-( :-( :-(\nYou Lost! You ran out of guesses. The word was: " + String.valueOf(secretWord));
    }

    public void printArray (char[] array) {
        for (char c : array) {
            System.out.print(c + " ");
        }
    }

    public void runGame() {
        boolean playAgain = true;

        while (playAgain) {
            // Choose a random word
            secretWord = words[random.nextInt(words.length)].toCharArray();
            maxTries = secretWord.length;
            playerGuesses = new char[secretWord.length];
            initializeGameState();

            // Game loop
            while (remainingTries > 0 && !isWordGuessed()) {
                printCurrentState();
                System.out.println("You have " + remainingTries + " tries left.");
                char guess = getNextGuess(scanner);

                process(guess);
            }

            // Game over message
            if (isWordGuessed()) {
                playerWon();
            } else {
                playerLost();
            }

            // Ask to play again
            playAgain = askToPlayAgain(scanner);
        }

        gameOver(); // Display "game over" message
        scanner.close();
    }

    public static void main(String [] args) {
        Wordguess game = new Wordguess();
        game.runGame();
    }











}