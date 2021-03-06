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

package org.screamingsandals.lib.bukkit.attribute;

import java.util.Optional;
import org.screamingsandals.lib.attribute.AttributeHolder;
import org.screamingsandals.lib.attribute.AttributeMapping;
import org.screamingsandals.lib.utils.annotations.Service;

@Service
public class BukkitAttributeMapping extends AttributeMapping {

  public BukkitAttributeMapping() {

  }

  @Override
  protected Optional<AttributeHolder> wrapAttribute0(Object attribute) {
    return Optional.empty();
  }
}
