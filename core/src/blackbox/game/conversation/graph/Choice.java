package blackbox.game.conversation.graph;

import blackbox.game.conversation.Conversation;

/**
 * Choice class, represents a choice you can go
 * to; contains code that is run when the choice
 * is selected. This class is abstract; you should
 * extend this for EVERY choice you create.
 *
 * @author Bowserinator
 */
public abstract class Choice {
    /**
     * label        - Label for the choice
     * displayText  - Text to display if not equal to label
     * enabled      - If false choice won't be allowed
     */
    public String label;
    public String displayText;
    public boolean enabled;

    /**
     * Construct a Choice
     * @param label       Label for the choice
     * @param displayText Text to display when choice is made,
     *                    if different from label (ie, label = "[Slap keyboard]",
     *                    displayText = "The player slaps the keyboard")
     */
    public Choice(String label, String displayText) {
        this.label = label;
        this.displayText = displayText;
    }

    /**
     * Execute this when the choice is selected. You can modify
     * AI data here. This method should modify the current chat
     * node in the conversation
     *
     * Also this method should switch the current chatnode. You
     * need to override this method.
     *
     * @param conversation The conversation the choice belongs to
     */
    public abstract void onSelect(Conversation conversation);
}