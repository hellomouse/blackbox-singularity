package blackbox.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeType;
import com.badlogic.gdx.utils.ObjectMap;

import blackbox.game.scenes.*;

public class BlackboxGame extends Game {
    /**
     * These are shared resources between all Screens.
     * DO NOT recreate them if you can use the one
     * defined in this class instead
     */
    private static final String[] fontSizeNames = {"title2", "title1", "normal", "small"};

    public ObjectMap<String, BitmapFont> ocrNormalFont;
    public ObjectMap<String, BitmapFont> robotoLightFont;

    @Override
    public void create() {
        /* Create fonts */
        //ocrNormalFont = generateFont("fonts/ocr_normal.fnt");
        robotoLightFont = generateFont("fonts/roboto_light");

        this.setScreen(new MainMenuScreen(this));
    }

    /**
     * Populates the ObjectMap with fonts of different sizes.
     * The sizes can be accessed by the following keys:
     * - "small", "normal", "title1", "title2"
     *
     * The title2 should have font size 64pt
     * The title1 should have font size 32pt
     * The normal should have font size 20pt
     * The small  should have font size 14pt
     *
     * @param file The base path to the file, ie "fonts/roboto_light"
     * @return     The generated ObjectMap
     */
    private ObjectMap<String, BitmapFont> generateFont(String file) {
        ObjectMap<String, BitmapFont> map = new ObjectMap<String, BitmapFont>();
        for (int i = 0; i < fontSizeNames.length; i++) {
            BitmapFont temp = new BitmapFont(Gdx.files.internal(file + "_" + fontSizeNames[i] + ".fnt"));
            map.put(fontSizeNames[i], temp);
        }
        return map;
    }


	@Override
	public void render () {
        super.render();
	}
	
	@Override
	public void dispose () {

	}
}
