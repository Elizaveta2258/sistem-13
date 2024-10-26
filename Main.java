import java.util.*;

class ConcertSeats {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        Map<Integer, Set<Integer>> occupiedSeats = new HashMap<>();

        // Считываем занятые места
        for (int i = 0; i < N; i++) {
            int row = scanner.nextInt();
            int seat = scanner.nextInt();
            occupiedSeats.putIfAbsent(row, new HashSet<>());
            occupiedSeats.get(row).add(seat);
        }

        int maxRow = -1;
        int minSeat = Integer.MAX_VALUE;

        // Проверяем каждый ряд на наличие подходящих мест
        for (Map.Entry<Integer, Set<Integer>> entry : occupiedSeats.entrySet()) {
            int row = entry.getKey();
            Set<Integer> seats = entry.getValue();
            List<Integer> sortedSeats = new ArrayList<>(seats);
            Collections.sort(sortedSeats);

            for (int i = 1; i < sortedSeats.size() - 1; i++) {
                int currentSeat = sortedSeats.get(i);
                int leftSeat = sortedSeats.get(i - 1);
                int rightSeat = sortedSeats.get(i + 1);

                // Проверяем условие: текущее место между двумя занятыми
                if (currentSeat == leftSeat + 1 && currentSeat == rightSeat - 1) {
                    if (row > maxRow || (row == maxRow && currentSeat < minSeat)) {
                        maxRow = row;
                        minSeat = currentSeat;
                    }
                }
            }
        }

        // Выводим результат
        System.out.println(maxRow + " " + minSeat);
    }
}
