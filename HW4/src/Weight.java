/**
 * Created by AlinaCh on 17.04.2017.
 * Weight of the road, containing distance, hours and cost
 */
public class Weight implements Comparable {

    public Double distance;
    public Double hours;
    public Double cost;

    /**
     * initializing with parameters
     * @param distance of the road
     * @param hours to get from one town to another
     * @param cost of the transaction
     */
    Weight(double distance, double hours, double cost) {
        this.distance = distance;
        this.hours = hours;
        this.cost = cost;
    }

    /**
     * default initializing
     */
    Weight() {
        this.distance = 0.0;
        this.hours = 0.0;
        this.cost = 0.0;
    }

    /**
     * counts the sum of two weights
     * @param other weight
     * @return the sum
     */
    public Weight plus(Weight other) {
        Weight res = new Weight(this.distance + other.distance,
                this.hours + other.hours, this.cost + other.cost);
        return res;
    }

    /**
     * compares weights by distance
     * @param other Weight
     * @return 0 if they are equal, -1 if the current weight's distance
     * is less than other's distance, and 1 if the current weight's distance
     * is bigger that other's distance
     */
    public int distanceCompareTo(Weight other) {
        Double d = this.distance - other.distance;
        return this.compareTo(d);
    }

    /**
     * compares weights by hours
     * @param other Weight
     * @return 0 if they are equal, -1 if the current weight's hours
     * is less than other's hours, and 1 if the current weight's hours
     * is bigger that other's hours
     */
    public int hoursCompareTo(Weight other) {
        Double d = this.hours - other.hours;
        return this.compareTo(d);
    }

    /**
     * compares weights by cost
     * @param other Weight
     * @return 0 if they are equal, -1 if the current weight's cost
     * is less than other's cost, and 1 if the current weight's cost
     * is bigger that other's cost
     */
    public int costCompareTo(Weight other) {
        Double d = this.cost - other.cost;
        return this.compareTo(d);
    }

    /**
     * comparator method
     * @param o
     * @return 0 if doubles are equal, -1 if double is less than 0,
     * 1 if double is bigger than 0
     */
    @Override
    public int compareTo(Object o) {
        Double d = (Double) o;
        if (d < 0.0)
            return -1;
        else if (d == 0.0)
            return 0;
        else
            return 1;
    }

    /**
     * @return String value of weight
     */
    @Override
    public String toString() {
        String res = this.distance.toString() + " " +
                this.hours.toString() + " " + this.cost.toString();
        return res;
    }
}
