/*
 * Copyright 2022 ScreamingSandals
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.screamingsandals.lib.bukkit.particle;

import java.util.Arrays;
import org.bukkit.Effect;
import org.screamingsandals.lib.particle.ParticleData;
import org.screamingsandals.lib.particle.ParticleTypeHolder;
import org.screamingsandals.lib.utils.BasicWrapper;

public class BukkitParticleTypeHolder extends BasicWrapper<Effect> implements ParticleTypeHolder {

  public BukkitParticleTypeHolder(Effect wrappedObject) {
    super(wrappedObject);
  }

  @Override
  public String platformName() {
    return wrappedObject.name();
  }

  @Override
  public Class<? extends ParticleData> expectedDataClass() {
    return null;
  }

  @Override
  public boolean is(Object object) {
    if (object instanceof Effect || object instanceof ParticleTypeHolder) {
      return equals(object);
    }
    return equals(ParticleTypeHolder.ofOptional(object).orElse(null));
  }

  @Override
  public boolean is(Object... objects) {
    return Arrays.stream(objects).anyMatch(this::is);
  }
}
