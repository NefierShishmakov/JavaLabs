package ru.nsu.ccfit.shishmakov.context;

import ru.nsu.ccfit.shishmakov.exceptions.VariableExistenceException;
import ru.nsu.ccfit.shishmakov.utils.CONSTANTS;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class Context {
    public Context()
    {
        variables = new HashMap<>();
        stack = new Stack<>();
    }

    public double pop()
    {
        return stack.pop();
    }

    public double peek()
    {
        return stack.peek();
    }

    public boolean isVariableExists(String varName)
    {
        return variables.containsKey(varName);
    }

    public void push(String varName)
    {
        stack.push(variables.get(varName));
    }

    public void push(Double varValue)
    {
        stack.push(varValue);
    }

    /*public void setVariable(String varName, double value)
    {
        variables.put(varName, value);
    }*/

    public void setVariable(String varName, String secondArg, boolean isVariable) throws VariableExistenceException
    {
        if (isVariable)
        {
            if (!this.variables.containsKey(secondArg))
            {
                throw new VariableExistenceException(CONSTANTS.VARIABLE_EXISTENCE_ERROR + secondArg
                        + CONSTANTS.ERROR_MESSAGE);
            }
            variables.put(varName, variables.get(secondArg));
        }
        else
        {
            variables.put(varName, Double.parseDouble(secondArg));
        }
    }

    private final Map<String, Double> variables;
    private final Stack<Double> stack;
}
