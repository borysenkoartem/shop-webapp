package com.epam.borysenko.filter.compression;

import javax.servlet.ServletOutputStream;
import javax.servlet.WriteListener;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.zip.GZIPOutputStream;

public class GZipServletOutputStream extends ServletOutputStream {

    private static final String CONTENT_TYPE_FOR_COMPRESSION = "/text";
    public static final String CONTENT_ENCODING = "Content-Encoding";
     public static final String GZIP ="gzip";

    private final ServletOutputStream outputStream;
    private final HttpServletResponse response;

    private GZIPOutputStream gzipOutputStream;


    private Boolean cacheIsContentTypeAvailableForCompression;

    public GZipServletOutputStream(final HttpServletResponse response) throws IOException {
        super();
        this.outputStream = response.getOutputStream();
        this.response = response;
    }

    @Override
    public void close() throws IOException {
        if (isContentTypeAvailableForCompression()) {
            gzipOutputStream.close();
        } else {
            outputStream.close();
        }
    }

    @Override
    public void flush() throws IOException {
        if (isContentTypeAvailableForCompression()) {
            gzipOutputStream.flush();
        } else {
            outputStream.flush();
        }
    }

    @Override
    public void write(final byte buffer[]) throws IOException {
        if (isContentTypeAvailableForCompression()) {
            gzipOutputStream.write(buffer);
        } else {
            outputStream.write(buffer);
        }
    }

    @Override
    public void write(final byte buffer[], final int offset, final int length) throws IOException {
        if (isContentTypeAvailableForCompression()) {
            gzipOutputStream.write(buffer, offset, length);
        } else {
            outputStream.write(buffer, offset, length);
        }
    }

    @Override
    public void write(final int number) throws IOException {
        if (isContentTypeAvailableForCompression()) {
            gzipOutputStream.write(number);
        } else {
            outputStream.write(number);
        }
    }

    @Override
    public boolean isReady() {
        return outputStream.isReady();
    }

    @Override
    public void setWriteListener(final WriteListener writeListener) {
        outputStream.setWriteListener(writeListener);
    }

    private boolean isContentTypeAvailableForCompression() throws IOException {
        if (cacheIsContentTypeAvailableForCompression != null) {
            return cacheIsContentTypeAvailableForCompression;
        }

        cacheIsContentTypeAvailableForCompression = response.getContentType() != null
                && response.getContentType().startsWith(CONTENT_TYPE_FOR_COMPRESSION);
        if (cacheIsContentTypeAvailableForCompression) {
            gzipOutputStream = new GZIPOutputStream(outputStream);
            response.addHeader(CONTENT_ENCODING, GZIP);
        }
        return cacheIsContentTypeAvailableForCompression;
    }
}
