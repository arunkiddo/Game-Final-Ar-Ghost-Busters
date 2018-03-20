package com.arun.games.chasewhisply.mechanics.engine;

import android.content.Context;

import com.arun.games.chasewhisply.mechanics.behaviors.GameBehaviorTime;
import com.arun.games.chasewhisply.mechanics.routine.Routine;
import com.arun.games.chasewhisply.ui.gameviews.GameViewDeathToTheKing;
import com.arun.games.chasewhisply.mechanics.behaviors.GameBehaviorDeathToTheKing;
import com.arun.games.chasewhisply.ui.gameviews.GameView;

public class GameEngineDeathToTheKing extends GameEngineTime implements GameBehaviorDeathToTheKing.IGameBehaviorDeathToTheKing {
    private GameBehaviorDeathToTheKing mGameBehavior;
    private GameViewDeathToTheKing mGameView;

    public GameEngineDeathToTheKing(Context context, IGameEngine iGameEngine, GameBehaviorTime gameBehavior) {
        super(context, iGameEngine, gameBehavior);
        mGameBehavior = (GameBehaviorDeathToTheKing) gameBehavior;
    }

    @Override
    protected void setGameView(GameView gameView) {
        super.setGameView(gameView);
        mGameView = (GameViewDeathToTheKing) gameView;
    }

    @Override
    public void onRun(int routineType, Object obj) {
        switch (routineType) {
            case Routine.TYPE_RELOADER:
                mGameBehavior.reload();
                break;
            case Routine.TYPE_TICKER:
                mGameBehavior.tick((Long) obj);
                break;

        }
    }

    public boolean hasTheKingAlreadyBeenSummoned() {
        return mGameBehavior.hasKingAlreadyBeenSummoned();
    }

    @Override
    public void onKingSummon() {
        mGameView.hideInstruction();
    }
}
