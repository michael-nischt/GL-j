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

import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

public final class GLProgramLocations
{  
  private GLProgramLocations() { /* static class */ } 

  
  @GL(GL20.class)
  static public void attributes(int gl_program, Object attributes)
  {
    new GLAttributes().locations(gl_program, attributes);
  }

  @GL(GL20.class)
  static public void attributes(int gl_program, String prefix, Object[] attributeArray)
  {
    new GLAttributes().locations(gl_program, prefix, attributeArray);
  }

  @GL(GL20.class)
  static public void attributes(int gl_program, String prefix, Object attributes)
  {
    new GLAttributes().locations(gl_program, prefix, attributes);
  }

  @GL(GL20.class)
  static public void linkAttributes(int gl_program, Object attributes)
  {
    new GLAttributes().linkLocations(gl_program, attributes);
  }

  @GL(GL20.class)
  static public void linkAttributes(int gl_program, String prefix, Object[] attributeArray)
  {
    new GLAttributes().linkLocations(gl_program, prefix, attributeArray);
  }

  @GL(GL20.class)
  static public void linkAttributes(int gl_program, String prefix, Object attributes)
  {
    new GLAttributes().linkLocations(gl_program, prefix, attributes);
  }


  @GL(GL20.class)
  static public void uniforms(int gl_program, Object uniforms)
  {
    new GLUniforms().locations(gl_program, uniforms);
  }

  @GL(GL20.class)
  static public void uniforms(int gl_program, String prefix, Object[] uniformArray)
  {
    new GLUniforms().locations(gl_program, prefix, uniformArray);
  }

  @GL(GL20.class)
  static public void uniforms(int gl_program, String prefix, Object uniforms)
  {
    new GLUniforms().locations(gl_program, prefix, uniforms);
  }


  @GL(GL30.class)
  static public void fragData(int gl_program, Object fragDataLocs)
  {
    new GLFragData().locations(gl_program, fragDataLocs);
  }

  @GL(GL30.class)
  static public void fragData(int gl_program, String prefix, Object[] fragDataLocArray)
  {
    new GLFragData().locations(gl_program, prefix, fragDataLocArray);
  }

  @GL(GL30.class)
  static public void fragData(int gl_program, String prefix, Object fragDataLocs)
  {
    new GLFragData().locations(gl_program, prefix, fragDataLocs);
  }

  @GL(GL30.class)
  static public void linkFragData(int gl_program, Object fragDataLocs)
  {
    new GLFragData().linkLocations(gl_program, fragDataLocs);
  }

  @GL(GL30.class)
  static public void linkFragData(int gl_program, String prefix, Object[] fragDataLocArray)
  {
    new GLFragData().linkLocations(gl_program, prefix, fragDataLocArray);
  }

  @GL(GL30.class)
  static public void linkFragData(int gl_program, String prefix, Object fragDataLocs)
  {
    new GLFragData().linkLocations(gl_program, prefix, fragDataLocs);
  }
  
}