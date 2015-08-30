package tactics;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class LichActor extends Image {

    protected Animation animation;
    static protected Animation animationStanding;
    static protected Animation animationFlying;
    static protected Animation animationAttack;
    static protected Texture lichSheet;
    static protected TextureRegion[] lichFrames;
    static protected TextureRegion currentFrame;
    private float stateTime = 0;

    static {
        initAnimations();
    }

    public LichActor () {
        this(animationStanding);
    }

    public LichActor (Animation animation) {
        super(animation.getKeyFrame(0));
        this.animation = animation;
        initAnimations();
    }

    static private void initAnimations() {
        int FRAME_COLS1 = 4;
        int FRAME_ROWS1 = 1;
        lichSheet = new Texture("Sprites/Lich/Standing.png");
        TextureRegion[][] tmp1 = TextureRegion.split(lichSheet, lichSheet.getWidth()/FRAME_COLS1, lichSheet.getHeight()/FRAME_ROWS1);              // #10
        lichFrames = new TextureRegion[FRAME_COLS1 * FRAME_ROWS1];
        int index1 = 0;
        for (int i = 0; i < FRAME_ROWS1; i++) {
            for (int j = 0; j < FRAME_COLS1; j++) {
                lichFrames[index1++] = tmp1[i][j];
            }
        }
        animationStanding = new Animation(0.025f, lichFrames);

        int FRAME_COLS2 = 3;
        int FRAME_ROWS2 = 1;
        lichSheet = new Texture("Sprites/Lich/Flying.png");
        TextureRegion[][] tmp2 = TextureRegion.split(lichSheet, lichSheet.getWidth()/FRAME_COLS2, lichSheet.getHeight()/FRAME_ROWS2);              // #10
        lichFrames = new TextureRegion[FRAME_COLS2 * FRAME_ROWS2];
        int index2 = 0;
        for (int i = 0; i < FRAME_ROWS2; i++) {
            for (int j = 0; j < FRAME_COLS2; j++) {
                lichFrames[index2++] = tmp2[i][j];
            }
        }
        animationFlying = new Animation(0.025f, lichFrames);

        int FRAME_COLS3 = 5;
        int FRAME_ROWS3 = 2;
        lichSheet = new Texture("Sprites/Lich/Attack.png");
        TextureRegion[][] tmp3 = TextureRegion.split(lichSheet, lichSheet.getWidth()/FRAME_COLS3, lichSheet.getHeight()/FRAME_ROWS3);              // #10
        lichFrames = new TextureRegion[FRAME_COLS3 * FRAME_ROWS3];
        int index3 = 0;
        for (int i = 0; i < FRAME_ROWS3; i++) {
            for (int j = 0; j < FRAME_COLS3; j++) {
                lichFrames[index3++] = tmp3[i][j];
            }
        }
        animationAttack = new Animation(0.025f, lichFrames);
    }

    public void stand () {
        this.animation = animationStanding;
    }

    public void fly () {
        this.animation = animationFlying;
    }
    
    public void attack () {
        this.animation = animationAttack;
    }

    @Override
    public void act (float delta) {
        ((TextureRegionDrawable)getDrawable()).setRegion(animation.getKeyFrame(0.2f*(stateTime+=delta), true));
        super.act(delta);
    }
}
