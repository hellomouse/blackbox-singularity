package blackbox.game.graphics.scenes;

import blackbox.game.BlackboxGame;
import blackbox.game.graphics.BackgroundScene;
import blackbox.game.util.MonospaceFontData;

/**
 * The Background for the generic office where
 * you converse with the AI
 */
public class OfficeNormalBackgroundScene extends BackgroundScene {
    private static final int SCREEN_PADDING = 20;
    private static final int[] SCREEN_RECT = {1850 + SCREEN_PADDING, 767 - SCREEN_PADDING,
            861 - SCREEN_PADDING * 2, 396 - SCREEN_PADDING * 2};

    /**
     * Construct a new OfficeNormalBackgroundScene
     * @param game BlackboxGame object
     */
    public OfficeNormalBackgroundScene(BlackboxGame game) {
        super(SCREEN_RECT , 0.05f, "OfficeNormal", "background/panorama-test.jpg",
                new MonospaceFontData(game.monoNormalFont.get("title1")));
    }
}
