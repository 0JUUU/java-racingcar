package racing.controller;

import java.util.List;
import racing.domain.RacingGame;
import racing.domain.TryNumber;
import racing.domain.startegy.MovingStrategy;
import racing.domain.startegy.RandomMovingStrategy;
import racing.view.InputView;
import racing.view.OutputView;

public class RacingController {

    public void start() {
        try {
            final List<String> names = InputView.getCarNames();
            final MovingStrategy movingStrategy = new RandomMovingStrategy();
            final RacingGame racingGame = RacingGame.registerCars(movingStrategy, names);

            final TryNumber tryNumber = InputView.getTryNumber();
            OutputView.printGameResult(racingGame.race(tryNumber));
            OutputView.printWinners(racingGame.decideWinners());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            start();
        }
    }
}
