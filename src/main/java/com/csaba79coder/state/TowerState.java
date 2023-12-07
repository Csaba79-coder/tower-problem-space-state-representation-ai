package com.csaba79coder.state;

import com.csaba79coder.model.Element;
import com.csaba79coder.core.AbstractState;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TowerState extends AbstractState {

    private static int personUpCounter = 3;
    private static int personDownCounter = 0;

    private Element person1;
    private Element person2;
    private Element person3;
    private Element stone;
    private Element basket1;
    private Element basket2;
    private List<Element> entitiesUp;
    private List<Element> entitiesDown;
    private int[] basketCapacity1;
    private int[] basketCapacity2;

    public TowerState() {
        this.person1 = new Element("person1", 78, "U");
        this.person2 = new Element("person2", 42, "U");
        this.person3 = new Element("person3", 36, "U");
        this.stone = new Element("stone", 30, "D");
        this.basket1 = new Element("basket1", 0, "U");
        this.basket2 = new Element("basket2", stone.getWeight(), "D");
        this.entitiesUp = new ArrayList<>(Arrays.asList(person1, person2, person3));
        this.entitiesDown = new ArrayList<>(Arrays.asList(stone));
        this.basketCapacity1 = new int[2];
        this.basketCapacity2 = new int[]{stone.getWeight(), 0};
        personUpCounter = getPersonUpCounter();
        personDownCounter = getPersonDownCounter();
    }

    @Override
    protected boolean isState() {
        return (personUpCounter > personDownCounter || personUpCounter == 0 && personDownCounter == 3) &&
                (personDownCounter > personUpCounter || personDownCounter == 0 && personUpCounter == 3);
    }

    @Override
    public boolean isGoalState() {
        return false;
    }

    @Override
    public int getOperatorCount() {
        // TODO update the number of operators regarding the variant of kilos ...
        // if basket up 1 stone goes down
        // if basket up 1 person goes down
        // if basket up 1 stone & 1 person goes up
        // if basket up 2 person goes down
        // if basket down empty goes up (only with a stone on the other side!)
        // if basket down 1 stone goes up
        // if basket down 1 person goes up
        // if basket down 1 stone & 1 person goes down
        // if basket down 2 person goes up
        return 9;
    }

    @Override public boolean isSuperOperator(int i) {
        return switch (i) {
            // if basket up 1 stone goes down
            // TODO make personCounters not static
            // TODO make instead of capacity simply the kilos as param (4 params first 2 represents one basket,
            //  second 2 represents the other basket)
            // TODO remove the Element model (as from size you can tell if it is a person or a stone)
            // TODO define basket position as String "U" or "D" (call the constructor with 4 params
            //  or maybe five (if you want to give the chance to modify the weight difference!)
            case 0 -> op(personUpCounter, personDownCounter, basketCapacity1, basketCapacity2);
            // if basket up 1 person goes down
            case 1 -> op(personUpCounter, personDownCounter, basketCapacity1, basketCapacity2);
            // if basket up 1 stone & 1 person goes up
            case 2 -> op(personUpCounter, personDownCounter, basketCapacity1, basketCapacity2);
            // if basket up 2 person goes down
            case 3 -> op(personUpCounter, personDownCounter, basketCapacity1, basketCapacity2);
            // if basket down empty goes up (only with a stone on the other side!)
            case 4 -> op(personUpCounter, personDownCounter, basketCapacity1, basketCapacity2);
            // if basket down 1 stone goes up
            case 5 -> op(personUpCounter, personDownCounter, basketCapacity1, basketCapacity2);
            // if basket down 1 person goes up
            case 6 -> op(personUpCounter, personDownCounter, basketCapacity1, basketCapacity2);
            // if basket down 1 stone & 1 person goes down
            case 7 -> op(personUpCounter, personDownCounter, basketCapacity1, basketCapacity2);
            // if basket down 2 person goes up
            case 8 -> op(personUpCounter, personDownCounter, basketCapacity1, basketCapacity2);
            default -> false;
        };
    }

    private boolean op(int personUp, int personDown, int[] basketCapacity1, int[] basketCapacity2) {
        if (!preOp(personUp, personDown, basketCapacity1, basketCapacity2)) {
            return false;
        }

        TowerState backup = (TowerState) clone();

        if (basket1.getPosition().equals("U") && basket2.getPosition().equals("D")) {
            basket1.setPosition("D");
            basket2.setPosition("U");
            // TODO think this logic over!
            entitiesUp.remove(basket1);
            entitiesDown.add(basket1);
            entitiesDown.remove(basket2);
            entitiesUp.add(basket2);
        } else {
            // TODO think this logic over!
            basket1.setPosition("U");
            basket2.setPosition("D");
            entitiesUp.remove(basket2);
            entitiesDown.add(basket2);
            entitiesDown.remove(basket1);
            entitiesUp.add(basket1);
        }

        if (isState()) {
            return true;
        }


        personDownCounter = backup.personDownCounter;


        return false;
    }

    private boolean preOp(int personsUp, int personsDown, int[] basketCapacity1, int[] basketCapacity2) {
        int sumBasket1 = Arrays.stream(basketCapacity1).sum();
        int sumBasket2 = Arrays.stream(basketCapacity2).sum();
        // Check the weight difference between the baskets if person in the basket!
        if (checkPersonInBasket(basketCapacity1, basketCapacity2)) {
            // Check if the weight difference is not greater than 6 when either basket contains an item over 30
            if (Math.abs(sumBasket1 - sumBasket2) > 6) {
                return false;
            }
        }
        if ((sumBasket1 < 0 || sumBasket1 > 78) ||
                (sumBasket2 < 0 || sumBasket2 > 78) ||
                (sumBasket1 == sumBasket2)) {
            return false;
        }

        if (basketCapacity1.length > 2 || basketCapacity2.length > 2) {
            return false;
        }

        if ((basketCapacity1[0] + basketCapacity1[1] < 0 || basketCapacity1[0] + basketCapacity1[1] > 78) ||
                (basketCapacity2[0] + basketCapacity2[1] < 0 || basketCapacity2[0] + basketCapacity2[1] > 78)) {
            return false;
        }

        return personsUp >= 0 && personsUp <= 3 && personsDown >= 0 && personsDown <= 3;
    }


    // Ellenőrzi, hogy bármelyik kosárban van-e elem, ami nagyobb, mint harminc
    private boolean checkPersonInBasket(int[] basketCapacity1, int[] basketCapacity2) {
        // Check first basket
        for (int value : basketCapacity1) {
            if (value > 30) {
                return true; // There is an element that is greater than thirty
            }
        }

        // Check second basket
        for (int value : basketCapacity2) {
            if (value > 30) {
                return true; // There is an element that is greater than thirty
            }
        }

        // If non of the basket contains an element that is greater than thirty
        return false;
    }

    public static int getPersonUpCounter() {
        return personUpCounter;
    }

    public static void setPersonUpCounter(int personUpCounter) {
        TowerState.personUpCounter = personUpCounter;
    }

    public static int getPersonDownCounter() {
        return personDownCounter;
    }

    public static void setPersonDownCounter(int personDownCounter) {
        TowerState.personDownCounter = personDownCounter;
    }

    public Element getPerson1() {
        return person1;
    }

    public void setPerson1(Element person1) {
        this.person1 = person1;
    }

    public Element getPerson2() {
        return person2;
    }

    public void setPerson2(Element person2) {
        this.person2 = person2;
    }

    public Element getPerson3() {
        return person3;
    }

    public void setPerson3(Element person3) {
        this.person3 = person3;
    }

    public Element getStone() {
        return stone;
    }

    public void setStone(Element stone) {
        this.stone = stone;
    }

    public Element getBasket1() {
        return basket1;
    }

    public void setBasket1(Element basket1) {
        this.basket1 = basket1;
    }

    public Element getBasket2() {
        return basket2;
    }

    public void setBasket2(Element basket2) {
        this.basket2 = basket2;
    }

    public List<Element> getEntitiesUp() {
        return entitiesUp;
    }

    public void setEntitiesUp(List<Element> entitiesUp) {
        this.entitiesUp = entitiesUp;
    }

    public List<Element> getEntitiesDown() {
        return entitiesDown;
    }

    public void setEntitiesDown(List<Element> entitiesDown) {
        this.entitiesDown = entitiesDown;
    }

    public int[] getBasketCapacity1() {
        return basketCapacity1;
    }

    public void setBasketCapacity1(int[] basketCapacity1) {
        this.basketCapacity1 = basketCapacity1;
    }

    public int[] getBasketCapacity2() {
        return basketCapacity2;
    }

    public void setBasketCapacity2(int[] basketCapacity2) {
        this.basketCapacity2 = basketCapacity2;
    }

    @Override
    public String toString() {
        return "TowerProblemState{" +
                "person1=" + person1 +
                ", \n" + "person2=" + person2 +
                ", \n" + "person3=" + person3 +
                ", \n" + "stone=" + stone +
                ", \n" + "basket1=" + basket1 +
                ", \n" + "basket2=" + basket2 +
                ", \n" + "entitiesUp=" + entitiesUp +
                ", \n" + "entitiesDown=" + entitiesDown +
                ", \n" + "basketCapacity1=" + Arrays.toString(basketCapacity1) +
                ", \n" + "basketCapacity2=" + Arrays.toString(basketCapacity2) +
                ", \n" + "personUpCounter: " + getPersonUpCounter() +
                " ," + "personUpCounter: " + getPersonDownCounter() +
                '}';
    }
}
