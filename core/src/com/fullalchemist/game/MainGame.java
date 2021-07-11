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
	Sprite ing3;
	Sprite fon;
	Sprite Myal;
	Texture fon2;
	OrthographicCamera camera;
	GestureDetector gestureDetector;
	Texture test;



	@Override     public void create () {
	batch = new SpriteBatch();

	Myal = new Sprite(new Texture(Gdx.files.internal("space.png")),64,64);
	Myal.setPosition(290,-150);

	ing1 = new Sprite(new Texture(Gdx.files.internal("H2.png")),64,64);
	ing1.setPosition(-50,90);


	ing2 = new Sprite(new Texture(Gdx.files.internal("o2.png")),64,64);
	ing2.setPosition(130,50);

	test = new Texture(Gdx.files.internal("h2o.png"));

	ing3 = new Sprite(new Texture(Gdx.files.internal("Ca.png")),64,64);
	ing3.setPosition(-30,-30);

	fon = new Sprite(new Texture(Gdx.files.internal("fon.png")),800,449);
	fon.setPosition(-400,-225);

	fon2 = new Texture("fon2.png");

	camera = new OrthographicCamera(800,449);
	camera.update();

	gestureDetector = new GestureDetector(this);
	Gdx.input.setInputProcessor(gestureDetector);
	}



	@Override     public void render () {
	Gdx.gl.glClearColor(0, 0, 0, 0);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
	batch.setProjectionMatrix(camera.combined);
	batch.begin();
	fon.draw(batch);
	Myal.draw(batch);

	if (ing1.getBoundingRectangle().overlaps(ing2.getBoundingRectangle())) {

		if (ing1.getBoundingRectangle().overlaps(Myal.getBoundingRectangle())){
			ing3.draw(batch);
		}
		else{

			ing1.setTexture(test);
			fon.setTexture(fon2);
			ing1.draw(batch);
			ing3.draw(batch);

		}
		}




		else{ //отрисовка до столкновения
		ing1.draw(batch);
		ing2.draw(batch);
		ing3.draw(batch);
	}



	batch.end();


	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}



	@Override
	public void dispose () {
		super.dispose();
		ing1.getTexture().dispose();
		ing2.getTexture().dispose();
		ing3.getTexture().dispose();
		batch.dispose();
		Myal.getTexture().dispose();
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
		if (ing1.getBoundingRectangle().contains(touchPos.x,touchPos.y)&&(!ing3.getBoundingRectangle().contains(touchPos.x,touchPos.y))) {
			ing1.setPosition(touchPos.x - ing1.getWidth() / 2, touchPos.y - ing1.getHeight() / 2);

		}

		if (ing2.getBoundingRectangle().contains(touchPos.x,touchPos.y)&&(!ing3.getBoundingRectangle().contains(touchPos.x,touchPos.y))) {
			ing2.setPosition(touchPos.x - ing2.getWidth() / 2, touchPos.y - ing2.getHeight() / 2);

		}

		if (ing3.getBoundingRectangle().contains(touchPos.x,touchPos.y)&&(!ing1.getBoundingRectangle().contains(touchPos.x,touchPos.y))&&(!ing2.getBoundingRectangle().contains(touchPos.x,touchPos.y))) {
			ing3.setPosition(touchPos.x - ing3.getWidth() / 2, touchPos.y - ing3.getHeight() / 2);

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