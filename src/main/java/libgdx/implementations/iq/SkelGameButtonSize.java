package libgdx.implementations.iq;

import libgdx.resources.dimen.MainDimen;

public enum SkelGameButtonSize implements libgdx.controls.button.ButtonSize {

    HEADER_BUTTON(MainDimen.horizontal_general_margin.getDimen() * 14f, MainDimen.horizontal_general_margin.getDimen() * 6f),;

    private float width;
    private float height;

    SkelGameButtonSize(float width, float height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }
}
