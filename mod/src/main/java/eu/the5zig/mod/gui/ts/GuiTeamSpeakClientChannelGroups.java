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

package eu.the5zig.mod.gui.ts;

import com.google.common.collect.Lists;
import eu.the5zig.mod.The5zigMod;
import eu.the5zig.mod.gui.Gui;
import eu.the5zig.mod.gui.elements.Clickable;
import eu.the5zig.mod.gui.elements.IButton;
import eu.the5zig.mod.gui.elements.IGuiList;
import eu.the5zig.mod.gui.ts.rows.GroupRow;
import eu.the5zig.teamspeak.TeamSpeak;
import eu.the5zig.teamspeak.api.Channel;
import eu.the5zig.teamspeak.api.Client;
import eu.the5zig.teamspeak.api.Group;
import eu.the5zig.teamspeak.api.ServerTab;

import java.util.List;

public class GuiTeamSpeakClientChannelGroups extends Gui implements Clickable<GroupRow> {

	private final Client client;
	private final Channel channel;

	private IGuiList<GroupRow> guiList;

	public GuiTeamSpeakClientChannelGroups(Gui currentScreen, Client client) {
		super(currentScreen);
		this.client = client;
		this.channel = client.getChannel();
	}

	@Override
	public void initGui() {
		List<GroupRow> rows = Lists.newArrayList();
		ServerTab selectedTab = TeamSpeak.getClient().getSelectedTab();
		if (selectedTab == null) {
			The5zigMod.getVars().displayScreen(lastScreen);
			return;
		}
		for (Group group : selectedTab.getChannelGroups()) {
			if (group.getType() == 1) {
				GroupRow row = new GroupRow(group, selectedTab.getServerInfo().getUniqueId());
				if (group.equals(client.getChannelGroup())) {
					row.member = true;
				}
				rows.add(row);
			}
		}

		guiList = The5zigMod.getVars().createGuiList(this, getWidth(), getHeight(), 48, getHeight() - 48, 0, getWidth(), rows);
		guiList.setRowWidth(200);
		addGuiList(guiList);

		addButton(The5zigMod.getVars().createButton(200, getWidth() / 2 - 155, getHeight() - 32, 150, 20, The5zigMod.getVars().translate("gui.cancel")));
		addButton(The5zigMod.getVars().createButton(100, getWidth() / 2 + 5, getHeight() - 32, 150, 20, The5zigMod.getVars().translate("gui.done")));
	}

	@Override
	protected void actionPerformed(IButton button) {
		if (button.getId() == 100) {
			GroupRow selectedRow = guiList.getSelectedRow();
			if (selectedRow == null) {
				return;
			}
			client.setChannelGroup(channel, selectedRow.group);
			The5zigMod.getVars().displayScreen(lastScreen);
		}
	}

	@Override
	protected void tick() {
		ServerTab selectedTab = TeamSpeak.getClient().getSelectedTab();
		if (selectedTab == null) {
			The5zigMod.getVars().displayScreen(lastScreen);
			return;
		}
		GroupRow selectedRow = guiList.getSelectedRow();
		getButtonById(100).setEnabled(selectedRow != null && !selectedRow.member && !selectedRow.group.equals(selectedTab.getDefaultChannelGroup()));
	}

	@Override
	public void onSelect(int id, GroupRow row, boolean doubleClick) {
		if (doubleClick) {
			actionPerformed(getButtonById(100));
		}
	}

	@Override
	public String getTitleKey() {
		return "teamspeak.edit_channel_group.title";
	}

}
