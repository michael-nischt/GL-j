#version 330

in vec4 foo;

layout(location=0) out vec4 fragColor;
layout(location=1) out vec2 fragData[2];

void main()
{
    fragColor = foo;
    fragData[0] = foo.xy;
    fragData[1] = foo.zw;
}
