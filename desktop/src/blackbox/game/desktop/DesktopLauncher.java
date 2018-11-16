package blackbox.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import blackbox.game.BlackboxGame;
import blackbox.game.Config;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = Config.WINDOW_TITLE;
		config.width = Config.WINDOW_WIDTH;
		config.height = Config.WINDOW_HEIGHT;

		new LwjglApplication(new BlackboxGame(), config);
	}
}
