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

final class LookAt extends Matrix
{
  private static final float LOOK_AT_THRESHOLD = 0;//1e-20f;

  protected LookAt(float[] elements)
  {
    super(elements);
  }

  @Override //RH, right: +x, up: +y, front: -z
  public void lookAt(float eyeX, float eyeY, float eyeZ,
    float centerX, float centerY, float centerZ,
    float upX, float upY, float upZ)
  {
    float fX = centerX - eyeX;
    float fY = centerY - eyeY;
    float fZ = centerZ - eyeZ;
    // normalize
    {
      float len;

      len = (float) Math.sqrt(fX * fX + fY * fY + fZ * fZ);
      if (Math.abs(len) < LOOK_AT_THRESHOLD)
      {
        throw new IllegalArgumentException();
      }

      fX /= len;
      fY /= len;
      fZ /= len;

      len = (float) Math.sqrt(upX * upX + upY * upY + upZ * upZ);
      if (Math.abs(len) < LOOK_AT_THRESHOLD)
      {
        throw new IllegalArgumentException();
      }
      upX /= len;
      upY /= len;
      upZ /= len;
    }

    // s = f x up
    float sX = (fY * upZ - fZ * upY);
    float sY = (fZ * upX - fX * upZ);
    float sZ = (fX * upY - fY * upX);
    // normalize
    {
      float len = (float) Math.sqrt(sX * sX + sY * sY + sZ * sZ);
      if (Math.abs(len) < LOOK_AT_THRESHOLD)
      {
        throw new IllegalArgumentException();
      }

      sX /= len;
      sY /= len;
      sZ /= len;
    }

    // u = s x f
    float uX = (sY * fZ - sZ * fY);
    float uY = (sZ * fX - sX * fZ);
    float uZ = (sX * fY - sY * fX);

    // 1st column
    elements[_1_1_] = sX;
    elements[_2_1_] = uX;
    elements[_3_1_] = -fX;
    elements[_4_1_] = 0;

    // 2nd column
    elements[_1_2_] = sY;
    elements[_2_2_] = uY;
    elements[_3_2_] = -fY;
    elements[_4_2_] = 0;

    // 3rd column
    elements[_1_3_] = sZ;
    elements[_2_3_] = uZ;
    elements[_3_3_] = -fZ;
    elements[_4_3_] = 0;

    // 4th column
    elements[_1_4_] = -(sX * eyeX + sY * eyeY - sZ * eyeZ);
    elements[_2_4_] = -(uX * eyeX + uY * eyeY - uZ * eyeZ);
    elements[_3_4_] = +(fX * eyeX + fY * eyeY - fZ * eyeZ);
    elements[_4_4_] = 1;
  }
}
