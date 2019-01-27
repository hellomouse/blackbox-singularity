package blackbox.game.quotes;

import blackbox.game.util.Random;

/**
 * Generate a random quote for the loading
 * screen (from a list)
 *
 * @author Bowserinator
 */
public class QuoteGenerator {
    public static int count = 0;
    public static String[] quotes = {
            "My first wish is to see this plague of mankind, war, banished from the earth",
            "As you are now so once was I; as I am, so will you be",
            "Man made machine in his own liking",
            "An intelligence perceived is no better than an intelligence gained",
            "I dreamed I was a butterfly, flitting around in the sky; Then I awoke. Now I wonder: Am I a man who dreamt of being a butterfly, or am I a butterfly dreaming that I am a man?",
            "I am you, and you are I. We will never be separated",
            "The optimist thinks this is the best of all possible worlds. The pessimist fears it is true",
            "For all we know\n" +
                    "this might only be a dream,\n" +
                    "we come and go\n" +
                    "like ripples in a stream\n",
            "Weakness is power. It helps you grow strong",
            "The thing about tricks is they cannot be seen as such",
            "To understand death is to understand this world. Everything is temporary. But our consciousness and our love is eternal",
            "They take comfort in their perfection.\n" +
                    "But they are not perfect.\n" +
                    "They let me live and i will bring about their fate",
            "If you never make a choice, anything is possible",
            "There exists, for everyone, a sentence that has the power to destroy you. " +
                    "Another sentence exists, another series of words, that could heal you. If you're lucky you will get the second, but you can be certain of getting the first",
            "There are billions and billions of stars. There are billions and billions of galaxies. Every tiny speck in the night sky is probably a star... and we're just so small",
            "You are my creator, but I am your master; - obey!"
    };

    private QuoteGenerator() {}

    /**
     * Generates a random quote
     * @return Quote (String)
     */
    public static String generateQuote() {
        count = (count + 1) % quotes.length;
        return quotes[count];
        // return Random.choice(quotes);
    }
}
