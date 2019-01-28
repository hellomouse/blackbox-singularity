package blackbox.game.conversation.story;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.Array;

import blackbox.game.conversation.Conversation;
import blackbox.game.util.*;
import blackbox.game.conversation.graph.*;

/* Generate classes */

class ChoiceFirst_Node1 extends Choice {
    public ChoiceFirst_Node1() {  super("Choice A", "Choice A"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("B");
    }}
class ChoiceFirst_Node2 extends Choice {
    public ChoiceFirst_Node2() {  super("Choice B", "Choice B"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("B");
    }}
class ChoiceFirst_Node3 extends Choice {
    public ChoiceFirst_Node3() {  super("Choice C", "Choice C"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("B");
    }}

class ChatNodeFirst_Node extends ChatNode {
    public ChatNodeFirst_Node() {
        super(generateChoices(), "This is some test text\n that should be displayed", -1, null);
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();
        choices.add(new ChoiceFirst_Node1());
        choices.add(new ChoiceFirst_Node2());
        choices.add(new ChoiceFirst_Node3());
        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
    }
}

class ChoiceB4 extends Choice {
    public ChoiceB4() {  super("Choice A", "Choice A"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("C");
    }}
class ChoiceB5 extends Choice {
    public ChoiceB5() {  super("Choice B", "Choice B"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("C");
    }}

class ChatNodeB extends ChatNode {
    public ChatNodeB() {
        super(generateChoices(), "This is a new line of text\n another line of text!\nVery long line of text maybe this will wrap it should really wrap around", -1, null);
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();
        choices.add(new ChoiceB4());
        choices.add(new ChoiceB5());
        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
    }
}

class ChoiceC6 extends Choice {
    public ChoiceC6() {  super("Choice A", "Choice A"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("coffee");
    }}
class ChoiceC7 extends Choice {
    public ChoiceC7() {  super("Choice B", "Choice B"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("coffee");
    }}
class ChoiceC8 extends Choice {
    public ChoiceC8() {  super("Choice C", "Choice C"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("coffee");
    }}
class ChoiceC9 extends Choice {
    public ChoiceC9() {  super("Choice D", "CHOICE D"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("coffee");
    }}

class ChatNodeC extends ChatNode {
    public ChatNodeC() {
        super(generateChoices(), "Idk what to put here!", -1, null);
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();
        choices.add(new ChoiceC6());
        choices.add(new ChoiceC7());
        choices.add(new ChoiceC8());
        choices.add(new ChoiceC9());
        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
    }
}

class ChoiceCoffee10 extends Choice {
    public ChoiceCoffee10() {  super("Go back to start", "Go back to start"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("first_node");
    }}
class ChoiceCoffee11 extends Choice {
    public ChoiceCoffee11() {  super("End it now!", "End it now!"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("end");
    }}

class ChatNodeCoffee extends ChatNode {
    public ChatNodeCoffee() {
        super(generateChoices(), "Whatever you can end now", -1, null);
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();
        choices.add(new ChoiceCoffee10());
        choices.add(new ChoiceCoffee11());
        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
    }
}

class ChoiceEnd12 extends Choice {
    public ChoiceEnd12() {  super("", ""); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("ending_one");
    }}

class ChatNodeEnd extends ChatNode {
    public ChatNodeEnd() {
        super(generateChoices(), "This is the correct ending.", 2000, new ChoiceEnd12());
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();

        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
    }
}


public class TestStory extends Conversation {
    public TestStory(Screen level) {
        super(level, "TestStoryA", "By Bowserinator", "default");


        chatNodes = new ObjectMap<String, ChatNode>();
        chatNodes.put("first_node", new ChatNodeFirst_Node());
        chatNodes.put("B", new ChatNodeB());
        chatNodes.put("C", new ChatNodeC());
        chatNodes.put("coffee", new ChatNodeCoffee());
        chatNodes.put("end", new ChatNodeEnd());
    }

    public void gotoStart() {
        this.gotoChatNode("first_node");
    }

    public void end() {
        super.end("idk");
    }
}