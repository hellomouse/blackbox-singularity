package blackbox.game.conversation.tree;

public class Choice {
    /**
     * label: Label for the choice
     * displayText: Text to display if not equal to label
     * enabled: If false choice won't be allowed
     */
    private String label;
    private String displayText;

    private boolean enabled;
    private AiChatData data;

    /**
     * Construct a ChatNode
     * @param data        The data the AI player has
     * @param label       Label for the choice
     * @param displayText Text to display when choice is made,
     *                    if different from label
     */
    public Choice(AiChatData data, String label, String displayText) {
        this.data = data;
        this.label = label;
        this.displayText = displayText;
    }

    /**
     * Construct a ChatNode
     * @param data    The data the AI player has
     * @param label   Label for the choice
     */
    public Choice(AiChatData data, String label) {
        this(data, label, label);
    }

    /**
     * Update any variables in the AiChatData
     * if this choice was selected. Use
     * this.data and its methods to do so.
     */
    public void updateAiChatData() {
        /* Default: Do nothing */
    }

    /**
     * Return the chat node to switch to when
     * the choice is made.
     * @return ChatNode to switch to
     */
    public ChatNode getNext() {
        /* Default: return null */
        return null;
    }

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
