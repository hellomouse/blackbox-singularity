package blackbox.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import blackbox.game.BlackboxGame;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

		config.title = "Drop";
		config.width = 1200;
		config.height = 700;

		new LwjglApplication(new BlackboxGame(), config);
	}
}
