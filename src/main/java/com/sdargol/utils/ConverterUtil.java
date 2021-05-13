package com.sdargol.utils;

import java.io.*;

public class ConverterUtil {
    public static <T> byte[] toArrayByte(final T src) {
        byte[] bytes = new byte[]{};

        try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
             ObjectOutputStream oos = new ObjectOutputStream(bos)) {

            oos.writeObject(src);
            bytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return bytes;
    }

    public static <T> T toEntity(final byte[] src) {
        T entity = null;

        try(ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(src))) {
            entity = (T)objectInputStream.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return entity;
    }
}
