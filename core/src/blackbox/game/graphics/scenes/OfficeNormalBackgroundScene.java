package blackbox.game.graphics.scenes;

import blackbox.game.graphics.BackgroundScene;

/**
 * The Background for the generic office where
 * you converse with the AI
 */
public class OfficeNormalBackgroundScene extends BackgroundScene {
    private static final int SCREEN_PADDING = 20;
    private static final int[] SCREEN_RECT = {636 + SCREEN_PADDING, 687 - SCREEN_PADDING,
            671 - SCREEN_PADDING * 2, 416 - SCREEN_PADDING * 2};

    /**
     * Construct a new OfficeNormalBackgroundScene
     */
    public OfficeNormalBackgroundScene() {
        super(SCREEN_RECT , 0.05f, "OfficeNormal", "background/title-temp.png");
    }
}
