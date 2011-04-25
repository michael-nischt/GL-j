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
package org.interaction3d.opengl.math;

final class Translation extends Matrix
{
  protected Translation(float x, float y, float z)
  {
    super(new float[]
      {
        1, 0, 0, 0, // 1st column
        0, 1, 0, 0, // 2nd column
        0, 0, 1, 0, // 3rd column
        x, y, z, 1, // 4th column
      });
  }

  protected Translation(float[] elements)
  {
    super(elements);
  }

  @Override
  public void translate(float x, float y, float z)
  {
    elements[_1_4_] += elements[_1_1_] * x + elements[_1_2_] * y + elements[_1_3_] * z;
    elements[_2_4_] += elements[_2_1_] * x + elements[_2_2_] * y + elements[_2_3_] * z;
    elements[_3_4_] += elements[_3_1_] * x + elements[_3_2_] * y + elements[_3_3_] * z;
    elements[_4_4_] += elements[_4_1_] * x + elements[_4_2_] * y + elements[_4_3_] * z;
  }

  @Override
  public void loadTranslation(float x, float y, float z)
  {
    elements[_1_1_] = 1;
    elements[_2_1_] = 0;
    elements[_3_1_] = 0;
    elements[_4_1_] = 0;

    elements[_1_2_] = 0;
    elements[_2_2_] = 1;
    elements[_3_2_] = 0;
    elements[_4_2_] = 0;

    elements[_1_3_] = 0;
    elements[_2_3_] = 0;
    elements[_3_3_] = 1;
    elements[_4_3_] = 0;

    elements[_1_4_] = x;
    elements[_2_4_] = y;
    elements[_3_4_] = z;
    elements[_4_4_] = 1;
  }
}
