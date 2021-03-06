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

public final class Draw
{
  public enum Mode
  {
    //1.1
    POINTS,
    LINE_STRIP, LINE_LOOP, LINES,
    TRIANGLE_STRIP, TRIANGLE_FAN, TRIANGLES,
    //3.2
    //LINE_STRIP_ADJACENCY, LINES_ADJACENCY,
    //TRIANGLE_STRIP_ADJACENCY, TRIANGLES_ADJACENCY,
    //4.0
    //PATCHES
  }

  public enum Type
  {
    UNSIGNED_BYTE, UNSIGNED_SHORT, UNSIGNED_INT;
  }

  public static final class Elements
  {
    public final Mode mode;
    public final int count;
    public final Type type;
    public final long offset;

    private Elements(Mode mode, int count, Type type, long offset)
    {
      this.mode = mode;
      this.count = count;
      this.type = type;
      this.offset = offset;
    }
  }

  public static final class Arrays
  {
    public final Mode mode;
    public final int first;
    public final int count;

    private Arrays(Mode mode, int first, int count)
    {
      this.mode = mode;
      this.first = first;
      this.count = count;
    }
  }

  public static Elements elements(Mode mode, int count, Type type, long offset)
  {
    return new Elements(mode, count, type, offset);
  }

  public static Arrays arrays(Mode mode, int first, int count)
  {
    return new Arrays(mode, first, count);
  }

  private Draw() { /* static class */ }
}
