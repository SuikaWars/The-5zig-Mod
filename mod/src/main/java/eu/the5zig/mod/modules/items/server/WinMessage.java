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

package eu.the5zig.mod.modules.items.server;

import eu.the5zig.mod.I18n;
import eu.the5zig.mod.The5zigMod;
import eu.the5zig.mod.modules.AbstractModuleItem;
import eu.the5zig.mod.render.DisplayRenderer;
import eu.the5zig.mod.render.RenderLocation;
import eu.the5zig.mod.server.GameServer;

public class WinMessage extends AbstractModuleItem {
	@Override
	public void render(int x, int y, RenderLocation renderLocation, boolean dummy) {
		if (The5zigMod.getDataManager().getServer() instanceof GameServer && ((GameServer) The5zigMod.getDataManager().getServer()).getGameMode() != null &&
				((GameServer) The5zigMod.getDataManager().getServer()).getGameMode().getWinner() != null)
			DisplayRenderer.largeTextRenderer.render(
					The5zigMod.getRenderer().getPrefix() + I18n.translate("ingame.win", ((GameServer) The5zigMod.getDataManager().getServer()).getGameMode().getWinner()));
	}

	@Override
	public int getWidth(boolean dummy) {
		return 0;
	}

	@Override
	public int getHeight(boolean dummy) {
		return 0;
	}

}
