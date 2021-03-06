package com.projectdgdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.projectdgdx.game.Config;
import com.projectdgdx.game.GameStates;
import com.projectdgdx.game.ProjectD;
import com.projectdgdx.game.model.InputModel;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.badlogic.gdx.Gdx.gl20;

/**
 * Main menu state displays and handles the main menu
 * Created by Eddie on 2017-04-28.
 */
public class MainMenuState implements iGameState {

    private Skin skin;
    private Stage stage;
    private TextButton newGameButton;
    private TextButton settingsButton;
    private TextButton exitButton;

    private SelectBox<Object> levelSelection;
    private List<String> levelList = new ArrayList<String>();

    private InputMultiplexer multiplexer;
    private Label mainMenuHeading;
    private Table table;
    private int controllerPosition = 0;

    @Override
    public void init(ProjectD projectD) {
        File f = new File("map/");
        levelList = new ArrayList<String>(Arrays.asList(f.list()));
    }

    @Override
    public void start(ProjectD projectD) {
        buildMenu();

        //Set to first selection as default
        Config.LEVEL_IN_PLAY = ((Label) levelSelection.getSelected()).getText().toString();

        this.multiplexer = projectD.getMultiplexer();
        multiplexer.addProcessor(stage);// Make the stage consume events
        Gdx.input.setInputProcessor(multiplexer);

    }

    @Override
    public void update(ProjectD projectD) {
        Gdx.gl.glClearColor(Config.MENU_DEFAULTBACKGROUND_R,
                Config.MENU_DEFAULTBACKGROUND_G,
                Config.MENU_DEFAULTBACKGROUND_B,
                Config.MENU_DEFAULTBACKGROUND_A);
        Gdx.gl.glClear(gl20.GL_COLOR_BUFFER_BIT);

        stage.act();
        stage.draw();

        if(newGameButton.isPressed()){
            this.exit(projectD);
            projectD.resetState(GameStates.INGAME);
            projectD.setState(GameStates.INGAME);
        }
        else if(settingsButton.isPressed()){
            this.exit(projectD);
            projectD.setState(GameStates.SETTINGS);
        }

        else if(exitButton.isPressed()){
            this.exit(projectD);
            Gdx.app.exit();
        }

        //TODO more logic for handeling movement of controllers or keyboards in menus
        for(InputController inputController : projectD.getInpuControllers()) {
            InputModel inputModel = inputController.getModel();

            float controllerValue = inputModel.getLeftStick().z;
            if(controllerValue != 0) {
                if(controllerValue < 0 && controllerPosition > 0) {
                    controllerPosition--;
                }else if(controllerValue < 0 && controllerPosition < 4) {
                    controllerPosition++;
                }
//                switch (controllerValue) {
//                }
            }

            if(inputModel.getMenuButton().isPressed() && inputModel.getMenuButton().getPressedCount() >= 1){
                this.stop(projectD);
                projectD.setState(GameStates.SETTINGS);
            }
        }
    }

    @Override
    public void stop(ProjectD projectD) {
        stage.dispose();
    }

    @Override
    public void exit(ProjectD projectD) {
        //stage.dispose();
    }

    private void buildMenu(){

        this.stage = new Stage();
        skin = createBasicSkin(Config.UI_SKIN_PATH);

        //Create buttons
        mainMenuHeading = new Label("Project D", skin);
        newGameButton = new TextButton("New game", skin);
        settingsButton = new TextButton("Settings", skin);
        exitButton = new TextButton("Exit Game", skin);


        //Set up the SelectionBox with content
        Object[] blob = new Object[levelList.size()];
        for(int i = 0; i < levelList.size(); i++){
            blob[i] = new Label(levelList.get(i), skin);
        }
        levelSelection = new SelectBox<Object>(skin);
        levelSelection.setItems(blob);

        //Create a table to display everything in rows
        table = new Table();
        table.setSize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        //add everything to table
        table.add(mainMenuHeading).expandX().height(60);
        table.row();
        table.add(newGameButton).expandX().width(600).height(60);
        table.row();
        table.add(levelSelection).expandX().width(600).height(30);;
        table.row();
        table.add(settingsButton).expandX().width(600).height(60);
        table.row();
        table.add(exitButton).expandX().width(600).height(60);
        table.row();

        stage.addActor(table);

        //add listeners
        levelSelection.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                System.out.println(((Label) levelSelection.getSelected()).getText());
                Config.LEVEL_IN_PLAY = ((Label) levelSelection.getSelected()).getText().toString();
            }
        });
    }

    /**
     * createBasicSkin reads an internalGUI file and creates a skin
     *
     * @param path Path to the GUIskin file
     * @return A new skin
     */
    private Skin createBasicSkin(String path){
        return new Skin(Gdx.files.internal(path));
    }
}