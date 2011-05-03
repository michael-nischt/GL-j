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

import org.interaction3d.opengl.Draw;
import org.interaction3d.opengl.lwjgl.GL;
import org.lwjgl.opengl.GL11;

public final class GLDrawCalls
{
   @GL(value=GL11.class)
  public static void draw(Draw.Elements elements)
  {
    GL11.glDrawElements(mode(elements.mode), elements.count, type(elements.type), elements.offset);
  }

   @GL(value=GL11.class)
  public static void draw(Draw.Arrays arrays)
  {
    GL11.glDrawArrays(mode(arrays.mode), arrays.first, arrays.count);
  }

  private static int type(Draw.Type type)
  {
    switch(type)
    {
      case UNSIGNED_BYTE:
        return GL11.GL_UNSIGNED_BYTE;
      case UNSIGNED_SHORT:
        return GL11.GL_UNSIGNED_SHORT;
      case UNSIGNED_INT:
        return GL11.GL_UNSIGNED_INT;
      default:
        throw new IllegalArgumentException();
    }
  }

  private static int mode(Draw.Mode mode)
  {
    switch(mode)
    {
      case TRIANGLES:
        return GL11.GL_TRIANGLES;
      default:
        throw new IllegalArgumentException();
    }
  }

  private GLDrawCalls() { /* static class */  }

}
