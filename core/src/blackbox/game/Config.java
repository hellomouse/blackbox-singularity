package blackbox.game;

public final class Config {
    public static final int WINDOW_WIDTH = 1920 / 2;
    public static final int WINDOW_HEIGHT = 1080 / 2;
    public static final String WINDOW_TITLE = "Blackbox: Singularity";
    public static final String MENU_NOTE = "v1.0.0 Bowserinator 2018";

    /**
     * Shader settings
     * - isDesktop: is it running on a desktop or mobile?
     */
    public static final boolean isDesktop = true;
    public static final String SHADER_PATH = "data/shaders/";

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
