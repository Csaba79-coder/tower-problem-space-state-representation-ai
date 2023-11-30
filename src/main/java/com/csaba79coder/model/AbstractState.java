package com.csaba79coder.model;

/**
 * The abstract base class for all state classes.
 */
public abstract class AbstractState implements Cloneable {

    // Checks if the internal state is a state.
    abstract boolean isState();
    // Checks if the internal state is a goal state.
    abstract boolean isGoalState();
    // Returns the number of operators available.
    abstract int getOperatorCount();
    // Access all operators through the super operator.
    // Returns true if the i-th base operator can be applied to the internal state.
    // Call this method from a for loop, starting from 0 to the number of base operators.
    // For example:
    // for (int i = 0; i < state.getOperatorCount(); i++) {
    //     AbstractState clone = (AbstractState) state.clone();
    //     if (clone.isSuperOperator(i)) {
    //         System.out.println("The " + i + "th operator can be applied to the state " + state);
    //     }
    // }
    abstract boolean isSuperOperator(int i);
    // Clone method. This method is required because we may need to undo the effects of some operators.
    // The simplest way is to clone the state, apply the operator to the clone, and check for problems.
    // If there are no issues, the clone becomes the new state for further search.
    // This is a shallow clone. If a deep clone is needed, it should be overridden in child classes.
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            // Handle the exception as needed.
            return null;
        }
    }
    // Override this method only if you want to use memory-based backtracking or depth-first search.
    // Otherwise, the default implementation is sufficient.
    @Override
    public boolean equals(Object other) {
        return false;
    }
    // If two instances are equal, their hash codes should be equal.
    // Override this method if you override the equals method.
    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
