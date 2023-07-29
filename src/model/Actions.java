package model;
import java.time.LocalDateTime;

/**
 * Базовый интерфейс для событий
*/
public interface Actions {
    public void setPeriodStart(LocalDateTime dt);
    public void setPeriodEnd(LocalDateTime dt);
    public void applyAction();
}
