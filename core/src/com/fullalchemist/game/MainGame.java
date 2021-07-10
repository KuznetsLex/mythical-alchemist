package com.fullalchemist.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class MainGame extends ApplicationAdapter implements GestureDetector.GestureListener {
	SpriteBatch batch;
	Sprite ing1;
	Sprite ing2;
	OrthographicCamera camera;
	GestureDetector gestureDetector;
	Texture test;



	@Override     public void create () {
	batch = new SpriteBatch();

	ing1 = new Sprite(new Texture(Gdx.files.internal("1.png")),64,64);
	ing1.setPosition(120,120);


	ing2 = new Sprite(new Texture(Gdx.files.internal("2.png")),64,64);
	ing2.setPosition(40,40);

	test = new Texture(Gdx.files.internal("4.png"));


	camera = new OrthographicCamera(800,480);
	camera.update();

	gestureDetector = new GestureDetector(this);
	Gdx.input.setInputProcessor(gestureDetector);
	}



	@Override     public void render () {
	Gdx.gl.glClearColor(0, 0, 0, 0);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	batch.setProjectionMatrix(camera.combined);
	batch.begin();


	if (ing1.getBoundingRectangle().overlaps(ing2.getBoundingRectangle())){
		ing1.setTexture(test);
		ing1.draw(batch);
		}
	else {
		ing1.draw(batch);
		ing2.draw(batch);
	}
	batch.end();


	}

	@Override
	public void dispose () {
		super.dispose();
		ing1.getTexture().dispose();
		ing2.getTexture().dispose();
		batch.dispose();
	}

	@Override
	public boolean touchDown(float x, float y, int pointer, int button) {
		return false;
	}


	@Override
	public boolean tap(float x, float y, int count, int button) {
		return true;
	}


	@Override
	public boolean longPress(float x, float y) {
		return true;
	}


	@Override
	public boolean fling(float velocityX, float velocityY, int button) {
		return false;
	}


	@Override
	public boolean pan(float x, float y, float deltaX, float deltaY) {
		Vector3 touchPos = new Vector3(x,y,0);
		camera.unproject(touchPos);
		if (ing1.getBoundingRectangle().contains(touchPos.x,touchPos.y) /*&&(!ing2.getBoundingRectangle().contains(touchPos.x,touchPos.y))*/) {
			ing1.setPosition(touchPos.x - ing1.getWidth() / 2, touchPos.y - ing1.getHeight() / 2);

		}

		if (ing2.getBoundingRectangle().contains(touchPos.x,touchPos.y)/*&&(!ing1.getBoundingRectangle().contains(touchPos.x,touchPos.y))*/) {
			ing2.setPosition(touchPos.x - ing2.getWidth() / 2, touchPos.y - ing2.getHeight() / 2);

		}




		return true;
}


	@Override
	public boolean panStop(float x, float y, int pointer, int button) {
		return false;
}


	@Override
	public boolean zoom(float initialDistance, float distance) {
		return true;
}


	@Override
	public boolean pinch(Vector2 initialPointer1, Vector2 initialPointer2, Vector2 pointer1, Vector2 pointer2) {
		return true;
}


	@Override
	public void pinchStop() {

	}
}