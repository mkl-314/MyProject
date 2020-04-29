package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import entities.EntityType;
import entities.Player;
import world.CustomGameMap;
import world.GameMap;
import world.TileType;
import world.TiledGameMap;

public class PlatformerGame extends ApplicationAdapter {

	OrthographicCamera cam;
	SpriteBatch batch;

	GameMap gameMap;

	@Override
	public void create () {
		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.update();
		gameMap = new TiledGameMap();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.isTouched()) {
			cam.translate(-Gdx.input.getDeltaX(), Gdx.input.getDeltaY());
			cam.update();
		}

		if (Gdx.input.justTouched()) {
			Vector3 pos = cam.unproject((new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0)));
			TileType type = gameMap.getTileTypeByLocation(1, pos.x, pos.y);

			if  (type != null) {
				System.out.println("Tile id" + type.getId() + " " + type.getName() + " " + type.isCollidable());
			}
		}


		cam.update();
		gameMap.update(Gdx.graphics.getDeltaTime());
		gameMap.render(cam, batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
