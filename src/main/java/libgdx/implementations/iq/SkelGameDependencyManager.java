package libgdx.implementations.iq;

import java.util.ArrayList;
import java.util.List;

import libgdx.campaign.CampaignGameDependencyManager;
import libgdx.campaign.LettersCampaignLevelEnum;
import libgdx.campaign.LettersQuestionCategoryEnum;
import libgdx.campaign.LettersQuestionDifficultyLevel;
import libgdx.campaign.StarsService;
import libgdx.resources.IncrementingRes;

public class SkelGameDependencyManager extends CampaignGameDependencyManager {

    @Override
    public List<? extends IncrementingRes> getIncrementResList() {
        List<IncrementingRes> list = new ArrayList<>();
        int totalQuestions = 38;
        list.add(new ImageQuestionIncrementRes(0, totalQuestions, ImageQuestionIncrementRes.PNG));
        for (int i = 0; i < 8; i++) {
            list.add(new ImageAnswerIncrementRes(0, totalQuestions, i, ImageQuestionIncrementRes.PNG));
        }
        return list;
    }

    @Override
    public Class<SkelGameSpecificResource> getSpecificResourceTypeEnum() {
        return SkelGameSpecificResource.class;
    }

    @Override
    public Class<LettersCampaignLevelEnum> getCampaignLevelTypeEnum() {
        return LettersCampaignLevelEnum.class;
    }

    @Override
    public Class<LettersQuestionCategoryEnum> getQuestionCategoryTypeEnum() {
        return LettersQuestionCategoryEnum.class;
    }

    @Override
    public Class<LettersQuestionDifficultyLevel> getQuestionDifficultyTypeEnum() {
        return LettersQuestionDifficultyLevel.class;
    }

    public StarsService getStarsService() {
        return new StarsService();
    }
}
