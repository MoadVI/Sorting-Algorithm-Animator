package sortinganimator.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class AnimationAction {

    public enum Type {
        COMPARE, SWAP, SET_VALUE, MARK_SORTED, PIVOT
    }

    private final Type type;
    private final List<Integer> indices;
    private final Integer value;

    public AnimationAction(Type type, Integer... indices) {
        if (type == Type.SET_VALUE) {
            throw new IllegalArgumentException("Use the constructor with value for SET_VALUE type.");
        }
        this.type = type;
        this.indices = Collections.unmodifiableList(Arrays.asList(indices));
        this.value = null;
    }


    public AnimationAction(int index, int value) {
        this.type = Type.SET_VALUE;
        this.indices = Collections.unmodifiableList(Arrays.asList(index));
        this.value = value;
    }

    public Type getType() {
        return type;
    }

    public List<Integer> getIndices() {
        return indices;
    }

    public Integer getValue() {
        return value;
    }

    @Override
    public String toString() {
        if (type == Type.SET_VALUE) {
            return String.format("SET_VALUE(index=%d, value=%d)", indices.get(0), value);
        } else {
            return String.format("%s(%s)", type, indices);
        }
    }
}
