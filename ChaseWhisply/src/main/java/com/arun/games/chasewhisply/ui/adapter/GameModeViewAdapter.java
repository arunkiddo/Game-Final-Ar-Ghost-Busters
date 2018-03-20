package com.arun.games.chasewhisply.ui.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.arun.games.chasewhisply.model.mode.GameMode;
import com.arun.games.chasewhisply.ui.customviews.GameModeView;

import java.util.ArrayList;

import com.arun.games.chasewhisply.R;
import com.arun.games.chasewhisply.model.PlayerProfile;


public class GameModeViewAdapter extends ArrayAdapter<GameMode> {

    public Listener mListener;
    private ArrayList<GameMode> mGameModes;
    private PlayerProfile mPlayerProfile;


    public GameModeViewAdapter(Context context, ArrayList<GameMode> gameModes, PlayerProfile p, Listener l) {
        super(context, R.layout.view_game_mode, gameModes);
        mGameModes = gameModes;
        mPlayerProfile = p;
        mListener = l;
    }

    public GameModeViewAdapter(Context context, ArrayList<GameMode> gameModes, Listener l) {
        super(context, R.layout.view_game_mode, gameModes);
        mGameModes = gameModes;
        mPlayerProfile = null;
        mListener = l;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final GameMode currentGameMode = mGameModes.get(position);
        GameModeView rowView = (GameModeView) convertView;

        if (rowView == null) {
            rowView = new GameModeView(getContext());
        }

        if (mPlayerProfile == null) {
            rowView.setModelForLeaderboard(currentGameMode);
        } else {
            rowView.setModel(currentGameMode);
            rowView.setGameModeEnabled(currentGameMode.isAvailable(mPlayerProfile));
        }
        rowView.setGameModeSelectedListener(mListener);

        return rowView;

    }

    public interface Listener {
        public void onGameModeSelected(GameModeView view);
    }
}
