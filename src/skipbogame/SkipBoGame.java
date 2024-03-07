/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package skipbogame;

/**
 *
 * @author Naripjeet Singh
 */
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SkipBoGame {
    private static final int NUM_PLAYERS = 2;
    private static final int STARTING_HAND_SIZE = 5;

    private final List<Player> players;
    private final List<Card> drawPile;
    private int currentPlayerIndex;

    public SkipBoGame() {
        players = new ArrayList<>();
        drawPile = new ArrayList<>();
        currentPlayerIndex = 0;
    }

    public void startGame() {
        initializeDeck();
        dealHands();
        playGame();
    }

    private void initializeDeck() {
        List<Card> deck = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            for (int j = 0; j < 4; j++) {
                deck.add(new Card(i));
            }
        }
        Collections.shuffle(deck);
        drawPile.addAll(deck);
    }

    private void dealHands() {
        for (int i = 0; i < NUM_PLAYERS; i++) {
            Player player = new Player("Player " + (i + 1));
            for (int j = 0; j < STARTING_HAND_SIZE; j++) {
                if (!drawPile.isEmpty()) {
                    player.addToHand(drawPile.remove(0));
                }
            }
            players.add(player);
        }
    }

    private void playGame() {
        Scanner scanner = new Scanner(System.in);
        boolean gameEnd = false;

        while (!gameEnd) {
            Player currentPlayer = players.get(currentPlayerIndex);

            System.out.println(currentPlayer.getName() + "'s Turn");
            System.out.println("Your Hand: " + currentPlayer.getHandAsString());

            System.out.println("Choose a card to play (enter the card number or 0 to draw from draw pile):");
            int cardNumber = scanner.nextInt();

            if (cardNumber == 0) {
                if (!drawPile.isEmpty()) {
                    Card drawnCard = drawPile.remove(0);
                    currentPlayer.addToHand(drawnCard);
                    System.out.println("You drew a " + drawnCard);
                } else {
                    System.out.println("Draw pile is empty. Cannot draw a card.");
                }
            } else {
                Card selectedCard = currentPlayer.playCard(cardNumber);
                if (selectedCard != null) {
                    System.out.println("You played a " + selectedCard);
                    // Add logic to play the card on a pile or discard it
                } else {
                    System.out.println("Invalid card selection. Try again.");
                }
            }

            if (currentPlayer.getHand().isEmpty()) {
                gameEnd = true;
                System.out.println(currentPlayer.getName() + " has emptied their hand. They win!");
            }

            currentPlayerIndex = (currentPlayerIndex + 1) % NUM_PLAYERS;
        }

        scanner.close();
    }

    public static void main(String[] args) {
        SkipBoGame game = new SkipBoGame();
        game.startGame();
    }
}
