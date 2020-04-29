package entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.*;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import world.GameMap;

public class Player extends Entity{

    private static final int SPEED = 80;
    private static final int JUMP_VELOCITY = 5;

    Texture image;
    TextureRegion[] animationFrames;
    Animation animation;

    @Override
    public void create(EntitySnapshot snapshot, EntityType type, GameMap map) {
        super.create(snapshot, type, map);
        image = new Texture("birdies.png");
        //spawnRadius = snapshot.getFloat("spawnRadius", 50);

        TextureRegion[][] tempFrames = TextureRegion.split(image, 256, 256);
    }

    @Override
    public void update(float deltaTime, float gravity) {
        if (Gdx.input.isKeyPressed(Keys.UP) && grounded) {
            this.velocityY += JUMP_VELOCITY * getWeight();
        } else if (Gdx.input.isKeyPressed(Keys.SPACE) && !grounded && this.velocityY > 0) {
            this.velocityY += JUMP_VELOCITY * getWeight() * deltaTime;
        }
        super.update(deltaTime, gravity);

        if (Gdx.input.isKeyPressed(Keys.LEFT)) {
            moveX(-SPEED * deltaTime);
        }

        if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
            moveX(SPEED * deltaTime);
        }

    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(image, pos.x, pos.y, getWidth(), getHeight());
    }

    @Override
    public EntitySnapshot getSaveSnapShot() {
        EntitySnapshot snapshot = super.getSaveSnapShot();
        // if used data eg. spawnRadius
        //snapshot.putFloat("spawnRadius", spawnradius);
        return snapshot;
    }
}
