#version 330

// Transform
uniform mat4 projection = mat4(1.0);
uniform mat4 view = mat4(1.0);
uniform mat4 model = mat4(1.0);

uniform Pair
{
    vec2 pairs[2];
};


uniform Block
{
    vec4 quad;
    vec2 pairs[2];
} single;

layout(location=0) in vec4 position;
layout(location=1) in mat4 matrix[2];


out vec4 foo;


void main()
{
   vec4 bar;
   {
        vec2 g_pair = min(pairs[0], pairs[1]);
        vec2 s_pair = min(single.pairs[0], single.pairs[1]);
        bar.xy = min (g_pair, s_pair);
   }
   {
        vec2 g_pair = max(pairs[0], pairs[1]);
        vec2 s_pair = max(single.pairs[0], single.pairs[1]);
        bar.zw = max(g_pair, s_pair);
   }
   vec4 baz = single.quad;

   foo = matrix[0] * matrix[1] * bar + baz;
   gl_Position = projection * view * model * position;
}
