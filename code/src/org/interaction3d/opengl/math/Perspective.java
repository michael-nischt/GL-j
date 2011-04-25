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

final class Perspective extends Matrix
{
  protected Perspective(float[] elements)
  {
    super(elements);
  }

  // (0,0,-zNear) -> -1
  // (0,0, -zFar) -> +1
  // RH = right: +x, up: +y, front: -z
  @Override //rhPerspectiveNegativeZ
  public void perspective(float fovY, float aspect, float zNear, float zFar)
  {
    if (zNear <= 0 || zFar < zNear)
    {
      throw new IllegalArgumentException();
    }
    if (fovY <= 0 || fovY > (float) Math.PI)
    {
      throw new IllegalArgumentException();
    }
    if (aspect <= 0)
    {
      throw new IllegalArgumentException();
    }


    float f = (1 / (float) Math.tan(fovY / 2));

    float neg_depth = (zNear - zFar);

    //1st column
    elements[_1_1_] = f / aspect;
    elements[_2_1_] = 0;
    elements[_3_1_] = 0;
    elements[_4_1_] = 0;

    //2nd column
    elements[_1_2_] = 0;
    elements[_2_2_] = f;
    elements[_3_2_] = 0;
    elements[_4_2_] = 0;

    //3rd column
    elements[_1_3_] = 0;
    elements[_2_3_] = 0;
    elements[_3_3_] = (zFar + zNear) / neg_depth;
    elements[_4_3_] = -1;

    //4th column
    elements[_1_4_] = 0;
    elements[_2_4_] = 0;
    elements[_3_4_] = 2 * (zNear * zFar) / neg_depth;
    elements[_4_4_] = 0;
  }

  // (0,0,-zNear) -> -1
  // (0,0, -zFar) -> +1
  // RH = right: +x, up: +y, front: -z
  private void lhPerspectiveNegativeZ(float fovY, float aspect, float zNear, float zFar)
  {
    if (zNear <= 0 || zFar < zNear)
    {
      throw new IllegalArgumentException();
    }
    if (fovY <= 0 || fovY > (float) Math.PI)
    {
      throw new IllegalArgumentException();
    }
    if (aspect <= 0)
    {
      throw new IllegalArgumentException();
    }


    float f = (1 / (float) Math.tan(fovY / 2));

    float neg_depth = (zNear - zFar);

    //1st column
    elements[_1_1_] = -f / aspect;
    elements[_2_1_] = 0;
    elements[_3_1_] = 0;
    elements[_4_1_] = 0;

    //2nd column
    elements[_1_2_] = 0;
    elements[_2_2_] = f;
    elements[_3_2_] = 0;
    elements[_4_2_] = 0;

    //3rd column
    elements[_1_3_] = 0;
    elements[_2_3_] = 0;
    elements[_3_3_] = (zFar + zNear) / neg_depth;
    elements[_4_3_] = -1;

    //4th column
    elements[_1_4_] = 0;
    elements[_2_4_] = 0;
    elements[_3_4_] = 2 * (zNear * zFar) / neg_depth;
    elements[_4_4_] = 0;
  }

  // (0,0,+zNear) -> -1
  // (0,0, +zFar) -> +1
  // RH = right: -x, up: +y, front: +z
  private void rhPerspectivePositiveZ(float fovY, float aspect, float zNear, float zFar)
  {
    if (zNear <= 0 || zFar < zNear)
    {
      throw new IllegalArgumentException();
    }
    if (fovY <= 0 || fovY > (float) Math.PI)
    {
      throw new IllegalArgumentException();
    }
    if (aspect <= 0)
    {
      throw new IllegalArgumentException();
    }


    float f = (1 / (float) Math.tan(fovY / 2));

    float Q = zFar / (zFar - zNear);

    //1st column
    elements[_1_1_] = -f / aspect;
    elements[_2_1_] = 0;
    elements[_3_1_] = 0;
    elements[_4_1_] = 0;

    //2nd column
    elements[_1_2_] = 0;
    elements[_2_2_] = f;
    elements[_3_2_] = 0;
    elements[_4_2_] = 0;

    //3rd column
    elements[_1_3_] = 0;
    elements[_2_3_] = 0;
    elements[_3_3_] = 2 * Q - 1;//Q;
    elements[_4_3_] = 1;

    //4th column
    elements[_1_4_] = 0;
    elements[_2_4_] = 0;
    elements[_3_4_] = -2 * Q * zNear;//-Q*near;
    elements[_4_4_] = 0;
  }

  // (0,0,+zNear) -> -1
  // (0,0, +zFar) -> +1
  // LH = right: +x, up: +y, front: +z
  private void lhPerspectivePositiveZ(float fovY, float aspect, float zNear, float zFar)
  {
    if (zNear <= 0 || zFar < zNear)
    {
      throw new IllegalArgumentException();
    }
    if (fovY <= 0 || fovY > (float) Math.PI)
    {
      throw new IllegalArgumentException();
    }
    if (aspect <= 0)
    {
      throw new IllegalArgumentException();
    }


    float f = (1 / (float) Math.tan(fovY / 2));

    float Q = zFar / (zFar - zNear);

    //1st column
    elements[_1_1_] = f / aspect;
    elements[_2_1_] = 0;
    elements[_3_1_] = 0;
    elements[_4_1_] = 0;

    //2nd column
    elements[_1_2_] = 0;
    elements[_2_2_] = f;
    elements[_3_2_] = 0;
    elements[_4_2_] = 0;

    //3rd column
    elements[_1_3_] = 0;
    elements[_2_3_] = 0;
    elements[_3_3_] = 2 * Q - 1;//Q;
    elements[_4_3_] = 1;

    //4th column
    elements[_1_4_] = 0;
    elements[_2_4_] = 0;
    elements[_3_4_] = -2 * Q * zNear;//-Q*near;
    elements[_4_4_] = 0;
  }
}
