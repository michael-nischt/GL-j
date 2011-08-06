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

import org.lwjgl.opengl.ContextCapabilities;
import org.lwjgl.opengl.GLContext;
import java.nio.IntBuffer;

import org.interaction3d.opengl.Vertex;
import org.interaction3d.opengl.Vertex.Attribute;


import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.APPLEVertexArrayObject.glBindVertexArrayAPPLE;
import static org.lwjgl.opengl.APPLEVertexArrayObject.glGenVertexArraysAPPLE;
import static org.lwjgl.opengl.APPLEVertexArrayObject.glIsVertexArrayAPPLE;
import static org.lwjgl.opengl.APPLEVertexArrayObject.glDeleteVertexArraysAPPLE;

import org.lwjgl.opengl.ARBVertexArrayObject;


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



import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;


import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;

import static org.lwjgl.opengl.GL20.GL_MAX_VERTEX_ATTRIBS;
import static org.lwjgl.opengl.GL30.glIsVertexArray;
import static org.lwjgl.opengl.GL30.glDeleteVertexArrays;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGetVertexAttribIu;

public class GLVertexArrays
{
  private interface Interface
  {
    int vertexArray(Vertex.Array vertexArray);

    void deleteVertexArrayAndBuffers(int gl_vertex_array);
  }

  private static final class GL30 implements Interface
  {
    @Override
    public int vertexArray(Vertex.Array vertexArray)
    {
      int gl_vertex_array = glGenVertexArrays();
      glBindVertexArray(gl_vertex_array);
      setVertexArray(vertexArray);
      glBindVertexArray(0);
      return gl_vertex_array;
    }

    @Override
    public void deleteVertexArrayAndBuffers(int gl_vertex_array)
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
  }

  private static final class GL20ARBVertexArrayObject implements Interface
  {
    @Override
    public int vertexArray(Vertex.Array vertexArray)
    {
      int gl_vertex_array = ARBVertexArrayObject.glGenVertexArrays();
      ARBVertexArrayObject.glBindVertexArray(gl_vertex_array);
      setVertexArray(vertexArray);
      ARBVertexArrayObject.glBindVertexArray(0);
      return gl_vertex_array;
    }

    @Override
    public void deleteVertexArrayAndBuffers(int gl_vertex_array)
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
  }

  private static final class GL20APPLEVertexArrayObject implements Interface
  {
    @Override
    public int vertexArray(Vertex.Array vertexArray)
    {
      int gl_vertex_array = glGenVertexArraysAPPLE();
      glBindVertexArrayAPPLE(gl_vertex_array);
      setVertexArray(vertexArray);
      glBindVertexArrayAPPLE(0);
      return gl_vertex_array;
    }

    @Override
    public void deleteVertexArrayAndBuffers(int gl_vertex_array)
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
  }

  public static int vertexArray(Vertex.Array vertexArray)
  {
    return impl.vertexArray(vertexArray);
  }

  static void deleteVertexArrayAndBuffers(int gl_vertex_array)
  {
    impl.deleteVertexArrayAndBuffers(gl_vertex_array);
  }

    // GL20
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

  // GL20
  public static void setVertexArray(Vertex.Array vertexArray)
  {
    for (Vertex.Array.AttribPointer vertexArrayBuffer : vertexArray.attribPointers)
    {
      int gl_array_buffer = vertexArrayBuffer.arrayBufferObject;
      Vertex.Attribute[] attributePointers = vertexArrayBuffer.attributes;
      setVertexAttributes(gl_array_buffer, attributePointers);
    }
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vertexArray.elementArrayBufferObject);
  }

  // GL20
  public static void clearVertexArray()
  {
    int max = glGetInteger(GL_MAX_VERTEX_ATTRIBS);
    glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, 0);
    for (int i = 0; i < max; i++)
    {
      glDisableVertexAttribArray(i);
    }
  }

  // GL11
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

  static
  {
    ContextCapabilities caps = GLContext.getCapabilities();
    if (caps.OpenGL30)
    {
      impl = new GL30();
    }
    else if (caps.GL_ARB_vertex_array_object)
    {
      impl = new GL20ARBVertexArrayObject();
    }
    else if (caps.GL_APPLE_vertex_array_object)
    {
      impl = new GL20APPLEVertexArrayObject();
    }
    else
    {
      impl = null;
    }
  }
  private static final Interface impl;

  private GLVertexArrays()
  { /* static class */ }
}
