/*
 * Copyright (c) 2011 Michael Nischt
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 *
 * Neither the name of the project's author nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS
 * FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED
 * TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 *
 */
package org.interaction3d.opengl.lwjgl;

import java.io.IOException;
import java.nio.ByteBuffer;

import java.nio.channels.ReadableByteChannel;
import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.GLContext;

import static org.lwjgl.opengl.ARBVertexBufferObject.GL_WRITE_ONLY_ARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.GL_STREAM_DRAW_ARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.GL_STREAM_READ_ARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.GL_STREAM_COPY_ARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.GL_STATIC_DRAW_ARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.GL_STATIC_READ_ARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.GL_STATIC_COPY_ARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.GL_DYNAMIC_DRAW_ARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.GL_DYNAMIC_READ_ARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.GL_DYNAMIC_COPY_ARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.GL_ELEMENT_ARRAY_BUFFER_ARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.glGenBuffersARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.glBindBufferARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.glBufferDataARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.glMapBufferARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.glUnmapBufferARB;


import static org.lwjgl.opengl.GL15.GL_WRITE_ONLY;
import static org.lwjgl.opengl.GL15.GL_STREAM_DRAW;
import static org.lwjgl.opengl.GL15.GL_STREAM_READ;
import static org.lwjgl.opengl.GL15.GL_STREAM_COPY;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.GL_STATIC_READ;
import static org.lwjgl.opengl.GL15.GL_STATIC_COPY;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_DRAW;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_READ;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_COPY;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glMapBuffer;
import static org.lwjgl.opengl.GL15.glUnmapBuffer;

import org.interaction3d.opengl.Buffer;

public final class GLBuffers
{
  public static int arrayBuffer(long size, Buffer.Usage usage)
  {
    return impl.arrayBuffer(size, usage);
  }

  public static int arrayBuffer(ReadableByteChannel channel, long size, Buffer.Usage usage) throws IOException
  {
    return impl.arrayBuffer(channel, size, usage);
  }

  public static int arrayBuffer(ByteBuffer vertexBuffer, Buffer.Usage usage)
  {
    return impl.arrayBuffer(vertexBuffer, usage);
  }

  public static int elementArrayBuffer(long size, Buffer.Usage usage)
  {
    return impl.elementArrayBuffer(size, usage);
  }

  public static int elementArrayBuffer(ReadableByteChannel channel, long size, Buffer.Usage usage) throws IOException
  {
    return impl.elementArrayBuffer(channel, size, usage);
  }

  public static int elementArrayBuffer(ByteBuffer indexBuffer, Buffer.Usage usage)
  {
    return impl.elementArrayBuffer(indexBuffer, usage);
  }

  private interface Interface
  {
    int arrayBuffer(long size, Buffer.Usage usage);

    int arrayBuffer(ReadableByteChannel channel, long size, Buffer.Usage usage) throws IOException;

    int arrayBuffer(ByteBuffer vertexBuffer, Buffer.Usage usage);

    int elementArrayBuffer(long size, Buffer.Usage usage);

    int elementArrayBuffer(ReadableByteChannel channel, long size, Buffer.Usage usage) throws IOException;

    int elementArrayBuffer(ByteBuffer indexBuffer, Buffer.Usage usage);
  }

  private static final class GL15 implements Interface
  {
    @Override
    public int arrayBuffer(long size, Buffer.Usage usage)
    {
      int gl_array_buffer = glGenBuffers();
      glBindBuffer(GL_ARRAY_BUFFER, gl_array_buffer);
      glBufferData(GL_ARRAY_BUFFER, size, glBufferUsage(usage));
      glBindBuffer(GL_ARRAY_BUFFER, 0);
      return gl_array_buffer;
    }

    @Override
    public int arrayBuffer(ReadableByteChannel channel, long size, Buffer.Usage usage) throws IOException
    {
      int gl_array_buffer = glGenBuffers();
      glBindBuffer(GL_ARRAY_BUFFER, gl_array_buffer);
      glBufferData(GL_ARRAY_BUFFER, size, glBufferUsage(usage));
      ByteBuffer data = glMapBuffer(GL_ARRAY_BUFFER, GL_WRITE_ONLY, null);
      channel.read(data);
      glUnmapBuffer(GL_ARRAY_BUFFER);
      glBindBuffer(GL_ARRAY_BUFFER, 0);
      return gl_array_buffer;
    }

    @Override
    public int arrayBuffer(ByteBuffer vertexBuffer, Buffer.Usage usage)
    {
      int gl_array_buffer = glGenBuffers();
      glBindBuffer(GL_ARRAY_BUFFER, gl_array_buffer);
      glBufferData(GL_ARRAY_BUFFER, vertexBuffer, glBufferUsage(usage));
      glBindBuffer(GL_ARRAY_BUFFER, 0);
      return gl_array_buffer;
    }

    @Override
    public int elementArrayBuffer(long size, Buffer.Usage usage)
    {
      int gl_element_array_buffer = glGenBuffers();
      glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, gl_element_array_buffer);
      glBufferData(GL_ELEMENT_ARRAY_BUFFER, size, glBufferUsage(usage));
      glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
      return gl_element_array_buffer;
    }

    @Override
    public int elementArrayBuffer(ReadableByteChannel channel, long size, Buffer.Usage usage) throws IOException
    {
      int gl_array_buffer = glGenBuffers();
      glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, gl_array_buffer);
      glBufferData(GL_ELEMENT_ARRAY_BUFFER, size, glBufferUsage(usage));
      ByteBuffer data = glMapBuffer(GL_ELEMENT_ARRAY_BUFFER, GL_WRITE_ONLY, null);
      channel.read(data);
      glUnmapBuffer(GL_ELEMENT_ARRAY_BUFFER);
      glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
      return gl_array_buffer;
    }

    @Override
    public int elementArrayBuffer(ByteBuffer indexBuffer, Buffer.Usage usage)
    {
      int gl_element_array_buffer = glGenBuffers();
      glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, gl_element_array_buffer);
      glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, glBufferUsage(usage));
      glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
      return gl_element_array_buffer;
    }

    private static int glBufferUsage(Buffer.Usage usage)
    {
      switch (usage)
      {
        case STREAM_DRAW:
          return GL_STREAM_DRAW;
        case STREAM_READ:
          return GL_STREAM_READ;
        case STREAM_COPY:
          return GL_STREAM_COPY;
        case STATIC_DRAW:
          return GL_STATIC_DRAW;
        case STATIC_READ:
          return GL_STATIC_READ;
        case STATIC_COPY:
          return GL_STATIC_COPY;
        case DYNAMIC_DRAW:
          return GL_DYNAMIC_DRAW;
        case DYNAMIC_READ:
          return GL_DYNAMIC_READ;
        case DYNAMIC_COPY:
          return GL_DYNAMIC_COPY;
        default:
          throw new IllegalArgumentException();
      }
    }
  }

  private static final class GL11ARBVertexBufferObject implements Interface
  {
    @Override
    public int arrayBuffer(long size, Buffer.Usage usage)
    {
      int gl_array_buffer = glGenBuffersARB();
      glBindBufferARB(GL_ARRAY_BUFFER_ARB, gl_array_buffer);
      glBufferDataARB(GL_ARRAY_BUFFER_ARB, size, glBufferUsageARB(usage));
      glBindBufferARB(GL_ARRAY_BUFFER_ARB, 0);
      return gl_array_buffer;
    }

    @Override
     public int arrayBuffer(ReadableByteChannel channel, long size, Buffer.Usage usage) throws IOException
     {
      int gl_array_buffer = glGenBuffersARB();
      glBindBufferARB(GL_ARRAY_BUFFER_ARB, gl_array_buffer);
      glBufferDataARB(GL_ARRAY_BUFFER_ARB, size, glBufferUsageARB(usage));
      ByteBuffer data = glMapBufferARB(GL_ARRAY_BUFFER_ARB, GL_WRITE_ONLY_ARB, null);
      channel.read(data);
      glUnmapBufferARB(GL_ARRAY_BUFFER_ARB);
      glBindBufferARB(GL_ARRAY_BUFFER_ARB, 0);
      return gl_array_buffer;
     }

    @Override
    public int arrayBuffer(ByteBuffer vertexBuffer, Buffer.Usage usage)
    {
      int gl_array_buffer = glGenBuffersARB();
      glBindBufferARB(GL_ARRAY_BUFFER_ARB, gl_array_buffer);
      glBufferDataARB(GL_ARRAY_BUFFER_ARB, vertexBuffer, glBufferUsageARB(usage));
      glBindBufferARB(GL_ARRAY_BUFFER_ARB, 0);
      return gl_array_buffer;
    }

    @Override
    public int elementArrayBuffer(long size, Buffer.Usage usage)
    {
      int gl_element_array_buffer = glGenBuffersARB();
      glBindBufferARB(GL_ELEMENT_ARRAY_BUFFER_ARB, gl_element_array_buffer);
      glBufferDataARB(GL_ELEMENT_ARRAY_BUFFER_ARB, size, glBufferUsageARB(usage));
      glBindBufferARB(GL_ELEMENT_ARRAY_BUFFER_ARB, 0);
      return gl_element_array_buffer;
    }

    @Override
     public int elementArrayBuffer(ReadableByteChannel channel, long size, Buffer.Usage usage) throws IOException
     {
      int gl_array_buffer = glGenBuffersARB();
      glBindBufferARB(GL_ELEMENT_ARRAY_BUFFER_ARB, gl_array_buffer);
      glBufferDataARB(GL_ELEMENT_ARRAY_BUFFER_ARB, size, glBufferUsageARB(usage));
      ByteBuffer data = glMapBufferARB(GL_ELEMENT_ARRAY_BUFFER_ARB, GL_WRITE_ONLY_ARB, null);
      channel.read(data);
      glUnmapBufferARB(GL_ELEMENT_ARRAY_BUFFER_ARB);
      glBindBufferARB(GL_ELEMENT_ARRAY_BUFFER_ARB, 0);
      return gl_array_buffer;
     }

    @Override
    public int elementArrayBuffer(ByteBuffer indexBuffer, Buffer.Usage usage)
    {
      int gl_element_array_buffer = glGenBuffersARB();
      glBindBufferARB(GL_ELEMENT_ARRAY_BUFFER_ARB, gl_element_array_buffer);
      glBufferDataARB(GL_ELEMENT_ARRAY_BUFFER_ARB, indexBuffer, glBufferUsageARB(usage));
      glBindBufferARB(GL_ELEMENT_ARRAY_BUFFER_ARB, 0);
      return gl_element_array_buffer;
    }

    private static int glBufferUsageARB(Buffer.Usage usage)
    {
      switch (usage)
      {
        case STREAM_DRAW:
          return GL_STREAM_DRAW_ARB;
        case STREAM_READ:
          return GL_STREAM_READ_ARB;
        case STREAM_COPY:
          return GL_STREAM_COPY_ARB;
        case STATIC_DRAW:
          return GL_STATIC_DRAW_ARB;
        case STATIC_READ:
          return GL_STATIC_READ_ARB;
        case STATIC_COPY:
          return GL_STATIC_COPY_ARB;
        case DYNAMIC_DRAW:
          return GL_DYNAMIC_DRAW_ARB;
        case DYNAMIC_READ:
          return GL_DYNAMIC_READ_ARB;
        case DYNAMIC_COPY:
          return GL_DYNAMIC_COPY_ARB;
        default:
          throw new IllegalArgumentException();
      }
    }
  }

  static
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    if (caps.OpenGL15)
    {
      impl = new GL15();
    }
    else if (caps.GL_ARB_vertex_buffer_object)
    {
      impl = new GL11ARBVertexBufferObject();
    }
    else
    {
      impl = null;
    }
  }

  private static final Interface impl;

  private GLBuffers()
  { /* static class */ }
}
