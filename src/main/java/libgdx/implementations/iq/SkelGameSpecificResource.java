package libgdx.implementations.iq;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.I18NBundle;

import libgdx.game.Game;
import libgdx.resources.SpecificResource;
import sun.net.www.content.image.png;

public enum SkelGameSpecificResource implements SpecificResource {

    // @formatter:off

    red_background("red_background.png", Texture.class),
    popup_background("popup_background.png", Texture.class),
    finalscore("questions/finalscore.png", Texture.class),
    specific_labels("labels/labels", I18NBundle.class),;
    // @formatter:on

    private String path;
    private Class<?> classType;

    SkelGameSpecificResource(String path, Class<?> classType) {
        this.path = path;
        this.classType = classType;
    }

    @Override
    public Class<?> getClassType() {
        return classType;
    }

    @Override
    public String getPath() {
        return Game.getInstance().getAppInfoService().getImplementationGameResourcesFolder() + path;
    }

}
