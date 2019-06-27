package libgdx.screens.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

import libgdx.controls.label.MyWrappedLabel;
import libgdx.controls.label.MyWrappedLabelConfigBuilder;
import libgdx.game.CurrentGame;
import libgdx.game.Game;
import libgdx.game.GameCreator;
import libgdx.game.QuestionService;
import libgdx.game.StoreService;
import libgdx.graphics.GraphicUtils;
import libgdx.implementations.iq.SkelGameLabel;
import libgdx.implementations.iq.SkelGameSpecificResource;
import libgdx.resources.FontManager;
import libgdx.resources.Res;
import libgdx.resources.ResourcesManager;
import libgdx.resources.dimen.MainDimen;
import libgdx.screens.AbstractScreen;
import libgdx.utils.ScreenDimensionsManager;
import libgdx.utils.model.RGBColor;

public class GameOverScreen extends AbstractScreen {

    private int finalScore;
    private int screenWidth = ScreenDimensionsManager.getScreenWidth();
    private int screenHeight = ScreenDimensionsManager.getScreenHeight();

    public GameOverScreen(int finalScore) {
        this.finalScore = finalScore;
        new StoreService().reset();
    }

    @Override
    public void buildStage() {
        setBackgroundColor(RGBColor.WHITE);
        Table table = new Table();
        table.setFillParent(true);
        table.add(createQuestionImage()).row();
        table.add(createInfoLabel()).center().pad(MainDimen.vertical_general_margin.getDimen()).growX().colspan(4).row();
        addActor(table);
    }

    private Table createInfoLabel() {
        Table table = new Table();
        MyWrappedLabel infoLabel = new MyWrappedLabel(new MyWrappedLabelConfigBuilder().setText((SkelGameLabel.finalscoreexplanation.getText())).setFontScale(FontManager.getSmallFontDim()).build());
        table.add(new MyWrappedLabel(new MyWrappedLabelConfigBuilder().setText("" + finalScore).setSingleLineLabel().setTextStyle(ResourcesManager.getLabelRed()).setFontScale(FontManager.getBigFontDim()).build())).growX().row();
        table.add(new MyWrappedLabel(new MyWrappedLabelConfigBuilder().setText("(" + new QuestionService().getLevelForScore(finalScore) + ")").setSingleLineLabel().setFontScale(FontManager.getNormalFontDim()).build())).growX().row();
        table.add(infoLabel);
        return table;
    }

    private Table createQuestionImage() {
        Image questionImage = GraphicUtils.getImage(SkelGameSpecificResource.finalscore);
        Table table = new Table();
        float sideRatio = questionImage.getHeight() / ((float) questionImage.getWidth());
        int screenWidth = ScreenDimensionsManager.getScreenWidth();
        table.add(questionImage).width(screenWidth).height(screenWidth * sideRatio);
        return table;
    }

    @Override
    public void render(float delta) {
        super.render(delta);
        float leftMarginPercent = (finalScore - QuestionService.MIN_SCORE)
                / (float) (QuestionService.MAX_SCORE - QuestionService.MIN_SCORE);
        ShapeRenderer shapeRenderer = new ShapeRenderer();
        shapeRenderer.setColor(Color.RED);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.rect(screenWidth * leftMarginPercent, screenHeight / 1.78f, screenWidth / 100, screenHeight / 4.4f);
        shapeRenderer.end();
    }

    @Override
    public void onBackKeyPress() {
        Game.getInstance().getScreenManager().showMainScreen();
    }

}
