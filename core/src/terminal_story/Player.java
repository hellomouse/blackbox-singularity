package terminal_story;

import blackbox.game.conversation.graph.Choice;
import blackbox.game.conversation.story.TestStory;
import blackbox.game.conversation.Conversation;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Terminal story player. You can go through the choices of your story
 * as normal, however it will throw an error if you try to use anything
 * from Conversation (As passed object is null)
 *
 * This is VERY simple, don't expect it to work for any story that
 * requires graphics or other fancy IO.
 *
 * @author Bowserinator
 */
public class Player {
    public static void main (String[] args) {
        Scanner scanner = new Scanner(System.in);
        Conversation story = new TestStory(null);
        story.gotoStart();

        while (true) {
            System.out.println(story.currentChatNode.getText());

            for (Choice choice : story.currentChatNode.getChoices())
                System.out.println("- " + choice.label);

            if (story.currentChatNode.getChoices().size == 0) {
                System.out.println("Current node has no choices, quitting...");
                break;
            }

            System.out.print("> ");

            try {
                int choice = scanner.nextInt();
                story.currentChatNode.getChoices().get(choice).onSelect(story);
            } catch(InputMismatchException e) {
                System.out.println("Invalid number, type number from 0 to " + story.currentChatNode.getChoices().size);
            } catch(IndexOutOfBoundsException e) {
                System.out.println("Invalid number, type number from 0 to " + story.currentChatNode.getChoices().size);
            }
        }
    }
}
