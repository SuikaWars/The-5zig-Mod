/*
 * Original: Copyright (c) 2015-2019 5zig [MIT]
 * Current: Copyright (c) 2019 5zig Reborn [GPLv3+]
 *
 * This file is part of The 5zig Mod
 * The 5zig Mod is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * The 5zig Mod is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with The 5zig Mod.  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.the5zig.mod.config.items;

import eu.the5zig.mod.I18n;
import eu.the5zig.util.Utils;

import java.util.List;

public abstract class ListItem<T> extends ConfigItem<List<T>> {

	/**
	 * Creates a Config Item.
	 *
	 * @param key          Der Key of the Item. Used in config File and to translate the Item.
	 * @param category     The Category of the Item. Used by {@link eu.the5zig.mod.gui.GuiSettings} for finding the corresponding items.
	 * @param defaultValue The Default Value of the Item.
	 */
	public ListItem(String key, String category, List<T> defaultValue) {
		super(key, category, defaultValue);
	}

	public abstract void setSafely(List<Object> objects);

	@Override
	public String translate() {
		return I18n.translate(getTranslationPrefix() + "." + getCategory() + "." + Utils.upperToDash(getKey()));
	}

	@Override
	public void next() {
	}
}
