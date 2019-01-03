package blackbox.game.conversation.graph;

import com.badlogic.gdx.utils.Array;
import blackbox.game.conversation.Conversation;

/**
 * A node in the conversation where the player
 * can select between multiple choices given
 * information that is displayed on the screen.
 *
 * @author Bowserinator
 */
public abstract class ChatNode {
    /**
     * Main variables
     * choices - Array of Choice object player chooses from
     * text    - Text to display on top of choices
     */
    private Array<Choice> choices;
    private String text;

    /**
     * Additional variables
     *  startTime   - System time the node was loaded in, used for timeout
     *                Uses result of System.currentTimeMillis()
     *  timeout     - Max time before timeoutChoice is defaulted to, set -1
     *                for no timeout
     *  timeoutChoice - Choice object to select if decision is timed out
     */
    private long startTime;
    private int timeout;
    private Choice timeoutChoice;

    /**
     * Construct a new ChatNode
     * @param choices       List of choices
     * @param text          Text to display (What the AI says)
     * @param timeout       Timeout of the choice (-1 = no timeout)
     * @param timeoutChoice The choice executed when timed out
     */
    public ChatNode(Array<Choice> choices, String text, int timeout, Choice timeoutChoice) {
        this.choices = choices;
        this.text = text;
        this.timeout = timeout;
        this.timeoutChoice = timeoutChoice;
    }

    /**
     * Code that is executed when the choice is
     * loaded (When the player goes to the choice)
     *
     * Override this method when you extend ChatNode,
     * but leave the super call in (super.onLoad(conversation))
     *
     * @param conversation The conversation the choice is in
     */
    public void onLoad(Conversation conversation) {
        this.startTime = System.currentTimeMillis();
    }

    /**
     * Returns the choice array (read only)
     * @return Array of choices
     */
    public Array<Choice> getChoices() {
        return choices;
    }

    /**
     * Returns the text of the node (read only)
     * @return Node text
     */
    public String getText() {
        return text;
    }

    /**
     * Returns the timeout value of the node
     * @return Timeout value
     */
    public int getTimeout() {
        return timeout;
    }

    /**
     * Returns the timeout choice of the node
     * @return Timeout choice
     */
    public Choice getTimeoutChoice() {
        return timeoutChoice;
    }
}
