package gym;

import java.util.*;

public class Timetable {

    private final Map<DayOfWeek, TreeMap<TimeOfDay, List<TrainingSession>>> timetable = new HashMap<>();

    public void addNewTrainingSession(TrainingSession trainingSession) {
        DayOfWeek day = trainingSession.getDayOfWeek();
        TimeOfDay time = trainingSession.getTimeOfDay();

        TreeMap<TimeOfDay, List<TrainingSession>> daySchedule = timetable.get(day);
        if (daySchedule == null) {
            daySchedule = new TreeMap<>();
            timetable.put(day, daySchedule);
        }
        List<TrainingSession> sessions = daySchedule.get(time);
        if (sessions == null) {
            sessions = new ArrayList<>();
            daySchedule.put(time, sessions);
        }
        sessions.add(trainingSession);
        //сохраняем занятие в расписании
    }

    public TreeMap<TimeOfDay, List<TrainingSession>> getTrainingSessionsForDay(DayOfWeek dayOfWeek) {
        if (timetable.get(dayOfWeek) == null) {
            return null;
        }
        return timetable.get(dayOfWeek);
        //как реализовать, тоже непонятно, но сложность должна быть О(1)
    }

    public List<TrainingSession> getTrainingSessionsForDayAndTime(DayOfWeek dayOfWeek, TimeOfDay timeOfDay) {
            TreeMap<TimeOfDay, List<TrainingSession>> trainingSession = timetable.get(dayOfWeek);
            if (trainingSession == null) {
                return null;
            }
            return trainingSession.get(timeOfDay);
    }

        public List<CounterOfTrainings> getCountByCoaches () {
            Map<Coach, Integer> coachesCounter = new HashMap<>();

            for (TreeMap<TimeOfDay, List<TrainingSession>> o : timetable.values()) {
                for (List<TrainingSession> k : o.values()) {
                    for (TrainingSession l : k) {

                        Coach coach = l.getCoach();
                        int currentCount = coachesCounter.getOrDefault(coach, 0);
                        coachesCounter.put(coach, currentCount + 1);
                    }
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