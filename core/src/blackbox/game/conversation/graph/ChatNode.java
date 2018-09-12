package blackbox.game.conversation.graph;

import java.util.*;

/**
 * A "choice" in a conversation
 * where multiple options are
 * presented to the player.
 */
public class ChatNode {
    private ArrayList<Choice> choices;
    private String text;

    /**
     * Construct a new ChatNode
     * @param choices List of choices
     * @param text    Text to display (What the AI says)
     */
    public ChatNode(ArrayList<Choice> choices, String text) {
        this.choices = choices;
        this.text = text;
    }

    /** Getter and setters */
    public ArrayList<Choice> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<Choice> choices) {
        this.choices = choices;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
