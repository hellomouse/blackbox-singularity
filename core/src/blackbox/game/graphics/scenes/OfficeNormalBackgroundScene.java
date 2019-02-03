package blackbox.game.graphics.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import blackbox.game.BlackboxGame;
import blackbox.game.graphics.BackgroundScene;
import blackbox.game.util.MathUtil;
import blackbox.game.util.MonospaceFontData;

/**
 * The Background for the generic office where
 * you converse with the AI
 */
public class OfficeNormalBackgroundScene extends BackgroundScene {
    private static final int SCREEN_PADDING = 20;
    private static final int bgImgWidth = 3201;
    private static final int bgImgHeight = 1500;
    private static final int[] SCREEN_RECT = {820 + SCREEN_PADDING, 697 - SCREEN_PADDING,
            670 - SCREEN_PADDING * 2, 236 - SCREEN_PADDING * 2};

    /*
     * Additional variables for background door closing
     * animation (light going through a door)
     */
    private Texture backgroundLight;
    public int doorOverlayWidth = bgImgWidth;
    public boolean beginDoorClose = false;
    public int doorCloseSpeed = 14;

    /**
     * Construct a new OfficeNormalBackgroundScene
     * @param game BlackboxGame object
     */
    public OfficeNormalBackgroundScene(BlackboxGame game) {
        super(SCREEN_RECT , 0.05f, "OfficeNormal", "background/panorama-test.jpg",
                new MonospaceFontData(game.monoNormalFont.get("normal")), bgImgWidth, bgImgHeight);
        Texture img = new Texture("background/ai-room-door-open.jpg");
        this.backgroundLight = img;
    }

    @Override
    public void render(float delta, SpriteBatch batch, BlackboxGame game) {
        this.renderBackground(delta, batch, game);

        /* Render the door light background */
        batch.draw(this.backgroundLight, this.scroll, 0,
                MathUtil.ratioH(doorOverlayWidth, imageHeight),
                Gdx.graphics.getHeight(),
                0, 0, doorOverlayWidth, bgImgHeight, false, false);
        if (beginDoorClose) {
            doorOverlayWidth -= doorCloseSpeed;
            if (doorOverlayWidth < 0) doorOverlayWidth = 0;
        }

        this.renderText(delta, batch, game);
    }
}
