class Order {
    Location restaurant;
    Location consumer;
    int preparationTime; // Time taken to prepare the order in minutes

    public Order(Location restaurant, Location consumer, int preparationTime) {
        this.restaurant = restaurant;
        this.consumer = consumer;
        this.preparationTime = preparationTime;
    }
}
