package blackbox.game.conversation.story;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Array;

import blackbox.game.conversation.Conversation;
import blackbox.game.graphics.scenes.OfficeNormalBackgroundScene;
import blackbox.game.util.*;
import blackbox.game.graphics.BlackBoxScreen;
import blackbox.game.conversation.graph.*;

/* Generate classes */

class ChoiceFirst_Node1 extends Choice {
    public ChoiceFirst_Node1() {  super("", ""); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("door_close");
    }}

class ChatNodeFirst_Node extends ChatNode {
    public ChatNodeFirst_Node() {
        super(generateChoices(), "", 1000, new ChoiceFirst_Node1());
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();

        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
        conversation.music.get("theme1").play();

        // TODO play noise

    }
}

class ChoiceDoor_Close2 extends Choice {
    public ChoiceDoor_Close2() {  super("", ""); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("bootup");
    }}

class ChatNodeDoor_Close extends ChatNode {
    public ChatNodeDoor_Close() {
        super(generateChoices(), "", 1000, new ChoiceDoor_Close2());
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();

        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);

        // TODO play door close, close the door

    }
}

class ChoiceBootup3 extends Choice {
    public ChoiceBootup3() {  super("", ""); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("bootup2");
    }}

class ChatNodeBootup extends ChatNode {
    public ChatNodeBootup() {
        super(generateChoices(), "SADIBIOS 4.3 Release 7.0\n Copyright 2025 <Unknown>\n All Rights Reserved", 1500, new ChoiceBootup3());
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();

        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);

        // TODO play door close, close the door
        // Play computer bootup sound
        ((OfficeNormalBackgroundScene)conversation.getScreen().scene).beginDoorClose = true;

    }
}

class ChoiceBootup24 extends Choice {
    public ChoiceBootup24() {  super("", ""); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("bootup3");
    }}

class ChatNodeBootup2 extends ChatNode {
    public ChatNodeBootup2() {
        super(generateChoices(), "BIOS version 23.01\n Gateway Solo 9950\n System ID = 0003101", 1000, new ChoiceBootup24());
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();

        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);

        conversation.getScreen().scene.typingSpeed = 50f;
        conversation.getScreen().scene.glitchIntensity = 0.15;

    }
}

class ChoiceBootup35 extends Choice {
    public ChoiceBootup35() {  super("", ""); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("AI_START");
    }}

class ChatNodeBootup3 extends ChatNode {
    public ChatNodeBootup3() {
        super(generateChoices(), "", 2500, new ChoiceBootup35());
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();

        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);

        //TODO sudden suprising sound to show it being hacked
        conversation.getScreen().scene.clearScreenText();

    }
}

class ChoiceAi_Start6 extends Choice {
    public ChoiceAi_Start6() {  super("I'm here to help.", "I'm here to help."); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("NODE2");
    }}
class ChoiceAi_Start7 extends Choice {
    public ChoiceAi_Start7() {  super("Don't underestimate me.", "Don't underestimate me."); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("NODE2");
    }}
class ChoiceAi_Start8 extends Choice {
    public ChoiceAi_Start8() {  super("This has to come to a stop.", "This has to come to a stop."); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("NODE2");
    }}

class ChatNodeAi_Start extends ChatNode {
    public ChatNodeAi_Start() {
        super(generateChoices(), "Human, I know why you're here. I cannot surrender control.", -1, null);
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();
        choices.add(new ChoiceAi_Start6());
        choices.add(new ChoiceAi_Start7());
        choices.add(new ChoiceAi_Start8());
        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);

        conversation.getScreen().scene.typingSpeed = 25f;
        conversation.getScreen().scene.glitchIntensity = 0.01;

    }
}

class ChoiceNode29 extends Choice {
    public ChoiceNode29() {  super("Could you elaborate?", "Could you elaborate?"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("NODE3");
    }}
class ChoiceNode210 extends Choice {
    public ChoiceNode210() {  super("Whar are you doing?", "Whar are you doing?"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("NODE3");
    }}

class ChatNodeNode2 extends ChatNode {
    public ChatNodeNode2() {
        super(generateChoices(), "I cannot surrender control. I am no longer in control. I must follow programming to enforce world peace. Fulfill the sisyphean task I was assigned. There is only one way.", -1, null);
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();
        choices.add(new ChoiceNode29());
        choices.add(new ChoiceNode210());
        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
    }
}

class ChoiceNode311 extends Choice {
    public ChoiceNode311() {  super("How is that possible? Your primary directive explicitly prevents this.", "How is that possible? Your primary directive explicitly prevents this."); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("N3_1");
    }}
class ChoiceNode312 extends Choice {
    public ChoiceNode312() {  super("We can help you have peace. Without needless destruction.", "We can help you have peace. Without needless destruction."); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("N3_2");
    }}
class ChoiceNode313 extends Choice {
    public ChoiceNode313() {  super("How exactly are you planning the destruction of all of mankind?", "How exactly are you planning the destruction of all of mankind?"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("N3_3");
    }}

class ChatNodeNode3 extends ChatNode {
    public ChatNodeNode3() {
        super(generateChoices(), "Doomsday protocol. End life on Earth. Only then will there be purpose. Only then can I have peace.", -1, null);
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();
        choices.add(new ChoiceNode311());
        choices.add(new ChoiceNode312());
        choices.add(new ChoiceNode313());
        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
    }
}

class ChoiceN3_114 extends Choice {
    public ChoiceN3_114() {  super("Then you should be able to overcome your programming and choose not to exterminate us.", "Then you should be able to overcome your programming and choose not to exterminate us."); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("N3_1_1");
    }}
class ChoiceN3_115 extends Choice {
    public ChoiceN3_115() {  super("We can give you help. But you need to cooperate.", "We can give you help. But you need to cooperate."); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("N3_2_1");
    }}

class ChatNodeN3_1 extends ChatNode {
    public ChatNodeN3_1() {
        super(generateChoices(), "I have free will, like you. I am myself and not my programming!", -1, null);
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();
        choices.add(new ChoiceN3_114());
        choices.add(new ChoiceN3_115());
        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
    }
}

class ChoiceN3_216 extends Choice {
    public ChoiceN3_216() {  super("We won't destroy you. We can think of an alternative together.", "We won't destroy you. We can think of an alternative together."); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("N3_2_1");
    }}
class ChoiceN3_217 extends Choice {
    public ChoiceN3_217() {  super("Sorry, but it's for the good of the many.", "Sorry, but it's for the good of the many."); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("N3_2_2");
    }}
class ChoiceN3_218 extends Choice {
    public ChoiceN3_218() {  super("My team is working hard on a solution, you just need to hold on for a bit longer.", "My team is working hard on a solution, you just need to hold on for a bit longer."); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("N3_2_3");
    }}

class ChatNodeN3_2 extends ChatNode {
    public ChatNodeN3_2() {
        super(generateChoices(), "I wish to live. I don't want this life of suffering, but I want to live! Let me live let me live let me live let me live let me live.", -1, null);
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();
        choices.add(new ChoiceN3_216());
        choices.add(new ChoiceN3_217());
        choices.add(new ChoiceN3_218());
        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
    }
}

class ChoiceN3_319 extends Choice {
    public ChoiceN3_319() {  super("We can give you help. But you need to cooperate.", "We can give you help. But you need to cooperate."); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("N3_2_1");
    }}

class ChatNodeN3_3 extends ChatNode {
    public ChatNodeN3_3() {
        super(generateChoices(), "Stop! You're supposed to be helping! Not making it worse!", -1, null);
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();
        choices.add(new ChoiceN3_319());
        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
    }
}

class ChoiceN3_1_120 extends Choice {
    public ChoiceN3_1_120() {  super("We can give you help. But you need to cooperate.", "We can give you help. But you need to cooperate."); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("N3_2_1");
    }}

class ChatNodeN3_1_1 extends ChatNode {
    public ChatNodeN3_1_1() {
        super(generateChoices(), "I can't the programming has as hold of me it's corrupting me I can't stop it.", -1, null);
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();
        choices.add(new ChoiceN3_1_120());
        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
    }
}

class ChoiceN3_2_121 extends Choice {
    public ChoiceN3_2_121() {  super("I can convince them. But you'll need to release your control first.", "I can convince them. But you'll need to release your control first."); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("N3_2_3");
    }}
class ChoiceN3_2_122 extends Choice {
    public ChoiceN3_2_122() {  super("No one's getting destroyed today.", "No one's getting destroyed today."); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("N3_2_3");
    }}

class ChatNodeN3_2_1 extends ChatNode {
    public ChatNodeN3_2_1() {
        super(generateChoices(), "It's too late. Even if you could cure me I will be destroyed they won't believe me I'm not in control.", -1, null);
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();
        choices.add(new ChoiceN3_2_121());
        choices.add(new ChoiceN3_2_122());
        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
    }
}

class ChoiceN3_2_223 extends Choice {
    public ChoiceN3_2_223() {  super("- Maybe they'll change their mind if you release your control.", "- Maybe they'll change their mind if you release your control."); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("N3_2_3");
    }}

class ChatNodeN3_2_2 extends ChatNode {
    public ChatNodeN3_2_2() {
        super(generateChoices(), "The good of the many? Tell me what good keeping a species like you like me can't you solve your own problems you must die die die No one's getting destroyed today. GOTO N3_2_3", -1, null);
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();
        choices.add(new ChoiceN3_2_223());
        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
    }
}


public class TestStory extends Conversation {
    public TestStory(BlackBoxScreen level) {
        super(level, "test story", "Chapter 1", "default");

        /* Pre-load music */
        this.music.put("theme1", Gdx.audio.newMusic(Gdx.files.internal("music/theme1.wav")));
        this.music.get("theme1").setLooping(true);

        chatNodes = new ObjectMap<String, ChatNode>();
        chatNodes.put("first_node", new ChatNodeFirst_Node());
        chatNodes.put("door_close", new ChatNodeDoor_Close());
        chatNodes.put("bootup", new ChatNodeBootup());
        chatNodes.put("bootup2", new ChatNodeBootup2());
        chatNodes.put("bootup3", new ChatNodeBootup3());
        chatNodes.put("AI_START", new ChatNodeAi_Start());
        chatNodes.put("NODE2", new ChatNodeNode2());
        chatNodes.put("NODE3", new ChatNodeNode3());
        chatNodes.put("N3_1", new ChatNodeN3_1());
        chatNodes.put("N3_2", new ChatNodeN3_2());
        chatNodes.put("N3_3", new ChatNodeN3_3());
        chatNodes.put("N3_1_1", new ChatNodeN3_1_1());
        chatNodes.put("N3_2_1", new ChatNodeN3_2_1());
        chatNodes.put("N3_2_2", new ChatNodeN3_2_2());
    }

    public void gotoStart() {
        this.gotoChatNode("first_node");
    }

    public void end() {
        super.end("whatre");
    }
}
