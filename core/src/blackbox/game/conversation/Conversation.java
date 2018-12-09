package blackbox.game.conversation;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.ObjectMap;
import blackbox.game.conversation.graph.*;


public abstract class Conversation {
    /* Data and level it belongs to */
    public AiChatData data;
    public Screen level;

    /* Misc variables */
    private ChatNode currentChatNode;
    private String title;
    private String subtitle;
    private String backgroundId;

    public ObjectMap<String, Object> variables;
    public ObjectMap<String, Sound> sounds;
    public ObjectMap<String, Music> music;

    /* The chatNode map */
    private ObjectMap<String, ChatNode> chatNodes;

    public Conversation(Screen level, String title, String subtitle, String backgroundId) {
        this.level = level;
        this.title = title;
        this.subtitle = subtitle;
        this.backgroundId = backgroundId;

        this.variables = new ObjectMap<String, Object>();
        this.sounds = new ObjectMap<String, Sound>();
        this.music = new ObjectMap<String, Music>();
    }

    /**
     * Define which chat node to go to
     * when the scene is loaded. Use gotoChatNode
     */
    public abstract void gotoStart();

    /**
     * Sets the next scene ID to go to after
     * the scene ends.
     * @param nextSceneId Id of next scene
     */
    public void end(String nextSceneId) {
        // TODO implement
    }

    /***
     * Loads a custom program to display onto
     * the screen
     * @param id ID of program
     */
    public void loadProgramToScreen(String id) {
        // TODO
    }

    /**
     * Sets new chat node ID to display
     * @param id Id of chat node
     */
    public void gotoChatNode(String id) {
        currentChatNode = chatNodes.get(id);
        currentChatNode.onLoad(this);
    }
}
