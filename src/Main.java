import java.util.ArrayList;
import java.util.List;

public class Main {

    // Method to calculate distance between two locations using Haversine formula
    public static double calculateDistance(Location loc1, Location loc2) {
        double lat1 = Math.toRadians(loc1.latitude);
        double lon1 = Math.toRadians(loc1.longitude);
        double lat2 = Math.toRadians(loc2.latitude);
        double lon2 = Math.toRadians(loc2.longitude);

        double dlon = lon2 - lon1;
        double dlat = lat2 - lat1;

        double a = Math.pow(Math.sin(dlat / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2)
                * Math.pow(Math.sin(dlon / 2), 2);

        double c = 2 * Math.asin(Math.sqrt(a));

        // Radius of earth in kilometers
        double radius = 6371;

        return radius * c;
    }

    // Method to find the shortest delivery time
    public static double shortestDeliveryTime(Location aman, List<Order> orders) {
        double shortestTime = Double.MAX_VALUE;
        List<Order> bestPath = new ArrayList<>();

        // Generate permutations of orders
        List<List<Order>> permutations = new ArrayList<>();
        generatePermutations(orders, new ArrayList<>(), permutations);

        // Calculate time for each permutation
        for (List<Order> permutation : permutations) {
            double time = calculateTotalTime(aman, permutation);
            if (time < shortestTime) {
                shortestTime = time;
                bestPath = permutation;
            }
        }

        // Print the best path
        System.out.println("Shortest Delivery Time Path:");
        for (Order order : bestPath) {
            System.out.println("Deliver order from " + order.restaurant.name + " to " + order.consumer.name);
        }

        return shortestTime;
    }

    // Method to check if the order can be added to the current path
    private static boolean isValid(List<Order> currentPath, Order newOrder) {
        if (currentPath.isEmpty()) {
            return true;
        }

        Order lastOrder = currentPath.get(currentPath.size() - 1);

        // Ensure that C1 is not visited before R1 and C2 is not visited before R2
        if ((lastOrder.restaurant == newOrder.consumer && newOrder.restaurant != lastOrder.restaurant) ||
                (lastOrder.restaurant == newOrder.restaurant && newOrder.consumer != lastOrder.consumer)) {
            return false;
        }

        return true;
    }

    // Method to generate permutations of orders
    private static void generatePermutations(List<Order> orders, List<Order> currentPath, List<List<Order>> permutations) {
        if (orders.isEmpty()) {
            permutations.add(new ArrayList<>(currentPath));
            return;
        }

        for (int i = 0; i < orders.size(); i++) {
            Order order = orders.get(i);
            if (isValid(currentPath, order)) {
                List<Order> newCurrentPath = new ArrayList<>(currentPath);
                newCurrentPath.add(order);

                List<Order> newRemainingOrders = new ArrayList<>(orders);
                newRemainingOrders.remove(i);

                generatePermutations(newRemainingOrders, newCurrentPath, permutations);
            }
        }
    }

    // Method to calculate total time for a given path
    private static double calculateTotalTime(Location aman, List<Order> path) {
        double time = 0;

        Location currentLocation = aman;

        for (Order order : path) {
            double travelTime = calculateDistance(currentLocation, order.restaurant) / 20 * 60; // Convert hours to minutes
            time += travelTime + order.preparationTime;

            currentLocation = order.consumer;
        }

        return time;
    }

    public static void printTimeMatrix(Location[] locations) {
        int n = locations.length;
        double[][] timeMatrix = new double[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                timeMatrix[i][j] = calculateDistance(locations[i], locations[j])*3;
            }
        }

        System.out.println("Time Matrix:");
        for (double[] row : timeMatrix) {
            for (double time : row) {
                System.out.printf("%.2f\t", Math.ceil(time));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        // Adding locations
        Location aman = new Location("Aman", 12.9279, 77.6271); // Aman's location in Koramangala
        Location r1 = new Location("R1", 12.9346, 77.6186); // Restaurant 1
        Location r2 = new Location("R2", 12.9352, 77.6256); // Restaurant 2
        Location c1 = new Location("C1", 12.9365, 77.6154); // Consumer 1
        Location c2 = new Location("C2", 12.9267, 77.6190); // Consumer 2

//        Location[] locations = {aman, r1, r2, c1, c2};
//        printTimeMatrix(locations);

        // Adding orders
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(r1, c1, 22)); // Order 1 from R1 to C1 with a preparation time of 20 minutes
        orders.add(new Order(r2, c2, 25)); // Order 2 from R2 to C2 with a preparation time of 25 minutes

        // Finding the shortest delivery time
        double shortestTime = shortestDeliveryTime(aman, orders);

        // Printing the total time
        System.out.println("Shortest Delivery Time: " + shortestTime + " minutes");
    }
}
