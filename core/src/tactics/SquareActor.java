package tactics;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class SquareActor extends Actor{
    ShapeRenderer shapeRenderer;
    static private boolean projectionMatrixSet;

    public int getxNo() {
        return xNo;
    }

    public void setxNo(int xNo) {
        this.xNo = xNo;
    }

    public int getyNo() {
        return yNo;
    }

    public void setyNo(int yNo) {
        this.yNo = yNo;
    }

    int xNo;
    int yNo;

    public SquareActor() {
        shapeRenderer = new ShapeRenderer();
        //shapeRenderer.setProjectionMatrix(cameraCombined);
        projectionMatrixSet = false;
        super.setWidth(50);
        super.setHeight(40);
    }

    /**
     * Draws the actor. The batch is configured to draw in the parent's coordinate system.
     * {@link com.badlogic.gdx.graphics.g2d.Batch#draw(com.badlogic.gdx.graphics.g2d.TextureRegion, float, float, float, float, float, float, float, float, float)
     * This draw method} is convenient to draw a rotated and scaled TextureRegion. {@link com.badlogic.gdx.graphics.g2d.Batch#begin()} has already been called on
     * the batch. If {@link com.badlogic.gdx.graphics.g2d.Batch#end()} is called to draw without the batch then {@link com.badlogic.gdx.graphics.g2d.Batch#begin()} must be called before the
     * method returns.
     * <p/>
     * The default implementation does nothing.
     *
     * @param batch batch
     * @param parentAlpha Should be multiplied with the actor's alpha, allowing a parent's alpha to affect all children.
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        super.draw(batch, parentAlpha);
        if(!projectionMatrixSet){
            shapeRenderer.setProjectionMatrix(batch.getProjectionMatrix());
        }
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(0.3f,0.1f,0.6f,0.5f);
        shapeRenderer.rect(super.getX(), super.getY(), 20, 30);
        shapeRenderer.end();

    }

}
