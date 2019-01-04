package blackbox.game;

public final class Config {
    public static final int WINDOW_WIDTH = 1200;
    public static final int WINDOW_HEIGHT = 700;
    public static final String WINDOW_TITLE = "Blackbox: Singularity";
    public static final String MENU_NOTE = "Bowserinator (c) 2018. V0.1.0";

    /**
     * Shader settings
     * - IS_DESKTOP: is it running on a desktop or mobile?
     * - SHADER_PATH: path to shader (relative root is android/assets)
     * - CURVATURE_AMOUNT: Amount to curve screen for pseudo-3d effect
     */
    public static final boolean IS_DESKTOP = true;
    public static final String SHADER_PATH = "data/shaders/";
    public static final float CURVATURE_AMOUNT = -0.14f;

    /**
     * Title screen config
     * - LOGO_WIDTH, LOGO_HEIGHT: Dimensions of img/logo.png
     * - LOGO_WIDTH_PERCENT: Percent of screen width logo takes up
     * - LOGO_PADDING: Percent of screen width for padding
     */
    public static final int LOGO_WIDTH = 900;
    public static final int LOGO_HEIGHT = 300;
    public static final double LOGO_WIDTH_PERCENT = 0.4;
    public static final double LOGO_PADDING = 0.067;
}
