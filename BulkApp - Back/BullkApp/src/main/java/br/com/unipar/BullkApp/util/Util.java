package br.com.unipar.BullkApp.util;

import com.google.common.base.Charsets;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.Base64Utils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.DataFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class Util {
    public static byte[] compressData(String messageContent) throws IOException
    {
        byte[] bytes = messageContent.getBytes(Charsets.UTF_8);

        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gzos = new GZIPOutputStream(bos);
        gzos.write(bytes);
        gzos.close();
        return bos.toByteArray();
    }

    public static byte[] compressDataByte(byte[] messageContent) throws IOException
    {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        GZIPOutputStream gzos = new GZIPOutputStream(bos);
        gzos.write(messageContent);
        gzos.close();
        return bos.toByteArray();
    }

    public static String decompress(byte[] bytes) throws IOException, DataFormatException
    {
        InputStream in = new GZIPInputStream(new ByteArrayInputStream((bytes)));
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[8192];
        int len;
        while ((len = in.read(buffer)) > 0)
            baos.write(buffer, 0, len);
        return new String(baos.toByteArray(), Charsets.UTF_8);
    }

    public static String getFileType(String textoSerializado) {
        Base64 base64 = new Base64();

        return new String(base64.decode(textoSerializado));
    }
}
