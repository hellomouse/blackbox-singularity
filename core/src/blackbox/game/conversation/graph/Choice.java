package blackbox.game.conversation.graph;

import blackbox.game.conversation.Conversation;

public abstract class Choice {
    /**
     * label: Label for the choice
     * displayText: Text to display if not equal to label
     * enabled: If false choice won't be allowed
     */
    private String label;
    private String displayText;

    private boolean enabled;

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
     * Also this method should switch the current chatnode
     *
     * @param conversation The conversation the choice belongs to
     */
    public abstract void onSelect(Conversation conversation);

    /** Getter and setter */
    public String getLabel() {
        return label;
    }
    public void setLabel(String label) {
        this.label = label;
    }
    public boolean isEnabled() {
        return enabled;
    }
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    public String getDisplayText() {
        return displayText;
    }
    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }
}
