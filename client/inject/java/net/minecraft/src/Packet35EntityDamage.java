package net.minecraft.src;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

public class Packet35EntityDamage extends Packet {
    public int entityId;
    public int damage;
    public boolean dead;

    public Packet35EntityDamage() {
        this.entityId = 0;
        this.damage = 0;
        this.dead = false;
    }

    @Override
    public void readPacketData(DataInputStream dataInputStream) throws IOException {
        this.entityId = dataInputStream.readInt();
        this.damage = dataInputStream.readInt();
        this.dead = dataInputStream.readBoolean();
    }

    @Override
    public void writePacket(DataOutputStream dataOutputStream) throws IOException {
        dataOutputStream.writeInt(entityId);
        dataOutputStream.writeInt(damage);
        dataOutputStream.writeBoolean(dead);
    }

    @Override
    public void processPacket(NetHandler netHandler) {

    }

    @Override
    public int getPacketSize() {
        return 9;
    }

    public Packet35EntityDamage hit(int entityID, int damage, boolean isDead) {
        this.entityId = entityID;
        this.damage = damage;
        this.dead = isDead;
        return this;
    }
}
