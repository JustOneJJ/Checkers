package Agents;

import java.util.List;
import java.util.Random;

import Checkers.Action;
import Checkers.GameState;

public class RandomAgent implements Agent {

	@Override
	public Action computeAction(GameState state) {
		List<Action> possibleActions = state.getActions();
		Random randomGenerator = new Random();
		int index = randomGenerator.nextInt(possibleActions.size());
		return possibleActions.get(index);
	}

}
