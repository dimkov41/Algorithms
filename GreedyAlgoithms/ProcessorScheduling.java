import java.util.*;

/**
 * @author Radostin Dimkov 27.05.19
 */
public class ProcessorScheduling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int tasksCount = Integer.parseInt(scanner.nextLine().split(": ")[1]);

        List<double[]> tasks = new ArrayList<>();

        for (int i = 0; i < tasksCount; i++) {
            String[] inputTask = scanner.nextLine().split(" - ");

            tasks.add(new double[]{Double.parseDouble(inputTask[0]), Double.parseDouble(inputTask[1])});
        }

        List<double[]> sortedTasks = new ArrayList<>(tasks);
        sortedTasks.sort((o1, o2) -> {
            Double o1Value = o1[0];
            Double o2Value = o2[0];

            return o2Value.compareTo(o1Value);
        });

        int step = 1;
        double totalValue = 0;

        double maxSteps = getMaxDeadline(tasks);

        Deque<Integer> scheduleIndexes = new ArrayDeque<>();
        for (double[] task : sortedTasks) {
            double value =  task[0];
            double deadline = task[1];

            if(maxSteps >= step && deadline <= step){
                totalValue += value;
                scheduleIndexes.add(tasks.indexOf(task));
            }
            step++;
        }

        System.out.println(String.format("Total value: %.0f", totalValue));
    }

    private static double getMaxDeadline(List<double[]> tasks){
        double maxDeadLine = 0;

        for (double[] task : tasks) {
            double deadline = task[1];

            if(deadline > maxDeadLine){
                maxDeadLine = deadline;
            }
        }

        return maxDeadLine;
    }
}
