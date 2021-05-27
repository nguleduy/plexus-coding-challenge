package joseph.test.water.overflow.model.impl;

import joseph.test.water.overflow.model.Glass;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class GlassImpl implements Glass {

    private Integer capacity;
    private Integer amount = 0;
    private List<Glass> child = new ArrayList<>();

    @Override
    public Integer doFillWater(Integer amount) {
        int overflow = 0;
        if (this.amount + amount > capacity) {
            overflow = this.amount + amount - capacity;
        }
        this.amount = Math.min(capacity, this.amount + amount);

        if (child.size() > 0) {
            final int sharedWater = overflow / 2;
            child.get(0).doFillWater(sharedWater);
            child.get(1).doFillWater(sharedWater);
        }
        return overflow;
    }

    @Override
    public Boolean isFull() {
        return this.amount == this.capacity;
    }
}
