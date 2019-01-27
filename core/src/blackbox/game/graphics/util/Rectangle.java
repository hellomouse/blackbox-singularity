package blackbox.game.graphics.util;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

/**
 * Rectangle class
 * Use to render a rectangle somewhere on
 * the screen. You can use setX, setY, setWidth, setHeight,
 * etc... to modify properties dynamically
 *
 * @author Alexandr
 * @see https://stackoverflow.com/a/34668612
 */
public class Rectangle extends Actor {
    private Texture texture;

    /**
     * Construct a rectangle
     * @param x      Corner x
     * @param y      Corner y
     * @param width  Width of rect
     * @param height Height of rect
     * @param color  Color of rect
     */
    public Rectangle(float x, float y, float width, float height, Color color) {
        createTexture((int)width, (int)height, color);

        setX(x);
        setY(y);
        setWidth(width);
        setHeight(height);
    }

    /**
     * Generates the rectangle texture that it draws
     * @param width  Width
     * @param height Height
     * @param color  Color
     */
    private void createTexture(int width, int height, Color color) {
        Pixmap pixmap = new Pixmap(width, height, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fillRectangle(0, 0, width, height);
        texture = new Texture(pixmap);
        pixmap.dispose();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }
}