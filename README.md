<p align="center">
<img src="https://cdn.wynntils.com/hades_logo_800x800.png" width=60%>
<br>
<a href="https://discord.gg/ve49m9J"><img src="https://img.shields.io/discord/394189072635133952?label=Discord&style=for-the-badge"></a>
<a href="https://github.com/Wynntils/Hades/blob/master/LICENSE"><img src="https://img.shields.io/badge/license-AGLP%203.0-green.svg?style=for-the-badge"></a>
<img src="https://img.shields.io/github/workflow/status/Wynntils/Hades/Publish Release/master?style=for-the-badge">
<img src="https://img.shields.io/github/v/release/Wynntils/Hades?style=for-the-badge">
</p>

Protocol Hades
========
Hades is the shared protocol between Athena and the Wynntils Client.<br>

Packets
========
Currently Implemented packets are:
### Client
 - `HCPacketAuthenticate` -> Authentication Packet: The client sends their Athena token forwards the server which decides how to handle it.
 - `HCPacketDiscordLobbyClient` **(NO IMPL)** -> Discord Lobby Packet:  Used for sending information to join a discord lobby to the server to send to the client joining.
 - `HCPacketSocialUpdate` -> Social Update Packet: This packet is used to indicate that a relation (party or friendship) has changed on the client.
 - `HCPacketUpdateGuild` **(NOT USED, NEEDS CHANGES)** -> Update Guild Packet: Used to update player's guild.
 - `HCPacketUpdateStatus` Update Status Packet: This packet is used by the client to send new data to the server (location and stats).
 - `HCPacketUpdateWorld` Update World Packet: Used by the client to set their world.

### Server
 - `HSPacketAuthenticationResponse` Authentication Responce Packet: Tells the client whether authentication was successful.
 - `HSPacketDisconnect` Disconnet Packet: Tells the client that the server is disconnecting the user.
 - `HSPacketDiscordLobbyServer` **(NO IMPL)** Discord Lobby Server Packet: Used for sending information to join a discord lobby to the client joining.
 - `HSPacketUpdateMutual` Update Mutual Packet: Sends updates to the client about the user's mutual users' info.
License
========
Protocol Hades is licensed over the license <a href="https://github.com/Wynntils/Hades/blob/master/LICENSE">GNU Affero General Public License v3.0</a>
