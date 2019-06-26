package libgdx.implementations.iq;

import libgdx.resources.dimen.Dimen;
import libgdx.resources.dimen.DimenUtils;

public enum SkelDimen implements Dimen {

    side_answer_img(14),;

    private float dimen;

    SkelDimen(float dimen) {
        this.dimen = dimen;
    }

    @Override
    public float getDimen() {
        return DimenUtils.getDimen(this);
    }

    @Override
    public int getIntegerValueOfDimen() {
        return DimenUtils.getIntegerValueOfDimen(this);
    }

    @Override
    public float getRawDimen() {
        return dimen;
    }
}
