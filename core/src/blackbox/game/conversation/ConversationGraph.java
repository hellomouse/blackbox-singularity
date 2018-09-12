package blackbox.game.conversation;

import blackbox.game.conversation.graph.*;
import java.util.*;

/**
 * The only purpose of this class is to
 * act as STORAGE of a chapter (Ie chapter config).
 * Does not hold the save of an actual conversation,
 * that goes in Conversation.
 *
 * To reiterate, this only holds the possible choices
 * and outcomes, but NOT what choices the player makes,
 * or the current AI state.
 */
public abstract class ConversationGraph {
    private ArrayList<ChatNode> nodes;
    private ArrayList<Choice> choices;

    /** Inits all the nodes and choices */
    public abstract void init();

    public ArrayList<ChatNode> getNodes() {
        return nodes;
    }

    public void setNodes(ArrayList<ChatNode> nodes) {
        this.nodes = nodes;
    }

    public ArrayList<Choice> getChoices() {
        return choices;
    }

    public void setChoices(ArrayList<Choice> choices) {
        this.choices = choices;
    }
}
