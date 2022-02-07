package racing.controller;

import java.util.List;
import racing.domain.RacingGame;
import racing.domain.TryNumber;
import racing.domain.startegy.RandomMovingStrategy;
import racing.view.InputView;
import racing.view.OutputView;

public class RacingController {

    public void start() {
        try {
            final List<String> names = InputView.getCarNames();
            final RacingGame racingGame = RacingGame.registerCars(new RandomMovingStrategy(), names);
            final TryNumber tryNumber = inputGetTryNumber();

            OutputView.printGameResult(racingGame.race(tryNumber));
            OutputView.printWinners(racingGame.decideWinners());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            start();
        }
    }

    private TryNumber inputGetTryNumber() {
        try {
            return new TryNumber(InputView.getTryNumber());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return inputGetTryNumber();
        }
    }
}
