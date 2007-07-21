/*
 * Copyright 2005-2007 Noelios Consulting.
 * 
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the "License"). You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the license at
 * http://www.opensource.org/licenses/cddl1.txt See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL HEADER in each file and
 * include the License file at http://www.opensource.org/licenses/cddl1.txt If
 * applicable, add the following below this CDDL HEADER, with the fields
 * enclosed by brackets "[]" replaced with your own identifying information:
 * Portions Copyright [yyyy] [name of copyright owner]
 */

package org.restlet.resource;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.util.Date;

import org.restlet.data.MediaType;
import org.restlet.data.Tag;

/**
 * Current or intended state of a resource. The content of a representation can
 * be retrieved several times if there is a stable and accessible source, like a
 * local file or a string. When the representation is obtained via a temporary
 * source like a network socket, its content can only be retrieved once. The
 * "transient" and "available" properties are available to help you figure out
 * those aspects at runtime.<br/><br/> For performance purpose, it is
 * essential that a minimal overhead occurs upon initialization. The main
 * overhead must only occur during invocation of content processing methods
 * (write, getStream, getChannel and toString).<br/><br/> "REST components
 * perform actions on a resource by using a representation to capture the
 * current or intended state of that resource and transferring that
 * representation between components. A representation is a sequence of bytes,
 * plus representation metadata to describe those bytes. Other commonly used but
 * less precise names for a representation include: document, file, and HTTP
 * message entity, instance, or variant." Roy T. Fielding
 * 
 * @see <a
 *      href="http://roy.gbiv.com/pubs/dissertation/rest_arch_style.htm#sec_5_2_1_2">Source
 *      dissertation</a>
 * @author Jerome Louvel (contact@noelios.com)
 */
public abstract class Representation extends Variant {
    /** Indicates if the representation's content is available. */
    private boolean contentAvailable;

    /** Indicates if the representation's content is transient. */
    private boolean contentTransient;

    /**
     * Default constructor.
     */
    public Representation() {
        this(null);
    }

    /**
     * Constructor.
     * 
     * @param mediaType
     *                The media type.
     */
    public Representation(MediaType mediaType) {
        super(mediaType);
        this.contentAvailable = true;
        this.contentTransient = false;
    }

    /**
     * Returns a channel with the representation's content.<br/> If it is
     * supported by a file, a read-only instance of FileChannel is returned.<br/>
     * This method is ensured to return a fresh channel for each invocation
     * unless it is a transient representation, in which case null is returned.
     * 
     * @return A channel with the representation's content.
     * @throws IOException
     */
    public abstract ReadableByteChannel getChannel() throws IOException;

    /**
     * Returns the future date when this representation expire. If this
     * information is not known, returns null.
     * 
     * @return The expiration date.
     */
    @SuppressWarnings("deprecation")
    public Date getExpirationDate() {
        return super.getExpirationDate();
    }

    /**
     * Returns the last date when this representation was modified. If this
     * information is not known, returns null.
     * 
     * @return The modification date.
     */
    @SuppressWarnings("deprecation")
    public Date getModificationDate() {
        return super.getModificationDate();
    }

    /**
     * Returns a characters reader with the representation's content. This
     * method is ensured to return a fresh reader for each invocation unless it
     * is a transient representation, in which case null is returned. If the
     * representation has no character set defined, the system's default one
     * will be used.
     * 
     * @return A reader with the representation's content.
     * @throws IOException
     */
    public abstract Reader getReader() throws IOException;

    /**
     * Returns the size in bytes if known, UNKNOWN_SIZE (-1) otherwise.
     * 
     * @return The size in bytes if known, UNKNOWN_SIZE (-1) otherwise.
     */
    @SuppressWarnings("deprecation")
    public long getSize() {
        return super.getSize();
    }

    /**
     * Returns a stream with the representation's content. This method is
     * ensured to return a fresh stream for each invocation unless it is a
     * transient representation, in which case null is returned.
     * 
     * @return A stream with the representation's content.
     * @throws IOException
     */
    public abstract InputStream getStream() throws IOException;

    /**
     * Returns the tag.
     * 
     * @return The tag.
     */
    @SuppressWarnings("deprecation")
    public Tag getTag() {
        return super.getTag();
    }

    /**
     * Converts the representation to a string value. Be careful when using this
     * method as the conversion of large content to a string fully stored in
     * memory can result in OutOfMemoryErrors being thrown.
     * 
     * @return The representation as a string value.
     */
    public String getText() throws IOException {
        String result = null;

        if (isAvailable()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            write(baos);

            if (getCharacterSet() != null) {
                result = baos.toString(getCharacterSet().getName());
            } else {
                result = baos.toString();
            }
        }

        return result;
    }

    /**
     * Indicates if some fresh content is available, without having to actually
     * call one of the content manipulation method like getStream() that would
     * actually consume it. This is especially useful for transient
     * representation whose content can only be accessed once and also when the
     * size of the representation is not known in advance.
     * 
     * @return True if some fresh content is available.
     */
    public boolean isAvailable() {
        return this.contentAvailable;
    }

    /**
     * Indicates if the representation's content is transient, which means that
     * it can be obtained only once. This is often the case with representations
     * transmitted via network sockets for example. In such case, if you need to
     * read the content several times, you need to cache it first, for example
     * into memory or into a file.
     * 
     * @return True if the representation's content is transient.
     */
    public boolean isTransient() {
        return this.contentTransient;
    }

    /**
     * Releases the representation's content and all associated objects like
     * sockets, channels or files. If the representation is transient and hasn't
     * been read yet, all the remaining content will be discarded, any open
     * socket, channel, file or similar source of content will be immediately
     * closed.
     */
    public void release() {
    }

    /**
     * Indicates if some fresh content is available.
     * 
     * @param available
     *                True if some fresh content is available.
     */
    public void setAvailable(boolean available) {
        this.contentAvailable = available;
    }

    /**
     * Sets the future date when this representation expire. If this information
     * is not known, pass null.
     * 
     * @param expirationDate
     *                The expiration date.
     */
    @SuppressWarnings("deprecation")
    public void setExpirationDate(Date expirationDate) {
        super.setExpirationDate(expirationDate);
    }

    /**
     * Sets the last date when this representation was modified. If this
     * information is not known, pass null.
     * 
     * @param modificationDate
     *                The modification date.
     */
    @SuppressWarnings("deprecation")
    public void setModificationDate(Date modificationDate) {
        super.setModificationDate(modificationDate);
    }

    /**
     * Sets the expected size in bytes if known, -1 otherwise.
     * 
     * @param expectedSize
     *                The expected size in bytes if known, -1 otherwise.
     */
    @SuppressWarnings("deprecation")
    public void setSize(long expectedSize) {
        super.setSize(expectedSize);
    }

    /**
     * Sets the tag.
     * 
     * @param tag
     *                The tag.
     */
    @SuppressWarnings("deprecation")
    public void setTag(Tag tag) {
        super.setTag(tag);
    }

    /**
     * Indicates if the representation's content is transient.
     * 
     * @param isTransient
     *                True if the representation's content is transient.
     */
    public void setTransient(boolean isTransient) {
        this.contentTransient = isTransient;
    }

    /**
     * Writes the representation to a byte stream. This method is ensured to
     * write the full content for each invocation unless it is a transient
     * representation, in which case an exception is thrown.
     * 
     * @param outputStream
     *                The output stream.
     * @throws IOException
     */
    public abstract void write(OutputStream outputStream) throws IOException;

    /**
     * Writes the representation to a byte channel. This method is ensured to
     * write the full content for each invocation unless it is a transient
     * representation, in which case an exception is thrown.
     * 
     * @param writableChannel
     *                A writable byte channel.
     * @throws IOException
     */
    public abstract void write(WritableByteChannel writableChannel)
            throws IOException;

    /**
     * Writes the representation to a characters writer. This method is ensured
     * to write the full content for each invocation unless it is a transient
     * representation, in which case an exception is thrown.
     * 
     * @param writer
     *                The characters writer.
     * @throws IOException
     */
    public abstract void write(Writer writer) throws IOException;

}
