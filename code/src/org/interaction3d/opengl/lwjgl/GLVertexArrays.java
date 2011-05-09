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

import org.interaction3d.opengl.Buffer;
import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.interaction3d.opengl.Vertex;
import org.interaction3d.opengl.Vertex.Attribute;
import org.interaction3d.opengl.lwjgl.GL;


import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.APPLEVertexArrayObject;
import static org.lwjgl.opengl.APPLEVertexArrayObject.glBindVertexArrayAPPLE;
import static org.lwjgl.opengl.APPLEVertexArrayObject.glGenVertexArraysAPPLE;
import static org.lwjgl.opengl.APPLEVertexArrayObject.glIsVertexArrayAPPLE;
import static org.lwjgl.opengl.APPLEVertexArrayObject.glDeleteVertexArraysAPPLE;

import org.lwjgl.opengl.ARBVertexArrayObject;

import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL30;
import org.lwjgl.opengl.GL20;

import static org.lwjgl.opengl.GL11.GL_BYTE;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.GL_SHORT;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_SHORT;
import static org.lwjgl.opengl.GL11.GL_INT;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_DOUBLE;
import static org.lwjgl.opengl.GL11.glGetInteger;

import static org.lwjgl.opengl.GL15.GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING;
import static org.lwjgl.opengl.GL15.glIsBuffer;
import static org.lwjgl.opengl.GL15.glDeleteBuffers;


import static org.lwjgl.opengl.ARBVertexBufferObject.GL_ARRAY_BUFFER_ARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.GL_ELEMENT_ARRAY_BUFFER_ARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.glGenBuffersARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.glBindBufferARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.glBufferDataARB;

import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;

import static org.lwjgl.opengl.GL15.GL_STREAM_DRAW;
import static org.lwjgl.opengl.GL15.GL_STREAM_READ;
import static org.lwjgl.opengl.GL15.GL_STREAM_COPY;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.GL_STATIC_READ;
import static org.lwjgl.opengl.GL15.GL_STATIC_COPY;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_DRAW;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_READ;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_COPY;

import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

import static org.lwjgl.opengl.GL20.GL_MAX_VERTEX_ATTRIBS;
import static org.lwjgl.opengl.GL30.glIsVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGetVertexAttribIu;


import org.lwjgl.opengl.ARBVertexBufferObject;
import static org.lwjgl.opengl.ARBVertexBufferObject.GL_STREAM_DRAW_ARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.GL_STREAM_READ_ARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.GL_STREAM_COPY_ARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.GL_STATIC_DRAW_ARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.GL_STATIC_READ_ARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.GL_STATIC_COPY_ARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.GL_DYNAMIC_DRAW_ARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.GL_DYNAMIC_READ_ARB;
import static org.lwjgl.opengl.ARBVertexBufferObject.GL_DYNAMIC_COPY_ARB;

public class GLVertexArrays
{
  @GL(value = GL11.class, extensions = ARBVertexBufferObject.class)
  public static int arrayBufferARB(long size, Buffer.Usage usage)
  {
    int gl_array_buffer = glGenBuffersARB();
    glBindBufferARB(GL_ARRAY_BUFFER_ARB, gl_array_buffer);
    glBufferDataARB(GL_ARRAY_BUFFER_ARB, size, glBufferUsageARB(usage));
    glBindBufferARB(GL_ARRAY_BUFFER_ARB, 0);
    return gl_array_buffer;
  }

  @GL(value = GL11.class, extensions = ARBVertexBufferObject.class)
  public static int arrayBufferARB(ByteBuffer vertexBuffer, Buffer.Usage usage)
  {
    int gl_array_buffer = glGenBuffersARB();
    glBindBufferARB(GL_ARRAY_BUFFER_ARB, gl_array_buffer);
    glBufferDataARB(GL_ARRAY_BUFFER_ARB, vertexBuffer, glBufferUsageARB(usage));
    glBindBufferARB(GL_ARRAY_BUFFER_ARB, 0);
    return gl_array_buffer;
  }

  @GL(value = GL11.class, extensions = ARBVertexBufferObject.class)
  public static int elementArrayBufferARB(long size, Buffer.Usage usage)
  {
    int gl_element_array_buffer = glGenBuffersARB();
    glBindBufferARB(GL_ELEMENT_ARRAY_BUFFER_ARB, gl_element_array_buffer);
    glBufferDataARB(GL_ELEMENT_ARRAY_BUFFER_ARB, size, glBufferUsageARB(usage));
    glBindBufferARB(GL_ELEMENT_ARRAY_BUFFER_ARB, 0);
    return gl_element_array_buffer;
  }

  @GL(value = GL11.class, extensions = ARBVertexBufferObject.class)
  public static int elementArrayBufferARB(ByteBuffer indexBuffer, Buffer.Usage usage)
  {
    int gl_element_array_buffer = glGenBuffersARB();
    glBindBufferARB(GL_ELEMENT_ARRAY_BUFFER_ARB, gl_element_array_buffer);
    glBufferDataARB(GL_ELEMENT_ARRAY_BUFFER_ARB, indexBuffer, glBufferUsageARB(usage));
    glBindBufferARB(GL_ELEMENT_ARRAY_BUFFER_ARB, 0);
    return gl_element_array_buffer;
  }

  @GL(GL15.class)
  public static int arrayBuffer(long size, Buffer.Usage usage)
  {
    int gl_array_buffer = glGenBuffers();
    glBindBuffer(GL_ARRAY_BUFFER, gl_array_buffer);
    glBufferData(GL_ARRAY_BUFFER, size, glBufferUsage(usage));
    glBindBuffer(GL_ARRAY_BUFFER, 0);
    return gl_array_buffer;
  }

  @GL(GL15.class)
  public static int arrayBuffer(ByteBuffer vertexBuffer, Buffer.Usage usage)
  {
    int gl_array_buffer = glGenBuffers();
    glBindBuffer(GL_ARRAY_BUFFER, gl_array_buffer);
    glBufferData(GL_ARRAY_BUFFER, vertexBuffer, glBufferUsage(usage));
    glBindBuffer(GL_ARRAY_BUFFER, 0);
    return gl_array_buffer;
  }

  public static int elementArrayBuffer(long size, Buffer.Usage usage)
  {
    int gl_element_array_buffer = glGenBuffers();
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, gl_element_array_buffer);
    glBufferData(GL_ELEMENT_ARRAY_BUFFER, size, glBufferUsage(usage));
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    return gl_element_array_buffer;
  }

  @GL(GL15.class)
  public static int elementArrayBuffer(ByteBuffer indexBuffer, Buffer.Usage usage)
  {
    int gl_element_array_buffer = glGenBuffers();
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, gl_element_array_buffer);
    glBufferData(GL_ELEMENT_ARRAY_BUFFER, indexBuffer, glBufferUsage(usage));
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    return gl_element_array_buffer;
  }


  @GL(GL20.class)
  public static void setVertexAttributes(int gl_array_buffer, Attribute... attributePointers)
  {
    glBindBuffer(GL_ARRAY_BUFFER, gl_array_buffer);
    for (Attribute attrib : attributePointers)
    {
      glVertexAttribPointer(attrib.index, attrib.size, glAttributeType(attrib.type), attrib.normalized, attrib.stride, attrib.offset);
      glEnableVertexAttribArray(attrib.index);
    }
    glBindBuffer(GL_ARRAY_BUFFER, 0);
  }

  @GL(GL20.class)
  static void setVertexArray(Vertex.Array vertexArray)
  {
    for (Vertex.Array.AttribPointer vertexArrayBuffer : vertexArray.attribPointers)
    {
      int gl_array_buffer = vertexArrayBuffer.arrayBufferObject;
      Vertex.Attribute[] attributePointers = vertexArrayBuffer.attributes;
      setVertexAttributes(gl_array_buffer, attributePointers);
    }
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vertexArray.elementArrayBufferObject);
  }

  @GL(GL20.class)
  public static void clearVertexArray()
  {
    int max = glGetInteger(GL_MAX_VERTEX_ATTRIBS);
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    for (int i = 0; i < max; i++)
    {
      glDisableVertexAttribArray(i);
    }
  }

  @GL(value = GL20.class, extensions = APPLEVertexArrayObject.class)
  public static int vertexArrayAPPLE(Vertex.Array vertexArray)
  {
    int gl_vertex_array = glGenVertexArraysAPPLE();
    glBindVertexArrayAPPLE(gl_vertex_array);
    setVertexArray(vertexArray);
    glBindVertexArrayAPPLE(0);
    return gl_vertex_array;
  }

  @GL(value = GL20.class, extensions = ARBVertexArrayObject.class)
  public static int vertexArrayARB(Vertex.Array vertexArray)
  {
    int gl_vertex_array = ARBVertexArrayObject.glGenVertexArrays();
    ARBVertexArrayObject.glBindVertexArray(gl_vertex_array);
    setVertexArray(vertexArray);
    ARBVertexArrayObject.glBindVertexArray(0);
    return gl_vertex_array;
  }

  @GL(GL30.class)
  public static int vertexArray(Vertex.Array vertexArray)
  {
    int gl_vertex_array = glGenVertexArrays();
    glBindVertexArray(gl_vertex_array);
    setVertexArray(vertexArray);
    glBindVertexArray(0);
    return gl_vertex_array;
  }


  @GL(value = GL20.class, extensions = APPLEVertexArrayObject.class)
  static void deleteVertexArrayAndBuffersAPPLE(int gl_vertex_array)
  {
    if (!glIsVertexArrayAPPLE(gl_vertex_array))
    {
      return;
    }
    glBindVertexArrayAPPLE(gl_vertex_array);
    IntBuffer gl_buffer = BufferUtils.createIntBuffer(1);
    int max = glGetInteger(GL_MAX_VERTEX_ATTRIBS);
    for (int i = 0; i < max; i++)
    {
      glGetVertexAttribIu(i, GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING, gl_buffer);

      if (glIsBuffer(gl_buffer.get(0)))
      {
        glDeleteBuffers(gl_buffer);
      }
    }
    glBindVertexArrayAPPLE(gl_vertex_array);
    glDeleteVertexArraysAPPLE(gl_vertex_array);
  }

  @GL(value = GL20.class, extensions = ARBVertexArrayObject.class)
  static void deleteVertexArrayAndBuffersARB(int gl_vertex_array)
  {
    if (!ARBVertexArrayObject.glIsVertexArray(gl_vertex_array))
    {
      return;
    }
    ARBVertexArrayObject.glBindVertexArray(gl_vertex_array);
    IntBuffer gl_buffer = BufferUtils.createIntBuffer(1);
    int max = glGetInteger(GL_MAX_VERTEX_ATTRIBS);
    for (int i = 0; i < max; i++)
    {
      glGetVertexAttribIu(i, GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING, gl_buffer);

      if (glIsBuffer(gl_buffer.get(0)))
      {
        glDeleteBuffers(gl_buffer);
      }
    }
    ARBVertexArrayObject.glBindVertexArray(gl_vertex_array);
    ARBVertexArrayObject.glDeleteVertexArrays(gl_vertex_array);
  }

  @GL(GL30.class)
  static void deleteVertexArrayAndBuffers(int gl_vertex_array)
  {
    if (!glIsVertexArray(gl_vertex_array))
    {
      return;
    }
    glBindVertexArray(gl_vertex_array);
    IntBuffer gl_buffer = BufferUtils.createIntBuffer(1);
    int max = glGetInteger(GL_MAX_VERTEX_ATTRIBS);
    for (int i = 0; i < max; i++)
    {
      glGetVertexAttribIu(i, GL_VERTEX_ATTRIB_ARRAY_BUFFER_BINDING, gl_buffer);

      if (glIsBuffer(gl_buffer.get(0)))
      {
        glDeleteBuffers(gl_buffer);
      }
    }
    glBindVertexArray(0);
    glDeleteVertexArrays(gl_vertex_array);
  }

  @GL(value = GL11.class, extensions = ARBVertexBufferObject.class)
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

  @GL(value = GL15.class)
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

  @GL(value = GL11.class)
  private static int glAttributeType(Attribute.Type type)
  {
    switch (type)
    {
      case BYTE:
        return GL_BYTE;
      case UNSIGNED_BYTE:
        return GL_UNSIGNED_BYTE;
      case SHORT:
        return GL_SHORT;
      case UNSIGNED_SHORT:
        return GL_UNSIGNED_SHORT;
      case INT:
        return GL_INT;
      case UNSIGNED_INT:
        return GL_UNSIGNED_INT;
      case FLOAT:
        return GL_FLOAT;
      case DOUBLE:
        return GL_DOUBLE;
      default:
        throw new IllegalArgumentException();
    }
  }

  private GLVertexArrays() { /* static class */ }
}
