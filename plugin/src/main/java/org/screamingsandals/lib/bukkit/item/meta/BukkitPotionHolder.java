package org.screamingsandals.lib.bukkit.item.meta;

import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;
import org.screamingsandals.lib.item.meta.PotionHolder;
import org.screamingsandals.lib.utils.BasicWrapper;

import java.util.Arrays;


public class BukkitPotionHolder extends BasicWrapper<PotionType> implements PotionHolder {

    protected BukkitPotionHolder(PotionType wrappedObject) {
        super(wrappedObject);
    }

    @Override
    public String platformName() {
        return wrappedObject.name();
    }

    @Override
    public boolean is(Object object) {
        if (object instanceof PotionData || object instanceof PotionHolder) {
            return equals(object);
        }
        return equals(PotionHolder.ofOptional(object).orElse(null));
    }

    @Override
    public boolean is(Object... objects) {
        return Arrays.stream(objects).anyMatch(this::is);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T as(Class<T> type) {
        if (type == PotionType.class) {
            return (T) wrappedObject;
        }
        return super.as(type);
    }
}
