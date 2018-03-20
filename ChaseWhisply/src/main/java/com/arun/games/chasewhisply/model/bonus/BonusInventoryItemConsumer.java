package com.arun.games.chasewhisply.model.bonus;

import com.arun.games.chasewhisply.model.PlayerProfile;

public interface BonusInventoryItemConsumer {
    public Bonus consume(PlayerProfile playerProfile);
}
