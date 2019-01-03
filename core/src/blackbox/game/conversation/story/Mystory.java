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
    public ChoiceFirst_Node1() {  super("Check your phone", "Check your phone"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("phone");
    }}
class ChoiceFirst_Node2 extends Choice {
    public ChoiceFirst_Node2() {  super("Read newspaper", "Read newspaper"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("newspaper");
    }}
class ChoiceFirst_Node3 extends Choice {
    public ChoiceFirst_Node3() {  super("Drink coffee", "Drink coffee"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("coffee");
    }}

class ChatNodeFirst_Node extends ChatNode {
    public ChatNodeFirst_Node() {
        super(generateChoices(), "You sit in a coffee shop. In front of you is a phone, newspaper and a cup of coffee. What do you do?", -1, null);
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

class ChoicePhone4 extends Choice {
    public ChoicePhone4() {  super("Check social media", "Check social media"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("social_media");
    }}
class ChoicePhone5 extends Choice {
    public ChoicePhone5() {  super("Look at memes", "Look at memes"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("memes");
    }}
class ChoicePhone6 extends Choice {
    public ChoicePhone6() {  super("Read a book", "Read a book"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("book");
    }}

class ChatNodePhone extends ChatNode {
    public ChatNodePhone() {
        super(generateChoices(), "You check your phone.", -1, null);
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();
        choices.add(new ChoicePhone4());
        choices.add(new ChoicePhone5());
        choices.add(new ChoicePhone6());
        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
    }
}

class ChoiceSocial_Media7 extends Choice {
    public ChoiceSocial_Media7() {  super("[Continue]", "[Continue]"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("end");
    }}

class ChatNodeSocial_Media extends ChatNode {
    public ChatNodeSocial_Media() {
        super(generateChoices(), "You browse social media. Where did the time go?", -1, null);
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();
        choices.add(new ChoiceSocial_Media7());
        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
    }
}

class ChoiceMemes8 extends Choice {
    public ChoiceMemes8() {  super("[Continue]", "[Continue]"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("end");
    }}

class ChatNodeMemes extends ChatNode {
    public ChatNodeMemes() {
        super(generateChoices(), "You look at some memes. Your happiness increases!", -1, null);
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();
        choices.add(new ChoiceMemes8());
        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
    }
}

class ChoiceBook9 extends Choice {
    public ChoiceBook9() {  super("[Continue]", "[Continue]"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("end");
    }}

class ChatNodeBook extends ChatNode {
    public ChatNodeBook() {
        super(generateChoices(), "You read an exciting book.", -1, null);
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();
        choices.add(new ChoiceBook9());
        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
    }
}

class ChoiceNewspaper10 extends Choice {
    public ChoiceNewspaper10() {  super("Read science news", "Read science news"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("science_news");
    }}
class ChoiceNewspaper11 extends Choice {
    public ChoiceNewspaper11() {  super("Read social news", "Read social news"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("social_news");
    }}
class ChoiceNewspaper12 extends Choice {
    public ChoiceNewspaper12() {  super("Read business news", "Read business news"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("business_news");
    }}

class ChatNodeNewspaper extends ChatNode {
    public ChatNodeNewspaper() {
        super(generateChoices(), "You open up the newspaper", -1, null);
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();
        choices.add(new ChoiceNewspaper10());
        choices.add(new ChoiceNewspaper11());
        choices.add(new ChoiceNewspaper12());
        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
    }
}

class ChoiceScience_News13 extends Choice {
    public ChoiceScience_News13() {  super("[Continue]", "[Continue]"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("end");
    }}

class ChatNodeScience_News extends ChatNode {
    public ChatNodeScience_News() {
        super(generateChoices(), "Scientists just discovered <insert cool thing here>!", -1, null);
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();
        choices.add(new ChoiceScience_News13());
        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
    }
}

class ChoiceSocial_News14 extends Choice {
    public ChoiceSocial_News14() {  super("[Continue]", "[Continue]"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("end");
    }}

class ChatNodeSocial_News extends ChatNode {
    public ChatNodeSocial_News() {
        super(generateChoices(), "<Celebrity> does something", -1, null);
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();
        choices.add(new ChoiceSocial_News14());
        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
    }
}

class ChoiceBusiness_News15 extends Choice {
    public ChoiceBusiness_News15() {  super("[Continue]", "[Continue]"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("end");
    }}

class ChatNodeBusiness_News extends ChatNode {
    public ChatNodeBusiness_News() {
        super(generateChoices(), "Business is good!", -1, null);
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();
        choices.add(new ChoiceBusiness_News15());
        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
    }
}

class ChoiceCoffee16 extends Choice {
    public ChoiceCoffee16() {  super("[Continue]", "[Continue]"); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("end");
    }}

class ChatNodeCoffee extends ChatNode {
    public ChatNodeCoffee() {
        super(generateChoices(), "You drink your coffee! You're more awake now!", -1, null);
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();
        choices.add(new ChoiceCoffee16());
        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
    }
}

class ChoiceEnd17 extends Choice {
    public ChoiceEnd17() {  super("", ""); }
    public void onSelect(Conversation conversation) {
        conversation.gotoChatNode("ending_one");
    }}

class ChatNodeEnd extends ChatNode {
    public ChatNodeEnd() {
        super(generateChoices(), "Satisfied with your stay, you go home.", 2000, new ChoiceEnd17());
    }

    private static Array<Choice> generateChoices() {
        Array<Choice> choices = new Array<Choice>();

        return choices;
    }

    public void onLoad(Conversation conversation) {
        super.onLoad(conversation);
    }
}


public class Mystory extends Conversation {
    public Mystory(Screen level) {
        super(level, "MyStory", "By Bowserinator", "default");


        chatNodes = new ObjectMap<String, ChatNode>();
        chatNodes.put("first_node", new ChatNodeFirst_Node());
        chatNodes.put("phone", new ChatNodePhone());
        chatNodes.put("social_media", new ChatNodeSocial_Media());
        chatNodes.put("memes", new ChatNodeMemes());
        chatNodes.put("book", new ChatNodeBook());
        chatNodes.put("newspaper", new ChatNodeNewspaper());
        chatNodes.put("science_news", new ChatNodeScience_News());
        chatNodes.put("social_news", new ChatNodeSocial_News());
        chatNodes.put("business_news", new ChatNodeBusiness_News());
        chatNodes.put("coffee", new ChatNodeCoffee());
        chatNodes.put("end", new ChatNodeEnd());
    }

    public void gotoStart() {
        this.gotoChatNode("first_node");
    }

    public void end() {
        super.end("temp");
    }
}