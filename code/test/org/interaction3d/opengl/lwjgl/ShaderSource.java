package org.interaction3d.opengl.lwjgl;

import java.util.Scanner;

final class ShaderSource
{
  public static CharSequence resource(String path)
  {
    Scanner scanner = new Scanner(ShaderSource.class.getResourceAsStream(path));
    try
    {
      StringBuilder sb = new StringBuilder();
      while(scanner.hasNext())
      {
        sb.append(scanner.nextLine()).append('\n');
      }
      return sb;
    }
    finally
    {
      scanner.close();
    }
  }

  private ShaderSource() { /* static class */ }
}
