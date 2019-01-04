package blackbox.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.ObjectMap;

import contribs.utils.*;
import contribs.postprocessing.*;
import contribs.postprocessing.effects.*;

import blackbox.game.scenes.*;

public class BlackboxGame extends Game {
    /**
     * These are shared resources between all Screens.
     * DO NOT recreate them if you can use the one
     * defined in this class instead
     *
     * TODO pregenerate fonts, but also have method
     * to dynamically create font
     */
    public static final String[] fontSizeNames = {"title3", "title2", "title1", "normal", "small"};
    public static final int[] fontSizes = {70, 44, 38, 24, 18};

    public ObjectMap<String, BitmapFont> monoNormalFont;
    public ObjectMap<String, BitmapFont> robotoLightFont;

    public TextButton.TextButtonStyle textButtonStyle1;

    @Override
    public void create() {
        /* Create fonts */
        //ocrNormalFont = generateFont("fonts/ocr_normal.fnt");

        this.generateFonts();

        textButtonStyle1 = new TextButton.TextButtonStyle();
        textButtonStyle1.font = this.robotoLightFont.get("title1");
        textButtonStyle1.fontColor = Color.LIGHT_GRAY;
        textButtonStyle1.overFontColor = Color.WHITE;

        this.setScreen(new MainMenuScreen(this));

    }

    /**
     * Populates the ObjectMap with fonts of different sizes.
     * The sizes can be accessed by the following keys:
     * - "small", "normal", "title1", "title2"
     *
     * @param file The base path to the file, ie "fonts/roboto_light.ttf"
     * @return     The generated ObjectMap
     */
    private ObjectMap<String, BitmapFont> generateFont(String file) {
        ObjectMap<String, BitmapFont> map = new ObjectMap<String, BitmapFont>();
        for (int i = 0; i < fontSizeNames.length; i++) {
            FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal(file));
            FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

            /* Generate the font and add to map */
            parameter.size = (int)((double)fontSizes[i] / 1080 * Gdx.graphics.getHeight());
            parameter.minFilter = Texture.TextureFilter.Nearest;
            parameter.magFilter = Texture.TextureFilter.MipMapLinearNearest;

            BitmapFont temp = generator.generateFont(parameter);
            map.put(fontSizeNames[i], temp);

            /* Dispose of generator to avoid memory leaks */
            generator.dispose();
        }
        return map;
    }

    public void generateFonts() {
        monoNormalFont = generateFont("fonts/mono.ttf");
        robotoLightFont = generateFont("fonts/roboto_light.ttf");
    }

    @Override
    public void resume() {

    }


	@Override
	public void render () {
        super.render();
	}
	
	@Override
	public void dispose () {

	}
}
