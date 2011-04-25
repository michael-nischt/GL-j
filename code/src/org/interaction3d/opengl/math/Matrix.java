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

import static java.lang.System.arraycopy;

public class Matrix implements Cloneable
{
  public static final int _1_1_ = 0, _1_2_ = 4, _1_3_ = 8, _1_4_ = 12;
  public static final int _2_1_ = 1, _2_2_ = 5, _2_3_ = 9, _2_4_ = 13;
  public static final int _3_1_ = 2, _3_2_ = 6, _3_3_ = 10, _3_4_ = 14;
  public static final int _4_1_ = 3, _4_2_ = 7, _4_3_ = 11, _4_4_ = 15;
  public final float[] elements;

  protected Matrix(float[] elements)
  {
    if (elements.length != 16)
    {
      throw new IllegalArgumentException();
    }

    this.elements = elements;
  }

  public void load(Matrix source)
  {
    arraycopy(source.elements, 0, this.elements, 0, 16);
  }

  // <editor-fold defaultstate="collapsed" desc="wrap & copy">
  public static Matrix wrap(float[] elements)
  {
    return new Matrix(elements);
  }

  public static Matrix copy(float[] elements)
  {
    return new Matrix(elements.clone());
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="equals, hashcode & clone">
  public boolean equals(Matrix other)
  {
    return this.elements == other.elements;
  }

  @Override
  public boolean equals(Object other)
  {
    if (other instanceof Matrix)
    {
      return ((Matrix) other).elements == this.elements;
    }
    return false;
  }

  @Override
  public int hashCode()
  {
    return elements.hashCode();
  }

  @Override
  public Matrix clone()
  {
    return new Matrix(elements.clone());
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="row & column access">
  public void getRow(int index, Vector row)
  {
    row.x(elements[index]);
    row.y(elements[index += 4]);
    row.z(elements[index += 4]);
    row.w(elements[index += 4]);
  }

  public void getColumn(int index, Vector column)
  {
    column.x(elements[index *= 4]);
    column.y(elements[++index]);
    column.z(elements[++index]);
    column.w(elements[++index]);
  }

  public void setRow(int index, Vector row)
  {
    elements[index] = row.x();
    elements[index += 4] = row.y();
    elements[index += 4] = row.z();
    elements[index += 4] = row.w();
  }

  public void setColumn(int index, Vector column)
  {
    elements[index *= 4] = column.x();
    elements[++index] = column.y();
    elements[++index] = column.z();
    elements[++index] = column.w();
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="transpose">
  public void transpose()
  {
    new MatrixTranspose(elements).transpose();
  }

  public void transpose(Matrix source)
  {
    new MatrixTranspose(elements).transpose(source);
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="invert">
  public boolean invert()
  {
    return invert(this);
  }

  public boolean invert(Matrix source)
  {
    return new MatrixInverse(elements).invert(source);
  }

  public boolean invert(Matrix source, float epsilon)
  {
    return new MatrixInverse(elements).invert(source, epsilon);
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="direction">
  public boolean direction()
  {
    elements[_1_4_] = 0;
    elements[_2_4_] = 0;
    elements[_3_4_] = 0;
    elements[_4_4_] = 1;

    transpose();
    return invert();
  }

  public boolean direction(Matrix source)
  {
    elements[_1_1_] = source.elements[_1_1_];
    elements[_1_2_] = source.elements[_1_2_];
    elements[_1_3_] = source.elements[_1_3_];
    elements[_1_4_] = 0;

    elements[_2_1_] = source.elements[_2_1_];
    elements[_2_2_] = source.elements[_2_2_];
    elements[_2_3_] = source.elements[_2_3_];
    elements[_2_4_] = 0;

    elements[_3_1_] = source.elements[_3_1_];
    elements[_3_2_] = source.elements[_3_2_];
    elements[_3_3_] = source.elements[_3_3_];
    elements[_3_4_] = 0;

    elements[_4_1_] = source.elements[_4_1_];
    elements[_4_2_] = source.elements[_4_2_];
    elements[_4_3_] = source.elements[_4_3_];
    elements[_4_4_] = 1;

    transpose();
    return invert();
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="multiply">
  public void multiply(Matrix right)
  {
    new MatrixMultiply(elements).multiply(right);
  }

  public void multiply(Matrix left, Matrix right)
  {
    new MatrixMultiply(elements).multiply(left, right);
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="perspective">
  public void perspective(float fovY, float aspect, float near, float far)
  {
    new Perspective(elements).perspective(fovY, aspect, near, far);
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="frustum">
  public void frustum(float left, float right, float bottom, float top, float near, float far)
  {
    new Frustum(elements).frustum(left, right, bottom, top, near, far);
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="ortho">
  public void ortho(float left, float right, float bottom, float top)
  {
    new Ortho(elements).ortho(left, right, bottom, top);
  }

  public void ortho(float left, float right, float bottom, float top, float near, float far)
  {
    new Ortho(elements).ortho(left, right, bottom, top, near, far);
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="lookAt">
  public void lookAt(float eyeX, float eyeY, float eyeZ,
    float targetX, float targetY, float targetZ,
    float upX, float upY, float upZ)
  {
    new LookAt(elements).lookAt(eyeX, eyeY, eyeZ, targetX, targetY, targetZ, upX, upY, upZ);
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="scale">
  public void scale(float s)
  {
    new Scaling(elements).scale(s);
  }

  public void scale(float x, float y, float z)
  {
    new Scaling(elements).scale(x, y, z);
  }

  public void loadScaling(float s)
  {
    new Scaling(elements).loadScaling(s);
  }

  public void loadScaling(float x, float y, float z)
  {
    new Scaling(elements).loadScaling(x, y, z);
  }

  public static Matrix scaling(float s)
  {
    return new Scaling(s);
  }

  public static Matrix scaling(float x, float y, float z)
  {
    return new Scaling(x, y, z);
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="translate">
  public void translate(float x, float y, float z)
  {
    new Translation(elements).translate(x, y, z);
  }

  public void loadTranslation(float x, float y, float z)
  {
    new Translation(elements).loadTranslation(x, y, z);
  }

  public static Matrix translation(float x, float y, float z)
  {
    return new Translation(x, y, z);
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="rotate">
  public void rotate(Vector axisAngle)
  {
    new Rotation(elements).rotate(axisAngle);
  }

  public void rotate(float angle, float x, float y, float z)
  {
    new Rotation(elements).rotate(angle, x, y, z);
  }

  public void rotateX(float angle)
  {
    new Rotation(elements).rotateX(angle);
  }

  public void rotateY(float angle)
  {
    new Rotation(elements).rotateY(angle);
  }

  public void rotateZ(float angle)
  {
    new Rotation(elements).rotateZ(angle);
  }

  public void loadRotation(Vector axisAngle)
  {
    new Rotation(elements).loadRotation(axisAngle);
  }

  public void loadRotation(float angle, float x, float y, float z)
  {
    new Rotation(elements).loadRotation(angle, x, y, z);
  }

  public void loadRotationX(float angle)
  {
    new Rotation(elements).loadRotationX(angle);
  }

  public void loadRotationY(float angle)
  {
    new Rotation(elements).loadRotationY(angle);
  }

  public void loadRotationZ(float angle)
  {
    new Rotation(elements).loadRotationZ(angle);
  }

  public static Matrix rotation(Vector axisAngle)
  {
    Matrix R = Matrix.zero();
    R.loadRotation(axisAngle);
    return R;
  }

  public static Matrix rotation(float angle, float x, float y, float z)
  {
    Matrix R = Matrix.zero();
    R.loadRotation(angle, x, y, z);
    return R;
  }

  public static Matrix rotationX(float angle)
  {
    Matrix R = Matrix.zero();
    R.loadRotationX(angle);
    return R;
  }

  public static Matrix rotationY(float angle)
  {
    Matrix R = Matrix.zero();
    R.loadRotationY(angle);
    return R;
  }

  public static Matrix rotationZ(float angle)
  {
    Matrix R = Matrix.zero();
    R.loadRotationZ(angle);
    return R;
  }

  // </editor-fold>

  // <editor-fold defaultstate="collapsed" desc="identity">
  public void loadIdentity()
  {
    elements[_1_1_] = elements[_2_2_] = elements[_3_3_] = elements[_4_4_] = 1;
    elements[_1_2_] = elements[_1_3_] = elements[_1_4_] =
      elements[_2_1_] = elements[_2_3_] = elements[_2_4_] =
      elements[_3_1_] = elements[_3_2_] = elements[_3_4_] =
      elements[_4_1_] = elements[_4_2_] = elements[_4_3_] = 0;
  }

  public static Matrix identity()
  {
    float[] elements =
    {
      1, 0, 0, 0,
      0, 1, 0, 0,
      0, 0, 1, 0,
      0, 0, 0, 1,
    };

    return new Matrix(elements);
  }

  // </editor-fold>
  
  // <editor-fold defaultstate="collapsed" desc="zero">
  public void loadZero()
  {
    elements[_1_1_] = elements[_2_1_] = elements[_3_1_] = elements[_4_1_] =
      elements[_1_2_] = elements[_2_2_] = elements[_3_2_] = elements[_4_2_] =
      elements[_1_3_] = elements[_2_3_] = elements[_3_3_] = elements[_4_3_] =
      elements[_1_4_] = elements[_2_4_] = elements[_3_4_] = elements[_4_4_] = 0;
  }

  public static Matrix zero()
  {
    return new Matrix(new float[16]);
  }
  // </editor-fold>
}
