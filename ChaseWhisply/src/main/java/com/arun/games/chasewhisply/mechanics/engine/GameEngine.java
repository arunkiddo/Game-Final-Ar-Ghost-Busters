package com.arun.games.chasewhisply.mechanics.engine;


import android.content.Context;
import android.view.View;

import com.arun.games.chasewhisply.mechanics.behaviors.GameBehavior;
import com.arun.games.chasewhisply.mechanics.routine.Routine;
import com.arun.games.chasewhisply.model.DisplayableItem;

import java.util.ArrayList;
import java.util.List;

import com.arun.games.chasewhisply.mechanics.informations.GameInformation;
import com.arun.games.chasewhisply.model.TargetableItem;
import com.arun.games.chasewhisply.sound.GameSoundManager;
import com.arun.games.chasewhisply.ui.AnimationLayer;
import com.arun.games.chasewhisply.ui.gameviews.GameView;

public abstract class GameEngine implements View.OnClickListener, GameBehavior.IGameBehavior, Routine.IRoutine {
    public static final int STATE_STOP = 0x00000001;
    public static final int STATE_RUNNING = 0x00000002;
    public static final int STATE_PAUSED = 0x00000003;
    final protected GameSoundManager mGameSoundManager;
    final protected IGameEngine mInterface;
    final protected GameBehavior mGameBehavior;
    final protected ArrayList<Routine> mRoutines;
    protected int mCurrentState;
    protected AnimationLayer mAnimationLayer;
    private GameView mGameView;

    public GameEngine(final Context context, IGameEngine iGameEngine, GameBehavior gameBehavior) {
        mGameSoundManager = new GameSoundManager(context);
        mRoutines = new ArrayList<Routine>();
        mCurrentState = STATE_STOP;
        mInterface = iGameEngine;
        mGameBehavior = gameBehavior;
        mAnimationLayer = new AnimationLayer(context);
    }

    @Override
    public void onClick(View v) {
        mGameBehavior.onClick();
    }

    /**
     * start the game, should be called only at the beginning once.
     */
    public void start() {
        startRoutines();
        mCurrentState = STATE_RUNNING;
    }

    /**
     * pause the game.
     */
    public void pause() {
        mGameSoundManager.stopAllSounds();
        stopRoutines();
        mCurrentState = STATE_PAUSED;
    }

    /**
     * resume the game
     */
    public void resume() {
        startRoutines();
        mCurrentState = STATE_RUNNING;
    }

    /**
     * stop the game, should not be called manually
     */
    public void stop() {
        mGameSoundManager.stopAllSounds();
        stopRoutines();
        mCurrentState = STATE_STOP;
        mInterface.onGameEngineStop();
    }

    protected void startRoutines() {
        for (Routine routine : mRoutines) {
            routine.startRoutine();
        }
    }

    protected void stopRoutines() {
        for (Routine routine : mRoutines) {
            routine.stopRoutine();
        }
    }

    protected void addRoutine(Routine routine) {
        routine.setIRoutine(this);
        mRoutines.add(routine);
    }

    public void changePosition(float posX, float posY, float posZ) {
        mGameBehavior.setCurrentPosition(posX, posY, posZ);
        mGameView.invalidate();
    }

    public GameInformation getGameInformation() {
        return mGameBehavior.getGameInformation();
    }

    public void setCameraAngle(float horizontal, float vertical) {
        mGameView.setCameraAngleInDegree(horizontal, vertical);
    }

    public float[] getCurrentPosition() {
        return mGameBehavior.getCurrentPosition();
    }

    public List<DisplayableItem> getItemsForDisplay() {
        return mGameBehavior.getItemsForDisplay();
    }

    public TargetableItem getCurrentTarget() {
        return mGameBehavior.getCurrentTarget();
    }

    public void setCurrentTarget(TargetableItem t) {
        mGameBehavior.setCurrentTarget(t);
    }

    public void removeTarget() {
        mGameBehavior.removeTarget();
    }

    public int getLastScoreAdded() {
        return mGameBehavior.getLastScoreAdded();
    }

    public GameView getGameView() {
        return mGameView;
    }

    protected void setGameView(GameView gameView) {
        mGameView = gameView;
        mGameView.setOnClickListener(this);
        mGameView.setAnimationLayer(mAnimationLayer);
    }

    public AnimationLayer getAnimationLayer() {
        return mAnimationLayer;
    }

    @Override
    public void onSoundRequest(int soundType) {
        mGameSoundManager.requestSound(soundType);
    }

    public interface IGameEngine {
        abstract public void onGameEngineStop();
    }
}
