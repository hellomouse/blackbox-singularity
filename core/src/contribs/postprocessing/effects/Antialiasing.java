package contribs.postprocessing.effects;
import contribs.postprocessing.PostProcessorEffect;

public abstract class Antialiasing extends PostProcessorEffect {

	public abstract void setViewportSize (int width, int height);
}
