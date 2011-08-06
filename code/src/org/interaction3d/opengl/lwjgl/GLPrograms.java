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

import java.nio.IntBuffer;
import org.lwjgl.BufferUtils;
import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_INFO_LOG_LENGTH;
import static org.lwjgl.opengl.GL20.glIsProgram;
import static org.lwjgl.opengl.GL20.glDeleteProgram;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glGetProgram;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glIsShader;
import static org.lwjgl.opengl.GL20.glDeleteShader;
import static org.lwjgl.opengl.GL20.glGetAttachedShaders;

public final class GLPrograms
{
  public static int program(int... gl_shaders)
  {
    int gl_program = glCreateProgram();
    for (int shader : gl_shaders)
    {
      glAttachShader(gl_program, shader);
    }

    linkProgram(gl_program);

    return gl_program;
  }

  static void linkProgram(int gl_program)
  {
    glLinkProgram(gl_program);

    int status = glGetProgram(gl_program, GL_LINK_STATUS);
    if (status == GL_FALSE)
    {
      int logSize = glGetProgram(gl_program, GL_INFO_LOG_LENGTH);
      String error = glGetProgramInfoLog(gl_program, logSize);
      throw new RuntimeException(error);
    }
  }

  static void deleteProgramAndShaders(int gl_program)
  {
    if (!glIsProgram(gl_program))
    {
      return;
    }

    int MAX = 5; // typically VERTEX, GEOMETRY, FRAGMENT
    IntBuffer count = BufferUtils.createIntBuffer(1);
    IntBuffer gl_shaders;
    do
    {
      count.put(0, MAX);
      gl_shaders = BufferUtils.createIntBuffer(MAX);
      glGetAttachedShaders(gl_program, count, gl_shaders);
    }
    while (MAX++ <= count.get(0));

    for (int i = 1; i < count.get(0); i++)
    {
      if (glIsShader(gl_shaders.get(i)))
      {
        glDeleteShader(gl_shaders.get(i));
      }
    }

    glDeleteProgram(gl_program);
  }

  private GLPrograms() { /* static class */ }

}
