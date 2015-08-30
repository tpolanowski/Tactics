package tactics;

import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class KnightActor extends Image {
    protected Animation animation = null;

    private float stateTime = 0;

    public KnightActor (Animation animation) {
        super(animation.getKeyFrame(0));
        this.animation = animation;
    }

    @Override
    public void act (float delta) {
        ((TextureRegionDrawable)getDrawable()).setRegion(animation.getKeyFrame(0.2f*(stateTime+=delta), true));
        super.act(delta);
    }
}
