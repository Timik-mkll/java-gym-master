package gym;

import java.util.Objects;

public class TimeOfDay implements Comparable<TimeOfDay> {
    //часы (от 0 до 23)

    private int hours;
    //минуты (от 0 до 59)
    private int minutes;

    public TimeOfDay(int hours, int minutes) {
        this.hours = hours;
        this.minutes = minutes;
    }

    public int getHours() {
        return hours;
    }

    public int getMinutes() {
        return minutes;
    }

    @Override
    public int compareTo(TimeOfDay o) {
        int result = Integer.compare(this.hours, o.hours);
        if (result == 0) {
            result = Integer.compare(this.minutes, o.minutes);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        TimeOfDay timeOfDay = (TimeOfDay) o;
        return hours == timeOfDay.hours && minutes == timeOfDay.minutes;
    }

    @Override
    public int hashCode() {
        return Objects.hash(hours, minutes);
    }
}
