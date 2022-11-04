package project_stone.project_stone.PlayerChats;

import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataType;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;

public class ChatSetDataType implements PersistentDataType<byte[],ChatSet> {
    @Override
    public Class<byte[]> getPrimitiveType() {
        return byte[].class;
    }

    @Override
    public Class<ChatSet> getComplexType() {
        return ChatSet.class;
    }

    @Override
    public byte[] toPrimitive(ChatSet complex, PersistentDataAdapterContext context) {
        return new byte[0];
    }


    @Override
    public ChatSet fromPrimitive(byte[] primitive, PersistentDataAdapterContext context) {
        try {
            InputStream inputStream = new ByteArrayInputStream(primitive);
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return (ChatSet) objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException exception) {
            exception.printStackTrace();
            return null;
        }
    }
}
