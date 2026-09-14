package com.wynntils.hades.protocol.packets;

import com.wynntils.hades.protocol.enums.HadesVersion;
import com.wynntils.hades.protocol.enums.VoiceTier;
import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.packets.client.*;
import com.wynntils.hades.utils.HadesBuffer;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ClientVoicePacketsTest {

    @SuppressWarnings("unchecked")
    static <P extends HadesPacket<?>> P roundTrip(P packet) throws Exception {
        HadesBuffer buffer = new HadesBuffer();
        buffer.setBuffer(Unpooled.buffer());
        packet.writeData(buffer);
        P decoded = (P) packet.getClass().getDeclaredConstructor().newInstance();
        decoded.readData(buffer);
        assertEquals(0, buffer.readableBytes(), "packet left unread bytes");
        return decoded;
    }

    @Test
    void versionAppended() {
        assertEquals(3, HadesVersion.VERSION_0_7_0.ordinal());
    }

    @Test
    void packetIdsAppendedInOrder() {
        assertEquals(8, PacketRegistry.CLIENT.getPacketId(HCPacketVoiceJoin.class));
        assertEquals(9, PacketRegistry.CLIENT.getPacketId(HCPacketVoiceUpdate.class));
        assertEquals(10, PacketRegistry.CLIENT.getPacketId(HCPacketVoiceLeave.class));
        assertEquals(11, PacketRegistry.CLIENT.getPacketId(HCPacketVoiceBlock.class));
        assertEquals(12, PacketRegistry.CLIENT.getPacketId(HCPacketVoiceReport.class));
    }

    @Test
    void joinRoundTrip() throws Exception {
        HCPacketVoiceJoin p = roundTrip(new HCPacketVoiceJoin(20, VoiceTier.FRIENDS_AND_GUILD, "housing:Scyu_"));
        assertEquals(20, p.getSvcCompatVersion());
        assertEquals(VoiceTier.FRIENDS_AND_GUILD, p.getTier());
        assertEquals("housing:Scyu_", p.getInstance());
    }

    @Test
    void updateRoundTrip() throws Exception {
        HCPacketVoiceUpdate p = roundTrip(new HCPacketVoiceUpdate(VoiceTier.EVERYONE, "", true));
        assertEquals(VoiceTier.EVERYONE, p.getTier());
        assertEquals("", p.getInstance());
        assertTrue(p.isDisabled());
    }

    @Test
    void leaveRoundTrip() throws Exception {
        assertNotNull(roundTrip(new HCPacketVoiceLeave()));
    }

    @Test
    void blockRoundTrip() throws Exception {
        HCPacketVoiceBlock p = roundTrip(new HCPacketVoiceBlock("Someone", false));
        assertEquals("Someone", p.getTargetName());
        assertFalse(p.isBlocked());
    }

    @Test
    void reportRoundTrip() throws Exception {
        HCPacketVoiceReport p = roundTrip(new HCPacketVoiceReport("Someone", "slurs"));
        assertEquals("Someone", p.getTargetName());
        assertEquals("slurs", p.getReason());
    }
}
