package libgdx.implementations.iq;

import libgdx.resources.IncrementingRes;

public class ImageAnswerIncrementRes extends IncrementingRes {

    public final static String PNG = "png";

    ImageAnswerIncrementRes(int beginIndex, int endIndex, int answerIndex, String imgExtension) {
        super(beginIndex, endIndex,
                SkelGame.getInstance().getAppInfoService().getImplementationGameResourcesFolder() + "questions/q%sa" + answerIndex + "." + imgExtension,
                "q%sa" + answerIndex);
    }

}
