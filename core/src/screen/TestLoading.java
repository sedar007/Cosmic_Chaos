
package screen;


        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Screen;
        import com.badlogic.gdx.graphics.Color;
        import com.badlogic.gdx.graphics.GL20;
        import com.badlogic.gdx.graphics.OrthographicCamera;
        import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
        import shoot_em_up.ShootEmUP;

public class TestLoading implements Screen {

    final ShootEmUP game;
    OrthographicCamera camera;

    ShapeRenderer shape;

    int x ;
    int y ;
    public TestLoading(final ShootEmUP game){
        this.game = game;
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 480);
        shape = new ShapeRenderer();
         x = 50;
        y = 50;
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        shape.begin(ShapeRenderer.ShapeType.Filled);

        shape.setColor(Color.WHITE);
        shape.rect(50, 20, 100,20);

        shape.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {
        // TODO Auto-generated method stub
    }

    @Override
    public void resume() {
        // TODO Auto-generated method stub
    }

    @Override
    public void hide() {
        // TODO Auto-generated method stub

    }

    @Override
    public void dispose() {
        // dispose of assets when not needed anymore
    }

}