package hu.daniel.szabo.rampupspringbootcassandra.kart.view;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import hu.daniel.szabo.rampupspringbootcassandra.kart.domain.Kart;

public class KartView {

    @Min(1)
    @Max(99)
    private int number;
    @Min(50)
    private int engineSize;

    public KartView() {
    }

    public KartView(Kart k) {
        number = k.getNumber();
        engineSize = k.getEngineSize();
    }

    public int getEngineSize() {
        return engineSize;
    }

    public void setEngineSize(int engineSize) {
        this.engineSize = engineSize;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KartView kartView = (KartView) o;

        if (number != kartView.number) return false;
        return engineSize == kartView.engineSize;
    }

    @Override
    public int hashCode() {
        int result = number;
        result = 31 * result + engineSize;
        return result;
    }
}
