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
package org.interaction3d.opengl;

public final class Vertex
{
  static public final class Attribute
  {
    public enum Type
    {
      BYTE, UNSIGNED_BYTE,
      SHORT, UNSIGNED_SHORT,
      INT, UNSIGNED_INT,
      FLOAT,
      DOUBLE;
    }

    public final int index;
    public final int size;
    public final Type type;
    public final boolean normalized;
    public final int stride;
    public final long offset;

    private Attribute(int index, int size, Type type, boolean normalized, int stride, long offset)
    {
      this.index = index;
      this.size = size;
      this.type = type;
      this.normalized = normalized;
      this.stride = stride;
      this.offset = offset;
    }
  }

  public static Attribute attribute(int index, int size, Attribute.Type type, boolean normalized, int stride, long offset)
  {
    return new Attribute(index, size, type, normalized, stride, offset);
  }

  static public final class Array
  {
    static public final class AttribPointer
    {
      public final int arrayBufferObject;
      public final Attribute[] attributes;

      private  AttribPointer(int arrayBufferObject, Attribute[] attributes)
      {
        this.arrayBufferObject = arrayBufferObject;
        this.attributes = attributes;
      }
    }

    public final int elementArrayBufferObject;
    public final AttribPointer[] attribPointers;

    private Array(int gl, AttribPointer[] attribPointers)
    {
      this.elementArrayBufferObject = gl;
      this.attribPointers = attribPointers;
    }
  }

  public static Array.AttribPointer arrayBuffer(int elementArrayBufferObject, Attribute... attributes)
  {
      return new Array.AttribPointer(elementArrayBufferObject, attributes);
  }

  public static Array array(int gl, Array.AttribPointer... attribPointers)
  {
      return new Array(gl, attribPointers);
  }


  private Vertex() { /* static class */ }
}
