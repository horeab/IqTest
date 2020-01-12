package libgdx.startgame;

import libgdx.constants.GameIdEnum;
import libgdx.implementations.iq.SkelGame;
import libgdx.utils.startgame.test.DefaultAppInfoService;

public class StartGame {

    public static void main(String[] args) {
        SkelGame game = new SkelGame(
                new DefaultAppInfoService() {
                    @Override
                    public String getGameIdPrefix() {
                        return GameIdEnum.skelgame.name();
                    }

                    @Override
                    public float gameScreenTopMargin() {
                        return super.gameScreenTopMargin();
                    }

                    @Override
                    public String getAppName() {
                        return "Crossword Garden";
                    }

                    @Override
                    public String getLanguage() {
                        return "en";
                    }
                });
        libgdx.utils.startgame.StartGame.main(game, args);
    }
}
