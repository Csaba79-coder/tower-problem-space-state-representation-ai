package com.csaba79coder.core;

/**
 * The BlindState class is provided here for illustrative purposes only.
 * It demonstrates how to write operators and integrate them into the super operator.
 */
public abstract class BlindState extends AbstractState {

    // This class is only provided for illustration purposes.
    // It shows how to implement operators and connect them to the super operator.

    // Define the fields that represent the internal state here.
    // Basic operators should be implemented first.
    // Each operator has preconditions and post conditions.
    // An operator returns true if it's applicable, false otherwise.
    // An operator is applicable if both its preconditions and post conditions are satisfied.

    // Implement the first basic operator.
    private boolean op1() {
        if (!preOp1()) {
            return false;
        }
        // State transition:
        //
        // TODO: Add the code here!
        //
        if (isValidState()) {
            return true;
        }
        // Otherwise, revert the state transition:
        //
        // TODO: Add the code here!
        //
        return false;
    }

    // Preconditions for the first basic operator.
    private boolean preOp1() {
        // Return true if preconditions are met.
        return true;
    }

    // Implement another basic operator.
    private boolean op2() {
        if (!preOp2()) return false;
        // State transition:
        //
        // TODO: Add the code here!
        //
        if (isValidState()) return true;
        // Otherwise, revert the state transition:
        //
        // TODO: Add the code here!
        //
        return false;
    }

    // Preconditions for the second basic operator.
    private boolean preOp2() {
        // Return true if preconditions are met.
        return true;
    }

    // Now, consider operators with parameters.
    // Such operators can correspond to multiple basic operators.

    private boolean op3(int i) {
        if (!preOp3(i)) return false;
        // State transition:
        //
        // TODO: Add the code here!
        //
        if (isValidState()) return true;
        // Otherwise, revert the state transition:
        //
        // TODO: Add the code here!
        //
        return false;
    }

    // Preconditions for the third basic operator.
    private boolean preOp3(int i) {
        // Return true if preconditions are met. Preconditions may depend on the parameter.
        return true;
    }

    // This is the super operator. Use this to call basic operators.
    // The 'i' parameter specifies which basic operator to call.
    // Typically, this is called from a for loop, iterating from 0 to the number of basic operators.
    @Override
    public boolean isSuperOperator(int i) {
        switch (i) {
            // List all the basic operators here.
            // If you add a new operator, make sure to add it here too.
            case 0: return op1();
            case 1: return op2();
            // Parameterized operators correspond to multiple basic operators.
            // The number of lines here may vary depending on the task.
            case 3: return op3(0);
            case 4: return op3(1);
            case 5: return op3(2);
            default: return false;
        }
    }

    // Return the number of basic operators.
    @Override
    public int getOperatorCount() {
        // Return the number of the last case in the switch statement above.
        // If you increase the number of operators, update this value accordingly.
        return 5;
    }
}
