package org.screamingsandals.lib.bukkit.item.meta;

import java.util.Arrays;
import org.bukkit.potion.PotionType;
import org.screamingsandals.lib.item.meta.PotionMapping;
import org.screamingsandals.lib.utils.annotations.Service;
import org.screamingsandals.lib.utils.key.NamespacedMappingKey;

@Service
public class BukkitPotionMapping extends PotionMapping {

  public BukkitPotionMapping() {
    potionConverter
        .registerP2W(PotionType.class, BukkitPotionHolder::new);

    Arrays.stream(PotionType.values()).forEach(potion -> {
      var holder = new BukkitPotionHolder(potion);
      mapping.put(NamespacedMappingKey.of(potion.name()), holder);
      values.add(holder);
    });
  }
}
