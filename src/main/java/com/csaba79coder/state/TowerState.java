package com.csaba79coder.state;

import com.csaba79coder.core.AbstractState;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class TowerState extends AbstractState implements Cloneable {

    private int personWeightPerson1;
    private int personWeightPerson2;
    private int personWeightPerson3;
    private int stoneWeight;
    private int basketWeightDifference;
    private Set<Integer> upEntities;
    private Set<Integer> downEntities;
    private int[] basketCapacity1;
    private int[] basketCapacity2;
    private String basketCapacity1Position = "UP";
    private String basketCapacity2Position = "DOWN";

    public TowerState(int personWeightPerson1, int personWeightPerson2, int personWeightPerson3, int stoneWeight, int basketWeightDifference) {
        this.personWeightPerson1 = personWeightPerson1;
        this.personWeightPerson2 = personWeightPerson2;
        this.personWeightPerson3 = personWeightPerson3;
        this.stoneWeight = stoneWeight;
        this.basketWeightDifference = basketWeightDifference;
        this.basketCapacity1 = new int[2];
        this.basketCapacity2 = new int[]{0, 30};
        this.upEntities = new HashSet<>(List.of(this.personWeightPerson1, this.personWeightPerson2, this.personWeightPerson3, 0));
        this.downEntities = new HashSet<>(List.of(this.stoneWeight, 0));
    }

    @Override
    protected boolean isValidState() {
        // Check if at least one entity is up and one entity is down
        boolean hasUp = false;
        boolean hasDown = false;

        for (int weight : upEntities) {
            if (weight >= 0) {
                hasUp = true;
                break;
            }
        }

        for (int weight : downEntities) {
            if (weight >= 0) {
                hasDown = true;
                break;
            }
        }

        // Check if there is at least one item up and one item down, and the maximum is not exceeded
        // All entities are down, it's still a valid state
        return (hasUp && hasDown && upEntities.size() <= 5 && downEntities.size() <= 5) &&
                hasDuplicates(upEntities) && hasDuplicates(downEntities);
    }

    private boolean hasDuplicates(Set<Integer> weights) {
        Set<Integer> seenWeights = new HashSet<>();

        for (int weight : weights) {
            if (weight > 0) {
                if (seenWeights.contains(weight)) {
                    // Duplicate weight found, not a valid state
                    return false;
                }
                seenWeights.add(weight);
            }
        }

        seenWeights.clear(); // Clear the set for the next iteration
        return true;
    }

    @Override
    public boolean isGoalState() {
        return downEntities.contains(personWeightPerson1) &&
                downEntities.contains(personWeightPerson2) &&
                downEntities.contains(personWeightPerson3) &&
                (upEntities.contains(stoneWeight) || (downEntities.contains(stoneWeight)));
    }

    @Override
    public int getOperatorCount() {
        return 117;
    }

    @Override
    public boolean isSuperOperator(int i) {
        return switch (i) {
            case 0 -> op(0, 0, 0, 0);    // Parameters: 0, 0, 0, 0
            case 1 -> op(0, 0, 0, 30);   // Parameters: 0, 0, 0, 30
            case 2 -> op(0, 0, 0, 78);   // Parameters: 0, 0, 0, 78
            case 3 -> op(0, 0, 0, 42);   // Parameters: 0, 0, 0, 42
            case 4 -> op(0, 0, 0, 36);   // Parameters: 0, 0, 0, 36
            case 5 -> op(30, 0, 0, 0);   // Parameters: 30, 0, 0, 0
            case 6 -> op(36, 0, 0, 0);   // Parameters: 36, 0, 0, 0
            case 7 -> op(42, 0, 0, 0);   // Parameters: 42, 0, 0, 0
            case 8 -> op(78, 0, 0, 0);   // Parameters: 78, 0, 0, 0
            case 9 -> op(0, 36, 0, 30);  // Parameters: 0, 36, 0, 30
            case 10 -> op(0, 42, 0, 30); // Parameters: 0, 42, 0, 30
            case 11 -> op(0, 78, 0, 30); // Parameters: 0, 78, 0, 30
            case 12 -> op(0, 30, 0, 36); // Parameters: 0, 30, 0, 36
            case 13 -> op(0, 30, 0, 42); // Parameters: 0, 30, 0, 42
            case 14 -> op(0, 30, 0, 78); // Parameters: 0, 30, 0, 78
            case 15 -> op(0, 30, 0, 36); // Parameters: 0, 30, 0, 36
            case 16 -> op(0, 42, 0, 36); // Parameters: 0, 42, 0, 36
            case 17 -> op(0, 78, 0, 36); // Parameters: 0, 78, 0, 36
            case 18 -> op(0, 36, 0, 30); // Parameters: 0, 36, 0, 30
            case 19 -> op(0, 36, 0, 42); // Parameters: 0, 36, 0, 42
            case 20 -> op(0, 36, 0, 78); // Parameters: 0, 36, 0, 78
            case 21 -> op(0, 30, 0, 42); // Parameters: 0, 30, 0, 42
            case 22 -> op(0, 36, 0, 42); // Parameters: 0, 36, 0, 42
            case 23 -> op(0, 78, 0, 42); // Parameters: 0, 78, 0, 42
            case 24 -> op(0, 42, 0, 30); // Parameters: 0, 42, 0, 30
            case 25 -> op(0, 42, 0, 36); // Parameters: 0, 42, 0, 36
            case 26 -> op(0, 42, 0, 78); // Parameters: 0, 42, 0, 78
            case 27 -> op(0, 30, 0, 78); // Parameters: 0, 30, 0, 78
            case 28 -> op(0, 36, 0, 78); // Parameters: 0, 36, 0, 78
            case 29 -> op(0, 42, 0, 78); // Parameters: 0, 42, 0, 78
            case 30 -> op(0, 78, 0, 30); // Parameters: 0, 78, 0, 30
            case 31 -> op(0, 78, 0, 36); // Parameters: 0, 78, 0, 36
            case 32 -> op(0, 78, 0, 42); // Parameters: 0, 78, 0, 42
            case 33 -> op(0, 0, 30, 36); // Parameters: 0, 0, 30, 36
            case 34 -> op(0, 0, 30, 42); // Parameters: 0, 0, 30, 42
            case 35 -> op(0, 0, 30, 78); // Parameters: 0, 0, 30, 78
            case 36 -> op(0, 0, 36, 30); // Parameters: 0, 0, 36, 30
            case 37 -> op(0, 0, 36, 42); // Parameters: 0, 0, 36, 42
            case 38 -> op(0, 0, 36, 78); // Parameters: 0, 0, 36, 78
            case 39 -> op(0, 0, 42, 30); // Parameters: 0, 0, 42, 30
            case 40 -> op(0, 0, 42, 36); // Parameters: 0, 0, 42, 36
            case 41 -> op(0, 0, 42, 78); // Parameters: 0, 0, 42, 78
            case 42 -> op(0, 0, 78, 30); // Parameters: 0, 0, 78, 30
            case 43 -> op(0, 0, 78, 36); // Parameters: 0, 0, 78, 36
            case 44 -> op(0, 0, 78, 42); // Parameters: 0, 0, 78, 42
            case 45 -> op(30, 36, 0, 0); // Parameters: 30, 36, 0, 0
            case 46 -> op(30, 42, 0, 0); // Parameters: 30, 42, 0, 0
            case 47 -> op(30, 78, 0, 0); // Parameters: 30, 78, 0, 0
            case 48 -> op(36, 30, 0, 0); // Parameters: 36, 30, 0, 0
            case 49 -> op(36, 42, 0, 0); // Parameters: 36, 42, 0, 0
            case 50 -> op(36, 78, 0, 0); // Parameters: 36, 78, 0, 0
            case 51 -> op(42, 30, 0, 0); // Parameters: 42, 30, 0, 0
            case 52 -> op(42, 36, 0, 0); // Parameters: 42, 36, 0, 0
            case 53 -> op(42, 78, 0, 0); // Parameters: 42, 78, 0, 0
            case 54 -> op(78, 30, 0, 0); // Parameters: 78, 30, 0, 0
            case 55 -> op(78, 36, 0, 0); // Parameters: 78, 36, 0, 0
            case 56 -> op(78, 42, 0, 0); // Parameters: 78, 42, 0, 0
            case 57 -> op(42, 0, 30, 36); // Parameters: 42, 0, 30, 36
            case 58 -> op(78, 0, 30, 36); // Parameters: 78, 0, 30, 36
            case 59 -> op(36, 0, 30, 42); // Parameters: 36, 0, 30, 42
            case 60 -> op(78, 0, 30, 42); // Parameters: 78, 0, 30, 42
            case 61 -> op(36, 0, 30, 78); // Parameters: 36, 0, 30, 78
            case 62 -> op(42, 0, 30, 78); // Parameters: 42, 0, 30, 78
            case 63 -> op(30, 36, 42, 0); // Parameters: 30, 36, 42, 0
            case 64 -> op(30, 36, 78, 0); // Parameters: 30, 36, 78, 0
            case 65 -> op(30, 42, 36, 0); // Parameters: 30, 42, 36, 0
            case 66 -> op(30, 42, 78, 0); // Parameters: 30, 42, 78, 0
            case 67 -> op(30, 78, 36, 0); // Parameters: 30, 78, 36, 0
            case 68 -> op(30, 78, 42, 0); // Parameters: 30, 78, 42, 0
            case 69 -> op(42, 0, 36, 30); // Parameters: 42, 0, 36, 30
            case 70 -> op(78, 0, 36, 30); // Parameters: 78, 0, 36, 30
            case 71 -> op(30, 0, 36, 42); // Parameters: 30, 0, 36, 42
            case 72 -> op(78, 0, 36, 42); // Parameters: 78, 0, 36, 42
            case 73 -> op(30, 0, 36, 78); // Parameters: 30, 0, 36, 78
            case 74 -> op(42, 0, 36, 78); // Parameters: 42, 0, 36, 78
            case 75 -> op(36, 30, 42, 0); // Parameters: 36, 30, 42, 0
            case 76 -> op(36, 30, 78, 0); // Parameters: 36, 30, 78, 0
            case 77 -> op(36, 42, 30, 0); // Parameters: 36, 42, 30, 0
            case 78 -> op(36, 42, 78, 0); // Parameters: 36, 42, 78, 0
            case 79 -> op(36, 78, 30, 0); // Parameters: 36, 78, 30, 0
            case 80 -> op(36, 78, 42, 0); // Parameters: 36, 78, 42, 0
            case 81 -> op(78, 0, 42, 30); // Parameters: 78, 0, 42, 30
            case 82 -> op(36, 0, 42, 30); // Parameters: 36, 0, 42, 30
            case 83 -> op(30, 0, 42, 36); // Parameters: 30, 0, 42, 36
            case 84 -> op(78, 0, 42, 36); // Parameters: 78, 0, 42, 36
            case 85 -> op(30, 0, 42, 78); // Parameters: 30, 0, 42, 78
            case 86 -> op(36, 0, 42, 78); // Parameters: 36, 0, 42, 78
            case 87 -> op(36, 0, 78, 30); // Parameters: 36, 0, 78, 30
            case 88 -> op(42, 0, 78, 30); // Parameters: 42, 0, 78, 30
            case 89 -> op(30, 0, 78, 36); // Parameters: 30, 0, 78, 36
            case 90 -> op(42, 0, 78, 36); // Parameters: 42, 0, 78, 36
            case 91 -> op(30, 0, 78, 42); // Parameters: 30, 0, 78, 42
            case 92 -> op(36, 0, 78, 42); // Parameters: 36, 0, 78, 42
            case 93 -> op(42, 78, 30, 36); // Parameters: 42, 78, 30, 36
            case 94 -> op(36, 78, 30, 42); // Parameters: 36, 78, 30, 42
            case 95 -> op(36, 42, 30, 78); // Parameters: 36, 42, 30, 78
            case 96 -> op(42, 78, 36, 30); // Parameters: 42, 78, 36, 30
            case 97 -> op(30, 78, 36, 42); // Parameters: 30, 78, 36, 42
            case 98 -> op(30, 42, 36, 78); // Parameters: 30, 42, 36, 78
            case 99 -> op(36, 78, 42, 30); // Parameters: 36, 78, 42, 30
            case 100 -> op(30, 78, 42, 36); // Parameters: 30, 78, 42, 36
            case 101 -> op(30, 36, 42, 78); // Parameters: 30, 36, 42, 78
            case 102 -> op(36, 42, 78, 30); // Parameters: 36, 42, 78, 30
            case 103 -> op(30, 42, 78, 36); // Parameters: 30, 42, 78, 36
            case 104 -> op(30, 36, 78, 42); // Parameters: 30, 36, 78, 42
            case 105 -> op(30, 36, 42, 78); // Parameters: 30, 36, 42, 78
            case 106 -> op(30, 42, 36, 78); // Parameters: 30, 42, 36, 78
            case 107 -> op(30, 78, 36, 42); // Parameters: 30, 78, 36, 42
            case 108 -> op(36, 30, 42, 78); // Parameters: 36, 30, 42, 78
            case 109 -> op(36, 42, 30, 78); // Parameters: 36, 42, 30, 78
            case 110 -> op(36, 78, 30, 42); // Parameters: 36, 78, 30, 42
            case 111 -> op(42, 30, 36, 78); // Parameters: 42, 30, 36, 78
            case 112 -> op(42, 36, 30, 78); // Parameters: 42, 36, 30, 78
            case 113 -> op(42, 78, 30, 36); // Parameters: 42, 78, 30, 36
            case 114 -> op(78, 30, 36, 42); // Parameters: 78, 30, 36, 42
            case 115 -> op(78, 36, 30, 42); // Parameters: 78, 36, 30, 42
            case 116 -> op(78, 42, 30, 36); // Parameters: 78, 42, 30, 36

            default -> false;
        };
    }

    private boolean op(int weight1, int weight2, int weight3, int weight4) {
        if (!isPreOp(weight1, weight2, weight3, weight4)) {
            return false;
        }

        TowerState backup = (TowerState) clone();

        // TODO maybe check which basket is up, and which is down, and regarding it, set the weights
        // Check if all four weights are not in downEntities or upEntities
        if (basketCapacity1Position.equalsIgnoreCase("UP") &&
                basketCapacity2Position.equalsIgnoreCase("DOWN") &&
                upEntities.contains(weight1) && upEntities.contains(weight2) &&
                downEntities.contains(weight3) && downEntities.contains(weight4)) {
            this.basketCapacity1Position = "DOWN";
            this.basketCapacity2Position = "UP";
            // Set weights in both baskets
            this.basketCapacity1[0] = weight1;
            this.basketCapacity1[1] = weight2;
            this.basketCapacity2[0] = weight3;
            this.basketCapacity2[1] = weight4;

            // Remove weights from up list to down, and opposite
            this.upEntities.remove(weight1);  // Remove by value, not index
            this.upEntities.remove(weight2);
            this.upEntities.add(weight3);
            this.upEntities.add(weight4);
            this.downEntities.remove(weight3);
            this.downEntities.remove(weight4);
            this.downEntities.add(weight1);
            this.downEntities.add(weight2);
        } else if (basketCapacity1Position.equalsIgnoreCase("DOWN") &&
                basketCapacity2Position.equalsIgnoreCase("UP") &&
                downEntities.contains(weight1) && downEntities.contains(weight2) &&
                upEntities.contains(weight3) && upEntities.contains(weight4)) {
            basketCapacity1Position = "UP";
            basketCapacity2Position = "DOWN";
            this.basketCapacity1[0] = weight1;
            this.basketCapacity1[1] = weight2;
            this.basketCapacity2[0] = weight3;
            this.basketCapacity2[1] = weight4;
            this.upEntities.remove(weight1);  // Remove by value, not index
            this.upEntities.remove(weight2);
            this.upEntities.add(weight3);
            this.upEntities.add(weight4);
            this.downEntities.remove(weight3);
            this.downEntities.remove(weight4);
            this.downEntities.add(weight1);
            this.downEntities.add(weight2);
        }

        if (isGoalState()) {
            return true;
        }

        // Set weights in the baskets
        backup.basketCapacity1[0] = weight1;
        backup.basketCapacity1[1] = weight2;
        backup.basketCapacity2[0] = weight3;
        backup.basketCapacity2[1] = weight4;

        // Remove weights from up list to down, and opposite
        backup.upEntities.remove(weight1);  // Remove by value, not index
        backup.upEntities.remove(weight2);
        backup.upEntities.add(weight3);
        backup.upEntities.add(weight4);
        backup.downEntities.remove(weight3);
        backup.downEntities.remove(weight4);
        backup.downEntities.add(weight1);
        backup.downEntities.add(weight2);

        backup.basketCapacity1Position = basketCapacity1Position;
        backup.basketCapacity2Position = basketCapacity2Position;

        System.out.println(backup);

        return false;
    }

    private boolean isPreOp(int weight1, int weight2, int weight3, int weight4) {
        // Check if all weights are zero, in which case it's not a valid state
        if (weight1 == 0 && weight2 == 0 && weight3 == 0 && weight4 == 0) {
            return false;
        }

        // Check if the sum of weight1 and weight2 or weight3 and weight4 is greater than 30
        if (weight1 + weight2 > 30 || weight3 + weight4 > 30) {
            // Check if the absolute difference is smaller or equal to 6
            if (!(Math.abs(weight1 + weight2 - (weight3 + weight4)) <= 6)) {
                // Your logic here if the conditions are met
                return false;
            }
        }
        // Return true if none of the conditions were met
        return true;
    }

    @Override
    public Object clone() {
        TowerState clonedState = (TowerState) super.clone();

        // Deep copy arrays with mutable objects
        clonedState.basketCapacity1 = deepCopy(this.basketCapacity1);
        clonedState.basketCapacity2 = deepCopy(this.basketCapacity2);

        // Deep copy lists with mutable objects
        clonedState.upEntities = deepCopy(this.upEntities);
        clonedState.downEntities = deepCopy(this.downEntities);

        return clonedState;
    }

    private int[] deepCopy(int[] original) {
        // deep copy of array (shallow copy is sufficient for arrays with primitive values)
        return Arrays.copyOf(original, original.length);
    }

    private Set<Integer> deepCopy(Set<Integer> original) {
        // deep copy of Set
        return original.stream()
                .collect(HashSet::new, HashSet::add, HashSet::addAll);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        TowerState otherState = (TowerState) obj;

        return personWeightPerson1 == otherState.personWeightPerson1 &&
                personWeightPerson2 == otherState.personWeightPerson2 &&
                personWeightPerson3 == otherState.personWeightPerson3 &&
                stoneWeight == otherState.stoneWeight &&
                basketCapacity1Position.equalsIgnoreCase(otherState.basketCapacity1Position) &&
                basketCapacity2Position.equalsIgnoreCase(otherState.basketCapacity2Position) &&
                basketWeightDifference == otherState.basketWeightDifference &&
                Arrays.equals(basketCapacity1, otherState.basketCapacity1) &&
                Arrays.equals(basketCapacity2, otherState.basketCapacity2) &&
                Objects.equals(upEntities, otherState.upEntities) &&
                Objects.equals(downEntities, otherState.downEntities);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(super.hashCode(), personWeightPerson1, personWeightPerson2, personWeightPerson3, stoneWeight,
                basketWeightDifference, upEntities, downEntities, basketCapacity1Position, basketCapacity2Position);
        result = 31 * result + Arrays.hashCode(basketCapacity1);
        result = 31 * result + Arrays.hashCode(basketCapacity2);
        return result;
    }

    @Override
    public String toString() {
        return "basketCapacity1=" + Arrays.toString(basketCapacity1) + " position: " + basketCapacity1Position +"\n" +
                "basketCapacity2=" + Arrays.toString(basketCapacity2) + " position: " + basketCapacity2Position+ "\n" +
                "upEntities=" + upEntities + "\n" +
                "downEntities=" + downEntities + "\n";
    }
}
