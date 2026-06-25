package com.mygdx.game.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import com.mygdx.game.shoot_em_up.ShootEmUP;

public class HtmlLauncher extends GwtApplication {

        private final Integer width = 1000;
        private final Integer height = 1000;

        @Override
        public GwtApplicationConfiguration getConfig () {
                //return new GwtApplicationConfiguration(true);
                return new GwtApplicationConfiguration(width, height);

        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new ShootEmUP(width, height);
        }
}