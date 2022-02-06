package racing.domain.car;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import racing.domain.startegy.MovingStrategy;

public class Cars {

    private final List<Car> cars;

    public Cars(List<Car> cars) {
        this.cars = cars;
        checkZeroCar(this.cars);
        checkSameNameCars(this.cars);
    }

    public Cars(List<String> names, int initValue) {
        this(
            names.stream()
                .map(name -> new Car(name, initValue))
                .collect(Collectors.toList())
        );
    }

    private void checkSameNameCars(List<Car> cars) {
        if (cars.size()
            != cars.stream().map(Car::getNameValue).distinct().count()) {
            throw new IllegalArgumentException("[ERROR] 중복된 이름은 들어갈 수 없습니다.");
        }
    }

    private void checkZeroCar(List<Car> cars) {
        if (Objects.isNull(cars) || cars.isEmpty()) {
            throw new IllegalArgumentException("[ERROR] 자동차 개수는 적어도 1개 이상이어야 합니다.");
        }
    }

    public List<Car> driveCars(MovingStrategy movingStrategy) {
        final List<Car> carsState = new ArrayList<>();
        for (Car car : cars) {
            car.drive(movingStrategy.moving());
            carsState.add(
                new Car(new Name(car.getNameValue()), new Position(car.getPositionValue())));
        }
        return new ArrayList<>(carsState);
    }

    public Position findMaxPosition() {
        int maxPosition = 0;
        for (Car car : cars) {
            maxPosition = Math.max(maxPosition, car.getPositionValue());
        }
        return new Position(maxPosition);
    }

    public List<Car> getCars() {
        return new ArrayList<>(cars);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Cars cars1 = (Cars) o;
        return Objects.equals(cars, cars1.cars);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cars);
    }


}
