import java.util.*;

/**
 * @author Radostin Dimkov 28.05.19
 */
public class bestLecturesSchedule {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int lecturesCount = Integer.parseInt(scanner.nextLine().split(": ")[1]);

        Map<int[], String> lectures = new LinkedHashMap<>();
        for (int i = 0; i < lecturesCount; i++) {
            String[] input = scanner.nextLine().split(": ");

            String lectureName = input[0];
            int[] startFinishTime =
                    Arrays.stream(input[1].split(" - ")).mapToInt(Integer::parseInt).toArray();

            lectures.putIfAbsent(startFinishTime, lectureName);
        }

        Map<int[], String> finalLectures = new LinkedHashMap<>();
        while (lectures.size() > 0) {
            int[] earliestTime = getEarliestLecture(lectures);

            removeOverlaps(lectures, earliestTime);

            finalLectures.putIfAbsent(earliestTime, lectures.get(earliestTime));
            lectures.remove(earliestTime);
        }

        System.out.println(String.format("Lectures (%d):", finalLectures.size()));
        finalLectures.forEach((k,v) -> System.out.println(String.format("%d-%d -> %s",k[0],k[1],v)));
    }

    private static int[] getEarliestLecture(Map<int[], String> lectures) {
        int earlierTime = Integer.MAX_VALUE;
        int[] time = new int[2];

        for (Map.Entry<int[], String> lecture : lectures.entrySet()) {
            int finishTime = lecture.getKey()[1];

            if (finishTime < earlierTime) {
                earlierTime = finishTime;
                time = lecture.getKey();
            }
        }

        return time;

    }

    private static void removeOverlaps(Map<int[], String> lectures, int[] earliestTime) {

        List<int[]> lecturesToRemove = new ArrayList<>();

        for (int[] lecturesTime : lectures.keySet()) {
            int earliestFinishTime = earliestTime[1];
            int currentStartTiem = lecturesTime[0];

            if (currentStartTiem < earliestFinishTime &&
                    earliestTime[0] != lecturesTime[0] &&
                    earliestTime[1] != lecturesTime[1]) {
                lecturesToRemove.add(lecturesTime);
            }
        }

        for (int[] lecturesTime : lecturesToRemove) {
            lectures.remove(lecturesTime);
        }
    }
}