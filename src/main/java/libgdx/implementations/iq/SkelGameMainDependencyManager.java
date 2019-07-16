package libgdx.implementations.iq;

import libgdx.constants.GameIdEnum;
import libgdx.controls.labelimage.InventoryTableBuilderCreator;
import libgdx.controls.popup.RatingService;
import libgdx.game.MainDependencyManager;
import libgdx.resources.gamelabel.GameLabel;
import libgdx.screens.ScreenManager;
import libgdx.resources.Resource;
import libgdx.resources.ResourceService;
import libgdx.screens.AbstractScreen;
import libgdx.transactions.TransactionsService;

public class SkelGameMainDependencyManager extends MainDependencyManager<ScreenManager, AbstractScreen, SkelGameLabel, Resource, GameIdEnum> {

    @Override
    public Class<Resource> getMainResourcesClass() {
        return Resource.class;
    }

    @Override
    public Class<GameIdEnum> getGameIdClass() {
        return GameIdEnum.class;
    }

    @Override
    public ResourceService createResourceService() {
        return new SkelGameResourceService();
    }

    @Override
    public RatingService createRatingService(AbstractScreen abstractScreen) {
        return new SkelGameRatingService(abstractScreen);
    }

    @Override
    public Class<SkelGameLabel> getGameLabelClass() {
        return SkelGameLabel.class;
    }

    @Override
    public ScreenManager createScreenManager() {
        return new ScreenManager();
    }

    @Override
    public InventoryTableBuilderCreator createInventoryTableBuilderCreator() {
        throw new RuntimeException("Transactions not implemented for Game");
    }

    @Override
    public TransactionsService getTransactionsService() {
        throw new RuntimeException("Transactions not implemented for Game");
    }
}
