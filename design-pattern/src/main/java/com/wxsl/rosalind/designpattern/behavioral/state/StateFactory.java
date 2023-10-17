package com.wxsl.rosalind.designpattern.behavioral.state;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

public class StateFactory {

    private static Map<StateType, BaseState> stateMap = new ImmutableMap.Builder<StateType, BaseState>()
            .put(StateType.NORMAL, new NormalState())
            .put(StateType.OVERDRAFT, new OverdraftState())
            .put(StateType.RESTRICT, new RestrictState())
            .build();

    public static BaseState getState(StateType stateType) {
        return stateMap.get(stateType);
    }
}
