#version 330

layout (location = 0) in vec2 in_pos;
layout (location = 1) in vec2 in_tex;

uniform mat4 projection;

out vec2 v_tex;

void main()
{
	gl_Position = projection * vec4(in_pos, 0.0, 1.0);
	v_tex = in_tex;
}