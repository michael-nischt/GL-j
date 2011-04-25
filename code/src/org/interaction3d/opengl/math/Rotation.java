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

final class Rotation extends Matrix
{
  protected Rotation(float[] elements)
  {
    super(elements);
  }

  @Override
  public void rotateX(float radians)
  {
    float cos = (float) Math.cos(radians);
    float sin = (float) Math.sin(radians);

    float c2, c3;

    c2 = elements[_1_2_];
    c3 = elements[_1_3_];
    elements[_1_2_] = c2 * cos + c3 * sin;
    elements[_1_3_] = c3 * cos - c2 * sin;

    c2 = elements[_2_2_];
    c3 = elements[_2_3_];
    elements[_2_2_] = c2 * cos + c3 * sin;
    elements[_2_3_] = c3 * cos - c2 * sin;

    c2 = elements[_3_2_];
    c3 = elements[_3_3_];
    elements[_3_2_] = c2 * cos + c3 * sin;
    elements[_3_3_] = c3 * cos - c2 * sin;

    c2 = elements[_4_2_];
    c3 = elements[_4_3_];
    elements[_4_2_] = c2 * cos + c3 * sin;
    elements[_4_3_] = c3 * cos - c2 * sin;
  }

  @Override
  public void rotateY(float radians)
  {
    float cos = (float) Math.cos(radians);
    float sin = (float) Math.sin(radians);

    float c1, c3;

    c1 = elements[_1_1_];
    c3 = elements[_1_3_];
    elements[_1_1_] = c1 * cos - c3 * sin;
    elements[_1_3_] = c1 * sin + c3 * cos;

    c1 = elements[_2_1_];
    c3 = elements[_2_3_];
    elements[_2_1_] = c1 * cos - c3 * sin;
    elements[_2_3_] = c1 * sin + c3 * cos;

    c1 = elements[_3_1_];
    c3 = elements[_3_3_];
    elements[_3_1_] = c1 * cos - c3 * sin;
    elements[_3_3_] = c1 * sin + c3 * cos;

    c1 = elements[_4_1_];
    c3 = elements[_4_3_];
    elements[_4_1_] = c1 * cos - c3 * sin;
    elements[_4_3_] = c1 * sin + c3 * cos;
  }

  @Override
  public void rotateZ(float radians)
  {
    float cos = (float) Math.cos(radians);
    float sin = (float) Math.sin(radians);

    float c1, c2;

    c1 = elements[_1_1_];
    c2 = elements[_1_2_];
    elements[_1_1_] = c1 * cos + c2 * sin;
    elements[_1_2_] = c2 * cos - c1 * sin;

    c1 = elements[_2_1_];
    c2 = elements[_2_2_];
    elements[_2_1_] = c1 * cos + c2 * sin;
    elements[_2_2_] = c2 * cos - c1 * sin;

    c1 = elements[_3_1_];
    c2 = elements[_3_2_];
    elements[_3_1_] = c1 * cos + c2 * sin;
    elements[_3_2_] = c2 * cos - c1 * sin;

    c1 = elements[_4_1_];
    c2 = elements[_4_2_];
    elements[_4_1_] = c1 * cos + c2 * sin;
    elements[_4_2_] = c2 * cos - c1 * sin;
  }

  @Override
  public void loadRotationX(float radians)
  {
    float cos = (float) Math.cos(radians);
    float sin = (float) Math.sin(radians);

    elements[_1_1_] = 1;
    elements[_2_1_] = 0;
    elements[_3_1_] = 0;
    elements[_4_1_] = 0;

    elements[_1_2_] = 0;
    elements[_2_2_] = cos;
    elements[_3_2_] = sin;
    elements[_4_2_] = 0;

    elements[_1_3_] = 0;
    elements[_2_3_] = -sin;
    elements[_3_3_] = cos;
    elements[_4_3_] = 0;

    elements[_1_4_] = 0;
    elements[_2_4_] = 0;
    elements[_3_4_] = 0;
    elements[_4_4_] = 1;
  }

  @Override
  public void loadRotationY(float radians)
  {
    float cos = (float) Math.cos(radians);
    float sin = (float) Math.sin(radians);

    elements[_1_1_] = cos;
    elements[_2_1_] = 0;
    elements[_3_1_] = -sin;
    elements[_4_1_] = 0;

    elements[_1_2_] = 0;
    elements[_2_2_] = 1;
    elements[_3_2_] = 0;
    elements[_4_2_] = 0;

    elements[_1_3_] = sin;
    elements[_2_3_] = 0;
    elements[_3_3_] = cos;
    elements[_4_3_] = 0;

    elements[_1_4_] = 0;
    elements[_2_4_] = 0;
    elements[_3_4_] = 0;
    elements[_4_4_] = 1;
  }

  @Override
  public void loadRotationZ(float radians)
  {
    float cos = (float) Math.cos(radians);
    float sin = (float) Math.sin(radians);


    double[] x =
    {
      cos, sin, 0
    };
    double[] y =
    {
      -sin, cos, 0
    };
    double[] z =
    {
      0, 0, 1
    };

    elements[_1_1_] = cos;
    elements[_2_1_] = sin;
    elements[_3_1_] = 0;
    elements[_4_1_] = 0;

    elements[_1_2_] = -sin;
    elements[_2_2_] = cos;
    elements[_3_2_] = 0;
    elements[_4_2_] = 0;

    elements[_1_3_] = 0;
    elements[_2_3_] = 0;
    elements[_3_3_] = 1;
    elements[_4_3_] = 0;

    elements[_1_4_] = 0;
    elements[_2_4_] = 0;
    elements[_3_4_] = 0;
    elements[_4_4_] = 1;
  }

  @Override
  public void loadRotation(float radians, float x, float y, float z)
  {
    float lenSq = x * x + y * y + z * z;
    if (lenSq < 1e-8)
    {
      loadIdentity();
      return;
    }


    float cos = (float) Math.cos(radians);
    float sin = (float) Math.sin(radians);

    float nCos = 1 - cos;

    float xSin = x * sin;
    float ySin = y * sin;
    float zSin = z * sin;

    elements[_1_1_] = x * x * (nCos) + cos;
    elements[_2_1_] = x * y * (nCos) + zSin;
    elements[_3_1_] = x * z * (nCos) - ySin;
    elements[_4_1_] = 0;

    elements[_1_2_] = x * y * (nCos) - zSin;
    elements[_2_2_] = y * y * (nCos) + cos;
    elements[_3_2_] = y * z * (nCos) + xSin;
    elements[_4_2_] = 0;

    elements[_1_3_] = x * z * (nCos) + ySin;
    elements[_2_3_] = y * z * (nCos) - xSin;
    elements[_3_3_] = z * z * (nCos) + cos;
    elements[_4_3_] = 0;

    elements[_1_4_] = 0;
    elements[_2_4_] = 0;
    elements[_3_4_] = 0;
    elements[_4_4_] = 1;
  }

  @Override
  public void loadRotation(Vector axisAngle)
  {
    loadRotation(axisAngle.w(), axisAngle.x(), axisAngle.y(), axisAngle.z());
  }

  @Override
  public void rotate(float radians, float x, float y, float z)
  {
    float lenSq = x * x + y * y + z * z;
    if (lenSq < 1e-8)
    {
      return;
    }

    float cos = (float) Math.cos(radians);
    float sin = (float) Math.sin(radians);

    float nCos = 1 - cos;

    float xSin = x * sin;
    float ySin = y * sin;
    float zSin = z * sin;

    float m11 = x * x * (nCos) + cos;
    float m21 = x * y * (nCos) + zSin;
    float m31 = x * z * (nCos) - ySin;

    float m12 = x * y * (nCos) - zSin;
    float m22 = y * y * (nCos) + cos;
    float m32 = y * z * (nCos) + xSin;

    float m13 = x * z * (nCos) + ySin;
    float m23 = y * z * (nCos) - xSin;
    float m33 = z * z * (nCos) + cos;


    multiply(m11, m12, m13, m21, m22, m23, m31, m32, m33);
  }

  @Override
  public void rotate(Vector axisAngle)
  {
    rotate(axisAngle.w(), axisAngle.x(), axisAngle.y(), axisAngle.z());
  }

  private void multiply(float m11, float m12, float m13,
    float m21, float m22, float m23,
    float m31, float m32, float m33)
  {
    float c1, c2, c3;

    c1 = elements[_1_1_];
    c2 = elements[_1_2_];
    c3 = elements[_1_3_];
    elements[_1_1_] = c1 * m11 + c2 * m21 + c3 * m31;
    elements[_1_2_] = c1 * m12 + c2 * m22 + c3 * m32;
    elements[_1_3_] = c1 * m13 + c2 * m23 + c3 * m33;

    c1 = elements[_2_1_];
    c2 = elements[_2_2_];
    c3 = elements[_2_3_];
    elements[_2_1_] = c1 * m11 + c2 * m21 + c3 * m31;
    elements[_2_2_] = c1 * m12 + c2 * m22 + c3 * m32;
    elements[_2_3_] = c1 * m13 + c2 * m23 + c3 * m33;

    c1 = elements[_3_1_];
    c2 = elements[_3_2_];
    c3 = elements[_3_3_];
    elements[_3_1_] = c1 * m11 + c2 * m21 + c3 * m31;
    elements[_3_2_] = c1 * m12 + c2 * m22 + c3 * m32;
    elements[_3_3_] = c1 * m13 + c2 * m23 + c3 * m33;
  }
}
