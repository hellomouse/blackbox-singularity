package blackbox.game.conversation.graph;

import java.util.*;

/**
 * Stores data the AI "feels" about
 * the Player and the previous
 * conversations. Changes depending
 * on choices made.
 */
public class AiChatData {
    /**
     * Data the AI records about
     * the player during conversations
     *
     * All instance variables here are

     * a real value, where 0 is neutral.
     * Each "change" in a conversation
     * should not increment any variable
     * by more than 0.2
     */
    private HashMap<String, Double> playerData;

    /** Data the AI has (opinions) on things */
    private HashMap<String, Double> aiData;

    /**
     * Construct an AiChatData.
     * Takes no parameters.
     */
    public AiChatData() {
        this.playerData = new HashMap<String, Double>();
        this.playerData.put("empathy", 0.0);
        this.playerData.put("intelligence", 0.0);
        this.playerData.put("openness", 0.0);

        this.aiData = new HashMap<String, Double>();
        this.aiData.put("trust", 0.0);
        this.aiData.put("morality", 0.0);
        this.aiData.put("faith", 0.0);
    }

    /** Getters and setters */
    public void setPlayerProperty(String name, Double value) {
        this.playerData.put(name, value);
    }

    public void setAiProperty(String name, Double value) {
        this.aiData.put(name, value);
    }

    public HashMap<String, Double> getPlayerProperty() {
        return this.playerData;
    }

    public HashMap<String, Double> getAiProperty() {
        return this.aiData;
    }
}
