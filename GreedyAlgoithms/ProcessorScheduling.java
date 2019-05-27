    import java.util.*;
    import java.util.stream.Collectors;

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

            List<double[]> sortedTasks = new LinkedList<>(tasks);
            sortedTasks.sort((o1, o2) -> {
                Double o1Value = o1[0];
                Double o2Value = o2[0];

                return o2Value.compareTo(o1Value);
            });

            int step = 1;
            double totalValue = 0;

            double maxSteps = getMaxDeadline(tasks);

            Map<double[],Integer> scheduleIndexes = new TreeMap<>((t1, t2) -> {
                Double t1Value = t1[0];
                Double t1Deadline = t1[1];

                Double t2Value = t2[0];
                Double t2Deadline = t2[1];

                int deadlineComp = t1Deadline.compareTo(t2Deadline);
                if(deadlineComp == 0) {
                    return t2Value.compareTo(t1Value);
                }

                return deadlineComp;
            });

            for (double[] task : sortedTasks) {
                double value = task[0];
                double deadline = task[1];

                if (maxSteps >= step && deadline <= maxSteps) {
                    totalValue += value;
                    scheduleIndexes.put(task, tasks.indexOf(task) + 1);

                }
                step++;
            }

            System.out.print("Optimal schedule: ");

            int index = 0;
            for (Integer value : scheduleIndexes.values()) {
                if (index == 0){
                    System.out.print(value);
                } else {
                    System.out.print(" -> " + value);
                }

                index++;
            }
            System.out.println();
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

