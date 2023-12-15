package com.csaba79coder.state;

import com.csaba79coder.core.AbstractState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TowerState extends AbstractState {

    int personWeightPerson1;
    int personWeightPerson2;
    int personWeightPerson3;
    int stoneWeight;
    int basketWeightDifference;
    private int personUpCounter;
    private int personDownCounter;
    private String basketLocationLeft;
    private String basketLocationRight;
    private List<Integer> upEntities;
    private List<Integer> downEntities;

    int[] basketCapacity1;
    int[] basketCapacity2;

    public TowerState(int personWeightPerson1, int personWeightPerson2, int personWeightPerson3, int stoneWeight, int basketWeightDifference) {
        this.personWeightPerson1 = personWeightPerson1;
        this.personWeightPerson2 = personWeightPerson2;
        this.personWeightPerson3 = personWeightPerson3;
        this.stoneWeight = stoneWeight;
        this.basketWeightDifference = basketWeightDifference;
        this.personUpCounter = 3;
        this.personDownCounter = 0;
        this.basketLocationLeft = "UP";
        this.basketLocationRight = "DOWN";
        this.basketCapacity1 = new int[2];
        this.basketCapacity2 = new int[]{0, 30};
        this.upEntities = new ArrayList<>(List.of(this.personWeightPerson1, this.personWeightPerson2, this.personWeightPerson3, 0));
        this.downEntities = new ArrayList<>(List.of(this.stoneWeight, 0, 0, 0));
    }


    /*@Override
    protected boolean isValidState() {
        return (personUpCounter > personDownCounter || personUpCounter == 0 && personDownCounter == 3) &&
                (personDownCounter > personUpCounter || personDownCounter == 0 && personUpCounter == 3);
    }*/

    // isState
    @Override
    protected boolean isValidState() {
        return (personUpCounter <= 3 && personDownCounter <= 3 &&
                (personUpCounter == 3 || personDownCounter == 3 || personUpCounter == 0 || personDownCounter == 0)) &&
                (personUpCounter > personDownCounter || personDownCounter > personUpCounter);
    }

    @Override
    public boolean isGoalState() {
        return (personUpCounter == 0 && personDownCounter == 3);
    }

    // Regarding the task, the basket is not going by gravity, it is moved by a motor / manually!!!
    // it means no matter what weight is in the basket, it can be moved up and down
    @Override
    public int getOperatorCount() {
        // it is not working as gravitation, the rope is moved manually!!!
        // elements position in basket does not matter!
        // means -> case both side empty
        // one side 1 element, other is empty (all combination) -> 1 operator
        // one side 2 element, other is empty (all combination) -> 10 operator
        return 58;
    }

    // TODO Check if all cases are here! (using excel!)
    @Override
    public boolean isSuperOperator(int i) {
        return switch (i) {
            // both sides zero
            case 0 -> op(0, 0, 0, 0); // Parameters: 0, 0, 0, 0

            // one side empty, other one (all combinations)
            case 1 -> op(0, 0, 0, 30); // Parameters: 0, 0, 0, 30
            case 2 -> op(0, 0, 0, 78); // Parameters: 0, 0, 0, 78
            case 3 -> op(0, 0, 0, 42); // Parameters: 0, 0, 0, 42
            case 4 -> op(0, 0, 0, 36); // Parameters: 0, 0, 0, 36

            // one side empty, other two (all combinations)
            case 5 -> op(0, 0, 78, 30); // Parameters: 0, 0, 78, 30
            case 6 -> op(0, 0, 78, 42); // Parameters: 0, 0, 78, 42
            case 7 -> op(0, 0, 78, 36); // Parameters: 0, 0, 78, 36
            case 8 -> op(0, 0, 42, 30); // Parameters: 0, 0, 42, 30
            case 9 -> op(0, 0, 42, 36); // Parameters: 0, 0, 42, 36
            case 10 -> op(0, 0, 36, 30); // Parameters: 0, 0, 36, 30

            // other side empty, other 1 (all combinations)
            case 11 -> op(30, 0, 0, 0); // Parameters: 30, 0, 0, 0
            case 12 -> op(78, 0, 0, 0); // Parameters: 78, 0, 0, 0
            case 13 -> op(42, 0, 0, 0); // Parameters: 42, 0, 0, 0
            case 14 -> op(36, 0, 0, 0); // Parameters: 36, 0, 0, 0

            // TODO check all cases!!! maybe not included!!!
            // case 59 -> op(36, 0, 0, 30); // Parameters: 0, 0, 0, 0


            // other side empty, other two (all combinations)
            case 15 -> op(78, 30, 0, 0); // Parameters: 78, 30, 0, 0
            case 16 -> op(78, 42, 0, 0); // Parameters: 78, 42, 0, 0
            case 17 -> op(78, 36, 0, 0); // Parameters: 78, 36, 0, 0
            case 18 -> op(42, 30, 0, 0); // Parameters: 42, 30, 0, 0
            case 19 -> op(42, 36, 0, 0); // Parameters: 42, 36, 0, 0
            case 20 -> op(36, 30, 0, 0); // Parameters: 36, 30, 0, 0

            // one side one element, other two (all combinations)
            case 21 -> op(0, 78, 42, 36); // Parameters: 0, 78, 42, 36
            case 22 -> op(0, 78, 36, 30); // Parameters: 0, 78, 36, 30
            case 23 -> op(0, 42, 78, 36); // Parameters: 0, 42, 78, 36
            case 24 -> op(0, 42, 36, 30); // Parameters: 0, 42, 36, 30
            case 25 -> op(0, 36, 78, 42); // Parameters: 0, 36, 78, 42
            case 26 -> op(0, 36, 42, 30); // Parameters: 0, 36, 42, 30
            case 27 -> op(0, 30, 78, 42); // Parameters: 0, 30, 78, 42
            case 28 -> op(0, 30, 42, 36); // Parameters: 0, 30, 42, 36

            // other side one element, other two (all combinations)
            case 29 -> op(42, 36, 0, 78); // Parameters: 42, 36, 0, 78
            case 30 -> op(36, 30, 0, 78); // Parameters: 36, 30, 0, 78
            case 31 -> op(78, 36, 0, 42); // Parameters: 78, 36, 0, 42
            case 32 -> op(36, 30, 0, 42); // Parameters: 36, 30, 0, 42
            case 33 -> op(78, 42, 0, 36); // Parameters: 78, 42, 0, 36
            case 34 -> op(42, 30, 0, 36); // Parameters: 42, 30, 0, 36
            case 35 -> op(78, 42, 0, 30); // Parameters: 78, 42, 0, 30
            case 36 -> op(42, 36, 0, 30); // Parameters: 42, 36, 0, 30

            // both sides two elements (all combinations)
            case 37 -> op(78, 42, 36, 30); // Parameters: 78, 42, 36, 30
            case 38 -> op(78, 30, 36, 42); // Parameters: 78, 30, 36, 42
            case 39 -> op(78, 36, 30, 42); // Parameters: 78, 36, 30, 42
            case 40 -> op(42, 36, 30, 78); // Parameters: 42, 36, 30, 78
            case 41 -> op(42, 30, 36, 78); // Parameters: 42, 30, 36, 78
            case 42 -> op(36, 30, 42, 78); // Parameters: 36, 30, 42, 78
            case 50 -> op(30, 78, 42, 36); // Parameters: 30, 78, 42, 36
            case 51 -> op(36, 30, 78, 42); // Parameters: 36, 30, 78, 42
            case 52 -> op(36, 42, 78, 30); // Parameters: 36, 42, 78, 30
            case 53 -> op(30, 42, 78, 36); // Parameters: 30, 42, 78, 36
            case 54 -> op(30, 78, 42, 36); // Parameters: 30, 78, 42, 36
            case 55 -> op(36, 78, 42, 30); // Parameters: 36, 78, 42, 30
            case 56 -> op(42, 78, 36, 30); // Parameters: 42, 78, 36, 30
            case 57 -> op(42, 36, 30, 78); // Parameters: 42, 36, 30, 78
            default -> false;
        };
    }

    // TODO check the operator logic
    private boolean op(int weight1, int weight2, int weight3, int weight4) {
        boolean isPreOp = checkPreCondition(weight1, weight2, weight3, weight4);
        if (!isPreOp) {
            return false;
        }

        // Create deep clones of the lists
        List<Integer> upEntitiesBackup = deepCopy(upEntities);
        List<Integer> downEntitiesBackup = deepCopy(downEntities);

        int[] basketCapacity1Backup = deepCopy(basketCapacity1);
        int[] basketCapacity2Backup = deepCopy(basketCapacity2);

        // Create deep clones of the array


        TowerState backup = (TowerState) this.clone();

        if (basketLocationLeft.equals("UP") && basketLocationRight.equals("DOWN")) {
            if (upEntities.contains(weight1)) {
                personUpCounter--;
                personDownCounter++;
                upEntities.remove(weight1);
                downEntities.add(weight1);
            }
            if (upEntities.contains(weight2)) {
                personUpCounter--;
                personDownCounter++;
                upEntities.remove(weight2);
                downEntities.add(weight2);
            }
            if (downEntities.contains(weight3)) {
                personDownCounter--;
                personUpCounter++;
                downEntities.remove(weight3);
                upEntities.add(weight3);
            }
            if (downEntities.contains(weight4)) {
                personDownCounter--;
                personUpCounter++;
                downEntities.remove(weight4);
                upEntities.add(weight4);
            }
            basketLocationLeft = "DOWN";
            basketLocationRight = "UP";
        } else {
            if (downEntities.contains(weight1)) {
                personDownCounter--;
                personUpCounter++;
                downEntities.remove(weight1);
                upEntities.add(weight1);
            }
            if (downEntities.contains(weight2)) {
                personDownCounter--;
                personUpCounter++;
                downEntities.remove(weight2);
                upEntities.add(weight2);
            }
            if (upEntities.contains(weight3)) {
                personUpCounter--;
                personDownCounter++;
                upEntities.remove(weight3);
                downEntities.add(weight3);
            }
            if (upEntities.contains(weight4)) {
                personUpCounter--;
                personDownCounter++;
                upEntities.remove(weight4);
                downEntities.add(weight4);
            }
            basketLocationLeft = "UP";
            basketLocationRight = "DOWN";
        }
        if (isValidState()) {
            return true;
        }

        personUpCounter = backup.personUpCounter;
        personDownCounter = backup.personDownCounter;
        basketLocationLeft = backup.basketLocationLeft;
        basketLocationRight = backup.basketLocationRight;
        upEntities = upEntitiesBackup;
        downEntities = downEntitiesBackup;
        basketCapacity1 = basketCapacity1Backup;
        basketCapacity2 = basketCapacity2Backup;
        return false;
    }

    // TODO check the login in preOp!
    // preOperator
    private boolean checkPreCondition(int weight1, int weight2, int weight3, int weight4) {
        // TODO checkPersonInBasket() is not used!
        boolean fillBasketUp = fillTheBasket(weight1, weight2, upEntities, basketCapacity1);
        boolean fillBasketDown = fillTheBasket(weight3, weight4, downEntities, basketCapacity2);

        // Check if either basket couldn't be filled
        if (!fillBasketUp || !fillBasketDown) {
            return false;
        }

        int sumBasket1 = Arrays.stream(basketCapacity1).sum();
        int sumBasket2 = Arrays.stream(basketCapacity2).sum();
        // Check sum of weights and individual weights in both baskets
        if ((0 <= sumBasket1 && sumBasket1 <= 120 && Arrays.stream(basketCapacity1).allMatch(weight -> 0 <= weight && weight <= 120)) &&
                (0 <= sumBasket2 && sumBasket2 <= 120 && Arrays.stream(basketCapacity2).allMatch(weight -> 0 <= weight && weight <= 120))) {
            return false;
        }

        /*if (basketCapacity1.length > 2 || basketCapacity2.length > 2) {
            return false;
        }*/

        return !(personUpCounter >= 0 && personUpCounter <= 3 && personDownCounter >= 0 && personDownCounter <= 3);
    }

    private boolean fillTheBasket(int weight1, int weight2, List<Integer> entities, int[] basket) {
        boolean foundWeight1 = entities.contains(weight1);
        boolean foundWeight2 = entities.contains(weight2);

        if (foundWeight1 && foundWeight2) {
            basket[0] = weight1;
            basket[1] = weight2;
            return true;  // Both weights found, basket filled successfully
        } else {
            return false;  // Either or both weights not found, basket not filled
        }
    }

    // Helper method to deep copy an array of mutable objects
    private int[] deepCopy(int[] original) {
        int[] copy = Arrays.copyOf(original, original.length);

        for (int i = 0; i < original.length; i++) {
            // Assuming Integer is used, replace with the actual class of mutable objects
            copy[i] = Integer.valueOf(original[i]);
            // Perform additional deep copy logic if necessary for mutable objects in the array
        }
        return copy;
    }

    // Helper method to deep copy a list of mutable objects
    private List<Integer> deepCopy(List<Integer> original) {
        List<Integer> copy = new ArrayList<>(original.size());
        for (Integer element : original) {
            // Assuming Integer is used, replace with the actual class of mutable objects
            copy.add(Integer.valueOf(element));
            // Perform additional deep copy logic if necessary for mutable objects in the list
        }
        return copy;
    }

    // Checks whether there is an element in any of the baskets that is greater than thirty
    // in case yes, that means a person is in the basket!
    private boolean checkPersonInBasket(int[] basketCapacity1, int[] basketCapacity2) {
        // Check both baskets for an element greater than thirty
        return Arrays.stream(basketCapacity1).anyMatch(value -> value > 30) ||
                Arrays.stream(basketCapacity2).anyMatch(value -> value > 30);
    }

    // TODO use equals method to avoid infinite loop (in child must be implemented!)

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

    public int getPersonWeightPerson1() {
        return personWeightPerson1;
    }

    public void setPersonWeightPerson1(int personWeightPerson1) {
        this.personWeightPerson1 = personWeightPerson1;
    }

    public int getPersonWeightPerson2() {
        return personWeightPerson2;
    }

    public void setPersonWeightPerson2(int personWeightPerson2) {
        this.personWeightPerson2 = personWeightPerson2;
    }

    public int getPersonWeightPerson3() {
        return personWeightPerson3;
    }

    public void setPersonWeightPerson3(int personWeightPerson3) {
        this.personWeightPerson3 = personWeightPerson3;
    }

    public int getStoneWeight() {
        return stoneWeight;
    }

    public void setStoneWeight(int stoneWeight) {
        this.stoneWeight = stoneWeight;
    }

    public int getBasketWeightDifference() {
        return basketWeightDifference;
    }

    public void setBasketWeightDifference(int basketWeightDifference) {
        this.basketWeightDifference = basketWeightDifference;
    }

    public int getPersonUpCounter() {
        return personUpCounter;
    }

    public void setPersonUpCounter(int personUpCounter) {
        this.personUpCounter = personUpCounter;
    }

    public int getPersonDownCounter() {
        return personDownCounter;
    }

    public void setPersonDownCounter(int personDownCounter) {
        this.personDownCounter = personDownCounter;
    }

    public String getBasketLocationLeft() {
        return basketLocationLeft;
    }

    public void setBasketLocationLeft(String basketLocationLeft) {
        this.basketLocationLeft = basketLocationLeft;
    }

    public String getBasketLocationRight() {
        return basketLocationRight;
    }

    public void setBasketLocationRight(String basketLocationRight) {
        this.basketLocationRight = basketLocationRight;
    }

    public List<Integer> getUpEntities() {
        return upEntities;
    }

    public void setUpEntities(List<Integer> upEntities) {
        this.upEntities = upEntities;
    }

    public List<Integer> getDownEntities() {
        return downEntities;
    }

    public void setDownEntities(List<Integer> downEntities) {
        this.downEntities = downEntities;
    }

    @Override
    public String toString() {
        return "TowerState{" +
                "personWeightPerson1=" + personWeightPerson1 +
                ", personWeightPerson2=" + personWeightPerson2 +
                ", personWeightPerson3=" + personWeightPerson3 +
                ", stoneWeight=" + stoneWeight +
                ", basketWeightDifference=" + basketWeightDifference +
                ", personUpCounter=" + personUpCounter +
                ", personDownCounter=" + personDownCounter +
                ", basketLocationLeft='" + basketLocationLeft + '\'' +
                ", basketLocationRight='" + basketLocationRight + '\'' +
                ", upEntities=" + upEntities +
                ", downEntities=" + downEntities +
                ", basketCapacity1=" + Arrays.toString(basketCapacity1) +
                ", basketCapacity2=" + Arrays.toString(basketCapacity2) +
                '}';
    }
}
