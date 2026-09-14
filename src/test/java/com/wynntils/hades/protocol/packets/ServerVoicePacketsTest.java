package com.wynntils.hades.protocol.packets;

import com.wynntils.hades.objects.VoicePeer;
import com.wynntils.hades.protocol.enums.VoiceEndReason;
import com.wynntils.hades.protocol.enums.VoiceResultKind;
import com.wynntils.hades.protocol.interfaces.HadesPacket;
import com.wynntils.hades.protocol.packets.server.*;
import com.wynntils.hades.utils.HadesBuffer;
import io.netty.buffer.Unpooled;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class ServerVoicePacketsTest {

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
    void packetIdsAppendedInOrder() {
        assertEquals(6, PacketRegistry.SERVER.getPacketId(HSPacketVoiceSecret.class));
        assertEquals(7, PacketRegistry.SERVER.getPacketId(HSPacketVoiceEnded.class));
        assertEquals(8, PacketRegistry.SERVER.getPacketId(HSPacketVoicePeers.class));
        assertEquals(9, PacketRegistry.SERVER.getPacketId(HSPacketVoiceResult.class));
    }

    @Test
    void secretRoundTrip() throws Exception {
        byte[] secret = new byte[16];
        for (int i = 0; i < 16; i++) secret[i] = (byte) i;
        HSPacketVoiceSecret p = roundTrip(new HSPacketVoiceSecret(secret, "voice.wynntils.com", 24454, 32.0, 1000));
        assertArrayEquals(secret, p.getSecret());
        assertEquals("voice.wynntils.com", p.getHost());
        assertEquals(24454, p.getPort());
        assertEquals(32.0, p.getRange());
        assertEquals(1000, p.getKeepAliveMs());
    }

    @Test
    void secretMustBe16Bytes() {
        HadesBuffer buffer = new HadesBuffer();
        buffer.setBuffer(Unpooled.buffer());
        HSPacketVoiceSecret p = new HSPacketVoiceSecret(new byte[15], "h", 1, 1.0, 1);
        assertThrows(IllegalArgumentException.class, () -> p.writeData(buffer));
    }

    @Test
    void endedRoundTrip() throws Exception {
        HSPacketVoiceEnded p = roundTrip(new HSPacketVoiceEnded(VoiceEndReason.NOT_IN_BETA, "Voice is in closed beta"));
        assertEquals(VoiceEndReason.NOT_IN_BETA, p.getReason());
        assertEquals("Voice is in closed beta", p.getMessage());
    }

    @Test
    void peersRoundTripEmpty() throws Exception {
        assertTrue(roundTrip(new HSPacketVoicePeers(List.of())).getPeers().isEmpty());
    }

    @Test
    void peersRoundTrip() throws Exception {
        UUID a = UUID.randomUUID();
        UUID b = UUID.randomUUID();
        HSPacketVoicePeers p = roundTrip(new HSPacketVoicePeers(List.of(
                new VoicePeer(a, "Alice", false, true),
                new VoicePeer(b, "Bob", true, false))));
        assertEquals(2, p.getPeers().size());
        assertEquals(a, p.getPeers().get(0).getUuid());
        assertEquals("Alice", p.getPeers().get(0).getName());
        assertFalse(p.getPeers().get(0).isDisabled());
        assertTrue(p.getPeers().get(0).isPartyMember());
        assertEquals(b, p.getPeers().get(1).getUuid());
        assertTrue(p.getPeers().get(1).isDisabled());
        assertFalse(p.getPeers().get(1).isPartyMember());
    }

    @Test
    void resultRoundTrip() throws Exception {
        HSPacketVoiceResult p = roundTrip(new HSPacketVoiceResult(VoiceResultKind.REPORT, true, "Report #12 filed"));
        assertEquals(VoiceResultKind.REPORT, p.getKind());
        assertTrue(p.isSuccess());
        assertEquals("Report #12 filed", p.getMessage());
    }
}
