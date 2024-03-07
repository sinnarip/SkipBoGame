/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package skipbogame;

/**
 *
 * @author Naripjeet Singh
 */
import java.util.ArrayList;
import java.util.List;

public class Player {
    private final String name;
    private final List<Card> hand;

    public Player(String name) {
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public void addToHand(Card card) {
        hand.add(card);
    }

    public Card playCard(int cardNumber) {
        for (Card card : hand) {
            if (card.getNumber() == cardNumber) {
                hand.remove(card);
                return card;
            }
        }
        return null;
    }

    public String getHandAsString() {
        StringBuilder sb = new StringBuilder();
        for (Card card : hand) {
            sb.append(card.getNumber()).append(" ");
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }
}
