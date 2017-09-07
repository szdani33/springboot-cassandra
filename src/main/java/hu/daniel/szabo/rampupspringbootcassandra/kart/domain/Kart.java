package hu.daniel.szabo.rampupspringbootcassandra.kart.domain;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

@Table
public class Kart {

    @PrimaryKey
    private final UUID id;
    private int number;
    private int engineSize;

    public Kart() {
        id = UUID.randomUUID();
    }

    public UUID getId() {
        return id;
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

        Kart kart = (Kart) o;

        return number == kart.number && engineSize == kart.engineSize && id.equals(kart.id);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + number;
        result = 31 * result + engineSize;
        return result;
    }
}
