package dk.sdu.mmmi.cbse;

import com.badlogic.gdx.ApplicationListener;


public class test implements ApplicationListener{

    @Override
    public void create() {
        System.out.println("create");
    }

    @Override
    public void resize(int i, int i1) {
        System.out.println("render test");
    }

    @Override
    public void render() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void pause() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void resume() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void dispose() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
