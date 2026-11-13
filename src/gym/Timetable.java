package gym;

import java.util.*;

public class Timetable {

    private final Map<DayOfWeek, TreeMap<TimeOfDay, TrainingSession>> timetable = new TreeMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        DayOfWeek day = trainingSession.getDayOfWeek();
        TreeMap<TimeOfDay, TrainingSession> daySchedule = timetable.getOrDefault(day, new TreeMap<>());
        daySchedule.put(trainingSession.getTimeOfDay(), trainingSession);
        timetable.put(day, daySchedule);
        //сохраняем занятие в расписании
    }

    public TreeMap<TimeOfDay, TrainingSession> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        if (timetable.get(dayOfWeek) == null) {
            return null;
        }
        return timetable.get(dayOfWeek);
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
    }

    public TrainingSession getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
        TreeMap<TimeOfDay, TrainingSession> trainingSession = timetable.get(dayOfWeek);

        if (trainingSession == null) {
            return null;
        }
        return trainingSession.get(timeOfDay);
    }

    public List<CounterOfTrainings> getCountByCoaches() {
        Map<Coach, Integer> coachesCounter = new HashMap<>();

        for (TreeMap<TimeOfDay, TrainingSession> o : timetable.values()) {
            for (TrainingSession k : o.values()) {
                Coach coach = k.getCoach();
                int currentCount = coachesCounter.getOrDefault(coach, 0);
                coachesCounter.put(coach, currentCount + 1);
            }
        }

        List<CounterOfTrainings> result = new ArrayList<>();
        for (Map.Entry<Coach, Integer> entry : coachesCounter.entrySet()) {
            CounterOfTrainings counter = new CounterOfTrainings(entry.getKey(), entry.getValue());
            result.add(counter);
        }
        Collections.sort(result);
        return result;
    }

}
