package blackbox.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
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
    public static final String[] fontSizeNames = {"title2", "title1", "normal", "small"};
    public static final int[] fontSizes = {70, 38, 24, 18};

    public ObjectMap<String, BitmapFont> monoNormalFont;
    public ObjectMap<String, BitmapFont> robotoLightFont;

    private PostProcessor postProcessor;

    @Override
    public void create() {
        /* Create fonts */
        //ocrNormalFont = generateFont("fonts/ocr_normal.fnt");

        this.generateFonts();


        this.setScreen(new MainMenuScreen(this));

        ShaderLoader.BasePath = "data/shaders/";
        postProcessor = new PostProcessor( false, false, true ); // TODO last argument is isDesktop

        Bloom bloom = new Bloom( (int)(Gdx.graphics.getWidth() * 0.25f), (int)(Gdx.graphics.getHeight() * 0.25f) );
        Curvature fishEye = new Curvature();
        fishEye.setDistortion(-0.15f);

        postProcessor.addEffect(bloom);
        postProcessor.addEffect(fishEye);
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
        postProcessor.rebind();
    }


	@Override
	public void render () {
        postProcessor.capture();
        super.render();
        postProcessor.render();
	}
	
	@Override
	public void dispose () {
        postProcessor.dispose();
	}
}
