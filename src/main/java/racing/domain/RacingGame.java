package racing.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import racing.domain.car.Car;
import racing.domain.car.Cars;
import racing.domain.car.Position;
import racing.domain.startegy.MovingStrategy;
import racing.domain.startegy.RandomMovingStrategy;

public class RacingGame {

    private static final String COMMA = ", ";

    private final MovingStrategy movingStrategy;
    private final Cars cars;

    public RacingGame(Cars cars) {
        movingStrategy = new RandomMovingStrategy();
        this.cars = cars;
    }

    public static RacingGame registerCars(List<String> names) {
        return new RacingGame(new Cars(names, 0));
    }

    public List<Cars> race(final TryNumber tryNumber) {
        final List<Cars> raceStates = new ArrayList<>();
        while (tryNumber.hasNextStep()) {
            raceStates.add(new Cars(this.cars.driveCars(movingStrategy)));
            tryNumber.nextStep();
        }
        return raceStates;
    }

    public String decideWinners() {
        return cars.getCars().stream()
            .filter(car -> car.isSamePosition(new Position(cars.getMaxPosition())))
            .map(Car::getNameValue)
            .collect(Collectors.joining(COMMA));
    }
}
