package mx.aflm.demojuego2d;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;

public class PantallaMenu extends Pantalla {

    private final Juego juego;      // para setScreen
    // Fondo
    private Texture texturaFondo;

    // Menú (botones)
    private Stage escenaMenu;  // Contenedor de Objetos eg: Contiene botones


    public PantallaMenu(Juego juego) {
        this.juego = juego;

    }

    /*
    Se ejecuta cuando la pantalla se va a mostrar
    INICIALIZAR LOS OBJETOS (tiempo)
     */
    @Override
    public void show() {
        texturaFondo = new Texture("fondoMenu.jpg");
        crearMenu();
    }

    private void crearMenu() {
        escenaMenu = new Stage(vista);

        // btnJugar
        Texture texturaBtnJugar = new Texture("botonesMenu/btnJugar.png");
        TextureRegionDrawable trdBtnJugar = new TextureRegionDrawable(new TextureRegion(texturaBtnJugar));
        // Inverso
        Texture texturaBtnJugarInverso = new Texture("botonesMenu/btnJugarInverso.png");
        TextureRegionDrawable trdBtnJugarInverso = new TextureRegionDrawable(new TextureRegion(texturaBtnJugarInverso));
        ImageButton btnJugar = new ImageButton(trdBtnJugar, trdBtnJugarInverso);
        btnJugar.setPosition(ANCHO/2,  ALTO/2, Align.center);
        //Programar el evento de click
        btnJugar.addListener(new ClickListener() {
            // Llama a la siguiente ventana
            @Override
            public void clicked(InputEvent event, float x, float y) {
                super.clicked(event, x, y);
                // Cambiamos de pantalla (el objeto juego lo hace a través de setScreen)
                juego.setScreen(new PantallaSpaceInvaders(juego));

            }
        });


        escenaMenu.addActor(btnJugar);

        Gdx.input.setInputProcessor(escenaMenu); //PROCESA  TODOS LOS EVENTOS
    }

    @Override
    public void render(float delta) {
        borrarPantalla();
        // ESCALAR INFORMACIÓN
        batch.setProjectionMatrix(camara.combined);

        batch.begin();
        batch.draw(texturaFondo,  0, 0);
        batch.end();

        escenaMenu.draw();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {
        texturaFondo.dispose();
        batch.dispose();

    }
}
